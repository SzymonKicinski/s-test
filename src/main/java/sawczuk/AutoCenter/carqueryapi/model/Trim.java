package sawczuk.AutoCenter.carqueryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;
import sawczuk.AutoCenter.util.NumberUtils;

public class Trim {
    @JsonProperty("model_id")
    private Long modelId;
    @JsonProperty("model_make_id")
    private String makeId;
    @JsonProperty("make_display")
    private String make;
    @JsonProperty("model_name")
    private String model;
    @JsonProperty("model_trim")
    private String trim;
    @JsonProperty("make_country")
    private String country;
    @JsonProperty("model_year")
    private Integer year;
    @JsonProperty("model_body")
    private String body;
    @JsonProperty("model_engine_position")
    private String enginePosition;
    @JsonProperty("model_engine_cc")
    private Integer engineCc;
    @JsonProperty("model_engine_cyl")
    private Integer engineCyl;
    @JsonProperty("model_engine_type")
    private String engineType;
    @JsonProperty("model_engine_valves_per_cyl")
    private Integer engineValvesPerCyl;
    @JsonProperty("model_engine_power_ps")
    private Integer enginePowerPs;
    @JsonProperty("model_engine_power_rpm")
    private Integer enginePowerRpm;
    @JsonProperty("model_engine_torque_nm")
    private Integer engineTorqueNm;
    @JsonProperty("model_engine_torque_rpm")
    private Integer engineTorqueRpm;
    @JsonProperty("model_engine_bore_mm")
    private Double engineBoreMm;
    @JsonProperty("model_engine_stroke_mm")
    private Double engineStrokeMm;
    @JsonProperty("model_engine_compression")
    private String engineCompression;
    @JsonProperty("model_engine_fuel")
    private String engineFuel;
    @JsonProperty("model_top_speed_kph")
    private Integer topSpeedKph;
    @JsonProperty("model_0_to_100_kph")
    private Double nullTo100Kph;
    @JsonProperty("model_drive")
    private String drive;
    @JsonProperty("model_transmission_type")
    private String transmissionType;
    @JsonProperty("model_seats")
    private Integer seats;
    @JsonProperty("model_doors")
    private Integer doors;
    @JsonProperty("model_weight_kg")
    private Integer weightKg;
    @JsonProperty("model_length_mm")
    private Integer lengthMm;
    @JsonProperty("model_width_mm")
    private Integer widthMm;
    @JsonProperty("model_height_mm")
    private Integer heightMm;
    @JsonProperty("model_wheelbase_mm")
    private Integer wheelbaseMm;
    @JsonProperty("model_lkm_hwy")
    private Double lPerKmHighway;
    @JsonProperty("model_lkm_mixed")
    private Double lPerKmMixed;
    @JsonProperty("model_lkm_city")
    private Double lPerKmCity;
    @JsonProperty("model_fuel_cap_l")
    private Integer fuelCapL;

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getMakeId() {
        return makeId;
    }

    public void setMakeId(String makeId) {
        this.makeId = makeId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTrim() {
        if (StringUtils.isEmpty(trim)) {
            String trimName = "";
            trimName = trimName.concat(NumberUtils.truncateEngineCc(engineCc).toString() + "L");
            if (body != null)
                trimName = trimName.concat(" " + body);
            if (enginePowerPs != null)
                trimName = trimName.concat(", " + NumberUtils.psToHpConverter(enginePowerPs) + "HP");
            if (transmissionType != null)
                trimName = trimName.concat(", " + transmissionType + " transmission");
            return trimName;
        } else
            return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEnginePosition() {
        return enginePosition;
    }

    public void setEnginePosition(String enginePosition) {
        this.enginePosition = enginePosition;
    }

    public Integer getEngineCc() {
        return engineCc;
    }

    public void setEngineCc(Integer engineCc) {
        this.engineCc = engineCc;
    }

    public Integer getEngineCyl() {
        return engineCyl;
    }

    public void setEngineCyl(Integer engineCyl) {
        this.engineCyl = engineCyl;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public Integer getEngineValvesPerCyl() {
        return engineValvesPerCyl;
    }

    public void setEngineValvesPerCyl(Integer engineValvesPerCyl) {
        this.engineValvesPerCyl = engineValvesPerCyl;
    }

    public Integer getEnginePowerPs() {
        return enginePowerPs;
    }

    public void setEnginePowerPs(Integer enginePowerPs) {
        this.enginePowerPs = enginePowerPs;
    }

    public Integer getEnginePowerRpm() {
        return enginePowerRpm;
    }

    public void setEnginePowerRpm(Integer enginePowerRpm) {
        this.enginePowerRpm = enginePowerRpm;
    }

    public Integer getEngineTorqueNm() {
        return engineTorqueNm;
    }

    public void setEngineTorqueNm(Integer engineTorqueNm) {
        this.engineTorqueNm = engineTorqueNm;
    }

    public Integer getEngineTorqueRpm() {
        return engineTorqueRpm;
    }

    public void setEngineTorqueRpm(Integer engineTorqueRpm) {
        this.engineTorqueRpm = engineTorqueRpm;
    }

    public Double getEngineBoreMm() {
        return engineBoreMm;
    }

    public void setEngineBoreMm(Double engineBoreMm) {
        this.engineBoreMm = engineBoreMm;
    }

    public Double getEngineStrokeMm() {
        return engineStrokeMm;
    }

    public void setEngineStrokeMm(Double engineStrokeMm) {
        this.engineStrokeMm = engineStrokeMm;
    }

    public String getEngineCompression() {
        return engineCompression;
    }

    public void setEngineCompression(String engineCompression) {
        this.engineCompression = engineCompression;
    }

    public String getEngineFuel() {
        return engineFuel;
    }

    public void setEngineFuel(String engineFuel) {
        this.engineFuel = engineFuel;
    }

    public Integer getTopSpeedKph() {
        return topSpeedKph;
    }

    public void setTopSpeedKph(Integer topSpeedKph) {
        this.topSpeedKph = topSpeedKph;
    }

    public Double getNullTo100Kph() {
        return nullTo100Kph;
    }

    public void setNullTo100Kph(Double nullTo100Kph) {
        this.nullTo100Kph = nullTo100Kph;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public Integer getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(Integer weightKg) {
        this.weightKg = weightKg;
    }

    public Integer getLengthMm() {
        return lengthMm;
    }

    public void setLengthMm(Integer lengthMm) {
        this.lengthMm = lengthMm;
    }

    public Integer getWidthMm() {
        return widthMm;
    }

    public void setWidthMm(Integer widthMm) {
        this.widthMm = widthMm;
    }

    public Integer getHeightMm() {
        return heightMm;
    }

    public void setHeightMm(Integer heightMm) {
        this.heightMm = heightMm;
    }

    public Integer getWheelbaseMm() {
        return wheelbaseMm;
    }

    public void setWheelbaseMm(Integer wheelbaseMm) {
        this.wheelbaseMm = wheelbaseMm;
    }

    public Double getlPerKmHighway() {
        return lPerKmHighway;
    }

    public void setlPerKmHighway(Double lPerKmHighway) {
        this.lPerKmHighway = lPerKmHighway;
    }

    public Double getlPerKmMixed() {
        return lPerKmMixed;
    }

    public void setlPerKmMixed(Double lPerKmMixed) {
        this.lPerKmMixed = lPerKmMixed;
    }

    public Double getlPerKmCity() {
        return lPerKmCity;
    }

    public void setlPerKmCity(Double lPerKmCity) {
        this.lPerKmCity = lPerKmCity;
    }

    public Integer getFuelCapL() {
        return fuelCapL;
    }

    public void setFuelCapL(Integer fuelCapL) {
        this.fuelCapL = fuelCapL;
    }
}