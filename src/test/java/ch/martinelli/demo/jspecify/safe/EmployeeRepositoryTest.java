package ch.martinelli.demo.jspecify.safe;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeRepositoryTest {

    private final EmployeeRepository repository = new EmployeeRepository();

    @Test
    void findByName_returnsEmployee_whenFound() {
        Employee employee = repository.findByName("Simon");

        // NullAway knows this can be null, so we must check!
        if (employee != null) {
            assertThat(employee.email()).isEqualTo("simon@example.com");
        } else {
            throw new AssertionError("Employee should not be null");
        }
    }

    @Test
    void findByName_returnsNull_whenNotFound() {
        Employee employee = repository.findByName("Unknown");

        // With @Nullable, we're forced to handle the null case
        // Trying to call employee.email() without a null check
        // would cause a compile-time error from NullAway!
        assertThat(employee).isNull();
    }
}
