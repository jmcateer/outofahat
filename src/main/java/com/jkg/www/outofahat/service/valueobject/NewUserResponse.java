package com.jkg.www.outofahat.service.valueobject;

import lombok.Getter;

@Getter
public class NewUserResponse implements IResponseMessage {
    private boolean successful;
    private String userId;
    private ErrorDetails errorDetails;

    public static NewUserResponse success(String userId) {
        return new NewUserResponse(userId);
    }

    public static NewUserResponse failure(ErrorDetails errorDetails) {
        return new NewUserResponse(errorDetails);
    }

    private NewUserResponse(String userId) {
        this.userId = userId;
        this.successful = true;
    }

    private NewUserResponse(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
        this.successful = false;
    }

    public String getValue() {
        return userId;
    }
}
