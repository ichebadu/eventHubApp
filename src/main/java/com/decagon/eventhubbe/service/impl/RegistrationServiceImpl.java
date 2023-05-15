package com.decagon.eventhubbe.service.impl;

import com.decagon.eventhubbe.ENUM.UserType;
import com.decagon.eventhubbe.domain.entities.AppUser;
import com.decagon.eventhubbe.domain.entities.ConfirmationToken;
import com.decagon.eventhubbe.dto.AppUserDTO;
import com.decagon.eventhubbe.service.AppUserService;
import com.decagon.eventhubbe.service.ConfirmationTokenService;
import com.decagon.eventhubbe.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final AppUserService appUserService;

    public String register(AppUser appUser) {

        return appUserService.signUpUser(
                new AppUser(
                        appUser.getFirstName(),
                        appUser.getLastName(),
                        appUser.getEmail(),
                        appUser.getPassword(),
                        UserType.EVENT_GOER
                )
        );
    }
}