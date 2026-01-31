package ch.martinelli.demo.jspecify.safe;

import org.jspecify.annotations.Nullable;
import org.springframework.data.repository.ListCrudRepository;

public interface EmployeeRepository extends ListCrudRepository<Employee, Long> {

    @Nullable Employee findByName(String name);
}
