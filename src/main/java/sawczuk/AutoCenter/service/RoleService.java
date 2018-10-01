package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.model.Role;

public interface RoleService {
    Role findByNameIgnoreCase(String name);
}
