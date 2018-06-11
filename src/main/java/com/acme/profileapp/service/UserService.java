package com.acme.profileapp.service;

import com.acme.profileapp.model.Role;
import com.acme.profileapp.model.User;
import com.acme.profileapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

  private final PasswordEncoder passwordEncoder;

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public void createUser(User user) {
    setDefaultRole(user);

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    userRepository.save(user);
  }

  public User findUserByUsername(String username) {
    return userRepository.findUserByUsernameEquals(username);
  }

  private void setDefaultRole(User user) {
    Set<Role> roles = new HashSet<>();
    roles.add(new Role("ROLE_USER", user));

    user.setRoles(roles);
  }

}
