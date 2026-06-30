package co.istad.productapidemo.dto.product;

import java.math.BigDecimal;

public record UpdateProductRequest(
        String name,
        String description,
        BigDecimal price
) {
}
