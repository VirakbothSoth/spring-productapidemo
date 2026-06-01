package co.istad.productapidemo.entity;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    private int id;
    private String name;
    private String description;
    private float price;
    private int userId;
}