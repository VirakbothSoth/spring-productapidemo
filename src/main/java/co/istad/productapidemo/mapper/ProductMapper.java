package co.istad.productapidemo.mapper;

import co.istad.productapidemo.dto.ProductRequest;
import co.istad.productapidemo.dto.ProductResponse;
import co.istad.productapidemo.entity.Product;
import co.istad.productapidemo.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    // turn tags object into pure string
    // ["iphone","17 pro max" , "apple"]
    // [{"id":1,....
    //@Mapping(target = "tags", ignore = true)
    @Mapping(target = "tags", source = "tags")
    ProductResponse mapToResponse(Product request);
    Product mapToProduct(ProductRequest request);


    // method for converting the Set<Tag> to Set<String>
    default Set<String> mapToString(Set<Tag> tags ){
        return tags.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());
    }
}