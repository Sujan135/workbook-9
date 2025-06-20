package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductsController {
    private List<Product> products = new ArrayList<>();

    public ProductsController() {
        products.add(new Product(1, "Chai", 1, 18.0));
        products.add(new Product(2, "Chang", 1, 19.0));
        products.add(new Product(5, "Gumbo Mix", 2, 21.35));
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public List<Product> getAllProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Double price
    ) {
        return products.stream()
                    .filter(p -> name == null || p.getProductName().equalsIgnoreCase(name))
                    .filter(p -> categoryId == null || p.getCategoryId() == categoryId)
                    .filter(p -> price == null || p.getUnitPrice() == price)
                    .collect(Collectors.toList());
    }

    @RequestMapping(path = "/products/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable int id) {
        for (Product product : products) {
            if (product.getProductId() == id) {
                return product;
            }
        }
        return null;
    }
}
