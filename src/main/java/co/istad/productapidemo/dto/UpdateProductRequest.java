package co.istad.productapidemo.dto;

public record UpdateProductRequest(
        String name,
        String description,
        Float price
) {
}
