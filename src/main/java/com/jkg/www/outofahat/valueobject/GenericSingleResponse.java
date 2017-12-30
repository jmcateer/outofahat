package com.jkg.www.outofahat.valueobject;

import lombok.Getter;

@Getter
public class GenericSingleResponse<T> implements IResponseMessage {
    private boolean successful;
    private T value;
    private ErrorDetails errorDetails;

    public static <C> GenericSingleResponse<C> success(C value) {
        return new GenericSingleResponse<>(value);
    }

    public static <C> GenericSingleResponse<C> failure(ErrorDetails errorDetails) {
        return new GenericSingleResponse<>(errorDetails);
    }

    private GenericSingleResponse(T value) {
        this.value = value;
        this.successful = true;
    }

    private GenericSingleResponse(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
        this.successful = false;
    }
}
