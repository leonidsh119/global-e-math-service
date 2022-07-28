package com.globale.math.server.controller;

import com.globale.math.sdk.PrimeNumberCheckerRequest;
import com.globale.math.sdk.PrimeNumberCheckerResponse;
import com.globale.math.server.service.CallRateValidator;
import com.globale.math.server.service.PrimeService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prime")
public class PrimeController extends BaseController {
    private final Logger _logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PrimeService service;

    @Autowired
    private CallRateValidator validator;

    @PostMapping
    @Schema
    PrimeNumberCheckerResponse isPrime(@RequestBody PrimeNumberCheckerRequest request) {
        _logger.trace("Request for checking prime number [{}] from client [{}]", request.getNumber(), request.getClientId());
        long cooldownSec = validator.getRemainigCooldownSec(request.getClientId());
        if(cooldownSec > 0) {
            return new PrimeNumberCheckerResponse()
                    .withNumber(request.getNumber())
                    .withHasError(true)
                    .withErrorMessage(String.format("You have exceeded your call limits. Remaining time until restriction removed is %d sec.", cooldownSec));
        }
        return new PrimeNumberCheckerResponse()
                .withNumber(request.getNumber())
                .withPrime(service.isPrimeNumber(request.getNumber()));
    }
}
