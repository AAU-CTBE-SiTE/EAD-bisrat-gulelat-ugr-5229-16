package com.craft.lab6.controller;

import com.craft.lab6.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
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
}