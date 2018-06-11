package com.acme.profileapp.model;

import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CREDIT_CARDS")
public class CreditCard {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Credit Card number cannot be blank")
  @CreditCardNumber(message = "Invalid credit card number")
  private String number;

  @NotBlank(message = "Card Holders name cannot be blank")
  @Size(min = 1, max = 50, message = "Card Holders name size must be between {min} and {max}")
  private String name;

  @NotNull(message = " Expiration month cannot be null")
  @Min(value = 1, message = "Expiration month must be greater or equals {value}")
  @Max(value = 12 , message = "Expiration month must less or equals {value}")
  private Integer expirationMonth;

  @NotNull(message = " Expiration year cannot be null")
  @Min(value = 2018, message = "Expiration year must be greater or equals {value}")
  @Max(value = 2047 , message = "Expiration year must less or equals {value}")
  private Integer expirationYear;

  @ManyToOne
  private User owner;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getExpirationMonth() {
    return expirationMonth;
  }

  public void setExpirationMonth(Integer expirationMonth) {
    this.expirationMonth = expirationMonth;
  }

  public Integer getExpirationYear() {
    return expirationYear;
  }

  public void setExpirationYear(Integer expirationYear) {
    this.expirationYear = expirationYear;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public String getExpirationDate() {
    return String.format("%02d/%04d", this.expirationMonth, this.expirationYear);
  }

  public CreditCard(String number, String name, Integer expirationMonth, Integer expirationYear, User owner) {
    this.number = number;
    this.name = name;
    this.expirationMonth = expirationMonth;
    this.expirationYear = expirationYear;
    this.owner = owner;
  }

  public CreditCard() {
  }

}
