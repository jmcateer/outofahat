package com.jkg.www.outofahat.service;

import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;

public interface IUserService {
    ServiceResponse createUser(NewUserRequest newUserRequest);

    ServiceResponse getUserInfo(final String userId);
}
