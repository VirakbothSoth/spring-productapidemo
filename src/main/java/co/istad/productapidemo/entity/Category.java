package co.istad.productapidemo.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Category {
    private Integer id;
    private String name;
    private String description;
    private Boolean isActive;
}
