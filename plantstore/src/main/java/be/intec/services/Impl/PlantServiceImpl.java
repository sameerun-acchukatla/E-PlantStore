package be.intec.services.Impl;

import be.intec.models.Plant;
import be.intec.repositories.PlantRepository;
import be.intec.services.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlantServiceImpl implements PlantService {


    private final PlantRepository plantRepository;

    @Autowired
    public PlantServiceImpl(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Plant> findAll(){

        List<Plant> plantList = plantRepository.findAll();
        List<Plant> activePlantList = new ArrayList<>();

        for(Plant plant: plantList){
            if (plant.isActive()){
                activePlantList.add(plant);
            }
        }
        return activePlantList;
    }

    @Override
    public Plant findById(Long id) {

        return plantRepository.findById(id).orElse(null) ;
    }

    @Override
    public List<Plant> findByCategory(String category) {

        List<Plant> plantList = plantRepository.findByCategory(category);

        List<Plant> activePlantList = new ArrayList<>();

        for (Plant plant : plantList){
            if (plant.isActive()){
                activePlantList.add(plant);
            }
        }
        return activePlantList;
    }

    @Override
    public List<Plant> blurrySearch(String name) {
        List<Plant> plantList = plantRepository.findByName(name);
        List<Plant> activePlantList = new ArrayList<>();

        for (Plant plant: plantList) {
            if(plant.isActive()) {
                activePlantList.add(plant);
            }
        }

        return activePlantList;
    }

}
