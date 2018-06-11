package com.acme.profileapp.service;

import com.acme.profileapp.model.User;
import com.acme.profileapp.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceUnitTest {

  @InjectMocks
  private UserService userService;

  @Mock
  private PasswordEncoder passwordEncoder;

  @Mock
  private UserRepository userRepository;

  @Captor
  private ArgumentCaptor<User> userArgumentCaptor;

  @Test
  public void shouldCreateUser() throws Exception {
    //given
    String encodedPassword = "encTestUser";

    given(passwordEncoder.encode(anyString())).willReturn(encodedPassword);
    User user = new User("testUser", "testUser");

    //when
    userService.createUser(user);

    //then
    verify(userRepository).save(userArgumentCaptor.capture());

    User capturedUser = userArgumentCaptor.getValue();
    assertThat(capturedUser).isNotNull();
    assertThat(capturedUser.getUsername()).isEqualTo("testUser");
    assertThat(capturedUser.getPassword()).isEqualTo(encodedPassword);
    assertThat(capturedUser.getRoles().size()).isEqualTo(1);
  }

  @Test
  public void shouldFindByUsername() throws Exception {
    //given
    given(userRepository.findUserByUsernameEquals(anyString())).willReturn(new User("testUser", "passUser"));
    User user = new User("testUser", "testUser");

    //when
    User result = userService.findUserByUsername("testUser");

    //then
    verify(userRepository).findUserByUsernameEquals(anyString());

    assertThat(result).isNotNull();
    assertThat(result.getUsername()).isEqualTo("testUser");
    assertThat(result.getPassword()).isEqualTo("passUser");
  }

}