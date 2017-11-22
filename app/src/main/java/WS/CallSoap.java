package WS;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by administrador on 22/11/17.
 */
//import org.ksoap2.*;
public class CallSoap {
    public final String SOAP_ACTION = "http://app.univo.edu.sv/obtener_json_demo_alumno";

    public final String OPERATION_NAME = "obtener_json_demo_alumno";



    public final String WSDL_TARGET_NAMESPACE = "http://app.univo.edu.sv/";

    public final String SOAP_ADDRESS = "http://app.univo.edu.sv/WS_APP.asmx";
    public CallSoap()
    {
    }
    /**/
    public String Call(int a, int b)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("a");
        pi.setValue(a);
        pi.setType(Integer.class);
        request.addProperty(pi);
        pi=new PropertyInfo();
        pi.setName("b");
        pi.setValue(b);
        pi.setType(Integer.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

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
}
