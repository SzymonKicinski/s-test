package sawczuk.AutoCenter.carqueryapi.service;

import sawczuk.AutoCenter.carqueryapi.model.Make;
import sawczuk.AutoCenter.carqueryapi.model.Model;
import sawczuk.AutoCenter.carqueryapi.model.Trim;
import sawczuk.AutoCenter.carqueryapi.model.Year;
import sawczuk.AutoCenter.exception.InvalidRequestParameterException;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;

import java.util.List;

public interface CarQueryService {
    Year getYears() throws ResourceNotFoundException;

    List<Make> getMakes(Integer year) throws ResourceNotFoundException;

    List<Model> getModels(String make, Integer year) throws ResourceNotFoundException, InvalidRequestParameterException;

    List<Trim> getTrims(String make, String model, Integer year) throws ResourceNotFoundException;

    Trim getTrim(Long id) throws ResourceNotFoundException, InvalidRequestParameterException;
}
