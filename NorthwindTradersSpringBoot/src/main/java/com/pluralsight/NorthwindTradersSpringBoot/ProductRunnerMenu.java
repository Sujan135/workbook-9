package com.pluralsight.NorthwindTradersSpringBoot;

import com.pluralsight.NorthwindTradersSpringBoot.ProductDao;
import com.pluralsight.NorthwindTradersSpringBoot.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.List;

@Component
public class ProductRunnerMenu {
    private final ProductDao productDao;

    @Autowired
    public ProductRunnerMenu(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. List Products\n2. Add Product\n3. Exit");
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
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
