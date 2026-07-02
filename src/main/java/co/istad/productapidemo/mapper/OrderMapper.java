package co.istad.productapidemo.mapper;

import co.istad.productapidemo.dto.order.CreateOrderRequest;
import co.istad.productapidemo.dto.order.OrderItemResponse;
import co.istad.productapidemo.dto.order.OrderResponse;
import co.istad.productapidemo.entity.Order;
import co.istad.productapidemo.entity.OrderLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target="customerId",source="customer.id")
    @Mapping(target="customerName", source="customer.email")
    @Mapping(target="total",expression="java(calculateTotal(order))")
    @Mapping(target="subTotal",expression="java(calculateSubTotal(order))")
    OrderResponse mapToResponse(Order order);

    @Mapping(target="productId", source="product.id")
    @Mapping(target="productName", source="product.name")
    @Mapping(target="thumbnail", source="product.thumbnail")
    @Mapping(target = "lineTotal",
            expression = "java(orderLine.getUnitPrice().multiply(java.math.BigDecimal.valueOf(orderLine.getQty())))"
    )
    OrderItemResponse mapToOrderItemResponse(OrderLine orderLine);

    default BigDecimal calculateSubTotal(Order order) {
        if (order.getItems()==null) return BigDecimal.ZERO;

        return order.getItems().stream()
                .map(l -> l.getUnitPrice().multiply(BigDecimal.valueOf(l.getQty())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    default BigDecimal calculateTotal(Order order) {
        BigDecimal discount = order.getDiscount() == null ? BigDecimal.ZERO : order.getDiscount();
        return calculateSubTotal(order).subtract(discount);
    }

    Order mapToEntity(CreateOrderRequest order);
}
