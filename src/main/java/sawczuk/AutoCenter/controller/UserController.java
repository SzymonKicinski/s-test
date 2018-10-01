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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sawczuk.AutoCenter.exception.InvalidRequestParameterException;
import sawczuk.AutoCenter.exception.PasswordException;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.User;
import sawczuk.AutoCenter.model.dto.UserDTO;
import sawczuk.AutoCenter.service.UserService;
import sawczuk.AutoCenter.util.UserUtils;

@Controller
public class UserController {
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 20;

    private UserService userService;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public ResponseEntity<User> createNewAccount(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = {"/users"}, method = RequestMethod.POST)
    public ResponseEntity<User> createNewAdminAccount(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRoleAdmin(true);
        user.setActive(1);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public ResponseEntity<User> updateAccount(@RequestBody UserDTO userDTO) throws PasswordException, ResourceNotFoundException{
        User user = userService.findByUsernameIgnoreCase(UserUtils.findLoggedInUsername());
        if (user == null) {
            throw new ResourceNotFoundException("User", "username", UserUtils.findLoggedInUsername());
        }
        userService.update(userDTO, user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<Page<User>> findAllAccounts(
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE)
            @SortDefault(sort = "username", direction = Sort.Direction.ASC)
            Pageable pageable) {
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> findAccountById(@PathVariable Long id) throws InvalidRequestParameterException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id);
        }
        return new ResponseEntity<>(userService.findOne(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAccount(@PathVariable Long id) throws InvalidRequestParameterException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id);
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}