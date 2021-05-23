package repository.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Post;
import repository.PostRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PostRepositoryImpl implements PostRepository {

    private final String postFile = "src/main/resources/posts.json";

    private static PostRepositoryImpl postRepositoryImpl;

    public static PostRepositoryImpl getPostRepositoryImpl() {
        if (postRepositoryImpl == null) {
            postRepositoryImpl = new PostRepositoryImpl();
        }
        return postRepositoryImpl;
    }


    @Override
    public Post getById(Long id) throws IOException {
        return readJSON().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Post> getAll() throws IOException {
        return readJSON();

    }

    @Override
    public Post update(Post postUpdate) throws IOException {
        List<Post> posts = getAll().stream()
                .peek(s -> {
                    if (s.getId().equals(postUpdate.getId())) {
                        s.setContent(postUpdate.getContent());
                        s.setUpdated(new Date());
                    }
                })
                .collect(Collectors.toList());
        writeJSON(posts);
        return postUpdate;
    }

    @Override
    public boolean deleteById(Long id) throws IOException {
        List<Post> posts = readJSON();
        posts.removeIf(s -> s.getId().equals(id));
        writeJSON(posts);
        return true;

    }

    @Override
    public Post save(Post postSave) throws IOException {
        List<Post> posts = readJSON();
        Post post = new Post(postSave.getId(), postSave.getContent(), postSave.getCreated(), postSave.getUpdated());
        posts.add(post);
        writeJSON(posts);
        return post;
    }

    private void writeJSON(List<Post> posts) throws IOException {
        try (Writer writer = Files.newBufferedWriter(Path.of(postFile), StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            gson.toJson(posts, writer);
        }
    }

    private List<Post> readJSON() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(postFile))) {
            List<Post> posts = new ArrayList<>();
            posts = new GsonBuilder().create().fromJson(reader, new TypeToken<List<Post>>() {
            }.getType());
            return posts;
        }
    }


}

