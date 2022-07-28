package com.globale.math.server.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

public class ErrorResponse {
    private final Logger LOG;
    private String code;
    private String message;
    private String hostName;

    public ErrorResponse() {
        this.LOG = LoggerFactory.getLogger(ErrorResponse.class);

        try {
            this.setHostName(InetAddress.getLocalHost().getHostName());
        } catch (Exception var2) {
            this.LOG.warn("Failed to get host name", var2);
        }
    }

    public ErrorResponse(String code, String message) {
        this();
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHostName() {
        return this.hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            ErrorResponse that = (ErrorResponse)o;
            return this.code.equals(that.code);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.code.hashCode();
    }
}
