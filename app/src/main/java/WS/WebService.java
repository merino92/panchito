package WS;

import java.util.ArrayList;
import java.util.List;

import univosv.listaperzo.Cofre;
import univosv.listaperzo.Modelos.Clase;
import univosv.listaperzo.Modelos.Materia;

/**
 * Created by administrador on 16/10/17.
 */

public class WebService {

    //Obtiene las materias del WebService.
  public static List<Materia> ObtenerMaterias(){
        List<Materia> listaMaterias = new ArrayList<>();
        Materia materia;

        materia = new Materia("Estructura de Datos");
        materia.Clase.add(new Clase(Cofre.Vars.Dias.Lu,"CT-7","8:50","10:30"));
        materia.Clase.add(new Clase(Cofre.Vars.Dias.Mi,"CT-7","13:50","15:40"));
        listaMaterias.add(materia);

        materia = new Materia("Investigacion de Operaciones");
        materia.Clase.add(new Clase(Cofre.Vars.Dias.Ma,"A2-4","10:50","12:20"));
        materia.Clase.add(new Clase(Cofre.Vars.Dias.Ju,"A2-3","10:50","12:20"));
        listaMaterias.add(materia);

        materia = new Materia("Sistemas Digitales");
        materia.Clase.add(new Clase(Cofre.Vars.Dias.Vi,"CJP-12","16:20","6:00"));
        listaMaterias.add(materia);

        materia = new Materia("Educacion Ambiental");
        materia.Clase.add(new Clase(Cofre.Vars.Dias.Lu,"CT-7","7:00","08:400"));
        materia.Clase.add(new Clase(Cofre.Vars.Dias.Mi,"CT-7","7:50","10:30"));
        listaMaterias.add(materia);


        return listaMaterias;
    }
}
