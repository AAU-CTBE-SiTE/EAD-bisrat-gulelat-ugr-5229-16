
package com.craft.lab6.controller;

import com.craft.lab6.service.StudentService;
import com.craft.lab6.service.StudentReportService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private final StudentReportService studentReportService;

    public StudentController(
            StudentService studentService,
            StudentReportService studentReportService) {
        this.studentService = studentService;
        this.studentReportService = studentReportService;
    }

    @PostMapping("/{name}")
    public String addStudent(@PathVariable String name) {
        return studentService.addStudent(name);
    }

    @GetMapping("/{name}")
    public String getStudent(@PathVariable String name) {
        return studentService.getStudent(name);
    }

    @DeleteMapping("/{name}")
    public String deleteStudent(@PathVariable String name) {
        studentService.deleteStudent(name);
        return "Student deleted";
    }

    @GetMapping("/error/test")
    public String testError() {
        studentService.failOperation();
        return "Completed";
    }

    @GetMapping("/process/{name}")
    public String processStudent(@PathVariable String name) {
        studentService.processStudent(name);
        return "Student processed";
    }

    @GetMapping("/internal")
    public String internalOperation() {
        studentService.internalStudentOperation();
        return "Internal operation completed";
    }

    @GetMapping("/report")
    public String generateReport() {
        studentReportService.generateReport();
        return "Report generated";
    }
}
