package com.craft.lab6.service;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public String addStudent(String name) {
        System.out.println("Adding student: " + name);
        return name + " added successfully";
    }

    public String getStudent(String name) {
        System.out.println("Fetching student: " + name);
        return "Student: " + name;
    }

    public void deleteStudent(String name) {
        System.out.println("Deleting student: " + name);
    }

    public void failOperation() {
        throw new RuntimeException("Student operation failed!");
    }
}