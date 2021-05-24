package controller;

import model.Post;
import model.Writer;
import repository.PostRepository;
import repository.RegionRepository;
import repository.WriterRepository;
import repository.io.PostRepositoryImpl;
import repository.io.RegionRepositoryImpl;
import repository.io.WriterRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class WriterController {
    WriterRepository writerRepository = new WriterRepositoryImpl();
    RegionRepository regionRepository = new RegionRepositoryImpl();
    PostRepository postRepository = new PostRepositoryImpl();

    public List<Writer> getAllWriters() {
        return writerRepository.getAll();
    }

    public Writer updateWriter(Long id, String firstName, String lastName, String idPost, Long idRegion) {
        if (writerRepository.getById(id) != null) {
            writerRepository.deleteById(id);
            return writerRepository.save(new Writer(id, firstName, lastName, getPostsById(idPost), regionRepository.getById(idRegion)));
        }
        return null;
    }

    public Writer getByIdWriter(Long id) {
        return writerRepository.getById(id);
    }

    public boolean deleteByIdWriter(Long id) {
        if (writerRepository.getById(id) == null) {
            return false;
        } else {
            writerRepository.deleteById(id);
            return true;
        }
    }

    public Writer saveWriter(Long id, String firstName, String lastName, String idPost, Long idRegion) {
        if (writerRepository.getById(id) == null) {
            return writerRepository.save(new Writer(id, firstName, lastName, getPostsById(idPost), regionRepository.getById(idRegion)));
        }
        return null;
    }

    public List<Post> getPostsById(String posts) {
        List<Post> resultPost = new ArrayList<>();
        List<Post> postList = postRepository.getAll();

        String items[] = posts.split(",");
        Long ent[] = new Long[items.length];
        for (int i = 0; i < items.length; i++) {
            try {
                ent[i] = Long.parseLong(items[i]);
            } catch (NumberFormatException e) {
            }
        }

        for (int i = 0; i < ent.length; i++) {
            resultPost.add(postRepository.getById(ent[i]));
        }
        return resultPost;
    }
}





