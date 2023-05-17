package com.decagon.eventhubbe.service;

import com.decagon.eventhubbe.dto.request.LoginRequest;
import com.decagon.eventhubbe.dto.request.RegistrationRequest;
import com.decagon.eventhubbe.dto.response.LoginResponse;
import com.decagon.eventhubbe.dto.response.RegistrationResponse;

public interface AppUserService {
    RegistrationResponse register(RegistrationRequest registrationRequest);

    LoginResponse authenticate(LoginRequest loginRequest);
}