package com.jkg.www.outofahat.testutils;

import com.jkg.www.outofahat.database.objects.ContactInfoDbo;
import com.jkg.www.outofahat.database.objects.OutOfAHatInfoDbo;
import com.jkg.www.outofahat.database.objects.ParticipantDbo;
import com.jkg.www.outofahat.service.valueobject.NewParticipantRequest;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.model.ContactInfo;
import com.jkg.www.outofahat.service.valueobject.model.UserInfo;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;

public class ObjectBuilder {
    private static final String userId = "5a55a7a7bf24bb3794378b80";

    public static String getUserId() {
        return userId;
    }

    public static OutOfAHatInfoDbo buildOutOfAHatInfoDbo() {
        OutOfAHatInfoDbo outOfAHatInfoDbo = new OutOfAHatInfoDbo(
            "userName",
            "password",
            buildContactInfoDbo(),
            Arrays.asList(buildParticipantDbo()),
            new ArrayList<>()
        );
        outOfAHatInfoDbo.setId(new ObjectId(userId));
        return outOfAHatInfoDbo;
    }

    public static ParticipantDbo buildParticipantDbo() {
        ContactInfoDbo contactInfoDbo = buildContactInfoDbo();
        String participantId = contactInfoDbo.getFirst() + "_" + contactInfoDbo.getLast();
        return new ParticipantDbo(participantId, contactInfoDbo, true);
    }

    public static ContactInfoDbo buildContactInfoDbo() {
        return new ContactInfoDbo("first", "last", "email", "phone");
    }

    public static NewUserRequest buildNewUserRequest() {
        return new NewUserRequest("userName", "password", "first", "last", "e@mail.com", "4255556666");
    }

    public static NewParticipantRequest buildNewParticipantRequest() {
        return new NewParticipantRequest("first", "last", "e@mail.com", "4255556666",
            new ArrayList<>(), new ArrayList<>());
    }

    public static UserInfo buildUserInfo() {
        return new UserInfo(userId, "userName", buildContactInfo());
    }

    public static ContactInfo buildContactInfo() {
        return new ContactInfo("first", "last", "email", "phone");
    }
}
