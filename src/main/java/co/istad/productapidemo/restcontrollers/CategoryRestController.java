package co.istad.productapidemo.restcontrollers;

import co.istad.productapidemo.dto.*;
import co.istad.productapidemo.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryRestController {
    private final CategoryService categoryService;

    @GetMapping
    List<CategoryResponse> getCategories() {
        return categoryService.findAll();
    }

    @PostMapping
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest request){
        return categoryService.createCategory(request);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteCategory(@PathVariable Integer id) {
        return categoryService.deleteCategory(id);
    }
}