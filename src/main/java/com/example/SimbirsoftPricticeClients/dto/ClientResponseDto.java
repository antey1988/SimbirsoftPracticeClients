package com.example.SimbirsoftPricticeClients.dto;

import java.util.ArrayList;
import java.util.List;

public class ClientResponseDto {
    private String name;

    private BankAccountResponseDto bankAccount;

    private List<OperationResponseDto> operations = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BankAccountResponseDto getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccountResponseDto bankAccount) {
        this.bankAccount = bankAccount;
    }

    public List<OperationResponseDto> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationResponseDto> operations) {
        this.operations = operations;
    }
}
