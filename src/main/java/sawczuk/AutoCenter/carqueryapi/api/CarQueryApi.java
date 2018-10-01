package sawczuk.AutoCenter.carqueryapi.api;

import sawczuk.AutoCenter.carqueryapi.model.Make;
import sawczuk.AutoCenter.carqueryapi.model.Model;
import sawczuk.AutoCenter.carqueryapi.model.Trim;
import sawczuk.AutoCenter.carqueryapi.model.Year;

import java.util.List;

public interface CarQueryApi {
    Year getYears();

    List<Make> getMakes(Integer year);

    List<Model> getModels(String make, Integer year);

    List<Trim> getTrims(String make, String model, Integer year);

    Trim getTrim(Long id);
}
