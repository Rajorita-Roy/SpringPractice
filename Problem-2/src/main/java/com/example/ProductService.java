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
        sortedList.sort(null);
        return sortedList;
    }

    public Set<Product> getProductsSortedByPrice() {
        Set<Product> sortedSet = new TreeSet<>(Product.sortByPrice());
        sortedSet.addAll(products.values());
        return sortedSet;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        ProductService productService = (ProductService) context.getBean("ProductService");
        
        productService.addProduct(new Product(101, "Laptop", 1200.50));
        productService.addProduct(new Product(102, "Mouse", 25.00));
        productService.addProduct(new Product(103, "Keyboard", 75.75));
        productService.addProduct(new Product(104, "Monitor", 350.25));

        System.out.println("Products sorted by name:");
        List<Product> sortedByName = productService.getProductsSortedByName();
        sortedByName.forEach(System.out::println);
        System.out.println();

        System.out.println("Products sorted by price:");
        Set<Product> sortedByPrice = productService.getProductsSortedByPrice();
        sortedByPrice.forEach(System.out::println);
    }
}

