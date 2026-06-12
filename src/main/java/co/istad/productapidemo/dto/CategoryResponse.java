package co.istad.productapidemo.dto;
import lombok.Builder;
@Builder
public record CategoryResponse(
        Integer id,
        String name,
        String description,
        Boolean isDeleted
) {
}