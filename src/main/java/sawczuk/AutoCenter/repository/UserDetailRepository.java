package sawczuk.AutoCenter.repository;

import org.springframework.data.repository.CrudRepository;
import sawczuk.AutoCenter.model.UserDetail;

public interface UserDetailRepository extends CrudRepository<UserDetail, Long> {
    UserDetail findOneByUserId(Long userId);

    UserDetail findOneByUserUsernameIgnoreCase(String username);
}
