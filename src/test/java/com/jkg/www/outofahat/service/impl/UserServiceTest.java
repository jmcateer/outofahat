package com.jkg.www.outofahat.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jkg.www.outofahat.repository.IUserRepository;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;
import com.jkg.www.outofahat.service.valueobject.model.UserInfo;
import com.jkg.www.outofahat.testutils.ObjectBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.test.util.ReflectionTestUtils;

public class UserServiceTest {

    @InjectMocks
    private UserService service;
    @Mock
    private IUserRepository userRepository;
    @Mock
    private Logger logger;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(service, "logger", logger);
    }

    @Test
    public void test_createUser() {
        NewUserRequest userRequest = ObjectBuilder.buildNewUserRequest();
        String id = ObjectBuilder.getUserId();
        when(userRepository.createUser(eq(userRequest))).thenReturn(id);

        ServiceResponse userResponse = service.createUser(userRequest);

        assertEquals(id, userResponse.getValue());
        verify(userRepository, times(1)).createUser(eq(userRequest));
    }

    @Test
    public void test_createUser_fail() {
        NewUserRequest userRequest = ObjectBuilder.buildNewUserRequest();
        when(userRepository.createUser(eq(userRequest))).thenThrow(new RuntimeException("doh"));

        ServiceResponse userResponse = service.createUser(userRequest);

        assertFalse(userResponse.isSuccessful());
        assertNotNull(userResponse.getErrorDetails());
        assertEquals(SystemEvent.USER_CREATE_FAIL.getId(), userResponse.getErrorDetails().getErrorCode());
        verify(logger, times(1)).error(anyString(), any(Throwable.class));
        verify(userRepository, times(1)).createUser(eq(userRequest));
    }

    @Test
    public void test_getUserInfo() {
        UserInfo userInfo = ObjectBuilder.buildUserInfo();
        when(userRepository.getUserInfo(eq(ObjectBuilder.getUserId()))).thenReturn(userInfo);

        ServiceResponse response = service.getUserInfo(ObjectBuilder.getUserId());

        assertTrue(response.isSuccessful());
        assertNotNull(response.getValue());
        verify(userRepository, times(1)).getUserInfo(eq(ObjectBuilder.getUserId()));
    }

    @Test
    public void test_getUserInfo_fail() {
        when(userRepository.getUserInfo(eq(ObjectBuilder.getUserId()))).thenReturn(null);

        ServiceResponse response = service.getUserInfo(ObjectBuilder.getUserId());

        assertFalse(response.isSuccessful());
        assertNotNull(response.getErrorDetails());
        assertEquals(SystemEvent.USER_FIND_ERROR.getId(), response.getErrorDetails().getErrorCode());
        verify(logger, times(1)).error(anyString(), any(Throwable.class));
        verify(userRepository, times(1)).getUserInfo(eq(ObjectBuilder.getUserId()));
    }

}
