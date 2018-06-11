package com.acme.profileapp.controller;

import com.acme.profileapp.model.User;
import com.acme.profileapp.model.UserDTO;
import com.acme.profileapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  public UserController() {
  }

  @GetMapping("users/new")
  public String createNewUser(Model model) {
    model.addAttribute("user", new UserDTO());
    return "registerUserView";
  }

  @PostMapping("/users")
  public String registerUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "registerUserView";
    }

    if (!user.isPasswordConfirmed()) {
      bindingResult.rejectValue("passwordConfirmation", "password.confirmation", "Password and confirmation should match");
      return "registerUserView";
    }

    userService.createUser(convertFromDTO(user));

    return "redirect:/login";
  }

  private User convertFromDTO(UserDTO userDTO) {
    return new User(userDTO.getUsername(), userDTO.getPassword());
  }

}