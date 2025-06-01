package itc.Model.DAO;

import java.util.List;

public interface BaseDAO<T> {
    void insertar(T entidad);
    List<T> obtenerTodos();
}
