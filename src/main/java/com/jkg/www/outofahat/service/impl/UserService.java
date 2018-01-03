package com.jkg.www.outofahat.service.impl;

import com.jkg.www.outofahat.repository.IUserRepository;
import com.jkg.www.outofahat.service.IUserService;
import com.jkg.www.outofahat.service.valueobject.ErrorDetails;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.NewUserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    Logger logger = LoggerFactory.getLogger(UserService.class);

    private IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public NewUserResponse createUser(NewUserRequest userRequest) {
        try {
            String userId = userRepository.createUser(userRequest);
            return NewUserResponse.success(userId);
        } catch (Exception ex) {
            ErrorDetails errorDetails = new ErrorDetails(42, "failed to create user: " + ex.getMessage());
            logger.error(errorDetails.getErrorMessage(), ex);
            return NewUserResponse.failure(errorDetails);
        }
    }
}
