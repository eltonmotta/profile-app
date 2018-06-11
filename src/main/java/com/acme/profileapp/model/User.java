package com.acme.profileapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User {

  @Id
  @Column(unique = true)
  @NotBlank(message = "Username cannot be blank")
  @Size(min = 1, max=30, message = "Username size must be between {min} and {max}")
  private String username;

  @NotBlank(message = "Password cannot be blank")
  @Size(min = 5, max=60, message = "Password size must be between {min} and {max}")
  private String password;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private Set<Role> roles = new HashSet<>();

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User() {
  }

}
