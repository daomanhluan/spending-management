package spending.management.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spending.management.app.model.Function;

@Repository
public interface FunctionRepository extends JpaRepository<Function, Integer>{

}
