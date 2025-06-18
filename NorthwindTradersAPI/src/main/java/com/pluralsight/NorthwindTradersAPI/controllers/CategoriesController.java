package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoriesController {
    private List<Category> categories = new ArrayList<>();

    public CategoriesController() {
        categories.add(new Category(1, "Beverages"));
        categories.add(new Category(2, "Condiments"));
        categories.add(new Category(17, "Seafood"));
    }

    @RequestMapping(path = "/categories", method = RequestMethod.GET)
    public List<Category> getAllCategories() {
        return categories;
    }

    @RequestMapping(path = "/categories/{id}", method = RequestMethod.GET)
    public Category getCategoryById(@PathVariable int id) {
        for (Category category : categories) {
            if (category.getCategoryId() == id) {
                return category;
            }
        }
        return null;
    }
}
