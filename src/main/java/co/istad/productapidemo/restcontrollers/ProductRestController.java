package co.istad.productapidemo.restcontrollers;

import co.istad.productapidemo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductRestController {
    // just injecting service
    private final ProductService productService;
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }


}