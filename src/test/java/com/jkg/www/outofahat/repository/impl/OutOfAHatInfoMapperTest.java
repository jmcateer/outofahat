package com.jkg.www.outofahat.repository.impl;

import com.jkg.www.outofahat.database.dbObjects.OutOfAHatInfoDbo;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.model.UserInfo;
import com.jkg.www.outofahat.testutils.ObjectBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
}
