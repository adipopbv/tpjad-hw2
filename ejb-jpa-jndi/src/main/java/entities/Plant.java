package entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "plants")
public class Plant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plantId = -1L;
    private String name;
    private int height;

    @ManyToOne
    @JoinColumn(name = "greenhouseId")
    private Greenhouse greenhouse;

    public Plant() {
    }

    public Plant(Long musicianId, String name, int height) {
        this.plantId = musicianId;
        this.name = name;
        this.height = height;
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

    public Greenhouse getGreenhouse() {
        return greenhouse;
    }

    public void setGreenhouse(Greenhouse person) {
        this.greenhouse = person;
    }

}
