
package com.craft.lab6.service;

import org.springframework.stereotype.Service;

@Service
public class StudentReportService {

    private final StudentService studentService;

    public StudentReportService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void generateReport() {
        System.out.println("Generating student report...");
        studentService.processStudent("External Call");
    }
}
