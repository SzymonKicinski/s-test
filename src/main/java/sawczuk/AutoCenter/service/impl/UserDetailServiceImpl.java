package sawczuk.AutoCenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.model.UserDetail;
import sawczuk.AutoCenter.repository.UserDetailRepository;
import sawczuk.AutoCenter.service.UserDetailService;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    private UserDetailRepository userDetailRepository;

    @Autowired
    public UserDetailServiceImpl(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    @Override
    public void save(UserDetail userDetail) {
        userDetailRepository.save(userDetail);
    }

    @Override
    public UserDetail findOneByUserId(Long userId) {
        return userDetailRepository.findOneByUserId(userId);
    }

    @Override
    public UserDetail findOneByUserUsernameIgnoreCase(String username) {
        return userDetailRepository.findOneByUserUsernameIgnoreCase(username);
    }
}
