package co.istad.productapidemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        @Size(min=1, max=100)
        String name,
        @Size(min=1, max=100)
        String description
){}