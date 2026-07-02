package co.istad.productapidemo.dto.order;

import java.math.BigDecimal;

public record OrderItemResponse(
        Integer productId,
        String productName,
        String thumbnail,
        Integer qty,
        BigDecimal unitPrice,
        BigDecimal lineTotal
) { }
