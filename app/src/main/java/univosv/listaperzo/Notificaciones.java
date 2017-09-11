package univosv.listaperzo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by administrador on 11/9/17.
 */

public class Notificaciones {
protected Activity activity;
    protected Context context;
public void Notificationes(Activity activity,Context context){
  this.activity=activity;
    this.context=context;

    }
    public void NotificacionPush(String titulo, String texto){

        NotificationCompat.Builder builder;
        NotificationManager AdNotificaciones=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        int icono = R.mipmap.ic_launcher;
        Intent intent = new Intent(this.activity, Horarios.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this.activity, 0,intent, 0);
        builder = new NotificationCompat.Builder(context).
        setContentIntent(pendingIntent).
        setContentTitle(titulo).
        setContentText(texto).
        setSmallIcon(icono).
        setVibrate(new long[] {100, 250, 100, 500}).
        setAutoCancel(true);




    }
}
