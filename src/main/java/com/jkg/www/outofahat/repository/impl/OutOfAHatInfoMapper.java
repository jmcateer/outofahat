package com.jkg.www.outofahat.repository.impl;

import com.jkg.www.outofahat.database.dbObjects.ContactDbo;
import com.jkg.www.outofahat.database.dbObjects.OutOfAHatInfoDbo;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.user.ContactInfo;
import com.jkg.www.outofahat.service.valueobject.user.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class OutOfAHatInfoMapper {

    public OutOfAHatInfoDbo mapFromNewUserRequest(NewUserRequest userRequest) {
        ContactDbo contactDbo = new ContactDbo(
                createHash(userRequest.getFirst(), userRequest.getLast(), userRequest.getEmail(), userRequest.getPhone()),
                userRequest.getFirst(),
                userRequest.getLast(),
                userRequest.getEmail(),
                userRequest.getPhone(),
                true
        );
        return new OutOfAHatInfoDbo(
                userRequest.getUserName(),
                userRequest.getPassword(),
                contactDbo
        );
    }

    public UserInfo mapToUserInfo(final OutOfAHatInfoDbo outOfAHatInfoDbo) {
        ContactInfo contactInfo = new ContactInfo(
                outOfAHatInfoDbo.getContact().getContactId(),
                outOfAHatInfoDbo.getContact().getFirst(),
                outOfAHatInfoDbo.getContact().getLast(),
                outOfAHatInfoDbo.getContact().getEmail(),
                outOfAHatInfoDbo.getContact().getPhone(),
                outOfAHatInfoDbo.getContact().getActive());

        return new UserInfo(
                outOfAHatInfoDbo.getId().toString(),
                outOfAHatInfoDbo.getUserName(),
                contactInfo);
    }

    private int createHash(String first, String last, String email, String phone) {
        return first.concat(last).concat(email).concat(phone).hashCode();
    }
}
