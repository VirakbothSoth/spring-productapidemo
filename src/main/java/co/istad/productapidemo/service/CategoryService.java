package co.istad.productapidemo.service;

import co.istad.productapidemo.dto.CategoryRequest;
import co.istad.productapidemo.dto.CategoryResponse;
import co.istad.productapidemo.dto.UpdateCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

// for the loosely coupling design
// this interface will be implemented by other class
public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    CategoryResponse updateCategory(Integer id, UpdateCategoryRequest request);
    void deleteCategory(Integer id);
    Page<CategoryResponse> findAllCategories(String name, Pageable pageable);

    CategoryResponse findById(Integer id);
}