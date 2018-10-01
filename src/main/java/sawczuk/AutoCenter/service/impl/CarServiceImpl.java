package sawczuk.AutoCenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.model.Car;
import sawczuk.AutoCenter.repository.CarRepository;
import sawczuk.AutoCenter.service.CarService;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }

    @Override
    public void delete(Long id) {
        carRepository.delete(id);
    }

    @Override
    public Car findOne(Long id) {
        return carRepository.findOne(id);
    }

    @Override
    public List<Car> findAllByUserId(Long userId) {
        return carRepository.findAllByUserId(userId);
    }

    @Override
    public Page<Car> findAllByUserId(Long userId, Pageable pageable) {
        return carRepository.findAllByUserId(userId, pageable);
    }
}
