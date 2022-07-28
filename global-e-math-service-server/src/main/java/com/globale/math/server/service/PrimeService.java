package com.globale.math.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PrimeService {
    private final Logger _logger = LoggerFactory.getLogger(getClass());

    /**
     * Taken from https://www.edureka.co/blog/prime-number-program-in-java/
     */
    public boolean isPrimeNumber(int number) {
        boolean isItPrime = true;

        if (number <= 1) {
            isItPrime = false;

            return isItPrime;
        } else {
            for (int i = 2; i <= number / 2; i++) {
                if ((number % i) == 0) {
                    isItPrime = false;

                    break;
                }
            }

            return isItPrime;
        }
    }
}
