package itc.Model.DAO;

import itc.Model.Alumno;
import java.util.List;

public interface AlumnoDAO extends BaseDAO<Alumno> {
    Alumno buscarPorNoControl(String noControl);
}
