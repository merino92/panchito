package univosv.listaperzo.Notificaciones;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import univosv.listaperzo.Horarios;
import univosv.listaperzo.R;

/**
 * Created by administrador on 11/9/17.
 */


public class Notificacion extends IntentService {
    public Notificacion(){
        super(Notificacion.class.getSimpleName());
    }

    private void NotificacionPush(String titulo, String texto){

        NotificationCompat.Builder builder;
        NotificationManager AdNotificaciones=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        int icono = R.mipmap.ic_launcher;
        Intent intent = new Intent(this, Horarios.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent, 0);
        builder = new NotificationCompat.Builder(this).
                setContentIntent(pendingIntent).
                setContentTitle(titulo).
                setContentText(texto).
                setSmallIcon(icono).
                setVibrate(new long[] {100, 250, 100, 500}).
                setAutoCancel(true);
        //le agrega titutolo,despcripcion,el icono a la notificacion
        AdNotificaciones.notify(1,builder.build());
        //lanza la notificacion
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        NotificacionPush("Titulo","Calendario");
    }
}

