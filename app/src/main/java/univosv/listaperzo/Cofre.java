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
import java.util.Calendar;
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
        public static final String FECHANOTICIAS="FECHANOTICIAS";
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

       public static void GuardarFechaNoticias(String fecha){
           Vars.Editor=Vars.preferencias.edit();
           Vars.Editor.putString(Vars.FECHANOTICIAS,fecha);
           Vars.Editor.commit();
       }
       public static String InvocarFechaNoticias(){
           String fecha="";
           if(Vars.preferencias.contains(Vars.FECHANOTICIAS)){
               fecha=Vars.preferencias.getString(Vars.FECHANOTICIAS,"");
           }
           return fecha;
       }

       public static String ObtenerFechaActual(){
           Calendar fecha = Calendar.getInstance();
           String año =String.valueOf(fecha.get(Calendar.YEAR));
           String mes = String.valueOf(fecha.get(Calendar.MONTH));
           String dia = String.valueOf(fecha.get(Calendar.DAY_OF_MONTH));
           String FechaActual=dia+"-"+mes+"-"+año;
           return FechaActual;
       }
    }
}
