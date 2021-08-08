package spending.management.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spending.management.app.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	
}
