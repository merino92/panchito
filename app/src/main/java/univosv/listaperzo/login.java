package univosv.listaperzo;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import WS.CallSoap;
import WS.RespuestaAsync;
import WS.WebService;
import univosv.listaperzo.Modelos.Estudiante;
import univosv.listaperzo.Notificaciones.AcercaDe;
import univosv.listaperzo.Notificaciones.Alarma;


public class login extends AppCompatActivity
implements RespuestaAsync{
    EditText contra,usuario;
    CheckBox cheque;
    public String  usuario1;
    ProgressDialog progreso;

    public  SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        startService(new Intent(this, ServiciosSegundoPlano.class));//inicio el servicio

        usuario=(EditText)findViewById(R.id.usuario);
        contra=(EditText)findViewById(R.id.clave);
        cheque=(CheckBox)findViewById(R.id.checquesito);
         sharedPreferences=getApplicationContext().
                getSharedPreferences(Cofre.Vars.NOMBRE_SHARED_PREFERENCE,MODE_PRIVATE);
        Cofre.Funciones.Iniciar(sharedPreferences,this);
        String Usuario=Cofre.Funciones.InvocarUsuario();
        String clave=Cofre.Funciones.InvocarClave();
        contra.setText(clave);
        usuario.setText(Usuario);

        Calendar ahoraMismo = Calendar.getInstance();
        ahoraMismo.add(Calendar.MILLISECOND,1000);
        Date fecha=new Date(ahoraMismo.getTimeInMillis());

        //Alarma.Crear(this,"materia","Aula",fecha,1);

    }
    public void PruebaWebService() throws JSONException {
        /*CallSoap callSoap = new CallSoap();
        String respuesta = callSoap.Call("admin_ws","@dminWS2017","U20120453","univo");
        Toast.makeText(this,respuesta,Toast.LENGTH_LONG);*/
        progreso = new ProgressDialog(login.this);
        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progreso.setMessage("Procesando...");
        progreso.setCancelable(true);
        progreso.setMax(100);
         progreso.show();
        CallSoap.LoginWS sincrono = new CallSoap.LoginWS();
        String user=usuario.getText().toString();
        String pass=contra.getText().toString();
        String [] arreglo={user,pass};
        sincrono.delegate = this;
        sincrono.execute(arreglo);
        progreso.onStart();
    }

    public List<Estudiante> ObtenerEstudiantesDeJson(String json) throws JSONException {
        List<Estudiante> listaEstudiante = new ArrayList<>();
        JSONArray objects = new JSONArray(json);
        //JSONArray jsonArray = object.optJSONArray()
        for(int i =0;i<objects.length();i++){
            listaEstudiante.add(new Estudiante(objects.getJSONObject(i)));
        }
        return listaEstudiante;
    }

    public void entrar(View view){
        String Usuario=Cofre.Funciones.InvocarUsuario();
        String clave=Cofre.Funciones.InvocarClave();
        //VerificarClave(Usuario,clave);
        try{
            PruebaWebService();
        }
        catch (Exception e){ Log.e("Error Json",e.getMessage());}

    }

   private void guardar(String usuario,String clave){

       if (cheque.isChecked()){
           Cofre.Funciones.GuardarUsuarioYClave(usuario,clave);

       }
   }

   public void VerificarClave(Boolean respuesta){
       if(respuesta==true){
            Cofre.Funciones.GuardarUsuarioYClave(usuario.getText().toString(),contra.getText().toString());
           Intent intent=new Intent(this,MainActivity.class);
           startActivity(intent);
           progreso.cancel();
       }
       else{
           progreso.cancel();
           Toast.makeText(this, "usuario o clave incorrecta",Toast.LENGTH_LONG).show();
       }

   }

   public void desarrolladores(View view){
       FragmentManager fragmentManager = getSupportFragmentManager();
       AcercaDe dialogo = new AcercaDe();
      dialogo.show(fragmentManager,"tagAlerta");
   }

    @Override
    public void ProcesoFinalizado(String salida) {
       // Toast.makeText(this,"Instancia..wee.."+salida,Toast.LENGTH_LONG).show();
        Boolean respuesta;
       if (salida.equals("1")){
           respuesta=true;
       } else{
           respuesta=false;
       }
        VerificarClave(respuesta);
    }
}
