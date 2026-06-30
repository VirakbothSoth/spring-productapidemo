package co.istad.productapidemo.dto.product;

import co.istad.productapidemo.dto.CategoryResponse;

import java.math.BigDecimal;
import java.util.Set;

public record ProductResponse(
    Integer id,
    String name,
    String description,
    BigDecimal price,
    CategoryResponse category,
    Set<String> tags
){}