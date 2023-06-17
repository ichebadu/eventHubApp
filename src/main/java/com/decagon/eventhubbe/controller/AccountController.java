package com.decagon.eventhubbe.controller;

import com.decagon.eventhubbe.dto.request.AccountRequest;
import com.decagon.eventhubbe.dto.request.AccountRequestDTO;
import com.decagon.eventhubbe.dto.request.RequestAccountDTO;
import com.decagon.eventhubbe.dto.response.APIResponse;
import com.decagon.eventhubbe.dto.response.AccountResponse;
import com.decagon.eventhubbe.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kotlin.OptionalExpectation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/bank/")
@RequiredArgsConstructor
@SecurityRequirement(
        name = "Bear Authentication"
)
@PreAuthorize("hasRole('users')")
@Tag(
        name = "CRUD REST APIs for Account Resource"
)

public class AccountController {

    private final AccountService accountService;

    @Operation(
            summary = "Get Account Name",
            description = "Retrieve the account name based on the bank name and account number."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @GetMapping("/getName")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> getAccountName(@RequestParam String bankName,
                                            @RequestParam String accountNumber) {
        return ResponseEntity.ok().body(
                accountService.getBankCodeAndSend(bankName, accountNumber));
    }
    @Operation(
            summary = "Create account",
            description = "Create a new account using the provided account details."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @PostMapping("/create-account")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<APIResponse<RequestAccountDTO>> saveAccount(@RequestBody RequestAccountDTO requestAccountDTO) {
        APIResponse<RequestAccountDTO> apiResponse = new APIResponse<>(accountService.saveAccount(requestAccountDTO));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @Operation(
            summary = "Get user's accounts",
            description = "Retrieve the account name based on the bank name and account number."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @GetMapping("/get-user-account/{userid}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<APIResponse<List<?>>> getAccountByUser(@PathVariable  String userid){
        APIResponse<List<?>> apiResponse = new APIResponse<List<?>>(accountService.getAccountByLogedInUser(userid));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @Operation(
            summary = "Update account",
            description = "Update an existing account with the provided account details."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @PutMapping("/updateAccount")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<APIResponse<AccountResponse>> updateAccount(@RequestBody AccountRequestDTO accountRequestDTO){
        APIResponse<AccountResponse> apiResponse =new APIResponse<>(accountService.updateAccount(accountRequestDTO));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }

    @Operation(
            summary = "Delete account",
            description = "Delete an existing account with the provided account details."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @DeleteMapping("/delete/{sub_account}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<APIResponse<AccountResponse>> delete(@PathVariable String sub_account){
        APIResponse<AccountResponse> apiResponse = new APIResponse<>(accountService.deleteAccount(sub_account));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

}
