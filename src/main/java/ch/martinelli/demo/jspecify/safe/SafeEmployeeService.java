package ch.martinelli.demo.jspecify.safe;

import org.springframework.stereotype.Service;

@Service
public class SafeEmployeeService {

    public SafeEmployee createEmployee(String firstName, String lastName) {
        return new SafeEmployee(firstName, lastName);
    }
}
