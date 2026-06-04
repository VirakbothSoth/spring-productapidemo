package co.istad.productapidemo.restcontrollers;

import co.istad.productapidemo.dto.ProductRequest;
import co.istad.productapidemo.dto.ProductResponse;
import co.istad.productapidemo.dto.UpdateProductRequest;
import co.istad.productapidemo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductRestController {
    // just injecting service
    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> getProducts() {
        return productService.findAllProducts();
    }

    // find product by id
    @GetMapping("/{id}")
    public ProductResponse getProductByID(@PathVariable Integer id) {
        return productService.findProductById(id);
    }

    @PostMapping
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @PatchMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable Integer id, @RequestBody UpdateProductRequest request) {
        return productService.updateProduct(id, request);
    }
}