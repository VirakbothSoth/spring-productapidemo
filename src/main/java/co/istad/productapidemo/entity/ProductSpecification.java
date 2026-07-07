package co.istad.productapidemo.entity;

import co.istad.productapidemo.dto.ProductFilter;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class ProductSpecification {
    public static Specification<Product> filterProduct(ProductFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getName() != null && !filter.getName().isBlank()) {
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%"+filter.getName().toLowerCase()+"%")
                );
            }

            if (filter.getMinPrice()!=null)
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"),filter.getMinPrice()));

            if (filter.getMaxPrice()!=null)
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),filter.getMaxPrice()));

            if (filter.getCategoryId()!=null)
                predicates.add(criteriaBuilder.equal(root.get("category").get("id"),filter.getCategoryId()));

            if (filter.getTagNames() != null && !filter.getTagNames().isEmpty()) {
                Join<Product,Tag> tagsJoin = root.join("tags");
                predicates.add(tagsJoin.get("name").in(filter.getTagNames()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
