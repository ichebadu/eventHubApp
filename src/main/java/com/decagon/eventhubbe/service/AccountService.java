package com.decagon.eventhubbe.service;import com.decagon.eventhubbe.dto.request.RequestAccountDTO;import com.decagon.eventhubbe.dto.request.BankData;import com.decagon.eventhubbe.dto.request.SubAccountRequest;import com.decagon.eventhubbe.dto.response.BanksRepo;import org.apache.tomcat.util.json.ParseException;import org.springframework.http.HttpHeaders;import java.util.List;public interface AccountService {    List<?> getBankCodeAndSend(String bankName, String accountNumber);    RequestAccountDTO saveAccount(RequestAccountDTO requestAccountDTO);    BanksRepo subAccount(HttpHeaders headers, SubAccountRequest request);}