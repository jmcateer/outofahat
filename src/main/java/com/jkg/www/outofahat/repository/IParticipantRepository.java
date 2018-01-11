package com.jkg.www.outofahat.repository;

import com.jkg.www.outofahat.service.valueobject.NewParticipantRequest;
import com.jkg.www.outofahat.service.valueobject.model.Participant;

import java.util.List;

public interface IParticipantRepository {
    String createParticipant(final String userId, final NewParticipantRequest participantRequest);
    List<Participant> getParticipants(final String userId);
}
