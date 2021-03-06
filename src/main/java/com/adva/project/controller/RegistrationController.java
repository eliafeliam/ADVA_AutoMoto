package com.adva.project.controller;

import com.adva.project.model.Role;
import com.adva.project.model.Status;
import com.adva.project.model.UserEntity;
import com.adva.project.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;


import javax.validation.Valid;


@Controller
public class RegistrationController {

    private final PasswordEncoder passwordEncoder;
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository, JdbcTemplate jdbcTemplate,
                                  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("userEntity") UserEntity userEntity) {
        return "registration/registration";
    }

    @PostMapping("/registration")
    private String userRegistration(@ModelAttribute("userEntity") @Valid UserEntity userEntity,
                                    BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "registration/registration";
        }
        if (!userEntity.getPassword().equals(userEntity.getPasswordConfirm())){
            model.addAttribute("passwordError", "Hasła nie pasują");
            return "registration/registration";
        }
        if (userRepository.findByEmail(userEntity.getEmail()).isPresent()){
            model.addAttribute("emailIsAlreadyExist",
                    "Użytkownik z takim e-mailem już istnieje");
            return "registration/registration";
        }
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRole(Role.USER);
        userEntity.setStatus(Status.ACTIVE);
        userRepository.save(userEntity);
        return "redirect:/login";
    }
}