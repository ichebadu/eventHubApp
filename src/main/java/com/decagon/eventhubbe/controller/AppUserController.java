package com.decagon.eventhubbe.controller;

import com.decagon.eventhubbe.domain.entities.AppUser;
import com.decagon.eventhubbe.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class AppUserController {
    private final RegistrationService registrationService;
    @PostMapping
    public String register(@RequestBody AppUser appUser) {
        return registrationService.register(appUser);
    }
}