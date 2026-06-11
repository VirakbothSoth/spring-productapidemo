package co.istad.productapidemo.service;

import co.istad.productapidemo.advisor.ResourceAlreadyExistException;
import co.istad.productapidemo.dto.CategoryRequest;
import co.istad.productapidemo.dto.CategoryResponse;
import co.istad.productapidemo.entity.Category;
import co.istad.productapidemo.mapper.CategoryMapper;
import co.istad.productapidemo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
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
    public CategoryResponse updateCategory(CategoryRequest request) {
        // Partial updates
        return null;
    }
// soft delete

    @Override
    public void deleteCategory(Integer id) {
        if(!categoryRepository.existsById(id)) {
            throw new NoSuchElementException("Category with id = " + id + " does not exist");
        }
        categoryRepository.deleteById(id);

    }

    @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::mapToResponse)
                .toList();
    }

    @Override
    public CategoryResponse findCategoryById(Integer id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Category with ID = "+id+" not found"));

        return categoryMapper.mapToResponse(category);
    }

    @Override
    public List<CategoryResponse> findByName(String name) {
        return List.of();
    }
}