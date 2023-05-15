package com.decagon.eventhubbe.service;

import com.decagon.eventhubbe.domain.entities.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {
    void saveConfirmationToken(ConfirmationToken token);
    Optional<ConfirmationToken> getToken(String token);
    void setConfirmedAt(ConfirmationToken confirmationToken);
}
