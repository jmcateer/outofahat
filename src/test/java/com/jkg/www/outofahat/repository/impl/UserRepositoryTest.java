package com.jkg.www.outofahat.repository.impl;

import com.jkg.www.outofahat.database.IOutOfAHatInfoConnector;
import com.jkg.www.outofahat.database.dbObjects.OutOfAHatInfoDbo;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserRepositoryTest {
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
        String id = "laskdjfoais";
        when(connector.createUser(any(OutOfAHatInfoDbo.class))).thenReturn("laskdjfoais");

        NewUserRequest userRequest = new NewUserRequest("userName", "password", "first", "last", "e@mail.com", "4255556666");
        String result = repository.createUser(userRequest);

        assertEquals(id, result);
        verify(connector, times(1)).createUser(any(OutOfAHatInfoDbo.class));

    }
}
