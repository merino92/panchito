package univosv.listaperzo.Modelos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import univosv.listaperzo.Basededatos.BaseSQL;
import univosv.listaperzo.Cofre;

/**
 * Created by administrador on 27/9/17.
 */

public class Clase {
    public Clase(){
        Dia = Cofre.Vars.Dias.Lu;
        Aula = "NA";
        HoraInicio = "00:00";
        HoraFin = "00:00";
    }
    //TODO HACER LA FUNCION
    public static List<Clase> ObtenerClases(int idMateria){
        List<Clase> clases = new ArrayList<>();
        SQLiteDatabase db= Cofre.Vars.Base.getReadableDatabase();
        Cursor bddatos = db.rawQuery(" SELECT  FROM "+ BaseSQL.NombreTablaClase, null);

        //Nos aseguramos de que existe al menos un registro
        if (bddatos.moveToFirst() ) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                String idmateria = bddatos.getString(1);
                String dia = bddatos.getString(2);
                String inicio =bddatos.getString(2);
                String fin = bddatos.getString(3);
                String aula = bddatos.getString(4);

            } while (bddatos.moveToNext() );
        }
        return clases;
    }
    public Clase(Cofre.Vars.Dias dia,String aula,String horaInicio, String horaFin){
        Dia = dia;
        Aula = aula;
        HoraInicio = horaInicio;
        HoraFin = horaFin;
    }
    public Cofre.Vars.Dias Dia;
    public String HoraInicio;//Verificar si hay una variable que almacene hora solamente.
    public String HoraFin;
    public String Aula;
}
