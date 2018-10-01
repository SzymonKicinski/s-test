package sawczuk.AutoCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sawczuk.AutoCenter.exception.InvalidRequestParameterException;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.Car;
import sawczuk.AutoCenter.model.Repair;
import sawczuk.AutoCenter.model.RepairTotalCost;
import sawczuk.AutoCenter.model.dto.RepairDTO;
import sawczuk.AutoCenter.service.CarService;
import sawczuk.AutoCenter.service.ExploitationTypeService;
import sawczuk.AutoCenter.service.RepairService;

import java.util.List;

@Controller
public class RepairController {
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 20;

    private RepairService repairService;
    private CarService carService;
    private ExploitationTypeService exploitationTypeService;

    @Autowired
    public RepairController(RepairService repairService, CarService carService, ExploitationTypeService exploitationTypeService) {
        this.repairService = repairService;
        this.carService = carService;
        this.exploitationTypeService = exploitationTypeService;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "cars/{carId}/repairs", method = RequestMethod.POST)
    public ResponseEntity<Repair> saveRepair(@PathVariable(value = "carId") Long carId,
                                             @RequestBody RepairDTO repairDTO) throws InvalidRequestParameterException, ResourceNotFoundException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        Car car = carService.findOne(carId);
        if (car == null) {
            throw new ResourceNotFoundException("Car", "carId", carId);
        }
        Repair repair = new Repair();
        if (repairDTO.getDate() != null)
            repair.setDate(repairDTO.getDate());
        repair.setCar(car);
        repair.setMileage(repairDTO.getMileage());
        repair.setDescription(repairDTO.getDescription());
        repair.setExploitationType(exploitationTypeService.findOneByValue(repairDTO.getExploitationType()));
        repair.setCost(repairDTO.getCost());
        repairService.save(repair);
        return new ResponseEntity<>(repair, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "cars/{carId}/repairs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Repair> editRepair(@PathVariable(value = "id") Long id,
                                             @PathVariable(value = "carId") Long carId,
                                             @RequestBody RepairDTO repairDTO) throws InvalidRequestParameterException, ResourceNotFoundException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id, "carId", carId);
        }
        Repair repair = repairService.findByIdAndCarId(id, carId);
        if (repair == null) {
            throw new ResourceNotFoundException("Repair", "id", id, "carId", carId);
        }
        if (repairDTO.getDate() != null)
            repair.setDate(repairDTO.getDate());
        if (repairDTO.getMileage() != null)
            repair.setMileage(repairDTO.getMileage());
        if (!StringUtils.isEmpty(repairDTO.getDescription()))
            repair.setDescription(repairDTO.getDescription());
        if (repairDTO.getExploitationType() != null)
            repair.setExploitationType(exploitationTypeService.findOneByValue(repairDTO.getExploitationType()));
        if (repairDTO.getCost() != null)
            repair.setCost(repairDTO.getCost());
        repairService.save(repair);
        return new ResponseEntity<>(repair, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "cars/{carId}/repairs/{id}", method = RequestMethod.GET)
    public ResponseEntity<Repair> getRepair(@PathVariable(value = "id") Long id,
                                            @PathVariable(value = "carId") Long carId) throws InvalidRequestParameterException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id, "carId", carId);
        }
        return new ResponseEntity<>(repairService.findByIdAndCarId(id, carId), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "cars/{carId}/repairs", method = RequestMethod.GET)
    public ResponseEntity<Page<Repair>> getAllRepairs(
            @PathVariable(value = "carId") Long carId,
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "date", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "id", direction = Sort.Direction.DESC)
            })
            Pageable pageable) throws InvalidRequestParameterException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        return new ResponseEntity<>(repairService.findAllByCarId(carId, pageable), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "cars/{carId}/repairs/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Repair> deleteRepair(@PathVariable(value = "id") Long id,
                                               @PathVariable(value = "carId") Long carId) throws InvalidRequestParameterException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id, "carId", carId);
        }
        repairService.deleteByIdAndCarId(id, carId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "cars/{carId}/repairs/total-cost", method = RequestMethod.GET)
    public ResponseEntity<List<RepairTotalCost>> repairsTotalCostByCarId(@PathVariable(value = "carId") Long carId) throws InvalidRequestParameterException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        return new ResponseEntity<>(repairService.repairsTotalCostByCarId(carId), HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "repairs/total-cost", method = RequestMethod.GET)
    public ResponseEntity<List<RepairTotalCost>> repairsTotalCostByCarApiId(@RequestParam(value = "carApiId") Long carApiId) {
        return new ResponseEntity<>(repairService.repairsTotalCostByCarApiId(carApiId), HttpStatus.OK);
    }
}