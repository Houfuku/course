package com.example.students.controller;

import com.example.students.dto.StudentForm;
import com.example.students.model.StudyForm;
import com.example.students.security.AuthUser;
import com.example.students.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @ModelAttribute("studyForms")
    public StudyForm[] studyForms() {
        return StudyForm.values();
    }

    @GetMapping
    public String list(@AuthenticationPrincipal AuthUser user,
                       @RequestParam(name = "form", required = false) StudyForm form,
                       Model model) {
        model.addAttribute("students", studentService.list(user.getId(), form));
        model.addAttribute("selectedForm", form);
        return "students/list";
    }

    @GetMapping("/new")
    public String addPage(Model model) {
        model.addAttribute("studentForm", new StudentForm());
        return "students/add";
    }

    @PostMapping("/new")
    public String add(@AuthenticationPrincipal AuthUser user,
                      @Valid @ModelAttribute("studentForm") StudentForm form,
                      BindingResult result,
                      RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "students/add";
        }
        studentService.create(user.getId(), form);
        redirect.addFlashAttribute("message", "Студент добавлен");
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@AuthenticationPrincipal AuthUser user,
                           @PathVariable("id") Long id,
                           Model model) {
        model.addAttribute("studentId", id);
        model.addAttribute("studentForm", StudentForm.from(studentService.get(id, user.getId())));
        return "students/edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(@AuthenticationPrincipal AuthUser user,
                       @PathVariable("id") Long id,
                       @Valid @ModelAttribute("studentForm") StudentForm form,
                       BindingResult result,
                       Model model,
                       RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("studentId", id);
            return "students/edit";
        }
        studentService.update(id, user.getId(), form);
        redirect.addFlashAttribute("message", "Изменения сохранены");
        return "redirect:/students";
    }

    @PostMapping("/{id}/delete")
    public String delete(@AuthenticationPrincipal AuthUser user,
                         @PathVariable("id") Long id,
                         RedirectAttributes redirect) {
        studentService.delete(id, user.getId());
        redirect.addFlashAttribute("message", "Студент удален");
        return "redirect:/students";
    }
}
