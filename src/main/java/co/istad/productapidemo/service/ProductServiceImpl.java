package co.istad.productapidemo.service;

import co.istad.productapidemo.dto.ProductRequest;
import co.istad.productapidemo.dto.ProductResponse;
import co.istad.productapidemo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    // inj le repo
    private final ProductRepository productRepository;
    @Override
    public ProductResponse createProduct(ProductRequest product) {
        return null;
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return List.of();
    }

    @Override
    public ProductResponse updateProduct(ProductRequest product) {
        return null;
    }

    @Override
    public boolean deleteProduct(int id) {
        return false;
    }
}
