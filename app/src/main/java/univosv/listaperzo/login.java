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
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
  EditText contra,usuario;
    public String  usuario1;
    public final static String NOMBRE_SHARED_PREFERENCE="PerfilDatosTemporales";
    public  SharedPreferences sharedPreferences=getApplicationContext().
                                                    getSharedPreferences(NOMBRE_SHARED_PREFERENCE,MODE_PRIVATE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
    public void entrar(View view){
        usuario=(EditText)findViewById(R.id.usuario);
        contra=(EditText)findViewById(R.id.clave);
        String contra1;
        usuario1=usuario.getText().toString();
        contra1=contra.getText().toString();

        if (usuario1.equals("panchito")&& contra1.equals("panchito"))
        {
                GuardarUsuario();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"ERROR USUARIO O CONTRA INCORRECTA",Toast.LENGTH_LONG).show();
        }
    }
    private void GuardarUsuario(){

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("USUARIO",usuario1);
        editor.commit();
    }

}
