package univosv.listaperzo.Modelos;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by RafaelZelaya on 29/11/17.
 */

public class Estudiante {
    public Estudiante(){
        Carnet = "";
        Nombre = "";
        Carrera = "";
    }
    public Estudiante(String carnet,String nombre,String carrera){
        Carnet = carnet;
        Nombre = nombre;
        Carrera = carrera;
    }
    public Estudiante(JSONObject objetoJson) throws JSONException {
        Carnet = objetoJson.getString("carnet");
        Nombre = objetoJson.getString("Nombre");
        Carrera = objetoJson.getString("Carrera");
    }
    public String Carnet;
    public String Nombre;
    public String Carrera;

}
