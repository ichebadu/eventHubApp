package com.decagon.eventhubbe.service.impl;

import com.decagon.eventhubbe.domain.repository.AppUserRepository;
import com.decagon.eventhubbe.security.JwtService;
import com.decagon.eventhubbe.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


}
