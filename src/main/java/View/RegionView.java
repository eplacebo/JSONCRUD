package View;

import controller.RegionController;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RegionView {
    RegionController regionController = new RegionController();
    private Scanner scanner = new Scanner(System.in);
    private List<model.Region> regions;
    private model.Region region;


    public void menuRegion() throws IOException {
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
                break;
            case (2):
                deleteById();
                break;
            case (3):
                save();
                break;
            case (4):
                update();
                break;
            case (5):
                getAll();
                break;
            case (6):
                System.exit(0);
            default:
                System.out.println("Input error");

        }
    }


    private void printResult(List<model.Region> regions) {
        Collections.reverse(regions);
        System.out.println(regions.stream()
                .sorted(model.Region::compareTo)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList())
                .toString().replace("[", "").replace(",", "").replace("]", ""));
    }

    public void getAll() throws IOException {
        printResult(regionController.getAllRegions());
    }

    public void getID() {
        System.out.println("Enter ID");
        long getID = scanner.nextLong();
        System.out.println(regionController.getByIdRegion(getID));
    }

    public void deleteById() throws IOException {
        System.out.println("Enter ID");
        long getID = scanner.nextLong();
        regionController.deleteByIdRegion(getID);
        System.out.println("Object deleted");
        printResult(regionController.getAllRegions());
    }

    public void save() throws IOException {
        System.out.println("Enter ID");
        long getID = scanner.nextLong();
        System.out.println("Enter name");
        String name = scanner.nextLine();
        regionController.saveRegion(getID, name);
        System.out.println("Object saved");
        printResult(regionController.getAllRegions());
    }

    public void update() throws IOException {
        System.out.println("Enter ID");
        Long getID = scanner.nextLong();
        System.out.println("Enter name");
        String name = scanner.next();
        regionController.updateRegion(getID, name);
        System.out.println("Object updated");
    }
}



