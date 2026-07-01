package co.istad.productapidemo.service;

import co.istad.productapidemo.dto.order.CreateOrderRequest;
import co.istad.productapidemo.dto.order.OrderResponse;
import co.istad.productapidemo.entity.Order;
import co.istad.productapidemo.entity.OrderLine;
import co.istad.productapidemo.mapper.OrderMapper;
import co.istad.productapidemo.repository.OrderRepository;
import co.istad.productapidemo.repository.ProductRepository;
import co.istad.productapidemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {
        Order order = orderMapper.mapToEntity(request);

        var customer = userRepository.findById(request.customerId())
                .orElseThrow(() ->
                        new NoSuchElementException("Customer with id ="+request.customerId()+" not found")
                );
        order.setCustomer(customer);

        var orderLines = request.items().stream()
                .map(item -> {
                    var product = productRepository.findById(item.productId())
                            .orElseThrow(() -> new NoSuchElementException("Product with id ="+item.productId()+" not found"));

                    if(!product.getIsAvailable())
                        throw new NoSuchElementException("Product with id="+item.productId()+" not available!");

                    product.setQty(product.getQty() - item.qty());

                    var orderLine = new OrderLine();
                    orderLine.setProduct(product);
                    orderLine.setUnitPrice(product.getPrice());
                    orderLine.setQty(item.qty());
                    orderLine.setOrder(order);
                    return orderLine;
                }).toList();
                order.setItems(orderLines);

        BigDecimal discount = request.discount()==null ? BigDecimal.ZERO : request.discount();

        BigDecimal subTotal = orderLines.stream()
                .map(line -> line.getUnitPrice().multiply(
                        BigDecimal.valueOf(line.getQty())))
                        .reduce(BigDecimal.ZERO, (a,b)->(a.add(b)));

        Order savedOrder = orderRepository.save(order);
        return null;
    }

    @Override
    public OrderResponse updateOrder() {
        return null;
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return List.of();
    }

    @Override
    public List<OrderResponse> getAllOrdersByCustomerId(Integer customerId) {
        return List.of();
    }
}
