package com.acme.profileapp.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDTO {

  @NotBlank(message = "Username cannot be blank")
  @Size(min = 1, max = 30, message = "Username size must be between {min} and {max}")
  private String username;

  @NotBlank(message = "Password cannot be blank")
  @Size(min = 5, max = 30, message = "Password size must be between {min} and {max}")
  private String password;

  @NotBlank(message = "Password confirmation cannot be blank")
  @Size(min = 5, max = 60, message = "Password confirmation size must be between {min} and {max}")
  private String passwordConfirmation;

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

  public String getPasswordConfirmation() {
    return passwordConfirmation;
  }

  public void setPasswordConfirmation(String passwordConfirmation) {
    this.passwordConfirmation = passwordConfirmation;
  }

  public UserDTO(String username, String password, String passwordConfirmation) {
    this.username = username;
    this.password = password;
    this.passwordConfirmation = passwordConfirmation;
  }

  public UserDTO() {
  }

  public boolean isPasswordConfirmed() {
    return this.password != null && this.passwordConfirmation != null && this.password.equals(this.passwordConfirmation);
  }

}
