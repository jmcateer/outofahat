package com.jkg.www.outofahat.service.impl;

import com.jkg.www.outofahat.repository.IUserRepository;
import com.jkg.www.outofahat.service.IUserService;
import com.jkg.www.outofahat.service.valueobject.ErrorDetails;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;
import com.jkg.www.outofahat.service.valueobject.user.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserService implements IUserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    private IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ServiceResponse createUser(NewUserRequest userRequest) {
        try {
            String userId = userRepository.createUser(userRequest);
            return ServiceResponse.success(userId);
        } catch (Exception ex) {
            ErrorDetails errorDetails = new ErrorDetails(SystemEvent.USER_CREATE_FAIL.getId(), SystemEvent.USER_CREATE_FAIL.getDescription() + ex.getMessage());
            logger.error(errorDetails.getErrorMessage(), ex);
            return ServiceResponse.failure(errorDetails);
        }
    }

    @Override
    public ServiceResponse getUserInfo(final String userId) {
        try {
            UserInfo userInfo = userRepository.getUserInfo(userId);
            Assert.notNull(userId, "failed to retrieve user info: " + userId);
            return ServiceResponse.success(userInfo);
        } catch (Exception ex) {
            ErrorDetails errorDetails = new ErrorDetails(SystemEvent.USER_FIND_ERROR.getId(), SystemEvent.USER_FIND_ERROR.getDescription() + ex.getMessage());
            logger.error(errorDetails.getErrorMessage(), ex);
            return ServiceResponse.failure(errorDetails);
        }
    }
}
