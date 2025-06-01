package itc.Model.Strategy;

import itc.Model.Alumno;
import java.util.List;

public interface OrdenamientoStrategy {
    List<Alumno> ordenar(List<Alumno> lista);
}
