package repository.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Region;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegionRepositoryImpl {

    private final String regionFile = "src/main/resources/regions.json";
    private static RegionRepositoryImpl regionRepository;


    public List<Region> getById(Long id) {
        return (List<Region>) getAll().stream()
                .filter((s) -> s.getId().equals(id)).collect(Collectors.toList());
     /*   return getAll().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);*/
    }


    public List<Region> getAll() {
        return readJSON();
    }


    public Region save(Long id, String name) throws IOException {
        List<Region> regions = getAll();
        Region region = new Region(id, name);
        regions.add(region);
        writeJSON(regions);
        return region;
    }

    public void update(Long id, String name) throws IOException {
        deleteById(id);
        save(id, name);
    }



    public void deleteById(Long id) throws IOException {
        List<Region> regions = getAll();
        regions.removeIf(s -> s.getId().equals(id));
        writeJSON(regions);
    }

    public void writeJSON(List<Region> regions) throws IOException {
        try (Writer writer = Files.newBufferedWriter(Path.of(regionFile), StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            gson.toJson(regions, writer);
        }
    }

    public List<Region> readJSON() {
        try (BufferedReader reader = new BufferedReader(new FileReader(regionFile))) {
            List<Region> regions = new ArrayList<>();
            regions = new GsonBuilder().create().fromJson(reader, new TypeToken<List<Region>>() {
            }.getType());
            return regions;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
