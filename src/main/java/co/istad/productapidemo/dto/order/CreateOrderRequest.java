package co.istad.productapidemo.dto.order;

import java.math.BigDecimal;
import java.util.List;

public record CreateOrderRequest (
        Integer customerId,
        String address,
        String remarks,
        BigDecimal discount,
        List<OrderItemRequest> items
) { }
