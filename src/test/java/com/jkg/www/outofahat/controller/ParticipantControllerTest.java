package com.jkg.www.outofahat.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jkg.www.outofahat.service.IParticipantService;
import com.jkg.www.outofahat.service.valueobject.ErrorDetails;
import com.jkg.www.outofahat.service.valueobject.NewParticipantRequest;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;
import com.jkg.www.outofahat.testutils.ObjectBuilder;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ParticipantControllerTest {

    private ParticipantController controller;
    @Mock
    private IParticipantService participantService;
    private ResponseEntityMapper responseEntityMapper = new ResponseEntityMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        controller = new ParticipantController(participantService, responseEntityMapper);
    }

    @Test
    public void test_createParticipant() {
        NewParticipantRequest participantRequest = ObjectBuilder.buildNewParticipantRequest();
        String participantId = "Ron_Weasley";
        String userId = ObjectBuilder.getUserId();
        when(participantService.createParticipant(userId, participantRequest))
                .thenReturn(ServiceResponse.success(participantId));

        ResponseEntity responseEntity = controller.createParticipant(userId, participantRequest);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(participantId, responseEntity.getHeaders().get("participantId").get(0));
        verify(participantService, times(1)).createParticipant(userId, participantRequest);
    }

    @Test
    public void test_createParticipant_fail() {
        NewParticipantRequest participantRequest = ObjectBuilder.buildNewParticipantRequest();
        String userId = ObjectBuilder.getUserId();
        when(participantService.createParticipant(userId, participantRequest))
                .thenReturn(ServiceResponse.failure(new ErrorDetails(42, "doh")));

        ResponseEntity responseEntity = controller.createParticipant(userId, participantRequest);

        assertNotNull(responseEntity);
        ServiceResponse response = (ServiceResponse) responseEntity.getBody();
        assertNotNull(response);
        assertFalse(response.isSuccessful());
        assertNotNull(response.getErrorDetails());
        verify(participantService, times(1)).createParticipant(userId, participantRequest);
    }

    @Test
    public void test_getUserInfo() {
        String userId = ObjectBuilder.getUserId();
        when(participantService.getParticipants(userId)).thenReturn(ServiceResponse.success(new ArrayList<>()));

        ResponseEntity responseEntity = controller.getParticipants(userId);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ServiceResponse response = (ServiceResponse) responseEntity.getBody();
        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertNotNull(response.getValue());
        verify(participantService, times(1)).getParticipants(userId);
    }

    @Test
    public void test_getUserInfo_fail() {
        String userId = ObjectBuilder.getUserId();
        when(participantService.getParticipants(userId))
                .thenReturn(ServiceResponse.failure(new ErrorDetails(42, "doh")));

        ResponseEntity responseEntity = controller.getParticipants(userId);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        ServiceResponse response = (ServiceResponse) responseEntity.getBody();
        assertNotNull(response);
        assertFalse(response.isSuccessful());
        assertNotNull(response.getErrorDetails());
        verify(participantService, times(1)).getParticipants(userId);
    }
}
