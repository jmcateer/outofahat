package com.jkg.www.outofahat.service.impl;

import com.jkg.www.outofahat.service.IUserService;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.NewUserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    @Override
    public NewUserResponse createUser(NewUserRequest newUserRequest) {
        return NewUserResponse.success("new user id");
    }
}
