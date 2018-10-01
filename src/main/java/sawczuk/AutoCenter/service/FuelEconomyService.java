package sawczuk.AutoCenter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sawczuk.AutoCenter.model.FuelEconomy;
import sawczuk.AutoCenter.model.FuelEconomyAverage;

import java.util.List;

public interface FuelEconomyService {
    void save(FuelEconomy fuelEconomy);

    void deleteByIdAndCarId(Long id, Long carId);

    FuelEconomy findByIdAndCarId(Long id, Long carId);

    List<FuelEconomy> findAllByCarId(Long carId);

    Page<FuelEconomy> findAllByCarId(Long carId, Pageable pageable);

    List<FuelEconomyAverage> fuelEconomyAveragesByCarId(Long carId);

    List<FuelEconomyAverage> fuelEconomyAveragesByCarApiId(Long carApiId);
}