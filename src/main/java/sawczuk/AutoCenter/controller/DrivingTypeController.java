package sawczuk.AutoCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.service.DrivingTypeService;

@Controller
public class DrivingTypeController {

    private DrivingTypeService drivingTypeService;

    @Autowired
    public DrivingTypeController(DrivingTypeService drivingTypeService) {
        this.drivingTypeService = drivingTypeService;
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/driving-types", method = RequestMethod.GET)
    public ResponseEntity<?> getDrivingType(@RequestParam(value = "value", required = false) Integer value,
                                            @RequestParam(value = "driving_type", required = false) String drivingType) throws ResourceNotFoundException {
        if (value != null)
            return new ResponseEntity<>(drivingTypeService.findOneByValue(value), HttpStatus.OK);
        else if (!StringUtils.isEmpty(drivingType))
            return new ResponseEntity<>(drivingTypeService.findOneByDrivingTypeIgnoreCase(drivingType), HttpStatus.OK);
        else
            return new ResponseEntity<>(drivingTypeService.findAll(), HttpStatus.OK);
    }
}
