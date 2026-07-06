package co.istad.productapidemo.restcontrollers;

import co.istad.productapidemo.dto.file.FileResponse;
import co.istad.productapidemo.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@RestController("/api/v1/files")
@RequiredArgsConstructor
public class FileUploadRestController {
    private final FileUploadService fileUploadService;

    @PostMapping(consumes = MediaType.)
    public FileResponse uploadFile(MultipartFile file) {
        return fileUploadService.upload(file);
    }
}
