package sawczuk.AutoCenter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sawczuk.AutoCenter.model.Repair;
import sawczuk.AutoCenter.model.RepairTotalCost;

import java.util.List;

public interface RepairService {
    void save(Repair repair);

    void deleteByIdAndCarId(Long id, Long carId);

    Repair findByIdAndCarId(Long id, Long carId);

    List<Repair> findAllByCarId(Long carId);

    Page<Repair> findAllByCarId(Long carId, Pageable pageable);

    List<RepairTotalCost> repairsTotalCostByCarId(Long carId);

    List<RepairTotalCost> repairsTotalCostByCarApiId(Long carApiId);
}