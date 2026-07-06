package co.istad.productapidemo.mapper;

import co.istad.productapidemo.dto.file.FileResponse;
import co.istad.productapidemo.entity.FileUpload;
import lombok.Value;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class FileUploadMapper {
    @Value("${file.base-url}")
    protected String baseUrl;

    @Mapping(target="url",expression="java(generateUrl(fileUpload))")
    public abstract FileResponse mapToResponse(FileUpload fileUpload);
    protected String generateUrl(FileUpload fileUpload) {
        return baseUrl+fileUpload.getName()+"."+fileUpload.getExtension();
    }
}
