package com.pluralsight.NorthwindTradersSpringBoot;

import java.util.List;

public interface ProductDao {
    void add(Product product);
    List<Product> getAll();

    Product findById(int id);
    void update(Product product);
    void delete(int id);
}
