package univosv.listaperzo;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

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

    }
    public void onStart(Intent intent, int startId){
        System.out.println("El servicio a Comenzado");
        this.stopSelf();
    }
    public void onDestroy(){
        super.onDestroy();
        System.out.println("El servicio a Terminado");
    }

    public void ArmarNotificaciones(Context context){

        AlarmManager alarmMgr;
        PendingIntent alarmIntent;

        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ServiciosSegundoPlano.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

/// Poner alarma para comenzar a las 1:30 a.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 30);

// setRepeating() permite definir el intervalo de repetición
        long unDia = 1000 * 60 * 60 * 24;
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                unDia, alarmIntent);

    }


}
