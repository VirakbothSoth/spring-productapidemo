package co.istad.productapidemo.dto;

public record UpdateCategoryRequest(
        String name,
        String description,
        Boolean isDeleted
){}