package com.acme.profileapp.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

  public String getLoggerUser() {
    return SecurityContextHolder.getContext().getAuthentication().getName();
  }

  public boolean isLoggedUserAdmin() {
    List<GrantedAuthority> adminAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
      .filter(auth -> auth.getAuthority().equals("ROLE_ADMIN"))
      .collect(Collectors.toList());

    return !adminAuthorities.isEmpty();
  }

}
