package co.istad.productapidemo.restcontrollers;

import co.istad.productapidemo.dto.file.FileResponse;
import co.istad.productapidemo.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

@RestController("/api/v1/files")
@RequiredArgsConstructor
public class FileUploadRestController {
    private final FileUploadService fileUploadService;

    @PostMapping(consumes = MediaType.)
    public FileResponse uploadFile(@RequestPart MultipartFile file) {
        return fileUploadService.upload(file);
    }

    @PostMapping("/multiple")
    public List<FileResponse> uploadMultipleFiles(@RequestPart List<MultipartFile> files) {
        return fileUploadService.uploadMultipleFiles(files);
    }

    @GetMapping
    public Page<FileResponse> getAllFiles(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return fileUploadService.findAll(pageNumber,pageSize);
    }

    @ResponseStatus(Http)
    public void deleteFile(String fileName) {

    }
}
