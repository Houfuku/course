package com.example.students.controller;

import com.example.students.dto.RegisterForm;
import com.example.students.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/students";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerForm") RegisterForm form,
                           BindingResult result) {
        if (!result.hasFieldErrors("login") && userService.loginTaken(form.getLogin())) {
            result.rejectValue("login", "taken", "Такой логин уже занят");
        }
        if (result.hasErrors()) {
            return "register";
        }
        userService.register(form);
        return "redirect:/login?registered";
    }
}
