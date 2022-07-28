package com.globale.math.sdk;

public interface IPrimeSdk {
    PrimeNumberCheckerResponse isPrime(PrimeNumberCheckerRequest request);
    boolean healthCheck();
}
