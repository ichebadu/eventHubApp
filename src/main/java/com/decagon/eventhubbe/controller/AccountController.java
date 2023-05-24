package com.decagon.eventhubbe.controller;

import com.decagon.eventhubbe.domain.entities.Account;
import com.decagon.eventhubbe.dto.RequestAccountDTO;
import com.decagon.eventhubbe.dto.request.PaymentRequest;
import com.decagon.eventhubbe.dto.request.SubAccountRequest;
import com.decagon.eventhubbe.dto.response.APIResponse;
import com.decagon.eventhubbe.dto.response.Response;
import com.decagon.eventhubbe.service.impl.AccountServiceImpl;
import com.decagon.eventhubbe.service.impl.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bank/")
@RequiredArgsConstructor

public class AccountController {
    private final AccountServiceImpl accountService;
    private final PaymentServiceImpl paymentService;

    /***
     *  JACKSON GET ALL BANKS PAY STACK
     * @return
     * @throws ParseException
     */
    @GetMapping("/get_banks")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> getBanks() throws ParseException {
        return ResponseEntity.ok().body(
                accountService.getBankApiCodeDetails()
        );
    }

    /***
     * GET ACCOUNT NAME
     * JACKSON
     * PAY STACK
     * @param bankName
     * @param accountNumber
     * @return
     */
    @GetMapping("/getName")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> getAccountName(@RequestParam String bankName,
                                            @RequestParam String accountNumber) {
        return ResponseEntity.ok().body(
                accountService.getBankCodeAndSend(bankName, accountNumber));
    }

    /***
     * JACKSON SAVING ACCOUNT
     * @param requestAccountDTO
     * @return
     */
    @PostMapping("/create-account")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<APIResponse<RequestAccountDTO>> saveAccount(@RequestBody RequestAccountDTO requestAccountDTO) {
        APIResponse<RequestAccountDTO> apiResponse = new APIResponse<>(accountService.saveAccount(requestAccountDTO));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /***
     * MICHEAL JOHN API CALL
     * UPDATE ACCOUNT
     */
    @PutMapping("/update{accountId}")
    public ResponseEntity<APIResponse<RequestAccountDTO>> updateAccount(@PathVariable String accountId, @RequestBody RequestAccountDTO requestAccountDTO) {
        APIResponse<RequestAccountDTO> apiResponse = new APIResponse<>(accountService.updateAccount(requestAccountDTO, accountId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /***
     * MICHEAL JOHN API CALL
     * DELETE ACCOUNT
     */
    @DeleteMapping("/delete{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable String accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.ok().body("Deleted");
    }

    @GetMapping("/subaccounts")
    public ResponseEntity<APIResponse<List<Account>>> getAllSubAccount(){
        APIResponse<List<Account>> listAPIResponse = new APIResponse<>(accountService.getAccount());
        return new ResponseEntity<>(listAPIResponse,HttpStatus.OK);

    }
    @PostMapping("/savePayment")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<APIResponse<PaymentRequest>> acceptTransfer(@RequestBody PaymentRequest paymentRequest) {
        APIResponse<PaymentRequest> save = new APIResponse<>(paymentService.payment(paymentRequest));
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }


}
