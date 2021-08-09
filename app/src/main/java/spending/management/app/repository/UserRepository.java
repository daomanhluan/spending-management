package spending.management.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spending.management.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByUsername(String username);
    
    boolean existsUserByUsername(String username);
    
    
}
