package beans;

import dto.GreenhouseDTO;
import dto.PlantDTO;
import entities.Greenhouse;
import entities.Plant;
import interfaces.AppServiceLocal;
import interfaces.AppServiceRemote;

import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.Collection;
import java.util.stream.Collectors;

@Stateless(name = "AppServiceBean")
@Local(AppServiceLocal.class)
@Remote(AppServiceRemote.class)
public class AppServiceBean implements AppServiceLocal, AppServiceRemote {
    @PersistenceContext(unitName = "test")
    private EntityManager entityManager;

    private static PlantDTO plantToDTO(Plant plant) {
        if (plant == null) {
            return null;
        }
        PlantDTO plantDTO = new PlantDTO();
        plantDTO.setPlantId(plant.getPlantId());
        plantDTO.setName(plant.getName());
        plantDTO.setHeight(plant.getHeight());
        plantDTO.setGreenhouseId(plant.getGreenhouse().getGreenhouseId());
        return plantDTO;
    }

    private static GreenhouseDTO greenhouseToDTO(Greenhouse greenhouse) {
        if (greenhouse == null) {
            return null;
        }
        GreenhouseDTO greenhouseDTO = new GreenhouseDTO();
        greenhouseDTO.setName(greenhouse.getName());
        greenhouseDTO.setGreenhouseId(greenhouse.getGreenhouseId());
        for (Plant plant : greenhouse.getReleasedPlants()) {
            greenhouseDTO.getPlants().add(plantToDTO(plant));
        }
        return greenhouseDTO;
    }

    @Override
    public Plant createPlant(Long plantId, String name, int height) {
        Plant plant = new Plant();
        plant.setName(name);
        plant.setHeight(height);
        Greenhouse person = findGreenhouse(plantId);
        if (person != null) {
            plant.setGreenhouse(person);
            person.getReleasedPlants().add(plant);
            entityManager.persist(person);
        }
        entityManager.persist(plant);
        return plant;
    }

    @Override
    public Greenhouse createGreenhouse(String name) {
        Greenhouse greenhouse = new Greenhouse();
        greenhouse.setName(name);
        entityManager.persist(greenhouse);
        return greenhouse;
    }

    @Override
    public Plant findPlant(Object id) {
        return entityManager.find(Plant.class, id);
    }

    @Override
    public Greenhouse findGreenhouse(Object id) {
        return entityManager.find(Greenhouse.class, id);
    }

    @Override
    public Collection<Plant> getAllPlants() {
        TypedQuery<Plant> query = entityManager.createQuery("SELECT R FROM Plant R", Plant.class);
        return query.getResultList();
    }

    @Override
    public Greenhouse findGreenhouseByName(String name) {
        TypedQuery<Greenhouse> query = entityManager.createQuery("SELECT R FROM Greenhouse R WHERE R.name = :name",
                Greenhouse.class);
        query.setParameter("name", name);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Collection<Plant> findPlantsForGreenhouse(Long id) {
        TypedQuery<Plant> query = entityManager
                .createQuery("SELECT R FROM Plant R JOIN R.greenhouse P WHERE P.greenhouseId = :greenhouseId", Plant.class);
        query.setParameter("greenhouseId", id);
        return query.getResultList();
    }

    @Override
    public Collection<Greenhouse> getAllGreenhouses() {
        TypedQuery<Greenhouse> query = entityManager.createQuery("SELECT R FROM Greenhouse R", Greenhouse.class);
        return query.getResultList();
    }

    @Override
    public PlantDTO createPlantR(Long plantId, String name, int height) {
        return plantToDTO(createPlant(plantId, name, height));
    }

    @Override
    public GreenhouseDTO createGreenhouseR(String name) {
        return greenhouseToDTO(createGreenhouse(name));
    }

    @Override
    public PlantDTO findPlantR(Object id) {
        return plantToDTO(findPlant(id));
    }

    @Override
    public GreenhouseDTO findGreenhouseR(Object id) {
        return greenhouseToDTO(findGreenhouse(id));
    }

    @Override
    public Collection<PlantDTO> getAllPlantsR() {
        return getAllPlants().stream()
                .map(AppServiceBean::plantToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GreenhouseDTO findGreenhouseDTOByName(String name) {
        return greenhouseToDTO(findGreenhouseByName(name));
    }

    @Override
    public Collection<PlantDTO> findPlantsDTOForGreenhouse(Long id) {
        return findPlantsForGreenhouse(id).stream()
                .map(AppServiceBean::plantToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<GreenhouseDTO> getAllGreenhousesR() {
        return getAllGreenhouses().stream()
                .map(AppServiceBean::greenhouseToDTO)
                .collect(Collectors.toList());
    }
}
