package univosv.listaperzo.Modelos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import univosv.listaperzo.Basededatos.BaseSQL;
import univosv.listaperzo.Cofre;

import static android.content.ContentValues.TAG;

/**
 * Created by administrador on 27/9/17.
 */

public class Materia {

    public String Nombre;
    public List<Clase> Clase;
    public Materia(){
        Clase = new ArrayList<>();
    }

    public Materia(String nombre){
        Nombre = nombre;
        Clase = new ArrayList<>();
    }

    public static void GuardarMaterias(List<Materia> materias)
    {
        SQLiteDatabase db = Cofre.Vars.Base.getWritableDatabase();
        if(db != null && db.isOpen()){
            if(TablaMateriasPoseeRegistros()){
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
                    datosClase.put("diaSemana",clase.Dia.toString());
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

    public static boolean TablaMateriasPoseeRegistros(){
        SQLiteDatabase db = Cofre.Vars.Base.getWritableDatabase();
        long numRows = DatabaseUtils.queryNumEntries(db, BaseSQL.NombreTablaMaterias);
        db.close();
        return (numRows>0);
    }

    //TODO verificar que funciona
    public static List<Materia> ObtenerMaterias(){
        List<Materia> materias = new ArrayList<>();
        SQLiteDatabase db= Cofre.Vars.Base.getReadableDatabase();
        Cursor bddatos = db.rawQuery(" SELECT * FROM "+BaseSQL.NombreTablaMaterias, null);
        List<Clase> clases = new ArrayList<>();
        Materia materiaTemp = new Materia();
        //Nos aseguramos de que existe al menos un registro
        if (bddatos.moveToFirst() ) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                String idmateria = bddatos.getString(0);
                String nombre=bddatos.getString(1);
                materiaTemp.Nombre = nombre;
                clases = univosv.listaperzo.Modelos.Clase.ObtenerClases(idmateria);
                materiaTemp.Clase = clases;
                materias.add(materiaTemp);

            } while (bddatos.moveToNext() );
        }



        return materias;
    }

    //TODO
    public static Materia ObtenerMateria(String nombreMateria){
        Materia materia = new Materia();
        return materia;
    }
}
