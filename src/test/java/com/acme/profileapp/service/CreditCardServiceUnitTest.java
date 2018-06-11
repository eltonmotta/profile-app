package com.acme.profileapp.service;

import com.acme.profileapp.model.CreditCard;
import com.acme.profileapp.model.User;
import com.acme.profileapp.repository.CreditCardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardServiceUnitTest {

  @InjectMocks
  private CreditCardService creditCardService;

  @Mock
  private AuthenticationService authenticationService;

  @Mock
  private UserService userService;

  @Mock
  private CreditCardRepository creditCardRepository;

  @Captor
  private ArgumentCaptor<CreditCard> creditCardArgumentCaptor;

  @Test
  public void shouldFindByNumberForAdmin() {
    //given
    given(authenticationService.isLoggedUserAdmin()).willReturn(true);
    given(creditCardRepository.findAllByNumberContaining(anyString())).willReturn(createCreditCardsList());

    //when
    List<CreditCard> result = creditCardService.findByNumber("4111");

    //then
    verify(creditCardRepository).findAllByNumberContaining(anyString());
    assertThat(result).isNotNull();
    assertThat(result).size().isEqualTo(2);
  }

  @Test
  public void shouldFindByNumberForNormalUsers() {
    //given
    given(authenticationService.isLoggedUserAdmin()).willReturn(false);
    given(authenticationService.getLoggerUser()).willReturn("testUser");

    given(creditCardRepository.findAllByNumberContainingAndOwner_UsernameEquals(anyString(), anyString())).willReturn(createCreditCardsList());

    //when
    List<CreditCard> result = creditCardService.findByNumber("4111");

    //then
    verify(creditCardRepository).findAllByNumberContainingAndOwner_UsernameEquals(anyString(), anyString());
    assertThat(result).isNotNull();
    assertThat(result).size().isEqualTo(2);
  }

  @Test
  public void shouldSaveANewCreditCard() {
    //given
    given(authenticationService.getLoggerUser()).willReturn("testUser");
    given(creditCardRepository.findByNumberEqualsAndOwner_UsernameEquals(anyString(), anyString())).willReturn(null);
    given(userService.findUserByUsername(anyString())).willReturn(new User("testUser", " testUser"));

    CreditCard creditCard = new CreditCard("4111111111111111", "TEST CARD", 1, 2020, null);

    //when
    creditCardService.saveOrUpdateCreditCard(creditCard);

    //then
    verify(creditCardRepository).save(creditCardArgumentCaptor.capture());

    CreditCard capturedCreditCard = creditCardArgumentCaptor.getValue();
    assertThat(capturedCreditCard).isNotNull();
    assertThat(capturedCreditCard.getNumber()).isEqualTo("4111111111111111");
    assertThat(capturedCreditCard.getName()).isEqualTo("TEST CARD");
    assertThat(capturedCreditCard.getExpirationMonth()).isEqualTo(1);
    assertThat(capturedCreditCard.getExpirationYear()).isEqualTo(2020);
    assertThat(capturedCreditCard.getOwner()).isNotNull();
    assertThat(capturedCreditCard.getOwner().getUsername()).isEqualTo("testUser");
  }

  @Test
  public void shouldUpdateAnExistingCreditCard() {
    //given
    given(authenticationService.getLoggerUser()).willReturn("testUser");
    given(creditCardRepository.findByNumberEqualsAndOwner_UsernameEquals(anyString(), anyString())).willReturn(createStoredCreditCard());
    given(userService.findUserByUsername(anyString())).willReturn(new User("testUser", " testUser"));

    CreditCard creditCard = new CreditCard("4111111111111111", "TEST CARD", 4, 2022, null);

    //when
    creditCardService.saveOrUpdateCreditCard(creditCard);

    //then
    verify(creditCardRepository).save(creditCardArgumentCaptor.capture());

    CreditCard capturedCreditCard = creditCardArgumentCaptor.getValue();
    assertThat(capturedCreditCard).isNotNull();
    assertThat(capturedCreditCard.getNumber()).isEqualTo("4111111111111111");
    assertThat(capturedCreditCard.getName()).isEqualTo("TEST CARD");
    assertThat(capturedCreditCard.getExpirationMonth()).isEqualTo(4);
    assertThat(capturedCreditCard.getExpirationYear()).isEqualTo(2022);
    assertThat(capturedCreditCard.getOwner()).isNotNull();
    assertThat(capturedCreditCard.getOwner().getUsername()).isEqualTo("testUser");
  }

  private CreditCard createStoredCreditCard() {
    User user = new User("testUser", "testUser");
    return new CreditCard("4111111111111111", "TEST CARD", 1, 2020, user);
  }

  private List<CreditCard> createCreditCardsList() {
    List<CreditCard> creditCards = new ArrayList<>();
    creditCards.add(new CreditCard("4111111111111111", "TEST CARD", 1, 2020, null));
    creditCards.add(new CreditCard("4222222222222222", "TEST CARD 2", 12, 2024, null));
    return creditCards;
  }

}