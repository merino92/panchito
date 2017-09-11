package univosv.listaperzo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;

import univosv.listaperzo.Notificaciones.Notificacion;

/**
 * Created by administrador on 11/9/17.
 */

public class ServiciosSegundoPlano extends Service {
    Notificacion notificaciones;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
    public void onCreate(){
        super.onCreate();
        LanzarAvisos();
    }
    public void onStart(Intent intent, int startId){
        System.out.println("El servicio a Comenzado");
        this.stopSelf();
    }
    public void onDestroy(){
        super.onDestroy();
        System.out.println("El servicio a Terminado");
    }

public void  LanzarAvisos(){


    String[] dias={"Domingo","Lunes","Martes", "Miércoles","Jueves","Viernes","Sábado"};
    Date hoy=new Date();
    int numeroDia=0;
    Calendar cal= Calendar.getInstance();
    cal.setTime(hoy);
    numeroDia=cal.get(Calendar.DAY_OF_WEEK);
    //System.out.println("hoy es "+ dias[numeroDia - 1]);
    String dia =dias[numeroDia - 1];
    if (dia.equals("Lunes")){



    }

}

}
