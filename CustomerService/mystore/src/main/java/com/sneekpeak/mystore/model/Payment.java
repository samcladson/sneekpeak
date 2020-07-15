package com.sneekpeak.mystore.model;

public class Payment {
    private String accountNumber;
    private String cardHolderName;
    private Address address;
    private String cardName;
    private String cardNumber;
    private Float balance;
    private String expiryDate;
    private String cvv;

    public Payment() {

    }

    public Payment(String accountNumber, String cardHolderName, String cardName, String cardNumber, Float balance,
            String expiryDate, String cvv, Address address) {
        this.accountNumber = accountNumber;
        this.cardHolderName = cardHolderName;
        this.address = address;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.expiryDate = expiryDate;
        this.cvv = cvv;

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}