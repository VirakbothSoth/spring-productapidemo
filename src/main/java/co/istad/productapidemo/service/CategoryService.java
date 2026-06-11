package co.istad.productapidemo.service;

import co.istad.productapidemo.dto.CategoryRequest;
import co.istad.productapidemo.dto.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

// for the loosely coupling design
// this interface will be implemented by other class
@Service
public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    CategoryResponse updateCategory(CategoryRequest request);
    Boolean deleteCategory(Integer id);
    List<CategoryResponse> findAll();
    CategoryResponse findById(Integer id);
    List<CategoryResponse> findByName(String name);
}