package com.jkg.www.outofahat.valueobject;

public interface IResponseMessage<T> {
    T getValue();
    ErrorDetails getErrorDetails();
    boolean isSuccessful();
}
