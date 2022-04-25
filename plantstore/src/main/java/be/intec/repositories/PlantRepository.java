package be.intec.repositories;

import be.intec.models.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface PlantRepository extends JpaRepository<Plant,Long> {

  List<Plant> findByName(String name);
  List<Plant> findByCategory(String category);

}
