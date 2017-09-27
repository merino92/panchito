package univosv.listaperzo.HorariosClases;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

import univosv.listaperzo.ServiciosSegundoPlano;

/**
 * Created by administrador on 27/9/17.
 */

public class FuncionesDeHorarios {

    public static class VariablesHorarios{

    }

    public static class Funciones{


    }

    public static void LanzamientoDeNotificacionesHoriarios(){





    }

    public void verificarHora(Context context){

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
