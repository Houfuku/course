package com.example.students.service;

import com.example.students.dto.RegisterForm;
import com.example.students.model.User;
import com.example.students.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean loginTaken(String login) {
        return userRepository.existsByLogin(login.trim());
    }


    @Transactional
    public void register(RegisterForm form) {
        String hash = passwordEncoder.encode(form.getPassword());
        userRepository.save(new User(form.getLogin().trim(), hash));
    }
}
