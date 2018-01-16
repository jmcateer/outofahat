package com.jkg.www.outofahat.service.impl;

import com.jkg.www.outofahat.repository.IParticipantRepository;
import com.jkg.www.outofahat.service.valueobject.GenericUserResponse;
import com.jkg.www.outofahat.service.valueobject.NewParticipantRequest;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;
import com.jkg.www.outofahat.service.valueobject.model.Participant;
import com.jkg.www.outofahat.testutils.ObjectBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ParticipantServiceTest {
    private final String userId = ObjectBuilder.getUserId();
    private static final String participantId = "Ron_Weasley";

    private ParticipantService service;
    @Mock
    private Logger logger;
    @Mock
    private IParticipantRepository participantRepository;

    @Before
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
        when(participantRepository.createParticipant(userId, participantRequest)).thenThrow(new RuntimeException("doh"));

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

        ServiceResponse<GenericUserResponse<List<Participant>>> response = service.getParticipants(userId);

        assertNotNull(response);
        assertTrue(response.isSuccessful());
        GenericUserResponse<List<Participant>> value = response.getValue();
        assertEquals(userId, value.getUserId());
        assertNotNull(value.getValue());
        verify(participantRepository, times(1)).getParticipants(userId);
    }

    @Test
    public void test_getParticipants_fail() {
        when(participantRepository.getParticipants(userId)).thenThrow(new RuntimeException("doh"));

        ServiceResponse<GenericUserResponse<List<Participant>>> response = service.getParticipants(userId);

        assertNotNull(response);
        assertFalse(response.isSuccessful());
        assertNotNull(response.getErrorDetails());
        assertTrue(response.getErrorDetails().getErrorMessage().contains("doh"));
        verify(participantRepository, times(1)).getParticipants(userId);
        verify(logger).error(anyString(), any(Exception.class));
    }
}
