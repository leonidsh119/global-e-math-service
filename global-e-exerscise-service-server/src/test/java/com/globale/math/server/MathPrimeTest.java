package com.globale.math.server;

import com.globale.math.sdk.PrimeNumberCheckerRequest;
import com.globale.math.sdk.PrimeNumberCheckerResponse;
import com.globale.math.sdk.PrimeSdk;
import com.globale.math.sdk.IPrimeSdk;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import javax.annotation.PostConstruct;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathPrimeTest {
    @LocalServerPort
    private int _randomServerPort;
    private IPrimeSdk _sdk;

    @PostConstruct
    private void initSdk() {
        _sdk = new PrimeSdk("localhost", _randomServerPort);
    }

    @Test
    public void healthCheckTest() {
        assert _sdk.healthCheck();
    }

    @Test
    public void isPrimeTest() {
        UUID clientId = UUID.randomUUID();
        PrimeNumberCheckerRequest request = new PrimeNumberCheckerRequest()
                .withClientId(clientId)
                .withNumber(17);

        PrimeNumberCheckerResponse response = _sdk.isPrime(request);
        assert response != null;
        assert !response.isHasError();
        assert response.getNumber() == request.getNumber();
        assert response.isPrime();
    }

    @Test
    public void isNotPrimeTest() {
        UUID clientId = UUID.randomUUID();
        PrimeNumberCheckerRequest request = new PrimeNumberCheckerRequest()
                .withClientId(clientId)
                .withNumber(22);

        PrimeNumberCheckerResponse response = _sdk.isPrime(request);
        assert response != null;
        assert !response.isHasError();
        assert response.getNumber() == request.getNumber();
        assert !response.isPrime();
    }
}
