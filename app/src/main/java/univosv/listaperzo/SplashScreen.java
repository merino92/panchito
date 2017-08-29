package univosv.listaperzo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {
ProgressBar barra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        barra=(ProgressBar)findViewById(R.id.progressBar);
        final int ProgresoTotal=100;
        barra.setProgress(0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int Progreso=0;
                while (Progreso<ProgresoTotal){
                    Progreso +=25;
                    barra.setProgress(Progreso);
                }


                Intent i = new Intent(SplashScreen.this,login.class);
                startActivity(i);///////
            }
        },4000L);
    }
}
