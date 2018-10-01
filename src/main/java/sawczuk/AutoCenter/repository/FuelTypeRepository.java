package sawczuk.AutoCenter.repository;

import org.springframework.data.repository.CrudRepository;
import sawczuk.AutoCenter.model.FuelType;

import java.util.List;

public interface FuelTypeRepository extends CrudRepository<FuelType, Long> {
    FuelType findOneByFuelTypeIgnoreCase(String fuelType);

    FuelType findOneByValue(Integer value);

    List<FuelType> findAll();
}

