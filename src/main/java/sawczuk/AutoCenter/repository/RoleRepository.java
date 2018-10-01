package sawczuk.AutoCenter.repository;

import org.springframework.data.repository.CrudRepository;
import sawczuk.AutoCenter.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByNameIgnoreCase(String name);
}
