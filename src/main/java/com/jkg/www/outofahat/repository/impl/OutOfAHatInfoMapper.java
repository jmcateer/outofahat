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
                ceateHash(userRequest.getFirst(), userRequest.getLast(), userRequest.getEmail(), userRequest.getPhone()),
                userRequest.getFirst(),
                userRequest.getLast(),
                userRequest.getEmail(),
                userRequest.getPhone(),
                true
        );
        OutOfAHatInfoDbo outOfAHatInfoDbo = new OutOfAHatInfoDbo(
                userRequest.getUserName(),
                userRequest.getPassword(),
                contactDbo
        );
        return outOfAHatInfoDbo;
    }

    public UserInfo mapToUserInfo(final OutOfAHatInfoDbo outOfAHatInfoDbo) {
        ContactInfo contactInfo = new ContactInfo(
                outOfAHatInfoDbo.getContact().getContactId(),
                outOfAHatInfoDbo.getContact().getFirst(),
                outOfAHatInfoDbo.getContact().getLast(),
                outOfAHatInfoDbo.getContact().getEmail(),
                outOfAHatInfoDbo.getContact().getActive());

        UserInfo userInfo = new UserInfo(
                outOfAHatInfoDbo.getId().toString(),
                outOfAHatInfoDbo.getUserName(),
                contactInfo);

        return userInfo;
    }

    private int ceateHash(String first, String last, String email, String phone) {
        return first.concat(last).concat(email).concat(phone).hashCode();
    }
}
