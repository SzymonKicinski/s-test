package sawczuk.AutoCenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sawczuk.AutoCenter.model.FuelEconomy;
import sawczuk.AutoCenter.model.FuelEconomyAverage;

import java.util.List;

public interface FuelEconomyRepository extends PagingAndSortingRepository<FuelEconomy, Long> {

    @Transactional
    void deleteByIdAndCarId(Long id, Long carId);

    FuelEconomy findByIdAndCarId(Long id, Long carId);

    List<FuelEconomy> findAllByCarIdOrderByDateDesc(Long carId);

    Page<FuelEconomy> findAllByCarId(Long carId, Pageable pageable);

    @Query("SELECT new sawczuk.AutoCenter.model.FuelEconomyAverage(d.value, f.value, AVG(e.consumption)) " +
            "FROM FuelEconomy e " +
            "JOIN e.car c " +
            "JOIN e.drivingType d " +
            "JOIN e.fuelType f " +
            "WHERE c.id = :carId " +
            "GROUP BY d.value, f.value")
    List<FuelEconomyAverage> fuelEconomyAveragesByCarId(@Param("carId") Long carId);

    @Query("SELECT new sawczuk.AutoCenter.model.FuelEconomyAverage(d.value, f.value, AVG(e.consumption)) " +
            "FROM FuelEconomy e " +
            "JOIN e.car c " +
            "JOIN e.drivingType d " +
            "JOIN e.fuelType f " +
            "WHERE c.carApiId = :carApiId " +
            "GROUP BY d.value, f.value")
    List<FuelEconomyAverage> fuelEconomyAveragesByCarApiId(@Param("carApiId") Long carApiId);

}
