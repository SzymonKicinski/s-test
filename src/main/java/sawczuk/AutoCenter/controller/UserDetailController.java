package sawczuk.AutoCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.User;
import sawczuk.AutoCenter.model.UserDetail;
import sawczuk.AutoCenter.model.dto.UserDetailDTO;
import sawczuk.AutoCenter.service.UserDetailService;
import sawczuk.AutoCenter.service.UserService;
import sawczuk.AutoCenter.util.UserUtils;

@Controller
public class UserDetailController {

    private UserDetailService userDetailService;
    private UserService userService;

    @Autowired
    public UserDetailController(UserDetailService userDetailService, UserService userService) {
        this.userDetailService = userDetailService;
        this.userService = userService;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/user-details", method = RequestMethod.POST)
    public ResponseEntity<UserDetail> saveUserDetail(@RequestBody UserDetailDTO userDetailDTO) throws ResourceNotFoundException {
        User user = userService.findByUsernameIgnoreCase(UserUtils.findLoggedInUsername());
        if (user == null) {
            throw new ResourceNotFoundException("User", "username", UserUtils.findLoggedInUsername());
        }
        UserDetail userDetail = new UserDetail();
        userDetail.setFirstname(userDetailDTO.getFirstname());
        userDetail.setSurname(userDetailDTO.getSurname());
        userDetail.setAddress(userDetailDTO.getAddress());
        userDetail.setCity(userDetailDTO.getCity());
        userDetail.setZipCode(userDetailDTO.getZipCode());
        userDetail.setPhoneNumber(userDetailDTO.getPhoneNumber());
        userDetail.setUser(user);
        userDetailService.save(userDetail);
        return new ResponseEntity<>(userDetail, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/user-details", method = RequestMethod.PUT)
    public ResponseEntity<UserDetail> editUserDetail(@RequestBody UserDetailDTO userDetailDTO) throws ResourceNotFoundException {
        UserDetail userDetail = userDetailService.findOneByUserUsernameIgnoreCase(UserUtils.findLoggedInUsername());
        if (userDetail == null) {
            throw new ResourceNotFoundException("User detail", "username", UserUtils.findLoggedInUsername());
        }
        userDetail.setFirstname(userDetailDTO.getFirstname());
        userDetail.setSurname(userDetailDTO.getSurname());
        userDetail.setAddress(userDetailDTO.getAddress());
        userDetail.setCity(userDetailDTO.getCity());
        userDetail.setZipCode(userDetailDTO.getZipCode());
        userDetail.setPhoneNumber(userDetailDTO.getPhoneNumber());
        userDetailService.save(userDetail);
        return new ResponseEntity<>(userDetail, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/user-details", method = RequestMethod.GET)
    public ResponseEntity<UserDetail> getUserDetail() throws ResourceNotFoundException {
        Long userId = userService.findByUsernameIgnoreCase(UserUtils.findLoggedInUsername()).getId();
        if (userId == null) {
            throw new ResourceNotFoundException("User ID", "username", UserUtils.findLoggedInUsername());
        }
        return new ResponseEntity<>(userDetailService.findOneByUserId(userId), HttpStatus.OK);
    }

}