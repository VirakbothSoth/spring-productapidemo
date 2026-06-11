package co.istad.productapidemo.service;

import co.istad.productapidemo.dto.CategoryRequest;
import co.istad.productapidemo.dto.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

// for the loosely coupling design
// this interface will be implemented by other class
public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    CategoryResponse updateCategory(CategoryRequest request);
    void deleteCategory(Integer id);
    // get all with Pagination ( follow products sample )
    // soft delete category ( changing the value of isDeleted )
    List<CategoryResponse> findAll();
    CategoryResponse findCategoryById(Integer id);
    List<CategoryResponse> findByName(String name);

}