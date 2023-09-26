package com.example.testtesttest.security;

import com.example.testtesttest.pojo.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository  extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findAppUserByLogin (String Login);

}
