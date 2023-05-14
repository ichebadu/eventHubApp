package com.decagon.eventhubbe.service.impl;

import com.decagon.eventhubbe.service.AppUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Override
    public UserDetails loadUserByUsername(String username) {
        return null;
    }
}
