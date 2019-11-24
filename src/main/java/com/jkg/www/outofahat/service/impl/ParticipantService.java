package com.jkg.www.outofahat.service.impl;

import com.jkg.www.outofahat.repository.IParticipantRepository;
import com.jkg.www.outofahat.service.IParticipantService;
import com.jkg.www.outofahat.service.valueobject.NewParticipantRequest;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;
import com.jkg.www.outofahat.service.valueobject.model.Participant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ParticipantService implements IParticipantService {

    private Logger logger = LoggerFactory.getLogger(ParticipantService.class);
    private IParticipantRepository participantRepository;

    @Autowired
    public ParticipantService(IParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public ServiceResponse<Boolean> addIneligibleToParticipant(
            String userId,
            String participant,
            List<String> ineligibles) {
        try {
            boolean result = participantRepository.addIneligibleToParticipant(userId, participant, ineligibles);
            Assert.isTrue(result, "failed to update participant with ineligible for" + userId);
            return ServiceResponse.success(result);
        } catch (Exception ex) {
            return ServiceResponseMapper.failure(logger, SystemEvent.PARTICIPANT_UPDATE_FAIL, ex);
        }
    }

    @Override
    public ServiceResponse<String> createParticipant(
            String userId,
            NewParticipantRequest newParticipantRequest) {
        try {
            String participantId = participantRepository.createParticipant(userId, newParticipantRequest);
            Assert.notNull(participantId, "failed to add participant to " + userId);
            return ServiceResponse.success(participantId);
        } catch (Exception ex) {
            return ServiceResponseMapper.failure(logger, SystemEvent.PARTICIPANT_ADD_FAIL, ex);
        }
    }

    @Override
    public ServiceResponse<List<Participant>> getParticipants(final String userId) {
        try {
            List<Participant> participants = participantRepository.getParticipants(userId);
            return ServiceResponse.success(participants);
        } catch (Exception ex) {
            return ServiceResponseMapper.failure(logger, SystemEvent.PARTICIPANT_RETIRIEVE_FAIL, ex);
        }
    }

    @Override
    public ServiceResponse<List<String>> getParticipantsIdList(final String userId) {
        try {
            List<Participant> participants = participantRepository.getParticipants(userId);
            List<String> participantsIdList = new ArrayList<>();
            if(participants != null) {
                participantsIdList = participants.stream()
                        .map(Participant::getId)
                        .collect(Collectors.toList());
            }
            return ServiceResponse.success(participantsIdList);
        } catch (Exception ex) {
            return ServiceResponseMapper.failure(logger, SystemEvent.PARTICIPANT_RETIRIEVE_FAIL, ex);
        }
    }
}
