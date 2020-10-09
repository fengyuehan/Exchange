package com.jumai.antexchange.utils.net.convert;

public class ApiException extends RuntimeException {

    private String mErrorCode;

    public ApiException(String errorCode, String errorMessage) {
        super(errorMessage);
        mErrorCode = errorCode;
    }
}
