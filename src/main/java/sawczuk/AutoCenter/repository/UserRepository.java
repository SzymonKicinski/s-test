package sawczuk.AutoCenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import sawczuk.AutoCenter.model.User;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsernameIgnoreCase(String username);

    List<User> findAll();

    Page<User> findAll(Pageable pageable);
}
