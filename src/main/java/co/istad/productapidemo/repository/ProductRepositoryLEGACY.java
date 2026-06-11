package co.istad.productapidemo.repository;

import co.istad.productapidemo.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ProductRepositoryLEGACY {
    // we aren't working with dbs yet, so this is an example
    private final List<Product> productList = new ArrayList<>(){{
        add(new Product(1001, "Coca Cola", "Nice when cold",23.4f,2));
        add(new Product(1002, "Fanta", "Nice when cold",0.75f,4));
        add(new Product(1003, "Sting", "Diabetes soon",0.65f,5));
    }};

    public List<Product> getProductList() {
        return productList;
    }

    public Product createProduct(Product product) {
        productList.add(product);
        return product;
    }

    public Product findProductById(Integer id) {
        return productList.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    public boolean deleteProductById(Integer id) {
        return productList.removeIf(product -> product.getId() == id);
    }

    public Product updateProduct(Product updateProduct) {
        for (int i =0; i < productList.size(); i++) {
            var product = productList.get(i);
            if (product.getId() == updateProduct.getId()) {
                productList.set(i, updateProduct);
                return updateProduct;
            }
        }
        return null;
    }
}