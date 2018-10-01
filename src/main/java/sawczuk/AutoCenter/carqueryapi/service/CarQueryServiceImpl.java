package sawczuk.AutoCenter.carqueryapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sawczuk.AutoCenter.carqueryapi.api.CarQueryApi;
import sawczuk.AutoCenter.carqueryapi.model.Make;
import sawczuk.AutoCenter.carqueryapi.model.Model;
import sawczuk.AutoCenter.carqueryapi.model.Trim;
import sawczuk.AutoCenter.carqueryapi.model.Year;
import sawczuk.AutoCenter.exception.InvalidRequestParameterException;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class CarQueryServiceImpl implements CarQueryService {

    private CarQueryApi carQueryApi;

    @Autowired
    public CarQueryServiceImpl(CarQueryApi carQueryApi) {
        this.carQueryApi = carQueryApi;
    }

    @Override
    public Year getYears() throws ResourceNotFoundException {
        Year year = carQueryApi.getYears();
        if (year == null) {
            throw new ResourceNotFoundException("Year");
        }
        return year;
    }

    @Override
    public List<Make> getMakes(Integer year) throws ResourceNotFoundException {
        List<Make> makes = carQueryApi.getMakes(year);
        if (makes == null || makes.isEmpty()) {
            throw new ResourceNotFoundException("Make list", "year", year);
        }
        return makes;
    }

    @Override
    public List<Model> getModels(String make, Integer year) throws ResourceNotFoundException, InvalidRequestParameterException {
        if (StringUtils.isEmpty(make)) {
            throw new InvalidRequestParameterException("make", make);
        }
        List<Model> models = carQueryApi.getModels(make, year);
        if (models == null || models.isEmpty()) {
            throw new ResourceNotFoundException("Model list", "make", make, "year", year);
        }
        return models;
    }

    @Override
    public List<Trim> getTrims(String make, String model, Integer year) throws ResourceNotFoundException {
        List<Trim> trims = carQueryApi.getTrims(make, model, year);
        if (trims == null || trims.isEmpty()) {
            throw new ResourceNotFoundException("Trim list", "make", make, "model", model, "year", year);
        }
        return trims;
    }


    @Override
    public Trim getTrim(Long id) throws ResourceNotFoundException, InvalidRequestParameterException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id);
        }
        Trim trim = carQueryApi.getTrim(id);
        if (trim.getModelId() == null || trim.getModelId().doubleValue() != id) {
            throw new ResourceNotFoundException("Trim", "id", id);
        }
        return trim;
    }

}