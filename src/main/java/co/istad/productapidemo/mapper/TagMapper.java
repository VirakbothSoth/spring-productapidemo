package co.istad.productapidemo.mapper;

import co.istad.productapidemo.dto.TagResponse;
import co.istad.productapidemo.dto.TagRequest;
import co.istad.productapidemo.entity.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagResponse mapToResponse(Tag category);
    Tag mapToEntity(TagRequest request);
}
