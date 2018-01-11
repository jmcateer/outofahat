package com.jkg.www.outofahat.service;

import com.jkg.www.outofahat.service.valueobject.NewParticipantRequest;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;

public interface IParticipantService {
    ServiceResponse createParticipant(final String userId, final NewParticipantRequest newParticipantRequest);
    ServiceResponse getParticipants(final String userId);
}
