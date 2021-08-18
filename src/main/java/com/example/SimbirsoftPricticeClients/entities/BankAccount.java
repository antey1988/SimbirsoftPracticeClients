package com.example.SimbirsoftPricticeClients.entities;


import javax.persistence.Embeddable;
import javax.persistence.Column;
import java.math.BigDecimal;

@Embeddable
public class BankAccount {
    @Column(nullable = false)
    private String number;

    private BigDecimal balance;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
