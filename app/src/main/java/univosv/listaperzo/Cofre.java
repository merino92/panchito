package univosv.listaperzo;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by administrador on 30/8/17.
 * clase para guardar archivos importantes de la aplicacion
 * mediante shareprefence
 */

public class Cofre {

    public static class Vars{
        public static final String USUARIO="USUARIO";
        public static final String CLAVE="CLAVE";
        public static final String NOTICIAS="NOTICIAS";
        public static final String DESCRIPCION="DESCRIPCION";
        public static final String URLNOTICIAS="URLNOTICIAS";
        public static SharedPreferences preferencias;
        public static SharedPreferences.Editor Editor;
        public final static String NOMBRE_SHARED_PREFERENCE="PerfilDatosTemporales";
        public static   Gson gson = new Gson();
        public static ArrayList<Object> listanotas = new ArrayList<Object>();
    }

    public static class Funciones{
        public static void GuardarUsuarioYClave(String usuario,String clave){
            Vars.Editor=Vars.preferencias.edit();
            Vars.Editor.putString(Vars.USUARIO,usuario);
            Vars.Editor.putString(Vars.CLAVE,clave);
            Vars.Editor.commit();

        }

        public static String InvocarUsuario(){
            String usuario = "";
            if(Vars.preferencias.contains(Vars.USUARIO)) {
                usuario = Vars.preferencias.getString(Vars.USUARIO, "");
            }
            return usuario;
        }

        public static void Iniciar(SharedPreferences sharedPreferences){
            Vars.preferencias=sharedPreferences;
        }

        public static String InvocarClave(){
            String clave="";
            if(Vars.preferencias.contains(Vars.CLAVE)) {
                clave = Vars.preferencias.getString(Vars.CLAVE, "");
            }
            return clave;
        }

       /* public static void  GuardarNotas(ArrayList<String>titulo,ArrayList<String>descripcion){

          Vars.listanotas.add(titulo);
            Vars.listanotas.add(descripcion);

            String jsonList = Vars.gson.toJson(Vars.listanotas);
            Vars.Editor.putString(Vars.NOTICIAS,jsonList);
            Vars.Editor.commit();
        } //funcion que convierte las noticias para guardarlas en el share preferences

        public static ArrayList<Object> Recuperarnotas(){
            // Recuperamos el string guardado
            String notas = Vars.preferencias.getString(Vars.NOTICIAS,"");
        // Esta línea sirve para extraer el tipo correspondiente al listado, necesario
            // para que Gson sepa a qué tiene que convertir
            Type type = new TypeToken<List<Object>>(){}.getType();
       // Convertimos el string en el listado
            ArrayList<Object> objects = Vars.gson.fromJson(notas, type);
            return objects;


        }*/
    }
}
