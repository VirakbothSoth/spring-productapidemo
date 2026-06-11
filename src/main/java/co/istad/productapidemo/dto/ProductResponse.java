package co.istad.productapidemo.dto;

public record ProductResponse(
    Integer id,
    String name,
    String description,
    Float price,
    CategoryResponse category
){}