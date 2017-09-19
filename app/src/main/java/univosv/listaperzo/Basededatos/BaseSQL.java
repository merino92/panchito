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

    //Sentencia SQL para crear la tabla de Usuarios
    String CrearBase = "CREATE TABLE NOTICIAS (titulo TEXT, descripcion TEXT,url TEXT)";

    public BaseSQL(Context contexto, String nombre,
                                CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CrearBase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS NOTICIAS");

        //Se crea la nueva versión de la tabla
        db.execSQL(CrearBase);
    }
}
