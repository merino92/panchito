package univosv.listaperzo.Modelos;

import java.text.SimpleDateFormat;
import java.util.Date;

import univosv.listaperzo.Cofre;

/**
 * Created by administrador on 27/9/17.
 */

public class Clase {
    public Clase(){
        Dia = Cofre.Vars.Dias.Lunes;
        Aula = "NA";
        Hora = "00:00";
    }
    public Clase(Cofre.Vars.Dias dia,String aula,String hora){
        Dia = dia;
        Aula = aula;
        Hora = hora;
    }
    public Cofre.Vars.Dias Dia;
    public String Hora;//Verificar si hay una variable que almacene hora solamente.
    public String Aula;
}
