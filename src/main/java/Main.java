import repository.io.RegionRepositoryImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        RegionRepositoryImpl regionRepository = new RegionRepositoryImpl();
        //System.out.println(regionRepository.getAll());
        regionRepository.deleteById(999L);
        System.out.println(regionRepository.getAll());
        regionRepository.save(999L,"ASD");
        System.out.println(regionRepository.getAll());
        System.out.println(regionRepository.getById(999L));

        regionRepository.update(999L,"ASd214");
        System.out.println(regionRepository.getAll());
    }
}
