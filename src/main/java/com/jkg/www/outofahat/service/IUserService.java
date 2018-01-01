package com.jkg.www.outofahat.service;

import com.jkg.www.outofahat.valueobject.GenericSingleResponse;
import com.jkg.www.outofahat.valueobject.NewUserRequest;
import com.jkg.www.outofahat.valueobject.NewUserResponse;

public interface IUserService {
    NewUserResponse createUser(NewUserRequest newUserRequest);
}
