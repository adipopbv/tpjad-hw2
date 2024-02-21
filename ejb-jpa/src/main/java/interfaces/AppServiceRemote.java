package interfaces;

import dto.GreenhouseDTO;
import dto.PlantDTO;

import jakarta.ejb.Remote;
import java.util.Collection;

@Remote
public interface AppServiceRemote {
    PlantDTO createPlantR(Long plantId, String name, int height);

    GreenhouseDTO createGreenhouseR(String name);

    PlantDTO findPlantR(Object id);

    GreenhouseDTO findGreenhouseR(Object id);

    Collection<PlantDTO> getAllPlantsR();

    GreenhouseDTO findGreenhouseDTOByName(String name);

    Collection<PlantDTO> findPlantsDTOForGreenhouse(Long id);

    Collection<GreenhouseDTO> getAllGreenhousesR();
}
