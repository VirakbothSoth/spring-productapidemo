package co.istad.productapidemo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductFilter {
    private String name;
    private String code;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Boolean isAvailable;
    private Integer categoryId;

    private List<String> tagNames;
}
