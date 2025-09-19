package com.example;
import java.util.Comparator;

public class Student implements Comparable<Student> {

    private int id;
    private String name;
    private int marks;

    public Student(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getMarks() { return marks; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setMarks(int marks) { this.marks = marks; }

    // Comparator for sorting by marks (descending) and then by id (ascending)
    public static Comparator<Student> sortByMarksAndId() {
        return Comparator.comparing(Student::getMarks).reversed()
                .thenComparing(Student::getId);
    }

    // Default natural ordering by ID
    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}
