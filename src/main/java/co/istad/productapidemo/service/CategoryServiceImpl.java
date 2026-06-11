package co.istad.productapidemo.service;

import co.istad.productapidemo.dto.CategoryRequest;
import co.istad.productapidemo.dto.CategoryResponse;
import co.istad.productapidemo.entity.Category;
import co.istad.productapidemo.mapper.CategoryMapper;
import co.istad.productapidemo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = categoryMapper.toEntity(request);
        // TODO: check if the name already exist
        if(categoryRepository.existsByName(request.name())){
            throw new RuntimeException("category already exists");
        }

        var newCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(newCategory);
    }

    @Override
    public CategoryResponse updateCategory(CategoryRequest request) {
        return null;
    }


    @Override
    public Boolean deleteCategory(Integer id) {
        if(categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public CategoryResponse findById(Integer id) {
        return null;
    }

    @Override
    public List<CategoryResponse> findByName(String name) {
        return List.of();
    }
}
