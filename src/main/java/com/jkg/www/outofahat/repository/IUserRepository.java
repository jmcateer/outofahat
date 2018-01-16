package com.jkg.www.outofahat.repository;

import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.model.UserInfo;

public interface IUserRepository {
    String createUser(final NewUserRequest userRequest);

    UserInfo getUserInfo(final String userId);
}
