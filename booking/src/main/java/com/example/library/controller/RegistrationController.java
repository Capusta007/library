package com.example.library.controller;

import com.example.library.dto.RegistrationRequestDto;
import com.example.library.service.RegistrationService;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @GetMapping
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new RegistrationRequestDto());
        return "registration";
    }

    @PostMapping
    public String processRegisterForm(@ModelAttribute("user") @Valid RegistrationRequestDto dto) {
        log.debug("Processing registration request: {}", dto);

        try {
            registrationService.registerUser(dto);
        } catch (EntityExistsException e) {
            return "redirect:/registration?error";
        }

        return "redirect:/login";
    }
}
