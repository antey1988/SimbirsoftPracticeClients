package com.example.SimbirsoftPricticeClients.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentProjectRequestDto {
    private UUID clientUuid;

    private String projectName;

    private BigDecimal price;

    public UUID getClientUuid() {
        return clientUuid;
    }

    public void setClientUuid(UUID clientUuid) {
        this.clientUuid = clientUuid;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
