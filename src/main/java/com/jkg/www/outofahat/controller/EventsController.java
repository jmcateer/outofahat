package com.jkg.www.outofahat.controller;

import com.jkg.www.outofahat.service.IEventService;
import com.jkg.www.outofahat.service.valueobject.IResponseMessage;
import com.jkg.www.outofahat.service.valueobject.NewEventRequest;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;
import com.jkg.www.outofahat.service.valueobject.model.EventInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("api")
public class EventsController {
    private ResponseEntityMapper responseEntityMapper;
    private IEventService eventService;

    @Autowired
    public EventsController(ResponseEntityMapper responseEntityMapper, IEventService eventService) {
        this.responseEntityMapper = responseEntityMapper;
        this.eventService = eventService;
    }

    @RequestMapping(path = "/v1/event/{userId}/create", produces = "application/json", method = RequestMethod.POST)
    @ApiOperation(value = "create an event for user", nickname = "create an event for user")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Success", response = ServiceResponse.class),
        @ApiResponse(code = 500, message = "Error", response = ServiceResponse.class)})
    @ResponseBody
    public ResponseEntity<? extends IResponseMessage> createEvent(@PathVariable String userId,
                                                                  @RequestBody NewEventRequest eventRequest) {
        ServiceResponse<EventInfo> response = eventService.createEvent(userId, eventRequest);
        return responseEntityMapper.map(response);
    }

    @RequestMapping(path = "/v1/event/{userId}/save", produces = "application/json", method = RequestMethod.POST)
    @ApiOperation(value = "save an event for user", nickname = "save an event for user")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Success", response = ServiceResponse.class),
        @ApiResponse(code = 500, message = "Error", response = ServiceResponse.class)})
    @ResponseBody
    public ResponseEntity<? extends IResponseMessage> saveEvent(@PathVariable String userId,
                                                                @RequestBody EventInfo eventInfo) {
        ServiceResponse<String> response = eventService.saveEvent(userId, eventInfo);
        return responseEntityMapper.map(response);
    }

    @RequestMapping(path = "/v1/event/{userId}/events", produces = "application/json", method = RequestMethod.GET)
    @ApiOperation(value = "get events for user", nickname = "get events for user")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Success", response = ServiceResponse.class),
        @ApiResponse(code = 500, message = "Error", response = ServiceResponse.class)})
    @ResponseBody
    public ResponseEntity<? extends IResponseMessage> getEvents(@PathVariable String userId) {
        ServiceResponse<List<EventInfo>> response = eventService.getEvents(userId);
        return responseEntityMapper.map(response);
    }
}
