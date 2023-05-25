package com.decagon.eventhubbe.service;

import com.decagon.eventhubbe.domain.entities.ConfirmationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface ConfirmationTokenService {
    void saveConfirmationToken(ConfirmationToken token);
}
