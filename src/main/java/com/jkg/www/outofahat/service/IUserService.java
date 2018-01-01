package com.jkg.www.outofahat.service;

import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.NewUserResponse;

public interface IUserService {
    NewUserResponse createUser(NewUserRequest newUserRequest);
}
