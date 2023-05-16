package com.decagon.eventhubbe.service.impl;

import com.decagon.eventhubbe.domain.entities.AppUser;
import com.decagon.eventhubbe.domain.repository.AppUserRepository;
import com.decagon.eventhubbe.dto.request.ResetPasswordRequest;
import com.decagon.eventhubbe.exception.AppUserNotFoundException;
import com.decagon.eventhubbe.exception.SamePasswordException;
import com.decagon.eventhubbe.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    @Override
    public String resetPassword(ResetPasswordRequest request){
        AppUser appUser = findUser(request.getEmail());
        if(passwordEncoder.matches(request.getPassword(), appUser.getPassword())){
            throw new SamePasswordException("Please Choose a Different Password");
        }
        appUser.setPassword(passwordEncoder.encode(request.getPassword()));
        return "Password Changed Successfully";
    }
    private AppUser findUser(String email){
        Optional<AppUser> optionalAppUser = appUserRepository.findByEmail(email);
        if(optionalAppUser.isEmpty()){
            throw new AppUserNotFoundException(email);
        }
        return optionalAppUser.get();
    }

}
