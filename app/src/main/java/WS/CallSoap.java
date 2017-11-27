package WS;

import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import univosv.listaperzo.Cofre;

/**
 * Created by administrador on 22/11/17.
 */
//import org.ksoap2.*;
    //https://es.androids.help/q21713
    //https://stackoverflow.com/questions/14955227/ksoap2-header-in-out
public class CallSoap {
    public static final String SOAP_ACTION = "http://ws_app.univo.edu.sv/obtener_json_demo_alumno";

    public  static final String OPERATION_NAME = "obtener_json_demo_alumno";



    public  static final String WSDL_TARGET_NAMESPACE = "http://app.univo.edu.sv/";

    public static final String SOAP_ADDRESS = "http://app.univo.edu.sv/WS_APP.asmx";
    public CallSoap()
    {
    }
    /**/
    public  static String Call (String ws_user,String ws_pass,String carnet,String contrasenia)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        //request.addProperty("ws_user",ws_user);
        //request.addProperty("ws_pass",ws_pass);
        PropertyInfo pi;
      /*  pi.setName("ws_user");
        pi.setValue(ws_user);
        pi.setType(String.class);
        request.addProperty(pi);

        pi=new PropertyInfo();
        pi.setName("ws_pass");
        pi.setValue(ws_pass);
        pi.setType(String.class);
        request.addProperty(pi);*/

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
        envelope.headerOut = new Element[1];
        envelope.headerOut[0] = ConstruirCabecera(ws_user,ws_pass);
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
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

    /**/
    }
    private static Element ConstruirCabecera(String user,String password){
        Element h = new Element().createElement(WSDL_TARGET_NAMESPACE, "AuthHeader");
        Element username = new Element().createElement(WSDL_TARGET_NAMESPACE, "ws_user");
        username.addChild(Node.TEXT, user);
        h.addChild(Node.ELEMENT, username);
        Element pass = new Element().createElement(WSDL_TARGET_NAMESPACE, "ws_pass");
        pass.addChild(Node.TEXT, password);
        h.addChild(Node.ELEMENT, pass);

        return h;
    }
    public static class llamadaWs extends AsyncTask<String,Void,Void>{

        protected Void doInBackground(String... arreglo) {
            String ws_user=arreglo[0];
            String ws_pass=arreglo[1];
            String carnet=arreglo[2];
            String clave=arreglo[3];
            Cofre.Vars.RespuestaWebService = Call(ws_user,ws_pass,carnet,clave);


            return null;
        }
    }
}
