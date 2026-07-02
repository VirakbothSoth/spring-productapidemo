package co.istad.productapidemo.dto.order;

public record OrderItemRequest(
        Integer productId,
        Integer qty
) { }