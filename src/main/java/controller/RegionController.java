package controller;


import model.Region;
import repository.io.RegionRepositoryImpl;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RegionController {
    RegionRepositoryImpl regionController = new RegionRepositoryImpl();


    public List<Region> getAllRegions() throws IOException {
        return regionController.getAll();
    }

    public Region getByIdRegion(Long id) {
        try {
            return regionController.getById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteByIdRegion(Long id) throws IOException {
        regionController.deleteById(id);
    }

    public void saveRegion(Long id, String name) throws IOException {
      regionController.save(id,name);
    }

    public void updateRegion(Long id, String name) throws IOException {
        regionController.update(id, name);
    }

    private void printResult(List<model.Region> regions) {
        Collections.reverse(regions);
        System.out.println(regions.stream()
                .sorted(model.Region::compareTo)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList())
                .toString().replace("[", "").replace(",", "").replace("]", ""));
    }

}




