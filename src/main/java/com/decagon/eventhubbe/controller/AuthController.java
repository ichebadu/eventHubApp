package com.decagon.eventhubbe.controller;

import com.decagon.eventhubbe.dto.request.RegistrationRequest;
import com.decagon.eventhubbe.dto.request.ResetPasswordRequest;
import com.decagon.eventhubbe.dto.response.APIResponse;
import com.decagon.eventhubbe.dto.response.RegistrationResponse;
import com.decagon.eventhubbe.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<APIResponse<RegistrationResponse>> register (@RequestBody RegistrationRequest registrationRequest) {
        APIResponse<RegistrationResponse> apiResponse = new APIResponse<>(appUserService.register(registrationRequest));
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
