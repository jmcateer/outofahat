package com.jkg.www.outofahat.repository.impl;

import com.jkg.www.outofahat.database.IOutOfAHatInfoConnector;
import com.jkg.www.outofahat.database.objects.OutOfAHatInfoDbo;
import com.jkg.www.outofahat.repository.IUserRepository;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class UserRepository implements IUserRepository {
    private IOutOfAHatInfoConnector outOfAHatInfoConnector;
    private OutOfAHatInfoMapper outOfAHatInfoMapper;

    @Autowired
    public UserRepository(IOutOfAHatInfoConnector outOfAHatInfoConnector, OutOfAHatInfoMapper outOfAHatInfoMapper) {
        this.outOfAHatInfoConnector = outOfAHatInfoConnector;
        this.outOfAHatInfoMapper = outOfAHatInfoMapper;
    }

    @Override
    public String createUser(NewUserRequest userRequest) {
        OutOfAHatInfoDbo outOfAHatInfoDbo = outOfAHatInfoMapper.mapFromNewUserRequest(userRequest);
        return outOfAHatInfoConnector.createUser(outOfAHatInfoDbo);
    }

    @Override
    public UserInfo getUserInfo(final String userId) {
        OutOfAHatInfoDbo outOfAHatInfoDbo = outOfAHatInfoConnector.findByUserId(userId);
        Assert.notNull(outOfAHatInfoDbo, "unablte to find user by id:" + userId);
        return outOfAHatInfoMapper.mapToUserInfo(outOfAHatInfoDbo);
    }
}
