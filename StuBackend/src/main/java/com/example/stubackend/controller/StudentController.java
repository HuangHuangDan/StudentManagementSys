package com.example.stubackend.controller;

import com.example.stubackend.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.stubackend.service.IStudentService;

import java.util.List;

@CrossOrigin("http:/localhost:3000")
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final IStudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getStudent() {
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.FOUND);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable Long id) {
        return studentService.updateStudent(student, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);

    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

}
