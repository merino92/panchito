package univosv.listaperzo.Notificaciones;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Calendar;

/**
 * Created by administrador on 11/9/17.
 */

public class Alarma {
    private static int INDEX = 1;
    private static String NOMBRE="NOMBRE1";
    public static void Crear(Context contexto){
        AlarmManager alarmManager = (AlarmManager) contexto.getSystemService(Context.ALARM_SERVICE);
        Intent intento = new Intent(contexto,Notificacion.class);
        intento.putExtra(NOMBRE,INDEX);
        PendingIntent intentoPendiente = PendingIntent.getService(contexto,INDEX,intento,PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendario = Calendar.getInstance();
        calendario.set(2017,9,11,15,35,0);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendario.getTimeInMillis(),intentoPendiente);
        }
        else{
            alarmManager.set(AlarmManager.RTC_WAKEUP,calendario.getTimeInMillis(),intentoPendiente);
        }
    }

}
