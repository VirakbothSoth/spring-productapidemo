package co.istad.productapidemo.service;

import co.istad.productapidemo.dto.product.ProductRequest;
import co.istad.productapidemo.dto.product.ProductResponse;
import co.istad.productapidemo.dto.product.UpdateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

// for the loosely coupling design
// this interface will be implemented by other class
public interface ProductService {
    ProductResponse createProduct(ProductRequest product);
    List<ProductResponse> findAllProducts();

    // for the pagination support when get all products
    Page<ProductResponse> findAllProducts(Pageable pageable);
    // Page<ProductResponse> name(String keywords, Pageable page);

    ProductResponse findProductById(Integer id);
    ProductResponse updateProduct(Integer id, UpdateProductRequest request);
    boolean deleteProduct(Integer id);
}