package com.example.students.service;

import com.example.students.dto.StudentForm;
import com.example.students.model.Student;
import com.example.students.model.StudyForm;
import com.example.students.model.User;
import com.example.students.repository.StudentRepository;
import com.example.students.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public StudentService(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }


    @Transactional(readOnly = true)
    public List<Student> list(Long userId, StudyForm filter) {
        if (filter == null) {
            return studentRepository.findByUserIdOrderByFullNameAsc(userId);
        }
        return studentRepository.findByUserIdAndStudyFormOrderByFullNameAsc(userId, filter);
    }


    @Transactional(readOnly = true)
    public Student get(Long id, Long userId) {
        return studentRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Студент не найден"));
    }

    @Transactional
    public void create(Long userId, StudentForm form) {
        User owner = userRepository.getReferenceById(userId);
        studentRepository.save(new Student(owner,
                form.getFullName().trim(), form.getGroupNumber().trim(),
                form.getPhone().trim(), form.getStudyForm()));
    }

    @Transactional
    public void update(Long id, Long userId, StudentForm form) {
        Student student = get(id, userId);
        student.update(form.getFullName().trim(), form.getGroupNumber().trim(),
                form.getPhone().trim(), form.getStudyForm());

    }

    @Transactional
    public void delete(Long id, Long userId) {
        studentRepository.delete(get(id, userId));
    }
}
