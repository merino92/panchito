package univosv.listaperzo.Notificaciones;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by administrador on 11/9/17.
 */

public class Alarma {

    public static class variable {
        public static final int INDEX = 1;
        public static final String NOMBRE="NOMBRE1";
        public static final String MATERIA="materia";
        public static  final String AULA="aula";
        public static final String HORA="hora";
        public static final String Nnotificacion="Nnotificacion";
    }


    public static void Crear(Context contexto, String Materia,String Horacompleta, List<Integer> hora, int numero){

        AlarmManager alarmManager = (AlarmManager) contexto.getSystemService(Context.ALARM_SERVICE);
        Intent intento = new Intent(contexto,Notificacion.class);
        intento.putExtra(variable.MATERIA,Materia);
        intento.putExtra(variable.AULA,Horacompleta);
        intento.putExtra(variable.Nnotificacion,numero);
        int horas,minuto;
        horas=hora.get(0);
        minuto=hora.get(1);
        PendingIntent intentoPendiente =
                PendingIntent.getService(contexto, variable.INDEX, intento, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.HOUR_OF_DAY,horas);
        calendario.set(Calendar.MINUTE,minuto);

        //calendario.add(calendario.MINUTE, hora.getMinutes());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendario.getTimeInMillis(), intentoPendiente);
        }
        else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendario.getTimeInMillis(), intentoPendiente);
        }
    }
}
