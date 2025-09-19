package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Configuration
@ComponentScan
public class EmployeeService {

    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public Map<String, List<Employee>> getEmployeesByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public Employee getTopPaidEmployee() {
        return employees.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }

    public void printEmployeesSortedByName() {
        Collections.sort(employees, (e1, e2) -> e1.getName().compareTo(e2.getName()));
        System.out.println("Employees sorted by name:");
        employees.forEach(System.out::println);
    }

    public static void main(String[] args) {
        // Create an application context from the configuration class
        ApplicationContext context = new AnnotationConfigApplicationContext(EmployeeService.class);

        // Get the EmployeeService bean
        EmployeeService employeeService = context.getBean(EmployeeService.class);

        // Add employees
        employeeService.addEmployee(new Employee(101, "Alice", 75000, "HR"));
        employeeService.addEmployee(new Employee(102, "Bob", 85000, "IT"));
        employeeService.addEmployee(new Employee(103, "Charlie", 75000, "HR"));
        employeeService.addEmployee(new Employee(104, "David", 95000, "IT"));
        employeeService.addEmployee(new Employee(105, "Eve", 60000, "Finance"));

        // Sort and print employees by name
        employeeService.printEmployeesSortedByName();
        System.out.println();

        // Group employees by department and print
        System.out.println("Employees grouped by department:");
        Map<String, List<Employee>> employeesByDept = employeeService.getEmployeesByDepartment();
        employeesByDept.forEach((dept, list) -> {
            System.out.println("--- " + dept + " ---");
            list.forEach(System.out::println);
        });
        System.out.println();

        // Find and print the top paid employee
        Employee topPaid = employeeService.getTopPaidEmployee();
        System.out.println("Top Paid Employee: " + topPaid);
    }
}

