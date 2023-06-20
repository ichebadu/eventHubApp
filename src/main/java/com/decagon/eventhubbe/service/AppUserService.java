package com.decagon.eventhubbe.service;

import com.decagon.eventhubbe.dto.request.LoginRequest;
import com.decagon.eventhubbe.dto.request.RegistrationRequest;
import com.decagon.eventhubbe.dto.response.LoginResponse;
import com.decagon.eventhubbe.dto.response.RefreshTokenResponse;
import com.decagon.eventhubbe.dto.response.RegistrationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface AppUserService {
    RegistrationResponse registerAsEventGoer(RegistrationRequest registrationRequest, String usertype,
                                             HttpServletRequest request);

    LoginResponse authenticate(LoginRequest loginRequest);

    RefreshTokenResponse refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}