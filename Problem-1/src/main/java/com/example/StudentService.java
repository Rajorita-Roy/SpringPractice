package com.example;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Configuration
@ComponentScan
public class StudentService {

    public void printStudentsSortedById(List<Student> students) {
        students.sort(null);
        System.out.println("Sorted by ID:");
        students.forEach(System.out::println);
    }

    public void printStudentRankings(List<Student> students) {
        students.sort(Student.sortByMarksAndId());
        System.out.println("Sorted by Rank (Marks Desc, ID Asc):");
        students.forEach(System.out::println);
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(StudentService.class);
        StudentService studentService = context.getBean(StudentService.class);

        List<Student> students = new ArrayList<>();
        students.add(new Student(101, "Alice", 95));
        students.add(new Student(102, "Bob", 88));
        students.add(new Student(103, "Charlie", 95));
        students.add(new Student(104, "David", 76));

        studentService.printStudentsSortedById(students);
        System.out.println();
        studentService.printStudentRankings(students);
    }

}
