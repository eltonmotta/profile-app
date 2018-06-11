package com.acme.profileapp.repository;

import com.acme.profileapp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
  User findUserByUsernameEquals(String username);
}
