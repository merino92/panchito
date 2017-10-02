package univosv.listaperzo.Modelos;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import univosv.listaperzo.Basededatos.BaseSQL;
import univosv.listaperzo.Cofre;

/**
 * Created by administrador on 27/9/17.
 */

public class Materia {
    public String Nombre;
    public ArrayList<Clase> Clase;

    public static void GuardarMaterias(ArrayList<Materia> Materias)
    {
        SQLiteDatabase db = Cofre.Vars.Base.getWritableDatabase();
        if(db != null){
            if(TablaMateriasPoseeRegistros()){
                //db.execSQL(BaseSQL.); borrar tablas materia y clase
            }
        }
        else{
            Log.e("BDD","No existe conexion a la bdd");
        }
    }

    public static boolean TablaMateriasPoseeRegistros(){
        SQLiteDatabase db = Cofre.Vars.Base.getWritableDatabase();
        long numRows = DatabaseUtils.queryNumEntries(db, BaseSQL.NombreTablaMaterias);
        return (numRows>0);
    }

    //Retorna las materias con sus clases y horarios
    public static List<Materia> ObtenerMaterias(){
        List<Materia> materias = new ArrayList<>();
        return materias;
    }

    public static Materia ObtenerMateria(String nombreMateria){
        Materia materia = new Materia();
        return materia;
    }
}
