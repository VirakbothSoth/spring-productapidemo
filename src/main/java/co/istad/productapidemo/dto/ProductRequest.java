package co.istad.productapidemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Set;

public record ProductRequest(
        @NotBlank(message = "name is required")
        String name,
        @NotBlank(message = "description is required")
        String description,
        @NotNull(message = "price is required")
        @Positive(message = "price must be positive")
        Float price,
        Integer categoryId,
        Set<Long> tagIds
) {
}