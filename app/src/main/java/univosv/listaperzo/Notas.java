package univosv.listaperzo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import WS.CallSoap;
import WS.RespuestaAsync;


/**
 * Created by root on 07-30-17.
 */

public class Notas extends Activity implements RespuestaAsync{
    public SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        sharedPreferences=getApplicationContext().
                getSharedPreferences(Cofre.Vars.NOMBRE_SHARED_PREFERENCE,MODE_PRIVATE);
        Cofre.Funciones.Iniciar(sharedPreferences,this);
        MostrarNotas();
    }

    private void MostrarNotas(){
        CallSoap.NotasWS sincrono = new CallSoap.NotasWS();
        String carnet=Cofre.Funciones.InvocarUsuario();
        String clave=Cofre.Funciones.InvocarClave();
        String [] arreglo={carnet,clave};
        sincrono.delegate = this;
        sincrono.execute(arreglo);
    }
    public void lanzarHorario(View view) {
        Intent i = new Intent(this, Horarios.class );
        startActivity(i);
    }
    public void lanzarNoticias(View view) {
        Intent i = new Intent(this, MainActivity.class );
        startActivity(i);
    }
    public void lanzarPagos(View view) {
        Intent i = new Intent(this, Pagos.class );
        startActivity(i);
    }
    public void lanzarPerfil(View view) {
        Intent i = new Intent(this, Perfil.class );
        startActivity(i);
    }

    @Override
    public void ProcesoFinalizado(String salida) {
        Toast.makeText(this,salida,Toast.LENGTH_LONG).show();
    }
}
