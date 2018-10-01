package sawczuk.AutoCenter.repository;

import org.springframework.data.repository.CrudRepository;
import sawczuk.AutoCenter.model.CarDetail;

public interface CarDetailRepository extends CrudRepository<CarDetail, Long> {
    CarDetail findOneByCarId(Long carId);
}
