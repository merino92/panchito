package univosv.listaperzo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by root on 07-30-17.
 */

public class Perfil extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
    }
    public void lanzarHorario(View view) {
        Intent i = new Intent(this, Horarios.class );
        startActivity(i);
    }
    public void lanzarNotas(View view) {
        Intent i = new Intent(this, Notas.class );
        startActivity(i);
    }
    public void lanzarPagos(View view) {
        Intent i = new Intent(this, Pagos.class );
        startActivity(i);
    }
    public void lanzarNoticias(View view) {
        Intent i = new Intent(this, MainActivity.class );
        startActivity(i);
    }
}
