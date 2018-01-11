package com.jkg.www.outofahat.service.impl;

import com.jkg.www.outofahat.repository.IParticipantRepository;
import com.jkg.www.outofahat.service.IParticipantService;
import com.jkg.www.outofahat.service.valueobject.GenericUserResponse;
import com.jkg.www.outofahat.service.valueobject.NewParticipantRequest;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;
import com.jkg.www.outofahat.service.valueobject.model.Participant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ParticipantService implements IParticipantService {
    private Logger logger = LoggerFactory.getLogger(ParticipantService.class);
    private IParticipantRepository participantRepository;

    @Autowired
    public ParticipantService(IParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public ServiceResponse createParticipant(String userId, NewParticipantRequest newParticipantRequest) {
        try {
            String participantId = participantRepository.createParticipant(userId, newParticipantRequest);
            Assert.notNull(participantId, "failed to add participant to " + userId);
            return ServiceResponse.success(participantId);
        } catch (Exception ex) {
            return ServiceResponseMapper.failure(logger, SystemEvent.PARTICIPANT_ADD_FAIL, ex);
        }
    }

    @Override
    public ServiceResponse getParticipants(final String userId) {
        try {
            List<Participant> participants = participantRepository.getParticipants(userId);
            GenericUserResponse response = new GenericUserResponse(userId, participants);
            return ServiceResponse.success(response);
        } catch (Exception ex) {
            return ServiceResponseMapper.failure(logger, SystemEvent.PARTICIPANT_RETIRIEVE_FAIL, ex);
        }
    }
}
