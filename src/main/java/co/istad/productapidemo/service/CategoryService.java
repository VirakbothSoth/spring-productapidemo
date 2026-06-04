package co.istad.productapidemo.service;

import co.istad.productapidemo.dto.CategoryRequest;
import co.istad.productapidemo.dto.CategoryResponse;
import co.istad.productapidemo.dto.UpdateCategoryRequest;
import org.springframework.stereotype.Service;

import java.util.List;


// for the loosely coupling design
// this interface will be implemented by other class
@Service
public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    List<CategoryResponse> findAllCategories();
    CategoryResponse findCategoryById(Integer categoryId);
    CategoryResponse updateCategory(Integer id, UpdateCategoryRequest request);
    boolean deleteCategory(Integer id);
}