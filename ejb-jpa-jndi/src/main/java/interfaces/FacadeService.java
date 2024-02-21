package interfaces;

import entities.Greenhouse;
import entities.Plant;

import java.util.Collection;

public interface FacadeService {
    Plant createPlant(Long plantId, String name, int height);

    Greenhouse createGreenhouse(String name);

    Plant findPlant(Object id);

    Greenhouse findGreenhouse(Object id);

    Collection<Plant> getAllPlants();

    Greenhouse findGreenhouseByName(String name);

    Collection<Plant> findPlantsForGreenhouse(Long id);

    Collection<Greenhouse> getAllGreenhouses();
}
