
package com.craft.lab6.service;

import com.craft.lab6.aspect.TrackExecutionTime;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @TrackExecutionTime
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

    public void processStudent(String name) {
        System.out.println("Processing student: " + name);
    }

    // Demonstrates self-invocation
    public void internalStudentOperation() {
        processStudent("Internal Student");
    }
}
