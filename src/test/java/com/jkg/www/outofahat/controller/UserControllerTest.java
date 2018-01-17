package com.jkg.www.outofahat.controller;

import com.jkg.www.outofahat.service.IUserService;
import com.jkg.www.outofahat.service.valueobject.ErrorDetails;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;
import com.jkg.www.outofahat.service.valueobject.model.UserInfo;
import com.jkg.www.outofahat.testutils.ObjectBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;


public class UserControllerTest {
    private UserController userController;
    @Mock
    private IUserService userService;
    private ResponseEntityMapper responseEntityMapper = new ResponseEntityMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userController = new UserController(userService, responseEntityMapper);
    }

    @Test
    public void test_createUser() {
        NewUserRequest request = ObjectBuilder.buildNewUserRequest();
        final String userId = ObjectBuilder.getUserId();
        when(userService.createUser(eq(request))).thenReturn(ServiceResponse.success(userId));

        ResponseEntity responseEntity = userController.createUser(request);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(userId, responseEntity.getHeaders().get("id").get(0));
    }

    @Test
    public void test_createUser_fail() {
        NewUserRequest request = ObjectBuilder.buildNewUserRequest();
        when(userService.createUser(eq(request))).thenReturn(ServiceResponse.failure(new ErrorDetails(42, "doh")));

        ResponseEntity responseEntity = userController.createUser(request);

        assertNotNull(responseEntity);
        ServiceResponse response = (ServiceResponse) responseEntity.getBody();
        assertNotNull(response);
        assertFalse(response.isSuccessful());
        assertNotNull(response.getErrorDetails());
    }

    @Test
    public void test_getUserInfo() {
        UserInfo userInfo = ObjectBuilder.buildUserInfo();
        when(userService.getUserInfo(ObjectBuilder.getUserId())).thenReturn(ServiceResponse.success(userInfo));

        ResponseEntity responseEntity = userController.getUserInfo(ObjectBuilder.getUserId());

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ServiceResponse response = (ServiceResponse) responseEntity.getBody();
        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertNotNull(response.getValue());
    }

    @Test
    public void test_getUserInfo_fail() {
        when(userService.getUserInfo(ObjectBuilder.getUserId()))
            .thenReturn(ServiceResponse.failure(new ErrorDetails(42, "doh")));

        ResponseEntity responseEntity = userController.getUserInfo(ObjectBuilder.getUserId());

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        ServiceResponse response = (ServiceResponse) responseEntity.getBody();
        assertNotNull(response);
        assertFalse(response.isSuccessful());
        assertNotNull(response.getErrorDetails());
    }
}
