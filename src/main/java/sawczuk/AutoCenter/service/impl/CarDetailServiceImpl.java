package sawczuk.AutoCenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sawczuk.AutoCenter.model.CarDetail;
import sawczuk.AutoCenter.repository.CarDetailRepository;
import sawczuk.AutoCenter.service.CarDetailService;

@Service
public class CarDetailServiceImpl implements CarDetailService {

    private CarDetailRepository carDetailRepository;

    @Autowired
    public CarDetailServiceImpl(CarDetailRepository carDetailRepository) {
        this.carDetailRepository = carDetailRepository;
    }

    @Override
    public void save(CarDetail carDetail) {
        if (!StringUtils.isEmpty(carDetail.getVin()))
            carDetail.setVin(carDetail.getVin().toUpperCase());
        if (!StringUtils.isEmpty(carDetail.getLicencePlateNumber()))
            carDetail.setLicencePlateNumber(carDetail.getLicencePlateNumber().toUpperCase());
        carDetailRepository.save(carDetail);
    }

    @Override
    public CarDetail findOneByCarId(Long carId) {
        return carDetailRepository.findOneByCarId(carId);
    }

}
