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
    //campos de la tabla noticias
    public static final String CampoTitulo="titulo";
    public static final String CampoDescripcion="descripcion";
    public static final String CampoUrl="url";
    //campos de la tabla materias
    public static final String CampoId="id";
    public static final String CampoNombre="nombre";
    //campos de la tabla clases
    public static final String idMateria="idMateria";
    public static final String diaSemana="diaSemana";
    public static final String horaInicio="horaInicio";
    public static final String horaFin="horaFin";
    public static final String aula="aula";
    //Sentencia SQL para crear la tabla de Usuarios
    String CrearBase = "CREATE TABLE "+NombreTabla
                    +" ("+CampoTitulo+" TEXT,"+CampoDescripcion+" TEXT,"+CampoUrl+" TEXT)";
    String CrearTablaMaterias="CREATE TABLE "+NombreTablaMaterias
                        +" ("+CampoId+" INTEGER PRIMARY KEY,"+CampoNombre+" TEXT)";
    //insert into CLASES(idMateria,diaSemana,hora,aula) values(1,'Lunes','16:20','CT-4');
    String CrearTablaClases="CREATE TABLE "+NombreTablaClase
            + " ("+idMateria+" INT, "+ diaSemana+" TEXT, "+horaInicio+" TEXT,"+horaFin+" TEXT,"+aula+" TEXT)";

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