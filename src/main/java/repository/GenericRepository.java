package repository;

import java.util.List;

public interface GenericRepository<ID, T> {

    T getById(ID id);

    List<T> getAll();

    T save(T t);

    T update(T t);

    void deleteById(ID id);
}
