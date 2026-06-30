package co.istad.productapidemo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "product_tbl")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price; // unit price
    private Boolean isAvailable=true;
    private Boolean isDeleted=false; // soft delete
    private String slug; // for seo purpose
    private String thumbnail; // for product image
    private Integer qty;
    private Integer userId;

    @ManyToMany
    @JoinTable(
            name = "product_tags",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @ManyToOne
    // determined foreign key
    @JoinColumn(name = "category_id")
    private Category category;
}