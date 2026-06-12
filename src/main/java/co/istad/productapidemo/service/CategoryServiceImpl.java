package co.istad.productapidemo.service;

import co.istad.productapidemo.advisor.ResourceAlreadyExistException;
import co.istad.productapidemo.dto.CategoryRequest;
import co.istad.productapidemo.dto.CategoryResponse;
import co.istad.productapidemo.dto.UpdateCategoryRequest;
import co.istad.productapidemo.entity.Category;
import co.istad.productapidemo.mapper.CategoryMapper;
import co.istad.productapidemo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        // map from request to entity
        Category category = categoryMapper.mapToEntity(request);
        // derived query
        if(categoryRepository.existsByName(request.name())){
            throw new ResourceAlreadyExistException("Category with name = "+request.name()+" already exists");
        }

        var newCategory = categoryRepository.save(category);
        return categoryMapper.mapToResponse(newCategory);
    }

    @Override
    public CategoryResponse updateCategory(Integer id, UpdateCategoryRequest request) {
        var existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category with ID = " + id + " not found"));

        if (request.name() != null)
            existingCategory.setName(request.name());
        if (request.description() != null)
            existingCategory.setDescription(request.description());
        if (request.isDeleted() != null)
            existingCategory.setIsDeleted(request.isDeleted());

        var updatedCategory = categoryRepository.save(existingCategory);
        return categoryMapper.mapToResponse(updatedCategory);
    }
    // soft delete

    @Override
    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + id));
        category.setIsDeleted(true);
        categoryRepository.save(category);
    }

    @Override
    public Page<CategoryResponse> findAllCategories(String name, Pageable pageable) {
        return categoryRepository
                .findByNameContainingIgnoreCaseAndIsDeletedFalse(name, pageable)
                .map(categoryMapper::mapToResponse);
    }

    @Override
    public CategoryResponse findById(Integer id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Category with ID = "+id+" not found"));

        return categoryMapper.mapToResponse(category);
    }
}