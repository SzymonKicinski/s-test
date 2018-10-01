package sawczuk.AutoCenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.model.Repair;
import sawczuk.AutoCenter.model.RepairTotalCost;
import sawczuk.AutoCenter.repository.RepairRepository;
import sawczuk.AutoCenter.service.RepairService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RepairServiceImpl implements RepairService {

    private RepairRepository repairRepository;

    @Autowired
    public RepairServiceImpl(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    @Override
    public void save(Repair repair) {
        repairRepository.save(repair);
    }

    @Override
    public void deleteByIdAndCarId(Long id, Long carId) {
        repairRepository.deleteByIdAndCarId(id, carId);
    }

    @Override
    public Repair findByIdAndCarId(Long id, Long carId) {
        return repairRepository.findByIdAndCarId(id, carId);
    }

    @Override
    public List<Repair> findAllByCarId(Long carId) {
        return repairRepository.findAllByCarIdOrderByDateDesc(carId);
    }

    @Override
    public Page<Repair> findAllByCarId(Long carId, Pageable pageable) {
        return repairRepository.findAllByCarId(carId, pageable);
    }

    @Override
    public List<RepairTotalCost> repairsTotalCostByCarId(Long carId) {
        return repairRepository.repairsTotalCostByCarId(carId);
    }

    @Override
    public List<RepairTotalCost> repairsTotalCostByCarApiId(Long carApiId) {
        Map<Integer, Double> avgsGroupedByExploitationType = repairRepository.repairsTotalCostByCarApiId(carApiId).stream()
                .collect(Collectors.groupingBy(RepairTotalCost::getExploitationTypeValue, Collectors.averagingDouble(RepairTotalCost::getTotalCost)));
        List<RepairTotalCost> avgTotalCosts = avgsGroupedByExploitationType.entrySet().stream()
                .map(entry -> new RepairTotalCost(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return avgTotalCosts;
    }
}