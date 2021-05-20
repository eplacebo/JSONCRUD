package view;

import controller.RegionController;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class RegionView {
    RegionController regionController = new RegionController();
    private Scanner scanner = new Scanner(System.in);
    private List<model.Region> regions;
    private model.Region region;


    public void menuRegion() throws IOException, InterruptedException {


        System.out.println("   *******REGIONS*******");
        System.out.println("Choose from these choices:\n" +
                "1. Get by ID\n" +
                "2. Delete by ID\n" +
                "3. Save\n" +
                "4. Update\n" +
                "5. Get All\n" +
                "6. Exit");

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
                System.exit(0);
            default:
                System.out.println("Input error");
                backToMenu();
        }
    }

    private void backToMenu() throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        menuRegion();
    }

    private void printResult(List<model.Region> regions) {
        Collections.reverse(regions);
        System.out.println(regions.stream()
                .sorted(model.Region::compareTo)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList())
                .toString().replace("[", "").replace(",", "").replace("]", "") + "\n");
    }

    public void getAll() throws IOException {
        printResult(regionController.getAllRegions());
    }

    public void getID() {
        System.out.println("Enter ID");
        long getID = scanner.nextLong();
        System.out.println();
        if (regionController.getByIdRegion(getID) == null) {
            System.out.println("Not found");
        } else System.out.println(regionController.getByIdRegion(getID) + "\n");
    }

    public void deleteById() throws IOException {
        System.out.println("Enter ID");
        long getID = scanner.nextLong();
        if (regionController.getByIdRegion(getID) == null) {
            System.out.println("Not found");
        } else {
            regionController.deleteByIdRegion(getID);
            System.out.println("\nObject " + getID + " deleted\n");
        }
    }


    public void save() throws IOException {
        System.out.println("Enter ID");
        long getID = scanner.nextLong();
        if (regionController.getByIdRegion(getID) != null) {
            System.out.println("Region with this ID already exists");
        } else {
            System.out.println("Enter name");
            String getName = scanner.next();
            regionController.saveRegion(getID, getName);
            System.out.println("\nObject saved\n");
        }
    }


    public void update() throws IOException {
        System.out.println("Enter ID");
        Long getID = scanner.nextLong();
        if (regionController.getByIdRegion(getID) == null) {
            System.out.println("Not found");
        } else {
            System.out.println("Enter name");
            String name = scanner.next();
            regionController.updateRegion(getID, name);
            System.out.println("\nObject updated\n");
        }
    }
}



