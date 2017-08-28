package univosv.listaperzo;

import android.content.Intent;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void entrar(View view){
        usuario=(EditText)findViewById(R.id.usuario);
        contra=(EditText)findViewById(R.id.clave);
        String usuario1,contra1;
        usuario1=usuario.getText().toString();
        contra1=contra.getText().toString();

        if (usuario1.equals("panchito")&& contra1.equals("panchito"))
        {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"ERROR USUARIO O CONTRA INCORRECTA",Toast.LENGTH_LONG).show();
        }
    }
}
