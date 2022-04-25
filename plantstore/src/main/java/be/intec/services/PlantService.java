package be.intec.services;

import be.intec.models.Plant;

import java.util.List;

public interface PlantService {
    List<Plant> findAll ();

    Plant findById(Long id);

    List<Plant> findByCategory(String category);

    List<Plant> blurrySearch(String name);
}
