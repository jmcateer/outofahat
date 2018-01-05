package com.jkg.www.outofahat.repository.impl;

import com.jkg.www.outofahat.database.dbObjects.ContactDbo;
import com.jkg.www.outofahat.database.dbObjects.OutOfAHatInfoDbo;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import org.springframework.stereotype.Component;

@Component
public class OutOfAHatInfoMapper {

    public OutOfAHatInfoDbo mapFromNewUserRequest(NewUserRequest userRequest) {
        ContactDbo contactDbo = new ContactDbo(
                userRequest.getFirst() + userRequest.getLast(),
                userRequest.getFirst(),
                userRequest.getLast(),
                userRequest.getEmail(),
                userRequest.getPhone()
        );
        OutOfAHatInfoDbo outOfAHatInfoDbo = new OutOfAHatInfoDbo(
                userRequest.getUserName(),
                userRequest.getPassword(),
                contactDbo
        );
        return outOfAHatInfoDbo;
    }
}
