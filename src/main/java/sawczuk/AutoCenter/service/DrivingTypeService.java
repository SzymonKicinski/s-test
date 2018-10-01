package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.DrivingType;

import java.util.List;

public interface DrivingTypeService {
    DrivingType findOneByDrivingTypeIgnoreCase(String drivingTypeName) throws ResourceNotFoundException;

    DrivingType findOneByValue(Integer value) throws ResourceNotFoundException;

    List<DrivingType> findAll() throws ResourceNotFoundException;
}
