package sawczuk.AutoCenter.repository;

import org.springframework.data.repository.CrudRepository;
import sawczuk.AutoCenter.model.DrivingType;

import java.util.List;

public interface DrivingTypeRepository extends CrudRepository<DrivingType, Long> {
    DrivingType findOneByDrivingTypeIgnoreCase(String drivingType);

    DrivingType findOneByValue(Integer value);

    List<DrivingType> findAll();
}
