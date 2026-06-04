package co.istad.productapidemo.service;

import co.istad.productapidemo.dto.ProductRequest;
import co.istad.productapidemo.dto.ProductResponse;
import co.istad.productapidemo.dto.UpdateProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;


// for the loosely coupling design
// this interface will be implemented by other class
@Service
public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    List<ProductResponse> findAllProducts();
    ProductResponse findProductById(Integer productId);
    ProductResponse updateProduct(Integer id, UpdateProductRequest request);
    boolean deleteProduct(Integer id);
}