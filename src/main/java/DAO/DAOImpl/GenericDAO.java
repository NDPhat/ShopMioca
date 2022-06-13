package DAO.DAOImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDAO<ID extends Serializable, T> {
    List<T> getAll();
    T update(T entity);
    T insert(T entity);
    T findById(ID id);
    boolean delete(T entity);
    T findEqualUnique(String property,Object value);
}
