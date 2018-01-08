package univosv.listaperzo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;

import WS.CallSoap;
import WS.RespuestaAsync;


/**
 * Created by root on 07-30-17.
 */

public class Notas extends Activity implements RespuestaAsync {
    public SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        sharedPreferences=getApplicationContext().
        getSharedPreferences(Cofre.Vars.NOMBRE_SHARED_PREFERENCE,MODE_PRIVATE);
        Cofre.Funciones.Iniciar(sharedPreferences,this);
        MostrarNotas();

        TablaNotas tabla = new TablaNotas(this, (TableLayout)findViewById(R.id.tabla));
        tabla.agregarCabecera(R.array.cabecera_tabla);
        for(int i = 0; i < 7; i++)
        {
            ArrayList<String> elementos = new ArrayList<String>();
            elementos.add(Integer.toString(i));
            elementos.add("LAb1 [" + i + ", 0]");
            elementos.add("Lab2 [" + i + ", 1]");
            elementos.add("Lab3 [" + i + ", 2]");
            elementos.add("Lab4 [" + i + ", 3]");
            elementos.add("par1 [" + i + ", 4]");
            elementos.add("par2 [" + i + ", 5]");
            elementos.add("Lab3 [" + i + ", 6]");
            elementos.add("Repo [" + i + ", 7]");
            elementos.add("Final [" + i + ", 8]");
            tabla.agregarFilaTabla(elementos);
        }
    }
    private void MostrarNotas(){
        CallSoap.NotasWS sincrono = new CallSoap.NotasWS();
        String carnet=Cofre.Funciones.InvocarUsuario();
        String clave=Cofre.Funciones.InvocarClave();
        String [] arreglo={carnet,clave};
        sincrono.delegate = this;
        sincrono.execute(arreglo);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tabladinamica, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        Toast.makeText(this,salida, Toast.LENGTH_LONG).show();
    }
}
