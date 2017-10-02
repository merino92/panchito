package univosv.listaperzo.Modelos;

import java.util.Date;

import univosv.listaperzo.Cofre;

/**
 * Created by administrador on 27/9/17.
 */

public class Clase {
    public Clase(){
        Dia = Cofre.Vars.Dias.Lunes;
        Aula = "NA";
    }
    public Cofre.Vars.Dias Dia;
    public Date hora;//Verificar si hay una variable que almacene hora solamente.
    public String Aula;
}
