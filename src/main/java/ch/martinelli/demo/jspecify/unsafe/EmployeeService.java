package ch.martinelli.demo.jspecify.unsafe;

import ch.martinelli.demo.jspecify.safe.Employee;
import ch.martinelli.demo.jspecify.safe.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service("unsafeEmployeeService")
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee findByName(String name) {
        return employeeRepository.findByName(name); // NPE trap - no compile-time warning!
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteAll() {
        employeeRepository.deleteAll();
    }
}
