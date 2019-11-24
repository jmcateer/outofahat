package com.jkg.www.outofahat.service;

import com.jkg.www.outofahat.service.valueobject.NewParticipantRequest;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;
import com.jkg.www.outofahat.service.valueobject.model.Participant;
import java.util.List;

public interface IParticipantService {

    ServiceResponse<String> createParticipant(
            final String userId,
            final NewParticipantRequest newParticipantRequest);

    ServiceResponse<Boolean> addIneligibleToParticipant(
            String userId,
            String participant,
            List<String> ineligibles);

    ServiceResponse<List<Participant>> getParticipants(final String userId);

    ServiceResponse<List<String>> getParticipantsIdList(final String userId);
}
