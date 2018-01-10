package com.jkg.www.outofahat.testutils;

import com.jkg.www.outofahat.database.dbObjects.ContactInfoDbo;
import com.jkg.www.outofahat.database.dbObjects.FriendDbo;
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
        OutOfAHatInfoDbo outOfAHatInfoDbo = new OutOfAHatInfoDbo("userName", "password", buildContactInfoDbo());
        outOfAHatInfoDbo.setId(new ObjectId(userId));
        return outOfAHatInfoDbo;
    }

    public static FriendDbo buildFriendDbo() {
        return new FriendDbo(316857, buildContactInfoDbo(), true);
    }

    public static ContactInfoDbo buildContactInfoDbo() {
        return new ContactInfoDbo("first", "last", "email", "phone");
    }

    public static NewUserRequest buildNewUserRequest() {
        return new NewUserRequest("userName", "password", "first", "last", "e@mail.com", "4255556666");
    }

    public static UserInfo buildUserInfo() {
        return new UserInfo(userId, "userName", buildContactInfo());
    }

    public static ContactInfo buildContactInfo() {
        return new ContactInfo("first", "last", "email", "phone");
    }
}