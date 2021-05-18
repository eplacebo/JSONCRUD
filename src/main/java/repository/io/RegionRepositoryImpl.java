package repository.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Region;
import repository.RegionRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RegionRepositoryImpl implements RegionRepository {

    private final String regionFile = "src/main/resources/regions.json";

    @Override
    public Region getById(Long id) throws IOException {
        return getAll().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Region> getAll() throws IOException {
        return readJSON();
    }

    @Override
    public void save(Long id, String name) throws IOException {
        List<Region> regions = readJSON();
        regions.add(new Region(id, name));
        writeJSON(regions);
    }

    @Override
    public void update(Long id, String name) throws IOException {
        deleteById(id);
        save(id, name);
    }

    @Override
    public void deleteById(Long id) throws IOException {
        List<Region> regions = readJSON();
        regions.removeIf(s -> s.getId().equals(id));
        writeJSON(regions);
    }

    private void writeJSON(List<Region> regions) throws IOException {
        try (Writer writer = Files.newBufferedWriter(Path.of(regionFile), StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            gson.toJson(regions, writer);
        }
    }

    private List<Region> readJSON() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(regionFile))) {
            List<Region> regions = new ArrayList<>();
            regions = new GsonBuilder().create().fromJson(reader, new TypeToken<List<Region>>() {
            }.getType());
            return regions;
        }
    }
}
