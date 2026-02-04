package ch.martinelli.demo.jspecify.safe;

import org.jspecify.annotations.Nullable;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

    private final JdbcClient jdbcClient;

    public EmployeeRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    public @Nullable Employee findByName(String name) {
        return jdbcClient.sql("SELECT id, name, email FROM employee WHERE name = :name")
                .param("name", name)
                .query(Employee.class)
                .optional()
                .orElse(null);
    }

    public Employee save(Employee employee) {
        if (employee.id() == null) {
            var keyHolder = new GeneratedKeyHolder();
            jdbcClient.sql("INSERT INTO employee (name, email) VALUES (:name, :email)")
                    .param("name", employee.name())
                    .param("email", employee.email())
                    .update(keyHolder);
            var id = keyHolder.getKey();
            return new Employee(id == null ? null : id.longValue(), employee.name(), employee.email());
        } else {
            jdbcClient.sql("UPDATE employee SET name = :name, email = :email WHERE id = :id")
                    .param("id", employee.id())
                    .param("name", employee.name())
                    .param("email", employee.email())
                    .update();
            return employee;
        }
    }

    public void deleteAll() {
        jdbcClient.sql("DELETE FROM employee").update();
    }
}
