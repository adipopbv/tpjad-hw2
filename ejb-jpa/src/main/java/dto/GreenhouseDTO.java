package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class GreenhouseDTO implements Serializable {
    private Long greenhouseId = 1L;
    private String name;
    private Collection<PlantDTO> plants = new ArrayList<>();

    public GreenhouseDTO() {
    }

    public GreenhouseDTO(Long greenhouseId, String name, Collection<PlantDTO> plants) {
        this.greenhouseId = greenhouseId;
        this.name = name;
        this.plants = plants;
    }

    public Long getGreenhouseId() {
        return greenhouseId;
    }

    public void setGreenhouseId(Long greenhouseId) {
        this.greenhouseId = greenhouseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<PlantDTO> getPlants() {
        return plants;
    }

    public void setPlants(Collection<PlantDTO> plants) {
        this.plants = plants;
    }
}
