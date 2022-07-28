package com.globale.math.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class CallRateValidator {
    private final Logger _logger = LoggerFactory.getLogger(getClass());

    @Value("${app.rate-limit}")
    private int rateLimit;

    @Value("${app.rate-period}")
    private int ratePeriodSec;

    private Dictionary<UUID, Queue<LocalDateTime>> callCache = new Hashtable<>();

    public long getRemainigCooldownSec(UUID clientId) {
        LocalDateTime callTimestamp = LocalDateTime.now();
        _logger.trace("Validating calls history for client [{}]", clientId);
        Queue<LocalDateTime> lastCalls = callCache.get(clientId);
        if(lastCalls == null) { // No calls for this client
            _logger.trace("No calls history for client [{}] found.", clientId);
            lastCalls = new LinkedList();
            lastCalls.add(callTimestamp);
            callCache.put(clientId, lastCalls);
            _logger.trace("Created calls history for client [{}]. No cooldown required", clientId);
            return 0;
        } else {
            _logger.trace("Found calls history for client [{}].", clientId);
            cleanupCallsHistory(lastCalls, callTimestamp);
            if(lastCalls.size() < rateLimit) { // rate limit is not exceeded
                _logger.trace("No Rate limit exceeded for client [{}]. No cooldown required.", clientId);
                lastCalls.add(callTimestamp);
                return 0;
            } else { // rate limit is exceeded
                long cooldownSec = Duration.between(lastCalls.poll(), callTimestamp).getSeconds();
                _logger.warn("Rate limit exceeded for client [{}]. Cooldown required for [{}] seconds.", clientId, cooldownSec);
                return cooldownSec;
            }
        }
    }

    private void cleanupCallsHistory(Queue<LocalDateTime> lastCalls, LocalDateTime callTimestamp) {
        LocalDateTime earliestRelevantTimestamp = callTimestamp.minusSeconds(ratePeriodSec);
        while(!lastCalls.isEmpty() && lastCalls.peek().isBefore(earliestRelevantTimestamp)) {
            LocalDateTime removedTimestamp = lastCalls.remove();
            _logger.trace("Removing expired call timestamp [{}].", removedTimestamp);
        }
    }
}
