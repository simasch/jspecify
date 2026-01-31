package ch.martinelli.demo.jspecify.safe;

import org.jspecify.annotations.Nullable;

public class EmployeeRepository {

    public @Nullable Employee findByName(String name) {
        if ("Simon".equals(name)) {
            return new Employee("Simon", "simon@example.com");
        }
        return null; // Safe - callers MUST check for null!
    }
}
