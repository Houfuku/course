package com.example.students.repository;

import com.example.students.model.Student;
import com.example.students.model.StudyForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByUserIdOrderByFullNameAsc(Long userId);

    List<Student> findByUserIdAndStudyFormOrderByFullNameAsc(Long userId, StudyForm studyForm);

    Optional<Student> findByIdAndUserId(Long id, Long userId);
}
