package WS;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import java.io.Console;
import java.util.ArrayList;

import univosv.listaperzo.Cofre;

import static android.content.ContentValues.TAG;

/**
 * Created by administrador on 22/11/17.
 */
public class CallSoap {
    public static final String SOAP_ACTION = "http://ws_app.univo.edu.sv/iniciar_sesion_app";
    public  static final String FUNCION = "iniciar_sesion_app";
    public  static final String NAMESPACE = "http://ws_app.univo.edu.sv/";
    public static final String URL = "http://app.univo.edu.sv/WS_APP.asmx";
    private static final String WS_USER="admin_ws";
    private static final String WS_PASS="@dminWS2017";
    public CallSoap()
    {
    }
    public static String Call(String carnet,String contrasenia)
    {
        SoapObject request = new SoapObject(NAMESPACE,FUNCION);
        PropertyInfo pi;

        pi=new PropertyInfo();
        pi.setName("carnet");
        pi.setValue(carnet);
        pi.setType(String.class);
        request.addProperty(pi);

        pi=new PropertyInfo();
        pi.setName("contrasenia");
        pi.setValue(contrasenia);
        pi.setType(String.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);
        envelope.bodyOut = request;
        envelope.headerOut = new Element[1];
        envelope.headerOut[0] = ConstruirCabecera(WS_USER,WS_PASS);

        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response=null;

        try
        {
            httpTransport.call(SOAP_ACTION, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();

    }
    private static Element ConstruirCabecera(String user,String password){

        Element h = new Element().createElement(NAMESPACE,"Cabecera");
        //Element h = new Element().createElement(WSDL_TARGET_NAMESPACE, "AuthHeader");
        Element username = new Element().createElement(NAMESPACE, "ws_user");
        username.addChild(Node.TEXT, user);
        h.addChild(Node.ELEMENT, username);
        Element pass = new Element().createElement(NAMESPACE, "ws_pass");
        pass.addChild(Node.TEXT, password);
        h.addChild(Node.ELEMENT, pass);
        Log.i(TAG, "ConstruirCabecera:"+h);
        return h;
    }
    public static class LoginWS extends AsyncTask<String,Void,Void>{

        protected Void doInBackground(String... arreglo) {
            String carnet=arreglo[0];
            String clave=arreglo[1];
            Cofre.Vars.RespuestaWebService = Call(carnet,clave);
            return null;
        }
    }
}
