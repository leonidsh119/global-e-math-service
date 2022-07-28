package com.globale.math.sdk;

import io.swagger.v3.oas.annotations.media.Schema;

public class PrimeNumberCheckerResponse {
    @Schema(example = "true", description = "If the number is prime")
    private boolean isPrime;

    @Schema(example = "17", description = "Number checked if is prime")
    private int number;

    @Schema(example = "false", description = "Indicates if there was an error")
    private boolean hasError = false;

    @Schema(example = "You have exceeded your call limits. Remaining time until restriction removed is 6 sec.", description = "Error description")
    private String errorMessage = null;

    public boolean isPrime() {
        return isPrime;
    }

    public void setPrime(boolean prime) {
        isPrime = prime;
    }

    public PrimeNumberCheckerResponse withPrime(boolean prime) {
        setPrime(prime);
        return this;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public PrimeNumberCheckerResponse withNumber(int number) {
        setNumber(number);
        return this;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public PrimeNumberCheckerResponse withHasError(boolean hasError) {
        setHasError(hasError);
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public PrimeNumberCheckerResponse withErrorMessage(String errorMessage) {
        setErrorMessage(errorMessage);
        return this;
    }
}
