package co.istad.productapidemo.service;

import co.istad.productapidemo.dto.file.FileResponse;
import co.istad.productapidemo.entity.FileUpload;
import co.istad.productapidemo.mapper.FileUploadMapper;
import co.istad.productapidemo.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {
    private final FileRepository fileRepository;
    private final FileUploadMapper fileUploadMapper;
    @Value("${file.storage-location}")
    private String fileStorageLocation;

    @Override
    public FileResponse upload(MultipartFile file) {
        return uploadFile(file);
    }

    @Override
    public List<FileResponse> uploadMultipleFiles(List<MultipartFile> files) {
        return files.stream().map(this::uploadFile).collect(Collectors.toList());
    }

    @Override
    public FileResponse findByName(String name) {
        var file = fileRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("File with name " + name + " not found"));
        return fileUploadMapper.mapToResponse(file);
    }

    @Override
    public Page<FileResponse> findAll(int pageNumber, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortById);
        return fileRepository.findAll(pageable)
                .map(fileUploadMapper::mapToResponse);
    }

    @Override
    public void deleteByName(String name) {
        var file = fileRepository.findByName(name).orElseThrow(()->new NoSuchElementException("File with name "+name+" not found"));
        fileRepository.delete(file);
        String fullPath = fileStorageLocation+file.getName()+"."+file.getExtension();
        Path pathToDelete = Paths.get(fullPath);
        try {
            var result = Files.deleteIfExists(pathToDelete);
            if (!result) {
                throw new NoSuchElementException("File with name "+name+" not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new NoSuchElementException("File with name "+name+" not found");
        }
    }

    private FileResponse uploadFile(MultipartFile file) {
        // 1. rename the file
        String name = UUID.randomUUID().toString();
        // 2. get extension
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
        String filename = name+"."+ext;
        // 3. construct path
        Path path = Paths.get(fileStorageLocation+filename);
        // 4. (save) copy the file to local machine
        try {
            Files.copy(file.getInputStream(),path);
        } catch(IOException exception){
            throw new IllegalArgumentException("Error uploading file");
        }
        // 5. return object
        var fileUpload = new FileUpload();
        fileUpload.setName(name);
        fileUpload.setExtension(ext);
        fileUpload.setCaption("ISTAD media service");
        fileUpload.setSize(file.getSize());
        fileUpload.setMediaType(file.getContentType());
        fileRepository.save(fileUpload);
        return fileUploadMapper.mapToResponse(fileUpload);
    }
}
