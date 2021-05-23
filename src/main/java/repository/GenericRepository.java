package repository;

import java.io.IOException;
import java.util.List;

public interface GenericRepository<T,ID> {

    T getById(ID id) throws IOException;

    List<T> getAll() throws IOException;

    T update(T t) throws IOException;

    boolean deleteById(ID id) throws IOException;

    T save(T t) throws IOException;
}