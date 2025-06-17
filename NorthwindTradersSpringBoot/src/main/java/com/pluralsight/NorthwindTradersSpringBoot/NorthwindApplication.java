package com.pluralsight.NorthwindTradersSpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.List;

@Component
public class NorthwindApplication implements CommandLineRunner {

    @Autowired
    private ProductDao productDao;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. List Products\n2. Add Product\n3. Search Product\n4. Update Product\n5. Delete Product\n6. Exit");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    List<Product> products = productDao.getAll();
                    for (Product p : products) {
                        System.out.printf("ID: %d | Name: %s | Category: %s | Price: %.2f\n",
                                p.getProductId(), p.getName(), p.getCategory(), p.getPrice());
                    }
                }
                case 2 -> {
                    System.out.print("Enter ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Category: ");
                    String category = scanner.nextLine();
                    System.out.print("Price: ");
                    double price = Double.parseDouble(scanner.nextLine());

                    productDao.add(new Product(id, name, category, price));
                    System.out.println("Product added.");
                }
                case 3 -> {
                    System.out.print("Enter ID to search: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Product found = productDao.findById(id);
                    if (found != null) {
                        System.out.printf("Found: ID: %d | Name: %s | Category: %s | Price: %.2f\n",
                                found.getProductId(), found.getName(), found.getCategory(), found.getPrice());
                    } else {
                        System.out.println("Product not found.");
                    }
                } case 4 -> {
                    System.out.print("Enter ID to update: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Product existing = productDao.findById(id);
                    if (existing != null) {
                        System.out.print("New Name: ");
                        String name = scanner.nextLine();
                        System.out.print("New Category: ");
                        String category = scanner.nextLine();
                        System.out.print("New Price: ");
                        double price = Double.parseDouble(scanner.nextLine());

                        productDao.update(new Product(id, name, category, price));
                        System.out.println("Product updated.");
                    } else {
                        System.out.println("Product not found.");
                    }

                }
                case 6 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
