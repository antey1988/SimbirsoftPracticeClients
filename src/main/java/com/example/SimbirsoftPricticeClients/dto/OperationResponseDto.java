package com.example.SimbirsoftPricticeClients.dto;

import java.math.BigDecimal;
import java.util.Date;

public class OperationResponseDto implements Comparable<OperationResponseDto> {
    private String type;

    private String description;

    private BigDecimal amount;

    private Date data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public int compareTo(OperationResponseDto o) {
        return data.compareTo(o.data);
    }
}
