package univosv.listaperzo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

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
        ahoraMismo.add(Calendar.MINUTE,1);
        Date fecha=new Date(ahoraMismo.getTimeInMillis());

        Alarma.Crear(this,"materia","Aula",fecha,1);

    }
    public void entrar(View view){
        String Usuario=Cofre.Funciones.InvocarUsuario();
        String clave=Cofre.Funciones.InvocarClave();
        VerificarClave(Usuario,clave);
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
}
