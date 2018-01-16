package com.jkg.www.outofahat.service;

import com.jkg.www.outofahat.service.valueobject.GenericUserResponse;
import com.jkg.www.outofahat.service.valueobject.NewParticipantRequest;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;
import com.jkg.www.outofahat.service.valueobject.model.Participant;

import java.util.List;

public interface IParticipantService {
    ServiceResponse<String> createParticipant(final String userId, final NewParticipantRequest newParticipantRequest);

    ServiceResponse<GenericUserResponse<List<Participant>>> getParticipants(final String userId);
}
