package com.jkg.www.outofahat.service.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NewUserRequest {
    private String userName;
    private String password;
    private String first;
    private String last;
    private String email;
    private String phone;
}
