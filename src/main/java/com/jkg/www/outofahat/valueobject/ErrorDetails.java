package com.jkg.www.outofahat.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorDetails {
    private int errorCode;
    private String errorMessage;
}
