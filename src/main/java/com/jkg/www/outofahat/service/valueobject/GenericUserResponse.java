package com.jkg.www.outofahat.service.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GenericUserResponse<T> {
    private String userId;
    private T value;
}
