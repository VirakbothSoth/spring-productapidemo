package co.istad.productapidemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name="order_tbl")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String address;
    private String remarks;
    private BigDecimal discount;
    private Boolean isDeleted = false;
    private LocalDateTime orderedAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User customer;
    @OneToMany(
            mappedBy="order",
            cascade=CascadeType.ALL,
            orphanRemoval = true
    )
    List<OrderLine> items = new ArrayList<>();
}
