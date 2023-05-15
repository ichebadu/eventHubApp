package com.decagon.eventhubbe.service;

import com.decagon.eventhubbe.domain.entities.AppUser;

public interface AppUserService {
//    AppUser loadUserByUsername(String username);
    String signUpUser(AppUser appUser);
}
