package univosv.listaperzo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by root on 07-30-17.
 */

public class Horarios extends Activity {
    // private ListView list;
    String[] Materias = {"Estructura de Datos","Investigacion de Operaciones", "Sistemas Digitales"};
    String[] Horas = {"8:50 a.m. a 10:30 a.m.","10:40 a.m. a 12:20 p.m.",
            "4:20 p.m. a 6:00 p.m."};
    String[] Dias = {"Lu-Mi","Ma-Ju","Vi"};
    String[] Aulas = {"CJP-12","CT-6","A2-4"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);
        ListView list = (ListView)findViewById(R.id.Listahorarios);
        //pasamos dos parametros this, Materias
        CustomAdapter customAdapter =
                new CustomAdapter(this,Materias,Horas,Dias,Aulas);

        list.setAdapter(customAdapter);
        //Toast.makeText(this,"hola2",Toast.LENGTH_LONG).show();
    }
    public void lanzarNoticias(View view) {
        Intent i = new Intent(this, MainActivity.class );
        startActivity(i);
    }
    public void lanzarNotas(View view) {
        Intent i = new Intent(this, Notas.class );
        startActivity(i);
    }
    public void lanzarPagos(View view) {
        Intent i = new Intent(this, Pagos.class );
        startActivity(i);
    }
    public void lanzarPerfil(View view) {
        Intent i = new Intent(this, Perfil.class );
        startActivity(i);
    }
    public class CustomAdapter extends BaseAdapter {

        //Creamos dos miembros de la clase
        private Context Contexto;
        private String[] ListaMaterias;
        private String[] ListaHoras;
        private String[] ListaDias;
        private String[] ListaAulas;

        public CustomAdapter(Context contexto,
                             String[] listaMaterias,
                             String[]listaHoras,
                             String[]listaDias,
                             String[]listaAulas){
            Contexto = contexto;
            ListaMaterias = listaMaterias;
            ListaHoras=listaHoras;
            ListaDias=listaDias;
            ListaAulas = listaAulas;
        }
        //Ahora retorna ListaMaterias.length
        @Override
        public int getCount() {
            return ListaMaterias.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        //Ahora retorna position
        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int i, View view,ViewGroup viewGroup){
            view = getLayoutInflater().inflate(R.layout.listhorarios,null);
            //ImageView imageView=(ImageView)view.findViewById(R.id.imageView);
            TextView materias = (TextView)view.findViewById(R.id.Nmateria);
            TextView horas = (TextView)view.findViewById(R.id.hora1);
            TextView dias=(TextView)view.findViewById(R.id.dias1);
            TextView aulas=(TextView)view.findViewById(R.id.aula1);

            //imageView.setImageResource(iconos[i]);
            materias.setText(ListaMaterias[i]);
            horas.setText(ListaHoras[i]);
            dias.setText(ListaDias[i]);
            aulas.setText(ListaAulas[i]);
            //Log.d("cadenas",Materias[i]);
            return view;
        }
    }
}