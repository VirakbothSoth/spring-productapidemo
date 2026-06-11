package co.istad.productapidemo.service;

import co.istad.productapidemo.dto.ProductRequest;
import co.istad.productapidemo.dto.ProductResponse;
import co.istad.productapidemo.dto.UpdateProductRequest;
import co.istad.productapidemo.entity.Product;
import co.istad.productapidemo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    // inj le repo
    private final ProductRepository productRepository;

    private Integer nextId = 1004;

    private Product mapToEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        return product;
    }

    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse (
                product.getId(), product.getName(),product.getDescription(), product.getPrice()
        );
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        var product = mapToEntity(request);
        product.setUserId(1);
        product.setId(nextId++);
        return mapToResponse(productRepository.save(product));
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToResponse).toList();
    }

    @Override
    public ProductResponse findProductById(Integer id) {
        var product = productRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find prod with this ID"));
        return mapToResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Integer id, UpdateProductRequest request) {
        var exitingProduct = productRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find prod with this ID"));
        if (request.name() != null) {
            exitingProduct.setName(request.name());
        }
        if (request.description() != null) {
            exitingProduct.setDescription(request.description());
        }
        if (request.price() != null) {
            exitingProduct.setPrice(request.price());
        }
        productRepository.save(exitingProduct);
        return mapToResponse(exitingProduct);
    }

    @Override
    public boolean deleteProduct(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
