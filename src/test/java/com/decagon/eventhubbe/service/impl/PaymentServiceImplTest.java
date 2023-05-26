//package com.decagon.eventhubbe.service.impl;
//
//import com.decagon.eventhubbe.domain.repository.*;
//import com.decagon.eventhubbe.dto.request.PaymentRequest;
//import com.decagon.eventhubbe.domain.entities.*;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.*;
//import org.springframework.web.client.RestTemplate;
//import com.decagon.eventhubbe.dto.response.EventTicketResponse;
//import com.decagon.eventhubbe.dto.response.Response;
//
//import java.util.*;
//@SpringBootTest
//
//class PaymentServiceImplTest {
//    @Mock
//    private EventRepository eventRepository;
//
//    @Mock
//    private AppUserRepository appUserRepository;
//
//    @Mock
//    private AccountRepository accountRepository;
//
//    @Mock
//    private EventTicketRepository eventTicketRepository;
//
//    @Mock
//    private PaymentRepository paymentRepository;
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @Mock
//    private AppUserServiceImpl appUserService;
//
//    @InjectMocks
//    private AccountServiceImpl accountService;
//
//    @InjectMocks
//    private PaymentServiceImpl paymentService;
//    @Test
//    public void makePayment() {
//        PaymentRequest paymentDTO = new PaymentRequest();
//        String id = "6470f65d87673a28e96a017b";
//
//        AppUser user = new AppUser();
//        user.setEmail("chiorlujack@gmail.com");
//
//        Account account = new Account();
//        account.setSubaccount_code("ACCT_cztzxugcr6tsd0g");
//        account.setAccountName("TEMPLE JACK WILLIAM CHIOLRU");
//        account.setAccountNumber("0794940296");
//        account.setBankName("Access Bank");
//        account.setAppUser(user);
//
//        Event event = new Event();
//        event.setAppUser(user);
//
//        List<EventTicketResponse> eventTicketResponseList = new ArrayList<>();
//
//        Mockito.when(eventRepository.findById(Mockito.anyString())).thenReturn(Optional.of(event));
//        Mockito.when(appUserService.getUserByEmail(Mockito.anyString())).thenReturn(user);
//        Mockito.when(accountRepository.findByAppUser(Mockito.any())).thenReturn(Optional.of(account));
//        Mockito.when(eventTicketRepository.findById(Mockito.anyString())).thenReturn(Optional.of(new EventTicket()));
//
//        ResponseEntity<Response> responseEntity = new ResponseEntity<>(HttpStatus.OK);
//        Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.any(), Mockito.any()))
//                .thenReturn(responseEntity);
//
//        String result = paymentService.makePayment(paymentDTO, id);
//
//        Assertions.assertEquals("Payment made successfully", result);
//    }
//
//
//}