package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.ProductDao;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductsController {
    private final ProductDao productDao;

    public ProductsController(ProductDao productDao) {
        this.productDao = productDao;

    }

     @RequestMapping(path = "/products", method = RequestMethod.GET)
    public List<Product> getAllProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Double price
    ) {

        List<Product> products = productDao.getAll();

        return products.stream()
                    .filter(p -> name == null || p.getProductName().equalsIgnoreCase(name))
                    .filter(p -> categoryId == null || p.getCategoryId() == categoryId)
                    .filter(p -> price == null || p.getUnitPrice() == price)
                    .collect(Collectors.toList());
    }

    @RequestMapping(path = "/products/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable int id) {
        return productDao.getById(id);

    }
}
