package com.acme.profileapp.repository;

import com.acme.profileapp.model.CreditCard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CreditCardRepository extends CrudRepository<CreditCard, Long> {
  public List<CreditCard> findAllByNumberContainingAndOwner_UsernameEquals(String number, String username);
  public CreditCard findByNumberEqualsAndOwner_UsernameEquals(String number, String username);
  public List<CreditCard> findAllByNumberContaining(String number);
}
