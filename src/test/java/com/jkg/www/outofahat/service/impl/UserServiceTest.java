package com.jkg.www.outofahat.service.impl;

import com.jkg.www.outofahat.repository.IUserRepository;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.NewUserResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    @InjectMocks
    private UserService service;
    @Mock
    private IUserRepository userRepository;
    @Mock
    Logger logger;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(service, "logger", logger);
    }

    @Test
    public void test_createUser() {
        NewUserRequest userRequest = new NewUserRequest("userName", "password", "first", "last", "e@mail.com", "4255556666");
        String id = "laksjdflaj";
        when(userRepository.createUser(eq(userRequest))).thenReturn(id);

        NewUserResponse userResponse = service.createUser(userRequest);

        assertEquals(id, userResponse.getValue());
        verify(userRepository, times(1)).createUser(eq(userRequest));
    }

    @Test
    public void test_createUser_fail() {
        NewUserRequest userRequest = new NewUserRequest("userName", "password", "first", "last", "e@mail.com", "4255556666");
        when(userRepository.createUser(eq(userRequest))).thenThrow(new RuntimeException("doh"));

        NewUserResponse userResponse = service.createUser(userRequest);

        assertFalse(userResponse.isSuccessful());
        assertNotNull(userResponse.getErrorDetails());
        verify(logger, times(1)).error(anyString(), any(Throwable.class));
        verify(userRepository, times(1)).createUser(eq(userRequest));
    }

}
