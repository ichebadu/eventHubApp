package com.decagon.eventhubbe.controller;

import com.decagon.eventhubbe.dto.request.LoginRequest;
import com.decagon.eventhubbe.dto.request.RegistrationRequest;
import com.decagon.eventhubbe.dto.response.APIResponse;
import com.decagon.eventhubbe.dto.response.LoginResponse;
import com.decagon.eventhubbe.dto.response.RefreshTokenResponse;
import com.decagon.eventhubbe.dto.response.RegistrationResponse;
import com.decagon.eventhubbe.service.AppUserService;
import com.decagon.eventhubbe.service.ConfirmationTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(
        name = "CRUD REST APIs for AppUser Resource"
)
public class AuthController {
    private final AppUserService appUserService;
    private final ConfirmationTokenService confirmationTokenService;

    @Operation(
            summary = "User Registration",
            description = "Register a new user."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 CREATED"
    )

    @PostMapping("/register")
    @PostMapping("/register/{usertype}")
    @PostMapping("/register-event-goer")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<APIResponse<RegistrationResponse>> registerEventGoer (@RequestBody RegistrationRequest registrationRequest, HttpServletRequest request) {
        APIResponse<RegistrationResponse> apiResponse = new APIResponse<>(appUserService.registerAsEventGoer(registrationRequest, request));
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
    @Operation(
            summary = "Event creator",
            description = "Registered event-creator can create event."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 CREATED"
    )
    @PostMapping("/register-event-creator")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<APIResponse<RegistrationResponse>> registerUser (@RequestBody RegistrationRequest registrationRequest,
                                                                                @PathVariable String usertype, HttpServletRequest request) {
        APIResponse<RegistrationResponse> apiResponse = new APIResponse<>(appUserService.registerUser(registrationRequest, usertype, request));
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @Operation(
            summary = "User Authentication",
            description = "Authenticate a user."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @PostMapping("/authenticate")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<APIResponse<LoginResponse>> loginUser (@RequestBody LoginRequest loginRequest){
        APIResponse<LoginResponse> apiResponse = new APIResponse<>(appUserService.authenticate(loginRequest));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Refresh Access Token",
            description = "Refresh the user's access token."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )

    @PostMapping("/refresh-token")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<APIResponse<RefreshTokenResponse>> refreshToken(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        APIResponse<RefreshTokenResponse> apiResponse = new APIResponse<>(appUserService.refreshToken(request,response));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @Operation(
            summary = "Verify Email",
            description = "Verify a user's email using a verification token."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @GetMapping("/verify-email")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<APIResponse<String>> verifyUser(@RequestParam("token") String token, HttpServletRequest request){
        APIResponse<String> apiResponse = new APIResponse<>(confirmationTokenService.verifyUser(token,request));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @Operation(
            summary = "Resend Authentication Email",
            description = "Resend the authentication email for a user."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @GetMapping("/new-verification-link")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<APIResponse<String>> resendAuthentication(@RequestParam ("email") String email, HttpServletRequest request){
        APIResponse<String> apiResponse = new APIResponse<>(confirmationTokenService.sendNewVerificationLink(email,request));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Forgot Password",
            description = "Initiate the process to reset a user's password."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @GetMapping("/verify-password-token")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<APIResponse<String>> forgotPassword(@RequestParam ("token") String token){
        APIResponse<String> apiResponse = new APIResponse<>(confirmationTokenService.forgotPassword(token));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @Operation(
            summary = "User Logout",
            description = "Logout a user."
    )
    @GetMapping("/logout")
    public void logout() {
    }
}