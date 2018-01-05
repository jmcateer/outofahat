package com.jkg.www.outofahat.controller;

import com.jkg.www.outofahat.service.IUserService;
import com.jkg.www.outofahat.service.valueobject.ErrorDetails;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.NewUserResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class UserControllerTest {
    private UserController userController;
    @Mock
    private IUserService userService;
    private ResponseEntityMapper responseEntityMapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        responseEntityMapper = new ResponseEntityMapper();
        userController = new UserController(userService, responseEntityMapper);
    }

    @Test
    public void test_createUser() {
        NewUserRequest request = new NewUserRequest("userName", "password", "first", "last", "email", "phone");
        String userId = "asdfavweasdf";
        when(userService.createUser(eq(request))).thenReturn(NewUserResponse.success(userId));

        ResponseEntity responseEntity = userController.createUser(request);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(userId, responseEntity.getHeaders().get("id").get(0));
    }

    @Test
    public void test_createUser_fail() {
        NewUserRequest request = new NewUserRequest("userName", "password", "first", "last", "email", "phone");
        when(userService.createUser(eq(request))).thenReturn(NewUserResponse.failure(new ErrorDetails(42, "doh")));

        ResponseEntity responseEntity = userController.createUser(request);

        assertNotNull(responseEntity);
        NewUserResponse response = (NewUserResponse) responseEntity.getBody();
        assertNotNull(response);
        assertFalse(response.isSuccessful());
        assertNotNull(response.getErrorDetails());
    }
}
