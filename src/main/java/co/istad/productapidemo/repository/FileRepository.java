package co.istad.productapidemo.repository;

import co.istad.productapidemo.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileUpload,Long> {
}
