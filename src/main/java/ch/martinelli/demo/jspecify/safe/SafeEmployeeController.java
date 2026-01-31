package ch.martinelli.demo.jspecify.safe;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/employees")
@RestController
public class SafeEmployeeController {

    private final SafeEmployeeService safeEmployeeService;

    public SafeEmployeeController(SafeEmployeeService safeEmployeeService) {
        this.safeEmployeeService = safeEmployeeService;
    }

    @PostMapping
    public SafeEmployee createEmployee(String firstName, String lastName) {
        return safeEmployeeService.createEmployee(firstName, lastName);
    }
}
