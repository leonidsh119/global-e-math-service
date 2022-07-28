package com.globale.math.server.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {
    protected ErrorResponse errorResponse = new ErrorResponse();

    public BaseException(String code, String msg) {
        super(msg);
        this.setErrorResponse(code, msg);
    }

    public BaseException(String code, Throwable cause) {
        super(cause);
        this.setErrorResponse(code, cause.getMessage());
    }

    public BaseException(String code, Throwable cause, String msg) {
        super(cause);
        this.setErrorResponse(code, cause.getMessage() + ". " + msg);
    }

    private void setErrorResponse(String code, String msg) {
        this.getErrorResponse().setCode(code);
        this.getErrorResponse().setMessage(msg);
    }

    public abstract HttpStatus getHttpStatusCode();

    public ErrorResponse getErrorResponse() {
        return this.errorResponse;
    }

    public static BaseException parseException(Exception e) {
        return e instanceof BaseException ? (BaseException)e : new GeneralException("INTERNAL_SERVER_ERROR", e);
    }
}