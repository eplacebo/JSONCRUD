package view;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu {

    public void getMainMenu() throws IOException, InterruptedException {
        PostView postView = new PostView();
        RegionView regionView =  new RegionView();


        Scanner scanner = new Scanner(System.in);
        System.out.println("   *******MENU*******");
        System.out.println("Choose from these choices:\n" +
                "1. Users\n" +
                "2. Posts\n" +
                "3. Regions\n" +
                "4. Exit\n");

        int selection = scanner.nextInt();

        switch (selection) {
            case (1):
                break;
            case (2):
                postView.menuPost();
            case (3):
                regionView.menuRegion();
            case (4):
                System.exit(0);
            default:
                System.out.println("Input error");
                getMainMenu();
        }
    }
}


