package ch.martinelli.demo.jspecify.safe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    @Qualifier("safeEmployeeService")
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService.deleteAll();
        employeeService.save(new Employee(null, "Simon", "simon@example.com"));
    }

    @Test
    void findByName_returnsEmployee_whenFound() {
        Employee employee = employeeService.findByName("Simon");

        // NullAway knows this can be null, so we must check!
        if (employee != null) {
            assertThat(employee.email()).isEqualTo("simon@example.com");
        } else {
            throw new AssertionError("Employee should not be null");
        }
    }

    @Test
    void findByName_returnsNull_whenNotFound() {
        Employee employee = employeeService.findByName("Unknown");

        // With @Nullable, we're forced to handle the null case
        // Trying to call employee.email() without a null check
        // would cause a compile-time error from NullAway!
        assertThat(employee).isNull();
    }
}
