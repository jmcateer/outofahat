package com.jkg.www.outofahat.repository.impl;

import com.jkg.www.outofahat.database.dbObjects.OutOfAHatInfoDbo;
import com.jkg.www.outofahat.database.dbObjects.ContactInfoDbo;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.user.ContactInfo;
import com.jkg.www.outofahat.service.valueobject.user.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class OutOfAHatInfoMapper {

    public OutOfAHatInfoDbo mapFromNewUserRequest(NewUserRequest userRequest) {
        ContactInfoDbo contactInfoDbo = new ContactInfoDbo(
                userRequest.getFirst(),
                userRequest.getLast(),
                userRequest.getEmail(),
                userRequest.getPhone());

        return new OutOfAHatInfoDbo(
                userRequest.getUserName(),
                userRequest.getPassword(),
                contactInfoDbo
        );
    }

    public UserInfo mapToUserInfo(final OutOfAHatInfoDbo outOfAHatInfoDbo) {
        ContactInfo contactInfo = new ContactInfo(
                outOfAHatInfoDbo.getContactInfoDbo().getFirst(),
                outOfAHatInfoDbo.getContactInfoDbo().getLast(),
                outOfAHatInfoDbo.getContactInfoDbo().getEmail(),
                outOfAHatInfoDbo.getContactInfoDbo().getPhone());

        return new UserInfo(
                outOfAHatInfoDbo.getId().toString(),
                outOfAHatInfoDbo.getUserName(),
                contactInfo);
    }

    private int createHash(String first, String last, String email, String phone) {
        return first.concat(last).concat(email).concat(phone).hashCode();
    }
}
