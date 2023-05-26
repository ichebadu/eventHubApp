package com.decagon.eventhubbe.service.impl;

import com.decagon.eventhubbe.domain.entities.Account;
import com.decagon.eventhubbe.domain.entities.AppUser;
import com.decagon.eventhubbe.domain.repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AccountServiceImplTest {
    @Mock
    private EventRepository eventRepository;

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private EventTicketRepository eventTicketRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private AppUserServiceImpl appUserService;

    @InjectMocks
    private AccountServiceImpl accountService;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    void getBankApiCodeDetails() {

    }

    @Test
    void saveAccount() {
    }

    @Test
    void getBankCodeAndSend() {
    }

    @Test
    void subAccount() {
    }

    @Test
    void updateAccount() {
    }
    @Test
    void deleteAccount() {
        String accountId = "646ec04e3681c87f25685e48";
        String userEmail = "chiorlujack@gmail.com";

            SecurityContext securityContext = Mockito.mock(SecurityContext.class);
            Mockito.when(securityContext.getAuthentication()).thenReturn(Mockito.mock(Authentication.class));
            Mockito.when(securityContext.getAuthentication().getName()).thenReturn(userEmail);
            SecurityContextHolder.setContext(securityContext);

            AppUser appUser = new AppUser();
            appUser.setEmail(userEmail);
            Account account = new Account();
            account.setId(accountId);

            Mockito.when(appUserRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(appUser));
            Mockito.when(accountRepository.findById(Mockito.anyString())).thenReturn(Optional.of(account));

            accountService.deleteAccount(accountId);

            SecurityContextHolder.clearContext();
        }


    }