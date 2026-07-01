package co.istad.productapidemo.mapper;

import co.istad.productapidemo.dto.order.CreateOrderRequest;
import co.istad.productapidemo.dto.order.OrderResponse;
import co.istad.productapidemo.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse mapToResponse(Order order);
    Order mapToEntity(CreateOrderRequest order);
}
