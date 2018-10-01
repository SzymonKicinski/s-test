package sawczuk.AutoCenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.FuelType;
import sawczuk.AutoCenter.repository.FuelTypeRepository;
import sawczuk.AutoCenter.service.FuelTypeService;

import java.util.List;

@Service
public class FuelTypeServiceImpl implements FuelTypeService {

    private FuelTypeRepository fuelTypeRepository;

    @Autowired
    public FuelTypeServiceImpl(FuelTypeRepository fuelTypeRepository) {
        this.fuelTypeRepository = fuelTypeRepository;
    }

    @Override
    public FuelType findOneByFuelTypeIgnoreCase(String fuelTypeName) throws ResourceNotFoundException {
        FuelType fuelType = fuelTypeRepository.findOneByFuelTypeIgnoreCase(fuelTypeName);
        if (fuelType == null)
            throw new ResourceNotFoundException("Fuel type", "fuelTypeName", fuelTypeName);
        return fuelType;
    }

    @Override
    public FuelType findOneByValue(Integer value) throws ResourceNotFoundException {
        FuelType fuelType = fuelTypeRepository.findOneByValue(value);
        if (fuelType == null)
            throw new ResourceNotFoundException("Fuel type", "value", value);
        return fuelType;
    }

    @Override
    public List<FuelType> findAll() throws ResourceNotFoundException {
        List<FuelType> fuelTypeList = fuelTypeRepository.findAll();
        if (fuelTypeList == null || fuelTypeList.isEmpty())
            throw new ResourceNotFoundException("Fuel type list");
        return fuelTypeList;
    }
}
