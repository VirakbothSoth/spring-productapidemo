package co.istad.productapidemo.dto;

import java.util.Set;

public record ProductResponse(
    Integer id,
    String name,
    String description,
    Float price,
    CategoryResponse category,
    Set<String> tags
){}