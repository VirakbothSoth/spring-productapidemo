package co.istad.productapidemo.repository;

import co.istad.productapidemo.entity.Order;
import co.istad.productapidemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByCustomerId(Integer customerId);
}
