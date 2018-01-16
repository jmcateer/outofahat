package com.jkg.www.outofahat.repository.impl;

import com.jkg.www.outofahat.database.IOutOfAHatInfoConnector;
import com.jkg.www.outofahat.database.dbObjects.ParticipantDbo;
import com.jkg.www.outofahat.repository.IParticipantRepository;
import com.jkg.www.outofahat.service.valueobject.NewParticipantRequest;
import com.jkg.www.outofahat.service.valueobject.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParticipantRepository implements IParticipantRepository {
    private IOutOfAHatInfoConnector outOfAHatInfoConnector;
    private OutOfAHatInfoMapper outOfAHatInfoMapper;

    @Autowired
    public ParticipantRepository(IOutOfAHatInfoConnector outOfAHatInfoConnector, OutOfAHatInfoMapper outOfAHatInfoMapper) {
        this.outOfAHatInfoConnector = outOfAHatInfoConnector;
        this.outOfAHatInfoMapper = outOfAHatInfoMapper;
    }

    @Override
    public String createParticipant(String userId, NewParticipantRequest participantRequest) {
        List<ParticipantDbo> participants = outOfAHatInfoConnector.getParticipants(userId);
        ParticipantDbo participantDbo = outOfAHatInfoMapper.mapFromNewParticipantRequest(participantRequest, participants);
        boolean saveResult = outOfAHatInfoConnector.addParticipant(userId, participantDbo);
        Assert.isTrue(saveResult, "failed to add participant to userId " + userId);
        return participantDbo.getId();
    }

    @Override
    public List<Participant> getParticipants(final String userId) {
        List<ParticipantDbo> participants = outOfAHatInfoConnector.getParticipants(userId);
        return participants.stream()
                .map(participantDbo -> outOfAHatInfoMapper.mapToParticipant(participantDbo))
                .collect(Collectors.toList());
    }
}
