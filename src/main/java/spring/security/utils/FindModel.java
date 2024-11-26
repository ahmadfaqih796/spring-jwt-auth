package spring.security.utils;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.security.models.Role;
import spring.security.repository.RoleRepository;

@Component
public class FindModel {

  @Autowired
  private RoleRepository roleRepository;

  public Role getRoleById(Long id) {
    if (id == null) {
      return null;
    }
    Optional<Role> role = roleRepository.findById(id);
    return role.orElseThrow(() -> new IllegalArgumentException("Role not found")
    );
  }
}
