package univosv.listaperzo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ListView ListaNoticias;
    //private HandleXML handleXML;
    private int posicion;
    ProgressDialog progressDialog;
    verificacionDeInternet Internet=new verificacionDeInternet(this);
    public SharedPreferences sharedPreferences;

    public MainActivity() throws XmlPullParserException {
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListaNoticias = (ListView) findViewById(R.id.lista);
        sharedPreferences=getApplicationContext().
                getSharedPreferences(Cofre.Vars.NOMBRE_SHARED_PREFERENCE,MODE_PRIVATE);
        Cofre.Funciones.Iniciar(sharedPreferences,this);//inicia el sharedpreference

    }

    //de donde debemos cargar las noticias
    //base de datos BDD
    //internet I
    private void DeDondeCargarNoticias(){
        String fechaActual=Cofre.Funciones.ObtenerFechaActual();
        String fechaUltimaCarga=Cofre.Funciones.InvocarFechaNoticias();
        if(fechaActual.equals(fechaUltimaCarga)){
            Log.i("Fechas iguales","Cargar de la bdd");
            //cargar de la BDD
            if(!Cofre.Funciones.VerificarExistenciadbs(this)){
                Log.i("No existe la bdd","Cargar de internet");
                if(VerificarInternet()){
                    String url=ObtenerUrlUnivoNews();
                    new CargarListaNoticias().execute(url);
                }
                else{
                    Log.i("No tiene internet...","jajaja");
                    Cofre.Funciones.Arreglovacio(this,ListaNoticias);
                }
            }
            else{//si la base no esta vacia
                Log.i("Carga de la bdd","Si existe la bdd");
                CargarNoticiasBDS();
            }
        }
        else{//Carga de internet
            Log.i("Carga de internet","Fechas diferentes");
            if(VerificarInternet()){
                Log.i("Cargando de internet...","jajaja");
                String url=ObtenerUrlUnivoNews();
                new CargarListaNoticias().execute(url);
            }
            else{
                Log.i("No tiene internet","Carga arreglo vacio");
                Cofre.Funciones.Arreglovacio(this,ListaNoticias);
            }
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
       DeDondeCargarNoticias();
    }//verifica internet y compara fecha del sistemas con las noticias
    /*codigo asincrono para cargar la lista de noticias*/

    private class CargarListaNoticias extends AsyncTask<String, Void, HandleXML> {
        @Override
        protected void onPreExecute(){
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setIcon(R.mipmap.ic_launcher);
            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(true);
            progressDialog.show();

        }

        @Override
        protected HandleXML doInBackground(String... url) {
            return ObtenerRssNews(url[0]);
        }

        @Override
        protected void onPostExecute(HandleXML noticias){
            CargarNoticiasUnivo(noticias);
                String fecha=Cofre.Funciones.ObtenerFechaActual();
            Cofre.Funciones.GuardarFechaNoticias(fecha);

            progressDialog.dismiss();
        }
    }

    /*Funcion para cargar la información del RSS de
    noticias*/
    private HandleXML ObtenerRssNews(String urlUnivo){
        HandleXML handleXML = new HandleXML(urlUnivo);
        handleXML.fetchXML();
        while(handleXML.parsingComplete);
        //ArrayList<items> cosas = new ArrayList<items>();
        // cosas.add(handleXML.ItemCompleto);
        return handleXML;
    }

    private void CargarNoticiasUnivo(final HandleXML handleXML){
       /* ArrayAdapter<String> adaptador =
                new ArrayAdapter(
                        this,
                        android.R.layout.simple_list_item_1,
                        ObtenerRssNews(urlUnivo));
        ListaNoticias.setAdapter(adaptador);*/
        //ObtenerRssNews(urlUnivo);
        if(handleXML.Titulo==null)
        {
            Toast.makeText(this,"vacio",Toast.LENGTH_SHORT);
        }else {
            AdapterItem adapter = new AdapterItem(this, handleXML.Titulo, handleXML.Descripcion);

            Cofre.Funciones.GuardarNoticias(
                    handleXML.Titulo,
                    handleXML.Descripcion,
                    handleXML.Enlace,
                    getApplicationContext());

            overridePendingTransition(R.anim.zoom_forward_in,R.anim.zoom_forward_out);
          //  Cofre.Funciones.GuardarNotas(handleXML.Titulo,handleXML.Descripcion);
            ListaNoticias.setAdapter(adapter);

            ListaNoticias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final int pos = position;
                    MostrarNoticias(pos,handleXML);//funcion que muestra la noticia

                }
            });
        }
    }

    /*Obtiene de la conexion con el webservice
    * la dirección del feed rss de la univo news*/
    private String ObtenerUrlUnivoNews(){
        return "http://univonews.com/feed/";
    }

    private void MostrarNoticias(int posicion,HandleXML handleXML) {

       /* se crea la barra de cargando*/
        int Posicion=posicion;//obtengo la posisicion del item
        ArrayList<String> enlaces=handleXML.Enlace;//contiene los enlaces
        _MostrarNoticias(posicion,enlaces);
    }//MostrarNoticias

    private void MostrarNoticias(int posicion,ArrayList<String>url) {

       /* se crea la barra de cargando*/
        int Posicion=posicion;//obtengo la posisicion del item
        //ArrayList<String> enlaces=url;//contiene los enlaces
        _MostrarNoticias(posicion,url);

    }//MostrarNoticiasBDS

    private void _MostrarNoticias(int posicion, ArrayList<String> enlaces){
        String url1=enlaces.get(posicion);//obtiene el enlace del item seleccionado
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        View enview1=getLayoutInflater().inflate(R.layout.dialogo_alerta,null);
        WebView wv = new WebView(this);

        wv.loadUrl(url1);
        wv.setWebViewClient(new WebViewClient() {

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setIcon(R.mipmap.ic_launcher);
                progressDialog.setMessage("Cargando...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                super.onPageStarted(view, url, favicon);
            } //muestra el dialogo mientras se carga la pagina


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub


                super.onPageFinished(view, url);
                progressDialog.dismiss();

            }//funcion que desaparece el dialogo cuando la pagina ha cargado completamente

        });
        alert.setView(enview1);
        alert.setView(wv);
        AlertDialog alerta=alert.create();
        alerta.show();//muestra la noticia
    }

    public void lanzarHorario(View view) {
        Intent i = new Intent(this, Horarios.class);
        startActivity(i);
        overridePendingTransition(R.anim.zoom_forward_in,R.anim.zoom_forward_out);
    }
    public void lanzarPagos(View view) {
        Intent i = new Intent(this, Pagos.class);
        startActivity(i);
    }
    public void lanzarNotas(View view) {
        Intent i = new Intent(this, Notas.class);
        startActivity(i);
    }
    public void lanzarPerfil(View view) {
        Intent i = new Intent(this, Perfil.class);
        startActivity(i);
    }
    private boolean VerificarInternet(){
        boolean inter,wifi;
        inter=Internet.conectadoRedMovil();
        wifi=Internet.conectadoWifi();
        return  (inter||wifi);
    }
    public void actualizar(View view){
        ListaNoticias = (ListView) findViewById(R.id.lista);
        if (VerificarInternet())
        {

            String url=ObtenerUrlUnivoNews();
            new CargarListaNoticias().execute(url);
        }
        else {



        }

    }

    public void CargarNoticiasBDS(){
        ArrayList<ArrayList> lista=Cofre.Funciones.ObtenerNoticias(this);

        ArrayList<String>titulo=lista.get(0);
        ArrayList<String>descripcion=lista.get(1);
       final ArrayList<String>url=lista.get(2);
        AdapterItem adapter =
                new AdapterItem(this,titulo , descripcion);
        ListaNoticias.setAdapter(adapter);
        ListaNoticias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                if(VerificarInternet()) {
                    MostrarNoticias(pos, url);//funcion que muestra la noticia
                }
                else {
                    ArrayList<String>vacio=new ArrayList<String>();
                    MostrarNoticias(pos, vacio);
                }//verifica el internet y si hay muestra las noticia completa

            }
        });//carga las noticias desde la base de datos;

    }
}
