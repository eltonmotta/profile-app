package com.acme.profileapp.service;

import com.acme.profileapp.model.CreditCard;
import com.acme.profileapp.model.User;
import com.acme.profileapp.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService {

  private final CreditCardRepository creditCardRepository;
  private final AuthenticationService authenticationService;
  private final UserService userService;

  @Autowired
  public CreditCardService(CreditCardRepository creditCardRepository, AuthenticationService authenticationService, UserService userService) {
    this.creditCardRepository = creditCardRepository;
    this.authenticationService = authenticationService;
    this.userService = userService;
  }

  public List<CreditCard> findByNumber(String number) {
    List<CreditCard> creditCards;

    if (authenticationService.isLoggedUserAdmin()) {
      creditCards = creditCardRepository.findAllByNumberContaining(number);
    } else {
      creditCards = creditCardRepository.findAllByNumberContainingAndOwner_UsernameEquals(number, authenticationService.getLoggerUser());
    }

    return creditCards;
  }

  public void saveOrUpdateCreditCard(CreditCard creditCard) {
    String loggedUser = authenticationService.getLoggerUser();
    User user = userService.findUserByUsername(loggedUser);

    CreditCard storedCreditCard = creditCardRepository.findByNumberEqualsAndOwner_UsernameEquals(creditCard.getNumber(), loggedUser);
    if (storedCreditCard == null) {
      creditCard.setOwner(user);
    } else {
      storedCreditCard.setExpirationMonth(creditCard.getExpirationMonth());
      storedCreditCard.setExpirationYear(creditCard.getExpirationYear());
      creditCard = storedCreditCard;
    }

    creditCardRepository.save(creditCard);
  }


}
