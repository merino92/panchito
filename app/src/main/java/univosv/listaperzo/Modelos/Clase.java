package univosv.listaperzo.Modelos;

import java.text.SimpleDateFormat;
import java.util.Date;

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
