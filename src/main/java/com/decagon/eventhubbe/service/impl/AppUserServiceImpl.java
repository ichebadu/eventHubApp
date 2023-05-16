package com.decagon.eventhubbe.service.impl;

import com.decagon.eventhubbe.domain.entities.AppUser;
import com.decagon.eventhubbe.domain.entities.ConfirmationToken;
import com.decagon.eventhubbe.domain.repository.AppUserRepository;
import com.decagon.eventhubbe.dto.request.RegistrationRequest;
import com.decagon.eventhubbe.dto.response.RegistrationResponse;
import com.decagon.eventhubbe.exception.AppUserAlreadyExistException;
import com.decagon.eventhubbe.service.AppUserService;
import com.decagon.eventhubbe.service.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final ModelMapper modelMapper;

    @Override
    public RegistrationResponse register(RegistrationRequest registrationRequest) {
        boolean userExists = appUserRepository.existsByEmail(registrationRequest.getEmail());

        if (userExists) {
            // TODO check if attributes are the same and
            // TODO if email not confirmed, send confirmation email.
            throw new AppUserAlreadyExistException(registrationRequest.getEmail());
        }

        AppUser appUser = registrationRequestToAppUser(registrationRequest);

        String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());
        appUser.setPassword(encodedPassword);

        AppUser savedUser = appUserRepository.insert(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                savedUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        appUserToRegistrationRequest(appUser);
        // TODO: SEND EMAIL

        return new RegistrationResponse(
                appUser.getEmail(),
                appUser.getFirstName(),
                appUser.getLastName(),
                appUser.getPhone(),
                confirmationToken.getToken(), false);
    }

    public void appUserToRegistrationRequest (AppUser appUser) {
        modelMapper.map(appUser, RegistrationRequest.class);
    }

    public AppUser registrationRequestToAppUser (RegistrationRequest registrationRequest) {
        return modelMapper.map(registrationRequest, AppUser.class);
    }
}
