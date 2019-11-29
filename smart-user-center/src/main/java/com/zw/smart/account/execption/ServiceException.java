package com.zw.smart.account.execption;

import com.zw.smart.account.common.result.ResponseStatus;

public class ServiceException extends RuntimeException {
    private final ResponseStatus responseStatus;
    public ServiceException(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
