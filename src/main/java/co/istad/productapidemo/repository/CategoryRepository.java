package co.istad.productapidemo.repository;

import co.istad.productapidemo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Boolean existsByName(String name);
}