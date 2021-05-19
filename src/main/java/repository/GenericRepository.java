package repository;

import java.io.IOException;
import java.util.List;

public interface GenericRepository<ID, T> {

    T getById(ID id) throws IOException;

    List<T> getAll() throws IOException;

    void update(Long id, String name) throws IOException;

    void deleteById(ID id) throws IOException;

    void save(Long id, String name) throws IOException;
}