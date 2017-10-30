package univosv.listaperzo;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import univosv.listaperzo.Basededatos.BaseSQL;
import univosv.listaperzo.Modelos.Clase;
import univosv.listaperzo.Modelos.Materia;
import univosv.listaperzo.Notificaciones.Alarma;
import univosv.listaperzo.Notificaciones.Notificacion;

import static android.content.ContentValues.TAG;
import static java.lang.System.in;

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
        ////DispararNotificaciones();
        this.stopSelf();
    }
    public void onDestroy(){
        super.onDestroy();
        System.out.println("El servicio a Terminado");
    }




    public void DispararNotificaciones(List<Materia> materias, Cofre.Vars.Dias hoy){
        for(int i = 0;i<materias.size();i++){
            Materia m = materias.get(i);
            for(int j = 0;j<m.Clase.size();j++){
                Clase c = m.Clase.get(j);
                if(c.Dia.equals(hoy)){
                    //AlarmaDeClase(this,m.Nombre,c.hora);
                }
            }
        }
    }
    public void AlarmaDeClase(Context context,Date hora,String materia,String aula,String horita){

        AlarmManager alarmMgr;
        PendingIntent alarmIntent;
         int  HoraRecortada=hora.getMinutes()-15;
        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, Notificacion.class);
        intent.putExtra(Alarma.variable.MATERIA,materia);
        intent.putExtra(Alarma.variable.AULA,aula);
        intent.putExtra(Alarma.variable.HORA,hora);

        alarmIntent =  PendingIntent.getService(context, Alarma.variable.INDEX, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,hora.getHours() );
        calendar.set(Calendar.MINUTE, HoraRecortada);
        alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);


    }

    private void GuardarMaterias(List<Materia> materias){



            SQLiteDatabase db = Cofre.Vars.Base.getWritableDatabase();
            if(db != null && db.isOpen()){
                if(Materia.TablaMateriasPoseeRegistros()){
                    db.execSQL(BaseSQL.EliminarDatosTablaClases);
                    db.execSQL(BaseSQL.EliminarDatosTablaMateria);
                    Log.i(TAG, "borra los datos ");
                }
                SQLiteDatabase db1 = Cofre.Vars.Base.getWritableDatabase();
                for (Materia materia:materias) {
                    ContentValues datos = new ContentValues();
                    datos.put("nombre",materia.Nombre);
                    long idMateria = db1.insert(BaseSQL.NombreTablaMaterias,null,datos);
                    Log.i("sqlite","Registro de materia insertado");
                    for(Clase clase:materia.Clase){
                        ContentValues datosClase = new ContentValues();
                        datosClase.put("dia",clase.Dia.toString());
                        datosClase.put("aula",clase.Aula);
                        datosClase.put("horaInicio",clase.HoraInicio);
                        datosClase.put("horaFin",clase.HoraFin);
                        datosClase.put("idMateria",idMateria);
                        db1.insert(BaseSQL.NombreTablaClase,null,datosClase);
                        Log.i("sqlite","Registro de clase insertado");


                    }
                }
                db.close();
            }
            else{
                Log.e("sqlite","No existe conexion a la bdd");

            }



    }

}
