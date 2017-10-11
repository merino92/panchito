package univosv.listaperzo.Basededatos;

/**
 * Created by root on 09-18-17.
 *
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseSQL extends SQLiteOpenHelper{
    public static final String NombreTabla = "NOTICIAS";
    public static final String NombreTablaMaterias="MATERIAS";
    public static final String NombreTablaClase = "CLASES";
    public static final String EliminarDatosTabla="DELETE FROM "+NombreTabla;
    public static final String EliminarDatosTablaMateria = "DELETE FROM "+NombreTablaMaterias;
    public static final String EliminarDatosTablaClases = "DELETE FROM "+NombreTablaClase;

    //Sentencia SQL para crear la tabla de Usuarios
    String CrearBase = "CREATE TABLE "+NombreTabla+" (titulo TEXT, descripcion TEXT,url TEXT)";
    String CrearTablaMaterias="CREATE TABLE "+NombreTablaMaterias+" (nombre TEXT)";
    //insert into CLASES(idMateria,diaSemana,hora,aula) values(1,'Lunes','16:20','CT-4');
    String CrearTablaClases="CREATE TABLE "+NombreTablaClase
            + " (idMateria INT, diaSemana TEXT, horaInicio TEXT,horaFin TEXT,aula TEXT)";

    public BaseSQL(Context contexto,
                   String nombre,
                   CursorFactory factory,
                   int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CrearBase);
        db.execSQL(CrearTablaMaterias);
        db.execSQL(CrearTablaClases);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS "+NombreTabla);
        db.execSQL("DROP TABLE IF EXISTS "+NombreTablaMaterias);
        db.execSQL("DROP TABLE IF EXISTS "+NombreTablaClase);

        //Se crea la nueva versión de la tabla
        db.execSQL(CrearBase);
        db.execSQL(CrearTablaMaterias);
        db.execSQL(CrearTablaClases);
    }
}