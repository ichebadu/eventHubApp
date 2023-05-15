package com.decagon.eventhubbe.controller;

import com.decagon.eventhubbe.service.impl.AppUserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AppUserServiceImpl appUserService;

}
