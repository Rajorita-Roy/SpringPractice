package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@Service
@Configuration
@ComponentScan
public class ProductService {
    private Map<Integer, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public List<Product> getProductsSortedByName() {
        List<Product> sortedList = new ArrayList<>(products.values());
        // Sorts using the natural order defined in Product (by name)
        sortedList.sort(null);
        return sortedList;
    }

    public Set<Product> getProductsSortedByPrice() {
        // Use a TreeSet with a custom comparator to sort by price
        Set<Product> sortedSet = new TreeSet<>(Product.sortByPrice());
        sortedSet.addAll(products.values());
        return sortedSet;
    }

    public static void main(String[] args) {
        // Load the Spring context from applicationContext.xml
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // Get the ProductService bean
        ProductService productService = (ProductService) context.getBean("ProductService");

        // Add products to the service
        productService.addProduct(new Product(101, "Laptop", 1200.50));
        productService.addProduct(new Product(102, "Mouse", 25.00));
        productService.addProduct(new Product(103, "Keyboard", 75.75));
        productService.addProduct(new Product(104, "Monitor", 350.25));

        // Display products sorted by name
        System.out.println("Products sorted by name:");
        List<Product> sortedByName = productService.getProductsSortedByName();
        sortedByName.forEach(System.out::println);
        System.out.println();

        // Display products sorted by price
        System.out.println("Products sorted by price:");
        Set<Product> sortedByPrice = productService.getProductsSortedByPrice();
        sortedByPrice.forEach(System.out::println);
    }
}
