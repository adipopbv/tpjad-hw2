package dto;

import java.io.Serializable;

public class PlantDTO implements Serializable {
    private Long plantId = 1L;
    private String name;
    private int height;
    private Long greenhouseId;

    public PlantDTO() {
    }

    public PlantDTO(Long plantId, String name, int height, Long greenhouseId) {
        this.plantId = plantId;
        this.name = name;
        this.height = height;
        this.greenhouseId = greenhouseId;
    }

    public Long getPlantId() {
        return plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Long getGreenhouseId() {
        return greenhouseId;
    }

    public void setGreenhouseId(Long greenhouseId) {
        this.greenhouseId = greenhouseId;
    }

}
