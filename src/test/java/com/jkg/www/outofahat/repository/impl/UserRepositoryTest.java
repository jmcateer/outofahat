package com.jkg.www.outofahat.repository.impl;

import com.jkg.www.outofahat.database.IOutOfAHatInfoConnector;
import com.jkg.www.outofahat.database.objects.OutOfAHatInfoDbo;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.model.UserInfo;
import com.jkg.www.outofahat.testutils.ObjectBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserRepositoryTest {
    private final String userId = ObjectBuilder.getUserId();
    private UserRepository repository;
    @Mock
    private IOutOfAHatInfoConnector connector;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        repository = new UserRepository(connector, new OutOfAHatInfoMapper());
    }

    @Test
    public void test_createUser() {
        String id = ObjectBuilder.getUserId();
        when(connector.createUser(any(OutOfAHatInfoDbo.class))).thenReturn(id);

        NewUserRequest userRequest = new NewUserRequest("userName", "password", "first", "last", "e@mail.com", "42555");
        String result = repository.createUser(userRequest);

        assertEquals(id, result);
        verify(connector, times(1)).createUser(any(OutOfAHatInfoDbo.class));
    }

    @Test
    public void test_getUserInfo() {
        when(connector.findByUserId(eq(userId))).thenReturn(ObjectBuilder.buildOutOfAHatInfoDbo());

        UserInfo userInfo = repository.getUserInfo(userId);

        assertNotNull(userInfo);
        assertNotNull(userInfo.getContactInfo());
        verify(connector, times(1)).findByUserId(eq(userId));
    }

    @Test
    public void test_getUserInfo_fail() {
        when(connector.findByUserId(eq(userId))).thenReturn(null);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            repository.getUserInfo(userId);
        });
        assertEquals("unablte to find user by id:5a55a7a7bf24bb3794378b80", ex.getMessage());
        verify(connector, times(1)).findByUserId(eq(userId));
    }
}
