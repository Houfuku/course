package com.example.students.dto;

import com.example.students.model.Student;
import com.example.students.model.StudyForm;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class StudentForm {

    @NotBlank(message = "Введите ФИО")
    @Size(max = 150, message = "ФИО не длиннее 150 символов")
    private String fullName;

    @NotBlank(message = "Введите номер группы")
    @Size(max = 20, message = "Номер группы не длиннее 20 символов")
    private String groupNumber;

    @NotBlank(message = "Введите телефон")
    @Pattern(regexp = "^\\+?[0-9\\s()-]{10,20}$",
            message = "Телефон в формате +7 900 123-45-67 (10-20 цифр, пробелы, скобки, дефисы)")
    private String phone;

    @NotNull(message = "Выберите форму обучения")
    private StudyForm studyForm;


    public static StudentForm from(Student student) {
        StudentForm form = new StudentForm();
        form.setFullName(student.getFullName());
        form.setGroupNumber(student.getGroupNumber());
        form.setPhone(student.getPhone());
        form.setStudyForm(student.getStudyForm());
        return form;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public StudyForm getStudyForm() {
        return studyForm;
    }

    public void setStudyForm(StudyForm studyForm) {
        this.studyForm = studyForm;
    }
}
