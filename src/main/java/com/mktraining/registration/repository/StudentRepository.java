package com.mktraining.registration.repository;

import com.mktraining.registration.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}