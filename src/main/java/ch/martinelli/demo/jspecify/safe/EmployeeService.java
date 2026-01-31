package ch.martinelli.demo.jspecify.safe;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service("safeEmployeeService")
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public @Nullable Employee findByName(String name) {
        return employeeRepository.findByName(name);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteAll() {
        employeeRepository.deleteAll();
    }
}
