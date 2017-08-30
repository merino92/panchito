package univosv.listaperzo;

import android.content.SharedPreferences;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by administrador on 30/8/17.
 * clase para guardar archivos importantes de la aplicacion
 * mediante shareprefence
 */

public class Cofre {

    public static class Vars{
        public static final String USUARIO="USUARIO";
        public static final String TITULO="TITULO";
        public static final String DESCRIPCION="DESCRIPCION";
        public static final String URLNOTICIAS="URLNOTICIAS";
        public static SharedPreferences preferencias;
        public static SharedPreferences.Editor Editor;
    }

    public static class Funciones{
        public static void GuardarUsuario(String usuario){
            Vars.Editor=Vars.preferencias.edit();
            
            Vars.Editor.putString(Vars.USUARIO,usuario);
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
    }
}
