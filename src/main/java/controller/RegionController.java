package controller;


import model.Region;
import model.RegionIdComparator;
import repository.RegionRepository;
import repository.io.RegionRepositoryImpl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RegionController {
    RegionRepository regionController = new RegionRepositoryImpl();
    Comparator orderLong = new RegionIdComparator();

    public List<Region> getAllRegions() {
        return regionController.getAll();
    }

    public Region getByIdRegion(Long id) {
        return regionController.getById(id);
    }

    public boolean deleteByIdRegion(Long id) {
        regionController.deleteById(id);
        return true;
    }

    public Region saveRegion(Long id, String name) {
        return regionController.save(new Region(id, name));
    }

    public Region updateRegion(Long id, String name) {
        return regionController.update(new Region(id, name));
    }

    private void printResult(List<model.Region> regions) {
        Collections.reverse(regions);
        System.out.println(regions.stream()
                .sorted(orderLong::compare)
                .collect(Collectors.toList())
                .toString().replace("[", "").replace(",", "").replace("]", ""));
    }

}




