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

    private void NotificacionPush(String materia,String aulayHora,int n ){

        NotificationCompat.Builder builder;
        NotificationManager AdNotificaciones=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        int icono = R.mipmap.logofab;
        Intent intent = new Intent(this, Horarios.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent, 0);
        builder = new NotificationCompat.Builder(this).
                setContentIntent(pendingIntent).
                setContentTitle(materia).
                setContentText(aulayHora).
                setSmallIcon(icono).
                setVibrate(new long[] {100, 250, 100, 500}).
                setAutoCancel(true);
        //le agrega titutolo,despcripcion,el icono a la notificacion
        AdNotificaciones.notify(n,builder.build());
        //lanza la notificacion
    }

}
