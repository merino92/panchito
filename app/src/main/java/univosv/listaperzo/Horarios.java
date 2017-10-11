package univosv.listaperzo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import univosv.listaperzo.Modelos.Clase;
import univosv.listaperzo.Modelos.Materia;

import static java.lang.System.out;

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

    }

    @Override
    protected void onStart(){
        super.onStart();
        ListView list = (ListView)findViewById(R.id.Listahorarios);

        List<Materia> materias = ObtenerMateriasWs();
        FormatearHorariosParaMostrar(materias);
        CustomAdapter customAdapter =
                new CustomAdapter(this,Materias,Horas,Dias,Aulas);

        list.setAdapter(customAdapter);

    }
    private void FormatearHorariosParaMostrar(List<Materia> materias){
        Materias = new String[materias.size()];
        int i = 0;
        //Iteración de materias
        for(Materia m:materias){
            Materias[i] = m.Nombre;
            //Asignando valor por defecto string
            Horas[i] = "";
            Dias[i] = "";
            Aulas[i] = "";
            String separador = " ^ ";
            boolean esMismaAula = false;
            String aulaTemporal = "";
            //Iteración de clases.
            for(int j=0;j<m.Clase.size();j++){
                Clase c = m.Clase.get(j);//Obtiene todas las clases
                //Quita la "|" antes de la última inserción.
                if(j+1 == m.Clase.size())
                {
                    separador = "";
                }
                //Guarda en una misma posición todas las clases
                Horas[i] += c.HoraInicio + "-" + c.HoraFin + separador;
                Dias[i] += c.Dia+separador;
                Aulas[i] += c.Aula+separador;
                if(j>0)//Determina si es la misma aula todas las clases.
                {
                    if(aulaTemporal == c.Aula){
                        esMismaAula = true;
                    }
                    else{
                        esMismaAula = false;
                    }
                }
                else{
                    aulaTemporal = c.Aula;
                }
            }
            if(esMismaAula){
                Aulas[i] = aulaTemporal;
            }
            i++;
        }
    }

    //Obtiene las materias del WebService.
    private List<Materia> ObtenerMateriasWs(){
        List<Materia> listaMaterias = new ArrayList<>();
        Materia materia;

        materia = new Materia("Estructura de Datos");
        materia.Clase.add(new Clase(Cofre.Vars.Dias.L,"CT-7","8:50","10:30"));
        materia.Clase.add(new Clase(Cofre.Vars.Dias.Mi,"CT-7","13:50","15:40"));
        listaMaterias.add(materia);

        materia = new Materia("Investigacion de Operaciones");
        materia.Clase.add(new Clase(Cofre.Vars.Dias.Ma,"A2-4","10:50","12:20"));
        materia.Clase.add(new Clase(Cofre.Vars.Dias.J,"A2-3","10:50","12:20"));
        listaMaterias.add(materia);

        materia = new Materia("Sistemas Digitales");
        materia.Clase.add(new Clase(Cofre.Vars.Dias.V,"CJP-12","16:20","6:00"));
        listaMaterias.add(materia);

        return listaMaterias;
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