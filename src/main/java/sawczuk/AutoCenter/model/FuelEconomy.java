package sawczuk.AutoCenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import sawczuk.AutoCenter.util.NumberUtils;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "fuel_economy")
public class FuelEconomy {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "fuel_economy_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "fuel_economy_id_gen", sequenceName = "fuel_economy_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private LocalDate date;
    @DecimalMin(value = "0.0", message = "The value must be positive")
    @Column(name = "distance_driven")
    private Double distanceDriven;
    @DecimalMin(value = "0.0", message = "The value must be positive")
    @Column(name = "fuel_amount_filled")
    private Double fuelAmountFilled;
    @DecimalMin(value = "0.0", message = "The value must be positive")
    @Column(name = "price_per_litre")
    private Double pricePerLitre;
    @Column(name = "consumption")
    private Double consumption;
    @Column(name = "fill_up_cost")
    private Double fillUpCost;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driving_type")
    private DrivingType drivingType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fuel_type")
    private FuelType fuelType;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_car_id")
    private Car car;

    public FuelEconomy() {
        this.date = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public DrivingType getDrivingType() {
        return drivingType;
    }

    public void setDrivingType(DrivingType drivingType) {
        this.drivingType = drivingType;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Double getDistanceDriven() {
        return distanceDriven;
    }

    public void setDistanceDriven(Double distanceDriven) {
        this.distanceDriven = NumberUtils.truncateDouble(distanceDriven);
    }

    public Double getFuelAmountFilled() {
        return fuelAmountFilled;
    }

    public void setFuelAmountFilled(Double fuelAmountFilled) {
        this.fuelAmountFilled = NumberUtils.truncateDouble(fuelAmountFilled);
    }

    public Double getPricePerLitre() {
        return pricePerLitre;
    }

    public void setPricePerLitre(Double pricePerLitre) {
        this.pricePerLitre = NumberUtils.truncateDouble(pricePerLitre);
    }

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = NumberUtils.truncateDouble(consumption);
    }

    public Double getFillUpCost() {
        return fillUpCost;
    }

    public void setFillUpCost(Double fillUpCost) {
        this.fillUpCost = NumberUtils.truncateDouble(fillUpCost);
    }
}
