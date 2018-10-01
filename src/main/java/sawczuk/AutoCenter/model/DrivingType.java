package sawczuk.AutoCenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "driving_type")
public class DrivingType {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "driving_type_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "driving_type_id_gen", sequenceName = "driving_type_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @NotNull
    @Min(value = 0, message = "The value must be positive")
    @Column(name = "value")
    private Integer value;
    @NotNull
    @Column(name = "driving_type")
    private String drivingType;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "drivingType")
    private List<FuelEconomy> fuelEconomyList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(DrivingTypeEnum drivingType) {
        this.value = drivingType.getValue();
    }

    public String getDrivingType() {
        return drivingType;
    }

    public void setDrivingType(DrivingTypeEnum drivingType) {
        this.drivingType = drivingType.name();
    }

    public List<FuelEconomy> getFuelEconomyList() {
        return fuelEconomyList;
    }

    public void setFuelEconomyList(List<FuelEconomy> fuelEconomyList) {
        this.fuelEconomyList = fuelEconomyList;
    }


    public enum DrivingTypeEnum {
        City(1),
        Highway(2),
        Mixed(3);

        private final int value;
        private static Map map = new HashMap<>();

        DrivingTypeEnum(int value) {
            this.value = value;
        }

        static {
            for (DrivingTypeEnum drivingType : DrivingTypeEnum.values()) {
                map.put(drivingType.value, drivingType);
            }
        }

        public static DrivingTypeEnum valueOf(int drivingType) {
            return (DrivingTypeEnum) map.get(drivingType);
        }

        public int getValue() {
            return value;
        }
    }
}
