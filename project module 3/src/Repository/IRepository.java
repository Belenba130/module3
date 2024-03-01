package Repository;

import java.util.List;

public interface IRepository<T,K> {
    List<T> getAll(Class<T> entityClass);
    T getById(Class<T> entityClass, K id);
    T update(T entity);
    T add(T entity);
    boolean delete(K key, Class<T> entityClass);

}
