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

            PendingIntent intentoPendiente = PendingIntent.getService(contexto, INDEX, intento, PendingIntent.FLAG_UPDATE_CURRENT);

            Calendar calendario = Calendar.getInstance();
        /*calendario.add(calendario.MONTH,9);
        calendario.add(calendario.DAY_OF_WEEK,3);*/

            calendario.add(calendario.MINUTE, INDEX);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendario.getTimeInMillis(), intentoPendiente);
            } else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendario.getTimeInMillis(), intentoPendiente);
            }

    }

}
