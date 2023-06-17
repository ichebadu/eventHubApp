package com.decagon.eventhubbe.controller;

import com.decagon.eventhubbe.dto.request.ResetPasswordRequest;
import com.decagon.eventhubbe.dto.response.APIResponse;
import com.decagon.eventhubbe.service.PasswordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password")
@RequiredArgsConstructor
@SecurityRequirement(
        name = "Bear Authentication"
)
@Tag(
        name = "Password Management API"
)
public class PasswordController {
    private final PasswordService passwordService;

    @Operation(
            summary = "Forgot Password",
            description = "Initiates the password reset process by sending a reset link to the user's email."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 OK"
    )
    @GetMapping("/forgot-password")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<APIResponse<String>> forgotPassword(@RequestParam("email") String email,
                                                              HttpServletRequest request){
        APIResponse<String> apiResponse = new APIResponse<>(passwordService.forgotPassword(email,request));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @Operation(
            summary = "Reset Password",
            description = "Resets the user's password using the provided reset token and new password."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 OK"
    )
    @PostMapping("/reset")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<APIResponse<String>> resetPassword(@RequestBody ResetPasswordRequest request){
        APIResponse<String> apiResponse = new APIResponse<>(passwordService.resetPassword(request));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
