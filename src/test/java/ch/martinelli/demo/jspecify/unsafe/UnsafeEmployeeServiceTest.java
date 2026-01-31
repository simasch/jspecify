package ch.martinelli.demo.jspecify.unsafe;

import ch.martinelli.demo.jspecify.safe.SafeEmployee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UnsafeEmployeeServiceTest {

    @Autowired
    private UnsafeEmployeeService unsafeEmployeeService;

    @Test
    void createEmployee() {
        SafeEmployee employee = unsafeEmployeeService.createEmployee("Martin", "Elli");

        assertThat(employee).isNotNull();

        System.out.println(employee.getFirstName() + " " + employee.getLastName());
    }

    @Test
    void createEmployeeNpe() {
        SafeEmployee employee = unsafeEmployeeService.createEmployee(null, null);

        assertThat(employee).isNull();

        assertThrows(NullPointerException.class, () -> System.out.println(employee.getFirstName()));
    }
}