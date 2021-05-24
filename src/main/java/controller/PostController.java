package controller;

import model.Post;
import repository.PostRepository;
import repository.io.PostRepositoryImpl;
import java.util.Date;
import java.util.List;


public class PostController {
    PostRepository postController = new PostRepositoryImpl();
    public List<Post> getAllPosts() {
        return postController.getAll();
    }

    public Post getByIdPost(Long id) {
        return postController.getById(id);
    }

    public boolean deleteByIdPost(Long id) {
        if (postController.getById(id) == null) {
            return false;
        } else {
            postController.deleteById(id);
            return true;
        }
    }

    public Post savePost(Long id, String content) {
        if (postController.getById(id) == null) {
            return postController.save(new Post(id, content, new Date(), new Date()));
        } else {
            return null;
        }
    }

    public Post update(Long id, String content) {
        return postController.update(new Post(id, content, new Date(), new Date()));
    }
}





