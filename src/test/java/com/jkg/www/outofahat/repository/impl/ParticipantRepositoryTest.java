package com.jkg.www.outofahat.repository.impl;

import com.jkg.www.outofahat.database.IOutOfAHatInfoConnector;
import com.jkg.www.outofahat.database.objects.ParticipantDbo;
import com.jkg.www.outofahat.service.valueobject.NewParticipantRequest;
import com.jkg.www.outofahat.service.valueobject.model.Participant;
import com.jkg.www.outofahat.testutils.ObjectBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ParticipantRepositoryTest {
    private final String userId = ObjectBuilder.getUserId();

    private ParticipantRepository repository;
    @Mock
    private IOutOfAHatInfoConnector outOfAHatInfoConnector;
    private OutOfAHatInfoMapper outOfAHatInfoMapper = new OutOfAHatInfoMapper();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        repository = new ParticipantRepository(outOfAHatInfoConnector, outOfAHatInfoMapper);
    }

    @Test
    public void test_createParticipant() {
        NewParticipantRequest participantRequest = ObjectBuilder.buildNewParticipantRequest();
        when(outOfAHatInfoConnector.addParticipant(eq(userId), any(ParticipantDbo.class))).thenReturn(true);

        String participantId = repository.createParticipant(userId, participantRequest);

        String expectedId = "first_last";
        assertEquals(expectedId, participantId);
        verify(outOfAHatInfoConnector, times(1)).addParticipant(eq(userId), any(ParticipantDbo.class));
    }

    @Test
    public void test_createParticipant_fail() {
        NewParticipantRequest participantRequest = ObjectBuilder.buildNewParticipantRequest();
        when(outOfAHatInfoConnector.addParticipant(eq(userId), any(ParticipantDbo.class))).thenReturn(false);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            repository.createParticipant(userId, participantRequest);
        });
        assertEquals("failed to add participant to userId 5a55a7a7bf24bb3794378b80", ex.getMessage());
    }

    @Test
    public void test_getParticipants() {
        List<ParticipantDbo> participantDboList = Arrays.asList(ObjectBuilder.buildParticipantDbo(),
            ObjectBuilder.buildParticipantDbo());
        when(outOfAHatInfoConnector.getParticipants(userId)).thenReturn(participantDboList);

        List<Participant> participants = repository.getParticipants(userId);

        assertTrue(!participants.isEmpty());
        assertEquals(participantDboList.size(), participants.size());
        verify(outOfAHatInfoConnector, times(1)).getParticipants(userId);
    }
}
