package com.example.testtesttest.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceDetails implements UserDetailsService {
    private final AppUserRepository repository;
    private final AppUserMapper mapper;
    private final AppUserDetails appUserDetails;


    public AppUserServiceDetails(AppUserRepository repository, AppUserDetails appUserDetails, AppUserMapper mapper) {
        this.repository = repository;
        this.appUserDetails = appUserDetails;
       this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var appUser = repository.findAppUserByLogin(username)
                .orElseThrow(() -> new IllegalStateException("User not found"));
        appUserDetails.setUserDetails(mapper.toDto(appUser));
        return appUserDetails;
    }
}