import repository.io.RegionRepositoryImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        RegionRepositoryImpl regionRepository = new RegionRepositoryImpl();
        regionRepository.deleteById(999L);
        System.out.println(regionRepository.getAll());
        System.out.println(regionRepository.getAll());
        System.out.println(regionRepository.getById(999L));



        regionRepository.save(13L,"dub");
        System.out.println(regionRepository.getAll());
        regionRepository.update(13L,"YYY");
        System.out.println(regionRepository.getAll());
    }
}
