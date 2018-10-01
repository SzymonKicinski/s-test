package sawczuk.AutoCenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.DrivingType;
import sawczuk.AutoCenter.repository.DrivingTypeRepository;
import sawczuk.AutoCenter.service.DrivingTypeService;

import java.util.List;

@Service
public class DrivingTypeServiceImpl implements DrivingTypeService {

    private DrivingTypeRepository drivingTypeRepository;

    @Autowired
    public DrivingTypeServiceImpl(DrivingTypeRepository drivingTypeRepository) {
        this.drivingTypeRepository = drivingTypeRepository;
    }

    @Override
    public DrivingType findOneByDrivingTypeIgnoreCase(String drivingTypeName) throws ResourceNotFoundException {
        DrivingType drivingType = drivingTypeRepository.findOneByDrivingTypeIgnoreCase(drivingTypeName);
        if (drivingType == null)
            throw new ResourceNotFoundException("Driving type", "drivingTypeName", drivingTypeName);
        return drivingType;
    }

    @Override
    public DrivingType findOneByValue(Integer value) throws ResourceNotFoundException {
        DrivingType drivingType = drivingTypeRepository.findOneByValue(value);
        if (drivingType == null)
            throw new ResourceNotFoundException("Driving type", "value", value);
        return drivingType;
    }

    @Override
    public List<DrivingType> findAll() throws ResourceNotFoundException {
        List<DrivingType> drivingTypeList = drivingTypeRepository.findAll();
        if (drivingTypeList == null || drivingTypeList.isEmpty())
            throw new ResourceNotFoundException("Driving type list");
        return drivingTypeList;
    }
}
