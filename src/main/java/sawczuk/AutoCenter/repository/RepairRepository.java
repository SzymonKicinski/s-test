package sawczuk.AutoCenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sawczuk.AutoCenter.model.Repair;
import sawczuk.AutoCenter.model.RepairTotalCost;

import java.util.List;

public interface RepairRepository extends PagingAndSortingRepository<Repair, Long> {

    @Transactional
    void deleteByIdAndCarId(Long id, Long carId);

    Repair findByIdAndCarId(Long id, Long carId);

    List<Repair> findAllByCarIdOrderByDateDesc(Long carId);

    Page<Repair> findAllByCarId(Long carId, Pageable pageable);

    @Query("SELECT new sawczuk.AutoCenter.model.RepairTotalCost(e.value, SUM(r.cost)) " +
            "FROM Repair r " +
            "JOIN r.car c " +
            "JOIN r.exploitationType e " +
            "WHERE c.id = :carId " +
            "GROUP BY e.value")
    List<RepairTotalCost> repairsTotalCostByCarId(@Param("carId") Long carId);

    @Query("SELECT new sawczuk.AutoCenter.model.RepairTotalCost(e.value, SUM(r.cost)) " +
            "FROM Repair r " +
            "JOIN r.car c " +
            "JOIN r.exploitationType e " +
            "WHERE c.carApiId = :carApiId " +
            "GROUP BY e.value, c.user")
    List<RepairTotalCost> repairsTotalCostByCarApiId(@Param("carApiId") Long carApiId);
}