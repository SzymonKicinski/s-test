package sawczuk.AutoCenter.model.dto;

import java.time.LocalDate;

public class FuelEconomyDTO {
    private LocalDate date;
    private Integer drivingType;
    private Integer fuelType;
    private Double distanceDriven;
    private Double fuelAmountFilled;
    private Double pricePerLitre;

    public LocalDate getDate() {
        return date;
    }

    public Integer getDrivingType() {
        return drivingType;
    }

    public Integer getFuelType() {
        return fuelType;
    }

    public Double getDistanceDriven() {
        return distanceDriven;
    }

    public Double getFuelAmountFilled() {
        return fuelAmountFilled;
    }

    public Double getPricePerLitre() {
        return pricePerLitre;
    }
}
