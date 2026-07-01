package co.istad.productapidemo.service;

import co.istad.productapidemo.dto.order.CreateOrderRequest;
import co.istad.productapidemo.dto.order.OrderResponse;

import java.util.List;

public interface OrderService {
    // create: createOrder, updateOrder, cancelOrder, deleteOrder, getAllOrders, getAllOrderByCustomerId
    OrderResponse createOrder(CreateOrderRequest orderRequest);
    OrderResponse updateOrder();

    List<OrderResponse> getAllOrders();
    List<OrderResponse> getAllOrdersByCustomerId(Integer customerId);
}
