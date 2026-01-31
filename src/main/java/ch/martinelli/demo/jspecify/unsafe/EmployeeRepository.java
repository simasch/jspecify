package ch.martinelli.demo.jspecify.unsafe;

public class EmployeeRepository {

    public Employee findByName(String name) {
        if ("Simon".equals(name)) {
            return new Employee("Simon", "simon@example.com");
        }
        return null; // NPE trap - no compile-time warning!
    }
}
