package com.jkg.www.outofahat.service.valueobject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ServiceResponse <T> implements IResponseMessage {
    private boolean successful;
    private T value;
    private ErrorDetails errorDetails;

    public static <C> ServiceResponse<C> success(C value) {
        return new ServiceResponse<C>(value);
    }

    public static <C> ServiceResponse<C> failure(ErrorDetails errorDetails) {
        ServiceResponse<C> response =  new ServiceResponse<C>();
        response.setErrorDetails(errorDetails);
        response.setSuccessful(false);
        return response;
    }

    private ServiceResponse(T userId) {
        this.value = userId;
        this.successful = true;
    }

    private ServiceResponse() {}
}
