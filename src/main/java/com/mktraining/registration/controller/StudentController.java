package com.mktraining.registration.controller;


import com.mktraining.registration.entity.Student;
import com.mktraining.registration.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentRepository studentRepository;
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String course,
            @RequestParam String skills,
            @RequestParam("resume") MultipartFile resumeFile
    ) {
        try {
            Student student = new Student();
            student.setName(name);
            student.setEmail(email);
            student.setPhone(phone);
            student.setCourse(course);
            student.setSkills(skills);
            student.setResume(resumeFile.getOriginalFilename());

            // You can also save the file to disk here

            studentRepository.save(student);
            return ResponseEntity.ok("Registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
