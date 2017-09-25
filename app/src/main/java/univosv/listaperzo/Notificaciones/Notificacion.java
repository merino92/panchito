package univosv.listaperzo.Notificaciones;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import java.util.Date;

import univosv.listaperzo.Horarios;
import univosv.listaperzo.R;

/**
 * Created by administrador on 11/9/17.
 * cambiando algo...
 */


public class Notificacion extends IntentService {

    /*private String materia,aula;
    private String hora;
    private int Nnotificacion;*/
    public Notificacion(){
        super(Notificacion.class.getSimpleName());//
    }



    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Intent intent1=new Intent();
        /*materia=intent1.getStringExtra(Alarma.variable.MATERIA);
        aula=intent1.getStringExtra(Alarma.variable.AULA);
        hora=intent1.getStringExtra(Alarma.variable.HORA);*/
        String materia =
        intent1.getIntExtra(Alarma.variable.Nnotificacion,Nnotificacion);
        String descripcion= aula+""+"\n"+hora;
        //NotificacionPush(materia,descripcion,Nnotificacion);
        //NotificacionPush("Rafa","Descripcion de rafa",1);

    }

    private void NotificacionPush(String titulo, String texto ,int Nnotificacion){

        NotificationCompat.Builder builder;
        NotificationManager AdNotificaciones=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        int icono = R.mipmap.logofab;
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
        AdNotificaciones.notify(Nnotificacion,builder.build());
        //lanza la notificacion
    }
}

