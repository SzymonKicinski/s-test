package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.model.CarDetail;

public interface CarDetailService {
    void save(CarDetail carDetail);

    CarDetail findOneByCarId(Long carId);
}
