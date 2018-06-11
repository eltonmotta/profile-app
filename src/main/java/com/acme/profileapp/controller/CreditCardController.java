package com.acme.profileapp.controller;

import com.acme.profileapp.model.CreditCard;
import com.acme.profileapp.model.CreditCardDTO;
import com.acme.profileapp.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CreditCardController {

  private final CreditCardService creditCardService;

  @Autowired
  public CreditCardController(CreditCardService creditCardService) {
    this.creditCardService = creditCardService;
  }

  @GetMapping("/credit-cards")
  public String showCreditCardsSearch(Model model) {
    model.addAttribute("creditCard", new CreditCardDTO());
    model.addAttribute("creditCards", creditCardService.findByNumber(""));
    return "searchCreditCardsView";
  }

  @PostMapping("/credit-cards")
  public String saveCreditCard(@Valid @ModelAttribute("creditCard") CreditCard creditCard, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "editCreditCardView";
    }

    creditCardService.saveOrUpdateCreditCard(creditCard);

    return "redirect:/credit-cards";
  }

  @PostMapping("/credit-cards/search")
  public String searchCreditCards(@ModelAttribute("creditCard") CreditCardDTO creditCard, Model model) {
    model.addAttribute("creditCards", creditCardService.findByNumber(creditCard.getNumber()));
    return "searchCreditCardsView";
  }

  @GetMapping("/credit-cards/new")
  public String createNewCreditCard(Model model) {
    model.addAttribute("creditCard", new CreditCard());
    return "editCreditCardView";
  }

}
