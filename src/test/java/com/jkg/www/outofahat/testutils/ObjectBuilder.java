package com.jkg.www.outofahat.testutils;

import com.jkg.www.outofahat.database.dbObjects.ContactDbo;
import com.jkg.www.outofahat.database.dbObjects.OutOfAHatInfoDbo;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.user.ContactInfo;
import com.jkg.www.outofahat.service.valueobject.user.UserInfo;
import org.bson.types.ObjectId;

public class ObjectBuilder {
    private static final String userId = "5a55a7a7bf24bb3794378b80";

    public static String getUserId() {
        return userId;
    }

    public static OutOfAHatInfoDbo buildOutOfAHatInfoDbo() {
        OutOfAHatInfoDbo outOfAHatInfoDbo = new OutOfAHatInfoDbo("userName", "password", buildContactDbo());
        outOfAHatInfoDbo.setId(new ObjectId(userId));
        return outOfAHatInfoDbo;
    }

    public static ContactDbo buildContactDbo() {
        return new ContactDbo(316857, "first", "last", "email", "phone", true);
    }

    public static NewUserRequest buildNewUserRequest() {
        return new NewUserRequest("userName", "password", "first", "last", "e@mail.com", "4255556666");
    }

    public static UserInfo buildUserInfo() {
        return new UserInfo(userId, "userName", buildContactInfo());
    }

    public static ContactInfo buildContactInfo() {
        return new ContactInfo(316857, "first", "last", "email", "phone", true);
    }
}
