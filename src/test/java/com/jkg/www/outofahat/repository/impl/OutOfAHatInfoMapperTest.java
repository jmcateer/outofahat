package com.jkg.www.outofahat.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jkg.www.outofahat.database.objects.OutOfAHatInfoDbo;
import com.jkg.www.outofahat.database.objects.ParticipantDbo;
import com.jkg.www.outofahat.service.valueobject.NewParticipantRequest;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.model.Participant;
import com.jkg.www.outofahat.service.valueobject.model.UserInfo;
import com.jkg.www.outofahat.testutils.ObjectBuilder;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OutOfAHatInfoMapperTest {

    private OutOfAHatInfoMapper mapper;

    @BeforeEach
    public void setup() {
        mapper = new OutOfAHatInfoMapper();
    }

    @Test
    public void test_mapFromNewUserRequest() {
        NewUserRequest userRequest = ObjectBuilder.buildNewUserRequest();

        OutOfAHatInfoDbo outOfAHatInfoDbo = mapper.mapFromNewUserRequest(userRequest);

        assertEquals(userRequest.getUserName(), outOfAHatInfoDbo.getUserName());
        assertEquals(userRequest.getPassword(), outOfAHatInfoDbo.getPassword());

        assertEquals(userRequest.getFirst(), outOfAHatInfoDbo.getContactInfo().getFirst());
        assertEquals(userRequest.getLast(), outOfAHatInfoDbo.getContactInfo().getLast());
        assertEquals(userRequest.getEmail(), outOfAHatInfoDbo.getContactInfo().getEmail());
        assertEquals(userRequest.getPhone(), outOfAHatInfoDbo.getContactInfo().getPhone());
    }

    @Test
    public void test_mapToUserInfo() {
        OutOfAHatInfoDbo outOfAHatInfoDbo = ObjectBuilder.buildOutOfAHatInfoDbo();

        UserInfo userInfo = mapper.mapToUserInfo(outOfAHatInfoDbo);

        assertEquals(outOfAHatInfoDbo.getId().toString(), userInfo.getUserId());
        assertEquals(outOfAHatInfoDbo.getUserName(), userInfo.getUserName());
        assertEquals(outOfAHatInfoDbo.getContactInfo().getFirst(), userInfo.getContactInfo().getFirst());
        assertEquals(outOfAHatInfoDbo.getContactInfo().getLast(), userInfo.getContactInfo().getLast());
        assertEquals(outOfAHatInfoDbo.getContactInfo().getEmail(), userInfo.getContactInfo().getEmail());
        assertEquals(outOfAHatInfoDbo.getContactInfo().getPhone(), userInfo.getContactInfo().getPhone());
    }

    @Test
    public void test_createParticipantId() {
        String first = "Harry";
        String last = "Potter";
        List<ParticipantDbo> participantDbos = Collections.singletonList(ObjectBuilder.buildParticipantDbo());

        String participantId = mapper.createParticipantId(first, last, participantDbos);

        assertEquals(first + "_" + last, participantId);
    }

    @Test
    public void test_createParticipantId_alreadyExists() {
        ParticipantDbo participantDbo = ObjectBuilder.buildParticipantDbo();
        String first = participantDbo.getContactInfo().getFirst();
        String last = participantDbo.getContactInfo().getLast();
        List<ParticipantDbo> participantDbos = Collections.singletonList(participantDbo);

        String participantId = mapper.createParticipantId(first, last, participantDbos);

        assertTrue(participantId.contains(first));
        assertTrue(participantId.contains(last));
        assertNotEquals(participantDbo.getParticipantId(), participantId);
    }

    @Test
    public void test_mapFromNewParticipantRequest() {
        NewParticipantRequest newRequest = ObjectBuilder.buildNewParticipantRequest();
        List<ParticipantDbo> participantDbos = Collections.singletonList(ObjectBuilder.buildParticipantDbo());

        ParticipantDbo participantDbo = mapper.mapFromNewParticipantRequest(newRequest, participantDbos);

        assertNotNull(participantDbo);
        assertEquals(newRequest.getFirst(), participantDbo.getContactInfo().getFirst());
        assertEquals(newRequest.getLast(), participantDbo.getContactInfo().getLast());
        assertEquals(newRequest.getEmail(), participantDbo.getContactInfo().getEmail());
        assertEquals(newRequest.getPhone(), participantDbo.getContactInfo().getPhone());
    }

    @Test
    public void test_mapToParticipant() {
        ParticipantDbo participantDbo = ObjectBuilder.buildParticipantDbo();

        Participant participant = mapper.mapToParticipant(participantDbo);

        assertEquals(participantDbo.getParticipantId(), participant.getId());
        assertEquals(participantDbo.getActive(), participant.getActive());
        assertEquals(participantDbo.getContactInfo().getFirst(), participant.getContactInfo().getFirst());
        assertEquals(participantDbo.getContactInfo().getLast(), participant.getContactInfo().getLast());
        assertEquals(participantDbo.getContactInfo().getEmail(), participant.getContactInfo().getEmail());
        assertEquals(participantDbo.getContactInfo().getPhone(), participant.getContactInfo().getPhone());
    }
}
