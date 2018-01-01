package com.jkg.www.outofahat.controller;

import com.jkg.www.outofahat.service.IUserService;
import com.jkg.www.outofahat.service.valueobject.IResponseMessage;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.NewUserResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("api")
public class UserController {
    private IUserService userService;
    private ResponseEntityMapper responseEntityMapper;

    @Autowired
    public UserController(IUserService userService, ResponseEntityMapper responseEntityMapper) {
        this.userService = userService;
        this.responseEntityMapper = responseEntityMapper;
    }

    @RequestMapping(path = "/v1/user/create", produces = "application/json", method = RequestMethod.POST)
    @ApiOperation(value = "create user", nickname = "create user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = NewUserResponse.class),
            @ApiResponse(code = 500, message = "Error", response = NewUserResponse.class)})
    @ResponseBody
    public ResponseEntity<? extends IResponseMessage> createUser(@RequestBody NewUserRequest newUserRequest) {
        NewUserResponse response = userService.createUser(newUserRequest);
        return responseEntityMapper.mapWithReqeustId(response);
    }
}
