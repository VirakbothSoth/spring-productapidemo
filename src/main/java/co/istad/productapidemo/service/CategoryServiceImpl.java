package co.istad.productapidemo.service;

import co.istad.productapidemo.dto.CategoryRequest;
import co.istad.productapidemo.dto.CategoryResponse;
import co.istad.productapidemo.dto.UpdateCategoryRequest;
import co.istad.productapidemo.entity.Category;
import co.istad.productapidemo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private Integer nextId = 1004;

    private Category mapToEntity(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.name());
        category.setDescription(request.description());
        category.setIsActive(true);
        return category;
    }

    private CategoryResponse mapToResponse(Category category) {
        return new CategoryResponse (
                category.getId(),category.getName(),category.getDescription(),Boolean.TRUE.equals(category.getIsActive())
        );
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        var category = mapToEntity(categoryRequest);
        category.setId(nextId++);
        return mapToResponse(categoryRepository.createCategory(category));
    }

    @Override
    public List<CategoryResponse> findAllCategories() {
        return categoryRepository.getCategoryList().stream()
                .map(this::mapToResponse).toList();
    }

    @Override
    public CategoryResponse findCategoryById(Integer categoryId) {
        var category = categoryRepository.findCategoryById(categoryId);
        if (category == null) {
            return null;
        }
        return mapToResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(Integer id, UpdateCategoryRequest request) {
        var existingCategory = categoryRepository.findCategoryById(id);
        if (existingCategory == null) {
            return null;
        }
        if (request.name() != null) {
            existingCategory.setName(request.name());
        }
        if (request.description() != null) {
            existingCategory.setDescription(request.description());
        }
        if (request.isActive() != null) {
            existingCategory.setIsActive(request.isActive());
        }
        categoryRepository.updateCategory(existingCategory);
        return mapToResponse(existingCategory);
    }

    @Override
    public boolean deleteCategory(Integer id) {
        return false;
    }
}
