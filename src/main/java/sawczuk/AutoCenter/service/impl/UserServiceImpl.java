package sawczuk.AutoCenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sawczuk.AutoCenter.exception.PasswordException;
import sawczuk.AutoCenter.model.User;
import sawczuk.AutoCenter.model.dto.UserDTO;
import sawczuk.AutoCenter.repository.UserRepository;
import sawczuk.AutoCenter.service.RoleService;
import sawczuk.AutoCenter.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleService roleService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.isRoleAdmin())
            user.setRoles(new HashSet<>(Arrays.asList(roleService.findByNameIgnoreCase("user"), roleService.findByNameIgnoreCase("admin"))));
        else
            user.setRoles(new HashSet<>(Arrays.asList(roleService.findByNameIgnoreCase("user"))));
        user.setActive(1);
        userRepository.save(user);
    }

    @Override
    public void update(UserDTO userDTO, User user) throws PasswordException {
        if (!StringUtils.isEmpty(userDTO.getUsername()))
            user.setUsername(userDTO.getUsername());
        if (!StringUtils.isEmpty(userDTO.getEmail()))
            user.setEmail(userDTO.getEmail());
        if (!StringUtils.isEmpty(userDTO.getPassword()))
            if (!StringUtils.isEmpty(userDTO.getCurrentPassword()))
                if (passwordEncoder.matches(userDTO.getCurrentPassword(), user.getPassword()))
                    if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword()))
                        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                    else
                        throw new PasswordException("New password is same as current password.");
                else
                    throw new PasswordException("Current password provided is incorrect.");
            else
                throw new PasswordException("Current password not provided.");
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByUsernameIgnoreCase(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}