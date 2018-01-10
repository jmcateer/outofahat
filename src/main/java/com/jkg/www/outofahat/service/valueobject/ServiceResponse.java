package com.jkg.www.outofahat.service.valueobject;

import lombok.Getter;

@Getter
public class ServiceResponse <T> implements IResponseMessage {
    private boolean successful;
    private T value;
    private ErrorDetails errorDetails;

    public static <C> ServiceResponse<C> success(C value) {
        return new ServiceResponse(value);
    }

    public static <C> ServiceResponse<C> failure(ErrorDetails errorDetails) {
        return new ServiceResponse(errorDetails);
    }

    private ServiceResponse(T userId) {
        this.value = userId;
        this.successful = true;
    }

    private ServiceResponse(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
        this.successful = false;
    }
}
