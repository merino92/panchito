package univosv.listaperzo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import WS.WebService;
import univosv.listaperzo.Modelos.Clase;
import univosv.listaperzo.Modelos.Materia;
import univosv.listaperzo.Notificaciones.Alarma;

/**
 * Created by root on 07-30-17.
 */

public class Horarios extends Activity {
     private ListView list;
    String[] Materias = {"Estructura de Datos","Investigacion de Operaciones", "Sistemas Digitales"};
    String[] Horas = {"8:50 a.m. a 10:30 a.m.","10:40 a.m. a 12:20 p.m.",
            "4:20 p.m. a 6:00 p.m."};
    String[] Dias = {"Lu-Mi","Ma-Ju","Vi"};
    String[] Aulas = {"CJP-12","CT-6","A2-4"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);
        Cofre.Funciones.IniciarBdd(this);

    }

    @Override
    protected void onStart(){
        super.onStart();
        list = (ListView)findViewById(R.id.Listahorarios);
        List<Materia> materias;
        Cofre.Funciones.IniciarBdd(this);

        if(Materia.TablaMateriasPoseeRegistros()){
            materias= Materia.ObtenerMaterias();
            FormatearHorariosParaMostrar(materias);
            CustomAdapter customAdapter =
                    new CustomAdapter(this,Materias,Horas,Dias,Aulas);
            list.setAdapter(customAdapter);
        }else {
            materias = WebService.ObtenerMaterias();
            FormatearHorariosParaMostrar(materias);
            CustomAdapter customAdapter =
                    new CustomAdapter(this,Materias,Horas,Dias,Aulas);

            list.setAdapter(customAdapter);
            Materia.GuardarMaterias(materias);
        }

    }
    private void FormatearHorariosParaMostrar(List<Materia> materias){
        Materias = new String[materias.size()];
        Horas = new String[materias.size()];
        Dias = new String[materias.size()];
        Aulas = new String[materias.size()];
        int i = 0;
        //Iteración de materias
        for(Materia m:materias){
            Materias[i] = m.Nombre;
            //Asignando valor por defecto string
            Horas[i] = "";
            Dias[i] = "";
            Aulas[i] = "";
            String separadorV = " ~ ";
            boolean esMismaAula = false;
            String aulaTemporal = "";
            //Iteración de clases.
            for(int j=0;j<m.Clase.size();j++){
                Clase c = m.Clase.get(j);//Obtiene todas las clases
                //Quita la "|" antes de la última inserción.
                if(j+1 == m.Clase.size())
                {
                    separadorV = "";
                }
                //Guarda en una misma posición todas las clases
                Horas[i] += c.HoraInicio + "-" + c.HoraFin + separadorV;
                Dias[i] += c.Dia+separadorV;
                Aulas[i] += c.Aula+separadorV;
                if(j>0)//Determina si es la misma aula todas las clases.
                {
                    if(aulaTemporal.equals(c.Aula)){
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
    private void Refrescar(){
        List<Materia>materias = WebService.ObtenerMaterias();
        FormatearHorariosParaMostrar(materias);
        CustomAdapter customAdapter =
                new CustomAdapter(this,Materias,Horas,Dias,Aulas);

        list.setAdapter(customAdapter);
        Materia.GuardarMaterias(materias);
    }

    private void ArmarNotificaciones(){
        SQLiteDatabase bd=Cofre.Vars.Base.getReadableDatabase();
        List<Materia> materias=Materia.ObtenerMaterias();
        for(Materia m:materias){
            String Nombre=m.Nombre;
           Date hora;
                int i=1;
                if(i==5){i=1;}
            for(Clase c:m.Clase){
                String horas,parte1,parte2,aula,horaCompleta;
                aula=c.Aula;
                horas=c.HoraInicio;
                horaCompleta="Inicia a las"+horas+"-"+" aula:"+aula;
                String [] part=horas.split(":");
                parte1=part[0];
                parte2=part[1];
                int hour,minuto;
                hour=Integer.parseInt(parte1);
                minuto=Integer.parseInt(parte2);
                ArrayList list=new ArrayList();
                list.add(hour);
                list.add(minuto);
                List<Integer> Horasformateadas=ArregloDeHoras(list);
                Alarma.Crear(this,Nombre,horaCompleta,Horasformateadas,i);
                i++;
            }

        }


    }
    private List<Integer> ArregloDeHoras(List<Integer>horas){
        int hora,minuto;
        hora=horas.get(0);
        minuto= horas.get(1);
        if(minuto==0){
            hora=hora-1;
            minuto=minuto+45;
        }
        List<Integer>formateado=new ArrayList<Integer>();
        formateado.add(hora);
        formateado.add(minuto);


        return formateado;
    }
}