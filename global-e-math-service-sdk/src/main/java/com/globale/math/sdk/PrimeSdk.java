package com.globale.math.sdk;

import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

public class PrimeSdk implements IPrimeSdk {
    private final RestTemplate _restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    private String _apiEndpoint;

    public PrimeSdk(String serviceUrl) {
        _apiEndpoint = MessageFormat.format("{0}/math", serviceUrl);
    }

    public PrimeSdk(String ip, int port) {
        this(MessageFormat.format("{0}{1}:{2}", ip.contains("//") ? "" : "http://", ip, Integer.toString(port)));
    }

    @Override
    public PrimeNumberCheckerResponse isPrime(PrimeNumberCheckerRequest request) {
        String url = MessageFormat.format("{0}/prime", _apiEndpoint);
        ResponseEntity<PrimeNumberCheckerResponse> responseEntity = sendRequest(url, HttpMethod.POST, new HttpEntity<>(request), PrimeNumberCheckerResponse.class);
        return responseEntity.getBody();
    }

    @Override
    public boolean healthCheck() {
        String url = MessageFormat.format("{0}/healthcheck", _apiEndpoint);
        try {
            ResponseEntity<Void> responseEntity = sendRequest(url, HttpMethod.GET, new HttpEntity(new HttpHeaders()), Void.class);
            return responseEntity.getStatusCode().equals(HttpStatus.OK);
        } catch (Exception e) {
            return false;
        }
    }

    private <T> ResponseEntity<T> sendRequest(String url, HttpMethod method, Class<T> responseType) {
        return this.sendRequest(url, method, (HttpEntity)null, responseType);
    }

    private <T> ResponseEntity<T> sendRequest(String url, HttpMethod method, HttpEntity<?> entity, Class<T> responseType) {
        return _restTemplate.exchange(url, method, entity, responseType);
        // TODO: Handle errors
    }
}
