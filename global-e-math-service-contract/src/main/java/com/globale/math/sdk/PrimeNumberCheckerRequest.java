package com.globale.math.sdk;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public class PrimeNumberCheckerRequest {
    @Schema(example = "1234", description = "Number to be checked if is prime")
    private int number;
    @Schema(example = "3fc32ca8-0e49-11ed-861d-0242ac120002", description = "Client identifier")
    private UUID clientId;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public PrimeNumberCheckerRequest withNumber(int number) {
        setNumber(number);
        return this;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public PrimeNumberCheckerRequest withClientId(UUID clientId) {
        setClientId(clientId);
        return this;
    }
}
