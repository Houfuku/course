package com.example.students.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class RegisterForm {

    @NotBlank(message = "Введите логин")
    @Size(min = 3, max = 50, message = "Логин должен быть от 3 до 50 символов")
    @Pattern(regexp = "^[A-Za-z0-9_.-]*$",
            message = "Логин может содержать только латинские буквы, цифры, точку, дефис и подчеркивание")
    private String login;

    @NotBlank(message = "Введите пароль")
    @Size(min = 8, max = 72, message = "Пароль должен быть от 8 до 72 символов")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
