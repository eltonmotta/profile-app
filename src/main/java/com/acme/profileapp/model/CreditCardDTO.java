package com.acme.profileapp.model;

public class CreditCardDTO {

  private String number;

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public CreditCardDTO(String number) {
    this.number = number;
  }

  public CreditCardDTO() {
  }

}
