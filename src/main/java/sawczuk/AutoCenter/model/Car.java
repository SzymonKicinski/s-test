package sawczuk.AutoCenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "user_car")
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "user_car_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_car_id_gen", sequenceName = "user_car_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @NotNull
    @Column(name = "car_api_id")
    private Long carApiId;
    @NotNull
    @Column(name = "car_name")
    private String carName;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "car")
    private List<FuelEconomy> fuelEconomies;
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "car")
    private List<Repair> repairs;
//    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "car")
    private CarDetail carDetail;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCarApiId() {
        return carApiId;
    }

    public void setCarApiId(Long carApiId) {
        this.carApiId = carApiId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public List<FuelEconomy> getFuelEconomies() {
        return fuelEconomies;
    }

    public void setFuelEconomies(List<FuelEconomy> fuelEconomies) {
        this.fuelEconomies = fuelEconomies;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

    public CarDetail getCarDetail() {
        return carDetail;
    }

    public void setCarDetail(CarDetail carDetail) {
        this.carDetail = carDetail;
    }
}
