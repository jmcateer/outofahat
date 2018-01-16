package com.jkg.www.outofahat.service.valueobject;

public interface IResponseMessage<T> {
    T getValue();

    ErrorDetails getErrorDetails();

    boolean isSuccessful();
}
