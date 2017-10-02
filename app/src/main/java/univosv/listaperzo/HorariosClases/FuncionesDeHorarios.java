package univosv.listaperzo.HorariosClases;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

import univosv.listaperzo.Cofre;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

import univosv.listaperzo.Modelos.Materia;
import univosv.listaperzo.ServiciosSegundoPlano;

/**
 * Created by administrador on 27/9/17.
 */

public class FuncionesDeHorarios {

    public static class VariablesHorarios{

    }


    public static class Funciones{
        public static void GuardarHorarios(
                ArrayList<Materia> Materias){
            SQLiteDatabase db = Cofre.Vars.Base.getWritableDatabase();
            if(db != null){

            }
            else{
                Log.e("BDD","No existe conexion a la bdd");
            }
        }
        public static void ObtenerHorarios(){}
        public static void ObtenerHorario(String materia){}
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
