package controller;

import model.Post;
import repository.io.PostRepositoryImpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;


public class PostController {
    PostRepositoryImpl postController = new PostRepositoryImpl();

    public List<Post> getAllPosts() throws IOException {
        return postController.getAll();
    }

    public Post getByIdPost(Long id) {
        try {
            return postController.getById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteByIdPost(Long id) throws IOException {
        if (postController.getById(id) == null) {
            return false;
        } else {
            postController.deleteById(id);
            return true;
        }
    }

    public Post savePost(Long id, String content) throws IOException {
        if (postController.getById(id) == null) {
            return postController.save(new Post(id, content, new Date(), new Date()));
        } else {
            return null;
        }
    }

    public Post update(Long id, String content) throws IOException {

        return postController.update(new Post(id, content, new Date(), new Date()));
    }
}





