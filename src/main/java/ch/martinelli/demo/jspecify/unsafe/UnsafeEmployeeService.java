package ch.martinelli.demo.jspecify.unsafe;

import ch.martinelli.demo.jspecify.safe.SafeEmployee;
import org.springframework.stereotype.Service;

@Service
public class UnsafeEmployeeService {

    public SafeEmployee createEmployee(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            return null;
        }
        return new SafeEmployee(firstName, lastName);
    }

}
