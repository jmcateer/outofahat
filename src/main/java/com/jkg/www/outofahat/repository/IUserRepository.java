package com.jkg.www.outofahat.repository;

import com.jkg.www.outofahat.service.valueobject.NewUserRequest;

public interface IUserRepository {
    String createUser(final NewUserRequest userRequest);
}
