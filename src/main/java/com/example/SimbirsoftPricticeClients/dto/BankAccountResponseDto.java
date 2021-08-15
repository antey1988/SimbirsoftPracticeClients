package com.example.SimbirsoftPricticeClients.dto;

import java.math.BigDecimal;

public class BankAccountResponseDto {
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
