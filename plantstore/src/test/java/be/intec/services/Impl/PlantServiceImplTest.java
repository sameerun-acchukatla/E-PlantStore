package be.intec.services.Impl;

import be.intec.models.Plant;
import be.intec.repositories.PlantRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlantServiceImplTest {

    private PlantRepository plantRepository=  mock(PlantRepository.class);
    private PlantServiceImpl plantService = new PlantServiceImpl(plantRepository);


    @Test
    public void testFindAll() {
        Plant plant1 = new Plant();
        plant1.setActive(true);
        Plant plant2 = new Plant();
        plant2.setActive(true);
        Plant plant3 = new Plant();
        plant3.setActive(false);
        List<Plant> plantList = List.of(plant1,plant2,plant3);

        when(plantRepository.findAll()).thenReturn(plantList);
        List<Plant> actualPlantList = plantService.findAll();
        assertThat(actualPlantList).hasSize(2);

    }

    @Test
    public void testFindByIdReturnsValidPlant() {
        // test setup
        Plant plant = new Plant();
        when(plantRepository.findById(1L)).thenReturn(Optional.of(plant));

        // test call/method
        Plant actualPlant = plantService.findById(1L);

        // assert results
        assertEquals(plant,actualPlant);
    }

    @Test
    public void testFindByIdReturnsNull() {
        when(plantRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
        Plant actualPlant = plantService.findById(1L);
        assertEquals(null,actualPlant);
    }

    @Test
    public void testFindByCategory(){
        Plant plant1 = new Plant();
        plant1.setActive(true);
        Plant plant2 = new Plant();
        plant2.setActive(false);
        Plant plant3 = new Plant();
        plant3.setActive(true);
        List<Plant> plantList = List.of(plant1,plant2,plant3);

        when(plantRepository.findByCategory("Hanging")).thenReturn(plantList);
        List<Plant> actualPlantList = plantService.findByCategory("Hanging");

        assertThat(actualPlantList).hasSize(2);

    }

    @Test
    public void blurrySearch() {
        Plant plant1 = new Plant();
        plant1.setActive(true);
        Plant plant2 = new Plant();
        plant2.setActive(false);
        Plant plant3 = new Plant();
        plant3.setActive(true);
        List<Plant> plantList = List.of(plant1,plant2,plant3);

        when(plantRepository.findByName("Pathos")).thenReturn(plantList);
        List<Plant> actualPlantList = plantService.blurrySearch("Pathos");

        assertThat(actualPlantList).hasSize(2);

    }
}