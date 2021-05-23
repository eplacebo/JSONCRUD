package view;

import controller.PostController;
import model.Post;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PostView {
    PostController postController = new PostController();
    private Scanner scanner = new Scanner(System.in);
    private List<Post> posts;
    private Post post;
    MainMenu mainMenu = new MainMenu();


    public void menuPost() throws IOException, InterruptedException {


        System.out.println("   *******POST*******");
        System.out.println("Choose from these choices:\n" +
                "1. Get by ID\n" +
                "2. Delete by ID\n" +
                "3. Save\n" +
                "4. Update\n" +
                "5. Get All\n" +
                "6. Back");

        int selection = scanner.nextInt();

        switch (selection) {
            case (1):
                getID();
                backToMenu();
            case (2):
                deleteById();
                backToMenu();
            case (3):
                save();
                backToMenu();
            case (4):
                update();
                backToMenu();
            case (5):
                getAll();
                backToMenu();
            case (6):
               mainMenu.getMainMenu();
            default:
                System.out.println("Input error");
                backToMenu();
        }
    }

    private void backToMenu() throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        menuPost();
    }

    public void getAll() throws IOException {
        System.out.println(postController.getAllPosts().toString().replace("]", "").replace("[", "").replace(",",""));
    }

    public void getID() {
        System.out.println("Enter ID");
        long getID = scanner.nextLong();
        System.out.println();
        if (postController.getByIdPost(getID) == null) {
            System.out.println("Not found");
        } else System.out.println(postController.getByIdPost(getID) + "\n");
    }

    public void deleteById() throws IOException {
        System.out.println("Enter ID");
        long getID = scanner.nextLong();
        if (postController.getByIdPost(getID) == null) {
            System.out.println("Not found");
        } else {
            postController.deleteByIdPost(getID);
            System.out.println("\nPost " + getID + " deleted\n");
        }
    }


    public void save() throws IOException {
        System.out.println("Enter ID");
        long getID = scanner.nextLong();
        if (postController.getByIdPost(getID) != null) {
            System.out.println("Post with this ID already exists");
        } else {
            System.out.println("Enter content");
            String getContent = scanner.next();
            postController.savePost(getID, getContent);
            System.out.println("\nPost saved\n");
        }
    }


    public void update() throws IOException {
        System.out.println("Enter ID");
        Long getID = scanner.nextLong();
        if (postController.getByIdPost(getID) == null) {
            System.out.println("Not found");
        } else {
            System.out.println("Enter content");
            String content = scanner.next();
            postController.update(getID, content);
            System.out.println("\nPost updated\n");
        }
    }
}


