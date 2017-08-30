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

    public static class Nombres{
        public static final String USUARIO="USUARIO";
        public static final String TITULO="TITULO";
        public static final String DESCRIPCION="DESCRIPCION";
        public static final String URLNOTICIAS="URLNOTICIAS";
        public static SharedPreferences preferencias;
    }

    public static class Funciones{
        public static void GuardarUsuario(String usuario){

            SharedPreferences.Editor editor=Nombres.preferencias.edit();
            editor.putString(Nombres.USUARIO,usuario);
            editor.commit();

        }

        public static String InvocarUsuario(){
            String usuario=Nombres.preferencias.getString(Nombres.USUARIO,"");
            return usuario;
        }

        public static void Iniciar(SharedPreferences sharedPreferences){
            sharedPreferences=Nombres.preferencias;

        }
    }
}
