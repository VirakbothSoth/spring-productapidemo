package co.istad.productapidemo.dto;

public record CategoryResponse(
    Integer id,
    String name,
    String description,
    Boolean isActive
){}