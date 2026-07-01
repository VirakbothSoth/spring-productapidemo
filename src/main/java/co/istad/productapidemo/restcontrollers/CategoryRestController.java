package co.istad.productapidemo.restcontrollers;

import co.istad.productapidemo.dto.*;
import co.istad.productapidemo.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryRestController {
    private final CategoryService categoryService;

    @GetMapping
    public Page<CategoryResponse> getCategories(
            @RequestParam(defaultValue = "") String name,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        return categoryService.findAllCategories(name, pageable);
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Integer id) {
        return categoryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest request) {
        return categoryService.createCategory(request);
    }

    @PutMapping("/{id}")
    public CategoryResponse updateCategory(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateCategoryRequest request
    ) {
        return categoryService.updateCategory(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
    }
}