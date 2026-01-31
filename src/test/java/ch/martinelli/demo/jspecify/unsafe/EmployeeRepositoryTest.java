package ch.martinelli.demo.jspecify.unsafe;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmployeeRepositoryTest {

    private final EmployeeRepository repository = new EmployeeRepository();

    @Test
    void findByName_returnsEmployee_whenFound() {
        Employee employee = repository.findByName("Simon");

        assertThat(employee.email()).isEqualTo("simon@example.com");
    }

    @Test
    void findByName_throwsNPE_whenNotFound() {
        // This compiles fine but crashes at runtime!
        // The compiler has no idea that findByName can return null
        assertThatThrownBy(() -> {
            Employee employee = repository.findByName("Unknown");
            employee.email(); // BOOM! NullPointerException
        }).isInstanceOf(NullPointerException.class);
    }
}
