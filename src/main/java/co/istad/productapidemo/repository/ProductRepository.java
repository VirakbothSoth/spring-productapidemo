package co.istad.productapidemo.repository;

import co.istad.productapidemo.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    // we aren't working with dbs yet, so this is an example
    private final List<Product> productList = new ArrayList<>(){{
        add(new Product(1001, "Coca Cola", "Nice when cold",23.4f,2));
        add(new Product(1002, "Fanta", "Nice when cold",0.75f,4));
        add(new Product(1003, "Sting", "Diabetes soon",0.65f,5));
    }};

    public List<Product> getProductList() {
        return productList;
    }
}