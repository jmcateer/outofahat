package com.jkg.www.outofahat.controller;

import com.jkg.www.outofahat.service.IParticipantService;
import com.jkg.www.outofahat.service.valueobject.IResponseMessage;
import com.jkg.www.outofahat.service.valueobject.NewParticipantRequest;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("api")
public class ParticipantController {
    private ResponseEntityMapper responseEntityMapper;
    private IParticipantService participantService;

    @Autowired
    public ParticipantController(IParticipantService participantService, ResponseEntityMapper responseEntityMapper) {
        this.participantService = participantService;
        this.responseEntityMapper = responseEntityMapper;
    }

    @RequestMapping(path = "/v1/participant/{userId}/add", produces = "application/json", method = RequestMethod.POST)
    @ApiOperation(value = "add participant to user", nickname = "add participant to user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success", response = ServiceResponse.class),
            @ApiResponse(code = 500, message = "Error", response = ServiceResponse.class)})
    @ResponseBody
    public ResponseEntity<? extends IResponseMessage> createParticipant(@PathVariable String userId, @RequestBody NewParticipantRequest newParticipantRequest) {
        ServiceResponse response = participantService.createParticipant(userId, newParticipantRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        if (response.isSuccessful()) {
            httpHeaders.set("participantId", (String) response.getValue());
        }
        return responseEntityMapper.mapWithHeaders(response, HttpStatus.CREATED, httpHeaders);
    }

    @RequestMapping(path = "/v1/participant/{userId}/list", produces = "application/json", method = RequestMethod.GET)
    @ApiOperation(value = "get participants for user", nickname = "get participants for user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success", response = ServiceResponse.class),
            @ApiResponse(code = 500, message = "Error", response = ServiceResponse.class)})
    @ResponseBody
    public ResponseEntity<? extends IResponseMessage> getParticipants(@PathVariable("userId") String userId) {
        ServiceResponse response = participantService.getParticipants(userId);
        return responseEntityMapper.map(response);
    }

}
