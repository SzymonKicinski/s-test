package sawczuk.AutoCenter.model;

import sawczuk.AutoCenter.util.NumberUtils;

public class FuelEconomyAverage {
    Integer drivingTypeValue;
    Integer fuelTypeValue;
    Double economyAverage;

    public Integer getDrivingTypeValue() {
        return drivingTypeValue;
    }

    public void setDrivingTypeValue(Integer drivingTypeValue) {
        this.drivingTypeValue = drivingTypeValue;
    }

    public Integer getFuelTypeValue() {
        return fuelTypeValue;
    }

    public void setFuelTypeValue(Integer fuelTypeValue) {
        this.fuelTypeValue = fuelTypeValue;
    }

    public Double getEconomyAverage() {
        return NumberUtils.truncateDouble(economyAverage);
    }

    public void setEconomyAverage(Double economyAverage) {
        this.economyAverage = NumberUtils.truncateDouble(economyAverage);
    }


    public FuelEconomyAverage() {
    }

    public FuelEconomyAverage(Integer drivingTypeValue, Integer fuelTypeValue, Double economyAverage) {
        this.drivingTypeValue = drivingTypeValue;
        this.fuelTypeValue = fuelTypeValue;
        this.economyAverage = NumberUtils.truncateDouble(economyAverage);
    }
}
