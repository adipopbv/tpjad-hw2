package entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "greenhouses")
public class Greenhouse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long greenhouseId = 0L;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Plant> plants = new ArrayList<>();

    public Greenhouse(Long greenhouseId, String name, Collection<Plant> plants) {
        this.greenhouseId = greenhouseId;
        this.name = name;
        this.plants = plants;
    }

    public Greenhouse() {
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

    public Collection<Plant> getReleasedPlants() {
        return plants;
    }

    public void setPlants(Collection<Plant> plants) {
        this.plants = plants;
    }

}
