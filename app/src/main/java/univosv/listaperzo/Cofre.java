package univosv.listaperzo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import univosv.listaperzo.Basededatos.BaseSQL;

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
        public static BaseSQL Base;
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

        public static void Iniciar(SharedPreferences sharedPreferences,Context contexto){
            Vars.preferencias=sharedPreferences;
            Vars.Base = new BaseSQL(contexto,"NOTICIAS",null,1);
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

        public static void GuardarNoticias(
               ArrayList<String>t,
               ArrayList<String>d,
               ArrayList<String>u
               ){

           SQLiteDatabase db = Vars.Base.getWritableDatabase();

           if (db!=null){
               ContentValues contenedorDeValoresSql;
               //funciona... pero hay que utilizar content values...
               if(BddPoseeAlgunRegistro()){

                   db.execSQL(BaseSQL.EliminarDatosTabla);
               }

               for (int i=0;i<t.size();i++){
                   contenedorDeValoresSql = new ContentValues();
                   //String nombre = "Usuario" + i;
                   //Insertamos los datos en la tabla Usuarios
                   contenedorDeValoresSql.put("titulo",t.get(i));
                   contenedorDeValoresSql.put("descripcion",d.get(i));
                   contenedorDeValoresSql.put("url",u.get(i));
                   db.insert(BaseSQL.NombreTabla,null,contenedorDeValoresSql);
                   //String sql = "INSERT INTO NOTICIAS (titulo, descripcion,url) " +
                   //        "VALUES ('" + t.get(i) + "', '" +d.get(i) +"','"+u.get(i)+"')";
                    Log.i("Guardando","noticias"+i);
                   //db.execSQL(sql);
               }
               db.close();
           }
       }

       public static boolean BddPoseeAlgunRegistro(){
           SQLiteDatabase db = Vars.Base.getWritableDatabase();
           long numRows = DatabaseUtils.queryNumEntries(db, BaseSQL.NombreTabla);
           return (numRows>0);
       }
       public static ArrayList<ArrayList> ObtenerNoticias(Context c){

            SQLiteDatabase db=Vars.Base.getReadableDatabase();
            Cursor titulo = db.rawQuery(" SELECT titulo FROM NOTICIAS  ", null);
            Cursor descripcion = db.rawQuery(" SELECT descripcion FROM NOTICIAS  ", null);
            Cursor url = db.rawQuery(" SELECT url FROM NOTICIAS  ", null);
            ArrayList<String> TITULO = new ArrayList<String>();
            ArrayList<String> DESCRIPCION = new ArrayList<String>();
            ArrayList<String> URL = new ArrayList<String>();
            //Nos aseguramos de que existe al menos un registro
            if (titulo.moveToFirst() && descripcion.moveToFirst() && url.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    String ti = titulo.getString(0);
                    String de = descripcion.getString(0);
                    String ur = url.getString(0);
                    TITULO.add(ti);
                    DESCRIPCION.add(de);
                    URL.add(ur);
                } while (titulo.moveToNext() && descripcion.moveToNext() && url.moveToNext());
            }
            ArrayList<ArrayList> arr = new ArrayList<ArrayList>();
            arr.add(TITULO);
            arr.add(DESCRIPCION);
            arr.add(URL);

            return arr;

        }
       public static boolean  VerificarExistenciadbs(Context c){
                //BaseSQL base =new BaseSQL(c,"NOTICIAS",null,1);
                SQLiteDatabase db = Vars.Base.getWritableDatabase();
                return (db!=null);
            }
       public static void Arreglovacio(Activity activity, ListView listView){

            ArrayList<String> tituloSinInter = new ArrayList<String>();
            ArrayList<String> descripcionSinInter = new ArrayList<String>();

            for (int i = 0; i < 10; i++) {
                tituloSinInter.add("Sin conexion a internet");
                descripcionSinInter.add("Verifica tu conexion");
            }

            AdapterItem adapter =
                    new AdapterItem(activity, tituloSinInter, descripcionSinInter);
            listView.setAdapter(adapter);

        }
    }//cierre de clase funciones
}
