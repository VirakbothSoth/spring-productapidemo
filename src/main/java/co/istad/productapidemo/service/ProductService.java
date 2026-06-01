package co.istad.productapidemo.service;

import co.istad.productapidemo.dto.ProductRequest;
import co.istad.productapidemo.dto.ProductResponse;
import co.istad.productapidemo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;


// for the loosely coupling design
// this interface will be implemented by other class
@Service
public interface ProductService {
    ProductResponse createProduct(ProductRequest product);
    List<ProductResponse> findAllProducts();
    ProductResponse updateProduct(ProductRequest product);
    boolean deleteProduct(int id);
}