package itc.Model.Strategy;

import itc.Model.Alumno;
import java.util.Comparator;
import java.util.List;

public class OrdenarPorNoControl implements OrdenamientoStrategy {
    @Override
    public List<Alumno> ordenar(List<Alumno> lista) {
        lista.sort(Comparator.comparing(Alumno::getNoControl));
        return lista;
    }
}
