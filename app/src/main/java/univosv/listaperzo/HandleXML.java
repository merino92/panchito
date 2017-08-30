package univosv.listaperzo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by RafaelZelaya on 11/7/2017.
 * Ejemplo de uso en el main.java
 handleXML = new HandleXML(RssUrl);
 handleXML.fetchXML();
 while(handleXML.parsingComplete);
 Toast.makeText(this,handleXML.title,Toast.LENGTH_LONG).show();
 ((TextView)findViewById(R.id.texto)).setText(handleXML.title);
 */

public class HandleXML {
    public ArrayList<String> Titulo;
    public ArrayList<String> Descripcion;
    public ArrayList<String> Enlace;
    public ArrayList<String> ItemCompleto;
    private String titulo="", descripcion="",enlace = "";
    public String urlString = null;
    public XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;
    public  final HandleXML context=this;

    public HandleXML(String url){
        ItemCompleto = new ArrayList<>();
        Enlace = new ArrayList<>();
        Descripcion = new ArrayList<>();
        Titulo = new ArrayList<>();

        this.urlString = url;
    }
    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        boolean estaEnItem=false;//
        String text=null;
        try {
            event = myParser.getEventType();
            int iteraciones = 0;
            while (event != XmlPullParser.END_DOCUMENT) {
                iteraciones++;
                String name=myParser.getName();
                switch (event){
                    case XmlPullParser.START_TAG:
                        if(name.equals("item")){
                            estaEnItem = true;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;
                    case XmlPullParser.END_TAG:

                        if(estaEnItem == true){
                            if(name.equals("title")){
                                Titulo.add(text);
                                titulo = text;
                            }else if(name.equals("description")){
                                Descripcion.add(text);
                                descripcion = text;
                            }else if(name.equals("link")){
                                Enlace.add(text);
                                enlace = text;
                            }else if(name.equals("item")){
                                estaEnItem = false;
                                ItemCompleto.add(titulo + ":" + descripcion );
                            }
                        }
                        break;
                }
                event = myParser.next();
            }
            parsingComplete = false;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void fetchXML(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {

                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);

                    // Starts the query
                    conn.connect();
                    InputStream stream = conn.getInputStream();

                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myparser = xmlFactoryObject.newPullParser();

                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    myparser.setInput(stream, null);

                    parseXMLAndStoreIt(myparser);
                    stream.close();
                }
                catch (Exception e) {
                }
            }
        });
        thread.start();
    }

}