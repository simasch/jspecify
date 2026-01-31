package ch.martinelli.demo.jspecify.safe;

import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;

public record Employee(@Id @Nullable Long id, String name, String email) {
}
