package sawczuk.AutoCenter.carqueryapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sawczuk.AutoCenter.carqueryapi.model.Make;
import sawczuk.AutoCenter.carqueryapi.model.Model;
import sawczuk.AutoCenter.carqueryapi.model.Trim;
import sawczuk.AutoCenter.carqueryapi.model.Year;
import sawczuk.AutoCenter.carqueryapi.service.CarQueryService;
import sawczuk.AutoCenter.exception.InvalidRequestParameterException;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/car-query-api")
public class CarQueryController {

    private CarQueryService carQueryService;

    @Autowired
    public CarQueryController(CarQueryService carQueryService) {
        this.carQueryService = carQueryService;
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/years", method = RequestMethod.GET)
    public ResponseEntity<Year> getYears() throws ResourceNotFoundException {
        Year year = carQueryService.getYears();
        return new ResponseEntity<>(year, HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/makes", method = RequestMethod.GET)
    public ResponseEntity<List<Make>> getMakes(@RequestParam(value = "year", required = false) Integer year) throws ResourceNotFoundException {
        List<Make> makes = carQueryService.getMakes(year);
        return new ResponseEntity<>(makes, HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/models", method = RequestMethod.GET)
    public ResponseEntity<List<Model>> getModelsByYearAndMake(
            @RequestParam(value = "make") String make,
            @RequestParam(value = "year", required = false) Integer year) throws ResourceNotFoundException, InvalidRequestParameterException {
        List<Model> models = carQueryService.getModels(make, year);
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/trims", method = RequestMethod.GET)
    public ResponseEntity<List<Trim>> getTrimsByYearAndMakeAndModel(
            @RequestParam(value = "make", required = false) String make,
            @RequestParam(value = "model", required = false) String model,
            @RequestParam(value = "year", required = false) Integer year) throws ResourceNotFoundException {
        List<Trim> trims = carQueryService.getTrims(make, model, year);
        return new ResponseEntity<>(trims, HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/trims/{id}", method = RequestMethod.GET)
    public ResponseEntity<Trim> getTrim(@PathVariable("id") Long id) throws ResourceNotFoundException, InvalidRequestParameterException {
        Trim model = carQueryService.getTrim(id);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

}