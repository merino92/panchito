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

public class login extends AppCompatActivity {
  EditText contra,usuario;
    CheckBox cheque;
    public String  usuario1;
    public final static String NOMBRE_SHARED_PREFERENCE="PerfilDatosTemporales";
    public  SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario=(EditText)findViewById(R.id.usuario);
        contra=(EditText)findViewById(R.id.clave);
        cheque=(CheckBox)findViewById(R.id.checquesito);
         sharedPreferences=getApplicationContext().
                getSharedPreferences(NOMBRE_SHARED_PREFERENCE,MODE_PRIVATE);
        Cofre.Funciones.Iniciar(sharedPreferences);
        String Usuario=Cofre.Funciones.InvocarUsuario();
        usuario.setText(Usuario);

    }
    public void entrar(View view){
        usuario=(EditText)findViewById(R.id.usuario);
        contra=(EditText)findViewById(R.id.clave);
        String contra1;
        usuario1=usuario.getText().toString();
        contra1=contra.getText().toString();


        if (usuario1.equals("panchito")&& contra1.equals("panchito"))
        {
                guardar(usuario1);
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"ERROR USUARIO O CLAVE INCORRECTA",Toast.LENGTH_LONG).show();
        }
    }

   private void guardar(String usuario){
       String clave=usuario;
       if (cheque.isChecked()){
           Cofre.Funciones.GuardarUsuario(clave);


       }
   }
}
