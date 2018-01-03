package com.jkg.www.outofahat.repository.impl;

import com.jkg.www.outofahat.database.OutOfAHatInfoConnector;
import com.jkg.www.outofahat.database.dbObjects.OutOfAHatInfoDbo;
import com.jkg.www.outofahat.repository.IUserRepository;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepository implements IUserRepository {

    private OutOfAHatInfoConnector outOfAHatInfoConnector;
    private OutOfAHatInfoMapper outOfAHatInfoMapper;

    @Autowired
    public UserRepository (OutOfAHatInfoConnector outOfAHatInfoConnector) {
        this.outOfAHatInfoConnector = outOfAHatInfoConnector;
        outOfAHatInfoMapper = new OutOfAHatInfoMapper();
    }
    @Override
    public String createUser(NewUserRequest userRequest) {
        OutOfAHatInfoDbo outOfAHatInfoDbo = outOfAHatInfoMapper.mapFromNewUserRequest(userRequest);
        return outOfAHatInfoConnector.createUser(outOfAHatInfoDbo);
    }
}
