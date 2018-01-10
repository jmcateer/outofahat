package com.jkg.www.outofahat.repository.impl;

import com.jkg.www.outofahat.database.dbObjects.OutOfAHatInfoDbo;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.user.UserInfo;
import com.jkg.www.outofahat.testutils.ObjectBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OutOfAHatInfoMapperTest {

    private OutOfAHatInfoMapper mapper;

    @Before
    public void setup() {
        mapper = new OutOfAHatInfoMapper();
    }

    @Test
    public void test_mapFromNewUserRequest() {
        NewUserRequest userRequest = ObjectBuilder.buildNewUserRequest();

        OutOfAHatInfoDbo outOfAHatInfoDbo = mapper.mapFromNewUserRequest(userRequest);

        assertEquals(userRequest.getUserName(), outOfAHatInfoDbo.getUserName());
        assertEquals(userRequest.getPassword(), outOfAHatInfoDbo.getPassword());
        assertTrue(outOfAHatInfoDbo.getContact().getContactId() > 0);
        assertTrue(outOfAHatInfoDbo.getContact().getActive());
        assertEquals(userRequest.getFirst(), outOfAHatInfoDbo.getContact().getFirst());
        assertEquals(userRequest.getLast(), outOfAHatInfoDbo.getContact().getLast());
        assertEquals(userRequest.getEmail(), outOfAHatInfoDbo.getContact().getEmail());
        assertEquals(userRequest.getPhone(), outOfAHatInfoDbo.getContact().getPhone());
    }

    @Test
    public void test_mapToUserInfo() {
        OutOfAHatInfoDbo outOfAHatInfoDbo = ObjectBuilder.buildOutOfAHatInfoDbo();

        UserInfo userInfo = mapper.mapToUserInfo(outOfAHatInfoDbo);

        assertEquals(outOfAHatInfoDbo.getId().toString(), userInfo.getUserId());
        assertEquals(outOfAHatInfoDbo.getUserName(), userInfo.getUserName());
        assertEquals(outOfAHatInfoDbo.getContact().getContactId(), userInfo.getContactInfo().getContactId());
        assertEquals(outOfAHatInfoDbo.getContact().getFirst(), userInfo.getContactInfo().getFirst());
        assertEquals(outOfAHatInfoDbo.getContact().getLast(), userInfo.getContactInfo().getLast());
        assertEquals(outOfAHatInfoDbo.getContact().getEmail(), userInfo.getContactInfo().getEmail());
        assertEquals(outOfAHatInfoDbo.getContact().getPhone(), userInfo.getContactInfo().getPhone());
        assertEquals(outOfAHatInfoDbo.getContact().getActive(), userInfo.getContactInfo().getActive());
    }
}
