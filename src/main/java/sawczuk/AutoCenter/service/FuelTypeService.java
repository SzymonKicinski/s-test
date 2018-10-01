package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.FuelType;

import java.util.List;

public interface FuelTypeService {
    FuelType findOneByFuelTypeIgnoreCase(String fuelTypeName) throws ResourceNotFoundException;

    FuelType findOneByValue(Integer value) throws ResourceNotFoundException;

    List<FuelType> findAll() throws ResourceNotFoundException;
}
