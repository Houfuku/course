package com.example.students.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(name = "group_number", nullable = false, length = 20)
    private String groupNumber;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "study_form", nullable = false, length = 20)
    private StudyForm studyForm;

    protected Student() {
    }

    public Student(User user, String fullName, String groupNumber, String phone, StudyForm studyForm) {
        this.user = user;
        update(fullName, groupNumber, phone, studyForm);
    }


    public void update(String fullName, String groupNumber, String phone, StudyForm studyForm) {
        this.fullName = fullName;
        this.groupNumber = groupNumber;
        this.phone = phone;
        this.studyForm = studyForm;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public String getPhone() {
        return phone;
    }

    public StudyForm getStudyForm() {
        return studyForm;
    }
}
