package spending.management.app.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spending.management.app.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	Set<Role> findByIdIn(List<Integer> ids);
}
