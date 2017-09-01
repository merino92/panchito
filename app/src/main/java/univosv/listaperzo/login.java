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
    EditText Contra,Usuario;
    CheckBox RecordarUsuario;
    public String  Usuario1;
    public final static String NOMBRE_SHARED_PREFERENCE="PerfilDatosTemporales";
    public  SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Usuario=(EditText)findViewById(R.id.usuario);
        Contra=(EditText)findViewById(R.id.clave);
        RecordarUsuario=(CheckBox)findViewById(R.id.recuerdaUsuario);
         sharedPreferences=getApplicationContext().
                getSharedPreferences(NOMBRE_SHARED_PREFERENCE,MODE_PRIVATE);
        Cofre.Funciones.Iniciar(sharedPreferences);
        String usuarioGuardado = Cofre.Funciones.InvocarUsuario();
        String claveGuardada = Cofre.Funciones.InvocarClave();
        Contra.setText(claveGuardada);
        Usuario.setText(usuarioGuardado);
    }

    public void entrar(View view){
        Usuario=(EditText)findViewById(R.id.usuario);
        Contra=(EditText)findViewById(R.id.clave);
        String contra1;
        Usuario1=Usuario.getText().toString();
        contra1=Contra.getText().toString();

        if (Usuario1.equals("panchito")&& contra1.equals("panchito"))
        {
            Guardar(Usuario1,contra1);
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"ERROR USUARIO O CLAVE INCORRECTA",Toast.LENGTH_LONG).show();
        }
    }

   private void Guardar(String usuario,String clave){
       if (RecordarUsuario.isChecked()){
           Cofre.Funciones.GuardarUsuarioYClave(usuario,clave);
       }
   }
}
