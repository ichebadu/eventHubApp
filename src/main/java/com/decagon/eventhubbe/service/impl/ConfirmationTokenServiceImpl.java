package com.decagon.eventhubbe.service.impl;

import com.decagon.eventhubbe.domain.entities.ConfirmationToken;
import com.decagon.eventhubbe.domain.repository.ConfirmationTokenRepository;
import com.decagon.eventhubbe.service.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }
}