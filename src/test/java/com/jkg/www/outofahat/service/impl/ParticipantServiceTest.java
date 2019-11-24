package com.jkg.www.outofahat.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.jkg.www.outofahat.repository.IParticipantRepository;
import com.jkg.www.outofahat.service.valueobject.NewParticipantRequest;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;
import com.jkg.www.outofahat.service.valueobject.model.Participant;
import com.jkg.www.outofahat.testutils.ObjectBuilder;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.test.util.ReflectionTestUtils;

public class ParticipantServiceTest {

    private static final String participantId = "Ron_Weasley";
    private final String userId = ObjectBuilder.getUserId();
    private ParticipantService service;
    @Mock
    private Logger logger;
    @Mock
    private IParticipantRepository participantRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        service = new ParticipantService(participantRepository);
        ReflectionTestUtils.setField(service, "logger", logger);
    }

    @Test
    public void test_createParticipant() {
        NewParticipantRequest participantRequest = ObjectBuilder.buildNewParticipantRequest();
        when(participantRepository.createParticipant(userId, participantRequest)).thenReturn(participantId);

        ServiceResponse<String> response = service.createParticipant(userId, participantRequest);

        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertEquals(participantId, response.getValue());
        verify(participantRepository, times(1)).createParticipant(userId, participantRequest);
    }

    @Test
    public void test_createParticipant_fail() {
        NewParticipantRequest participantRequest = ObjectBuilder.buildNewParticipantRequest();
        when(participantRepository.createParticipant(userId, participantRequest))
                .thenThrow(new RuntimeException("doh"));

        ServiceResponse<String> response = service.createParticipant(userId, participantRequest);

        assertNotNull(response);
        assertFalse(response.isSuccessful());
        assertNotNull(response.getErrorDetails());
        assertTrue(response.getErrorDetails().getErrorMessage().contains("doh"));
        verify(participantRepository, times(1)).createParticipant(userId, participantRequest);
        verify(logger).error(anyString(), any(Exception.class));
    }

    @Test
    public void test_getParticipants() {
        when(participantRepository.getParticipants(userId)).thenReturn(new ArrayList<>());

        ServiceResponse<List<Participant>> response = service.getParticipants(userId);

        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertNotNull(response.getValue());
        verify(participantRepository, times(1)).getParticipants(userId);
    }

    @Test
    public void test_getParticipants_fail() {
        when(participantRepository.getParticipants(userId)).thenThrow(new RuntimeException("doh"));

        ServiceResponse<List<Participant>> response = service.getParticipants(userId);

        assertNotNull(response);
        assertFalse(response.isSuccessful());
        assertNotNull(response.getErrorDetails());
        assertTrue(response.getErrorDetails().getErrorMessage().contains("doh"));
        verify(participantRepository, times(1)).getParticipants(userId);
        verify(logger).error(anyString(), any(Exception.class));
    }
}
