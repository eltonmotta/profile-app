package com.acme.profileapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceUnitTest {

  private AuthenticationService authenticationService = new AuthenticationService();

  @Test
  public void shouldReturnTrueIfLoggedUserIsAdmin() {
    //given
    List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
    authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("testUser", "testPass", authorityList));

    //when
    boolean result = authenticationService.isLoggedUserAdmin();
    //then
    assertTrue(result);
  }

  @Test
  public void shouldReturnFalseIfLoggedUserIsNormalUser() {
    //given
    List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
    authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("testUser", "testPass", authorityList));

    //when
    boolean result = authenticationService.isLoggedUserAdmin();
    //then
    assertFalse(result);
  }

  @Test
  public void shouldReturnLoggedUser() throws Exception {
    //given
    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("testUser", "testPass"));

    //when
    String result = authenticationService.getLoggerUser();
    //then
    assertThat(result).isNotNull();
    assertThat(result).isEqualTo("testUser");
  }

}