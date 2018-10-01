package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.model.UserDetail;

public interface UserDetailService {
    void save(UserDetail userDetail);

    UserDetail findOneByUserId(Long userId);

    UserDetail findOneByUserUsernameIgnoreCase(String username);
}
