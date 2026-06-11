package co.istad.productapidemo.mapper;

import co.istad.productapidemo.dto.CategoryRequest;
import co.istad.productapidemo.dto.CategoryResponse;
import co.istad.productapidemo.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toResponse(Category category);
    Category toEntity(CategoryRequest request);
}