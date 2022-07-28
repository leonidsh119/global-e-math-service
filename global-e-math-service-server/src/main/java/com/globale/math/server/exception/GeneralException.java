package com.globale.math.server.exception;

import org.springframework.http.HttpStatus;

public class GeneralException extends BaseException {
    public GeneralException(String code, String msg) {
        super(code, msg);
    }

    public GeneralException(String code, Throwable cause, String msg) {
        super(code, cause, msg);
    }

    public GeneralException(String code, Throwable cause) {
        super(code, cause);
    }

    public HttpStatus getHttpStatusCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
