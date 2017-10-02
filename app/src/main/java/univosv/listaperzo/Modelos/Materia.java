package univosv.listaperzo.Modelos;

import android.content.ContentValues;
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

    public static void GuardarMaterias(ArrayList<Materia> materias)
    {
        SQLiteDatabase db = Cofre.Vars.Base.getWritableDatabase();
        if(db != null){
            if(TablaMateriasPoseeRegistros()){
                db.execSQL(BaseSQL.EliminarDatosTablaClases);
                db.execSQL(BaseSQL.EliminarDatosTablaMateria);
            }
            for (Materia materia:materias) {
                ContentValues datos = new ContentValues();
                datos.put("nombre",materia.Nombre);
                long id = db.insert(BaseSQL.NombreTablaMaterias,null,datos);
                for(Clase clase:materia.Clase){
                    ContentValues datosClase = new ContentValues();
                    datosClase.put("dia",clase.Dia.toString());
                    datosClase.put("aula",clase.Aula);
                    //datosClase.put("hora",getD);
                }
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
