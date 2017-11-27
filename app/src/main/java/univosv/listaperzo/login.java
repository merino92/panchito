package univosv.listaperzo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import WS.CallSoap;
import WS.WebService;
import univosv.listaperzo.Notificaciones.Alarma;


public class login extends AppCompatActivity {
  EditText contra,usuario;
    CheckBox cheque;
    public String  usuario1;

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
//

    }
    public void PruebaWebService(){
        /*CallSoap callSoap = new CallSoap();
        String respuesta = callSoap.Call("admin_ws","@dminWS2017","U20120453","univo");
        Toast.makeText(this,respuesta,Toast.LENGTH_LONG);*/
        String [] arreglo={"admin_ws","@dminWS2017","U20120453","univo"};
        new CallSoap.llamadaWs().execute(arreglo);
        Log.i("RespuestaWS",Cofre.Vars.RespuestaWebService);
        ((TextView)findViewById(R.id.link_signup)).setText(Cofre.Vars.RespuestaWebService);
    }
    public void entrar(View view){
        String Usuario=Cofre.Funciones.InvocarUsuario();
        String clave=Cofre.Funciones.InvocarClave();
        //VerificarClave(Usuario,clave);
        PruebaWebService();
    }

   private void guardar(String usuario,String clave){

       if (cheque.isChecked()){
           Cofre.Funciones.GuardarUsuarioYClave(usuario,clave);

       }
   }

   private void VerificarClave(String codigo,String clave){
         usuario=(EditText)findViewById(R.id.usuario);
            contra=(EditText)findViewById(R.id.clave);
            String contra1;
            usuario1=usuario.getText().toString();
            contra1=contra.getText().toString();
        if(!codigo.equals("")&& !clave.equals("")){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
           }
           else
                  {
                if (usuario1.equals("panchito") && contra1.equals("panchito")) {
                guardar(usuario1, contra1);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                }
            else {
                Toast.makeText(this, "ERROR USUARIO O CLAVE INCORRECTA", Toast.LENGTH_LONG).show();
            }
        }
   }

   public void desarrolladores(View view){
       AlertDialog.Builder alert = new AlertDialog.Builder(login.this);
       View enview1=getLayoutInflater().inflate(R.layout.activity_alertadesarrollaroderes,null);
       AlertDialog alerta=alert.create();
       alerta.show();//muestra la noticia
   }
}
