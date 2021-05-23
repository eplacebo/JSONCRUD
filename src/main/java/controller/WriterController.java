package controller;

import model.Post;
import model.Region;
import model.Writer;
import repository.io.RegionRepositoryImpl;
import repository.io.WriterRepositoryImpl;

import java.io.IOException;
import java.util.List;

public class WriterController {
    WriterRepositoryImpl writerRepository = new WriterRepositoryImpl();
    RegionRepositoryImpl regionRepository = new RegionRepositoryImpl();

    public List<Writer> getAllWriters() throws IOException {
        return writerRepository.getAll();
    }

    public Writer getByIdWriter(Long id) {
        try {
            return writerRepository.getById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteByIdWriter(Long id) throws IOException {
        if (writerRepository.getById(id) == null) {
            return false;
        } else {
            writerRepository.deleteById(id);
            return true;
        }
    }

    public Writer saveWriter(Writer writer) throws IOException {
        writer = writerRepository.save(writer);
     /*   if (writerRepository.getById(id) == null) {
            return writerRepository.save(new Writer(id, firstName, lastName, posts, region));
        } else {
            return null;
        }*/
    }

    public Writer update(Long id, String firstName, String lastName, List<Post> posts, Region region) throws IOException {

        return writerRepository.update(new Writer(id, firstName, lastName, posts, region));
    }
}






