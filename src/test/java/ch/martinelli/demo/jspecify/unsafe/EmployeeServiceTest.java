package ch.martinelli.demo.jspecify.unsafe;

import ch.martinelli.demo.jspecify.safe.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    @Qualifier("unsafeEmployeeService")
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService.deleteAll();
        employeeService.save(new Employee(null, "Simon", "simon@example.com"));
    }

    @Test
    void findByName_returnsEmployee_whenFound() {
        Employee employee = employeeService.findByName("Simon");

        assertThat(employee.email()).isEqualTo("simon@example.com");
    }

    @Test
    void findByName_throwsNPE_whenNotFound() {
        // This compiles fine but crashes at runtime!
        // The compiler has no idea that findByName can return null
        assertThatThrownBy(() -> {
            Employee employee = employeeService.findByName("Unknown");
            employee.email(); // BOOM! NullPointerException
        }).isInstanceOf(NullPointerException.class);
    }
}
