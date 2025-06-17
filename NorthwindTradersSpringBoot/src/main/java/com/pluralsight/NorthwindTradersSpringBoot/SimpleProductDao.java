package com.pluralsight.NorthwindTradersSpringBoot;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleProductDao implements ProductDao {
    private List<Product> products;

    public SimpleProductDao() {
        products = new ArrayList<>();
        products.add(new Product(1, "Apple iPhone", "Electronics", 999.99));
        products.add(new Product(2, "Android iPhone", "Electronics", 899.99));
        products.add(new Product(3, "Window iPhone", "Electronics", 799.99));
        products.add(new Product(4, "Nike Sneakers", "Footwear", 129.99));
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Product findById(int id) {
        return products.stream()
                .filter(p -> p.getProductId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Product updatedProduct) {
        Product existing = findById(updatedProduct.getProductId());
        if (existing != null) {
            existing.setName(updatedProduct.getName());
            existing.setCategory(updatedProduct.getCategory());
            existing.setPrice(updatedProduct.getPrice());
        }
    }
    @Override
    public void delete(int id) {
        products.removeIf(p -> p.getProductId() == id);
    }




}
