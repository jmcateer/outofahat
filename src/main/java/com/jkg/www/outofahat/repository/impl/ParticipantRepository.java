package com.jkg.www.outofahat.repository.impl;

import com.jkg.www.outofahat.database.IOutOfAHatInfoConnector;
import com.jkg.www.outofahat.database.objects.ParticipantDbo;
import com.jkg.www.outofahat.repository.IParticipantRepository;
import com.jkg.www.outofahat.service.valueobject.NewParticipantRequest;
import com.jkg.www.outofahat.service.valueobject.model.Participant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ParticipantRepository implements IParticipantRepository {

    private IOutOfAHatInfoConnector outOfAHatInfoConnector;
    private OutOfAHatInfoMapper outOfAHatInfoMapper;

    @Autowired
    public ParticipantRepository(
            IOutOfAHatInfoConnector outOfAHatInfoConnector,
            OutOfAHatInfoMapper outOfAHatInfoMapper) {
        this.outOfAHatInfoConnector = outOfAHatInfoConnector;
        this.outOfAHatInfoMapper = outOfAHatInfoMapper;
    }

    @Override
    public String createParticipant(
            String userId,
            NewParticipantRequest newRequest) {
        List<ParticipantDbo> participants = outOfAHatInfoConnector.getParticipants(userId);
        ParticipantDbo participantDbo = outOfAHatInfoMapper.mapFromNewParticipantRequest(newRequest, participants);
        boolean saveResult = outOfAHatInfoConnector.addParticipant(userId, participantDbo);
        Assert.isTrue(saveResult, "failed to add participant to userId " + userId);
        return participantDbo.getParticipantId();
    }

    @Override
    public List<Participant> getParticipants(final String userId) {
        List<ParticipantDbo> participants = outOfAHatInfoConnector.getParticipants(userId);
        return participants.stream()
                .map(participantDbo -> outOfAHatInfoMapper.mapToParticipant(participantDbo))
                .collect(Collectors.toList());
    }

    @Override
    public boolean addIneligibleToParticipant(
            String userId,
            String participant,
            List<String> ineligibles) {
        List<ParticipantDbo> participants = outOfAHatInfoConnector.getParticipants(userId);
        Assert.notNull(participants, "Participant list is null");
        Assert.notEmpty(participants, "Participant list is empty");

        List<ParticipantDbo> updatedParticipants = participants.stream()
                .map(p -> {
                    if (p.getParticipantId().equals(participant)) {
                        List<String> currentIneligibles = p.getIneligibles();
                        currentIneligibles =
                                currentIneligibles == null ? new ArrayList<>() : currentIneligibles;
                        currentIneligibles.addAll(ineligibles);
                        p.setIneligibles(currentIneligibles);
                    }
                    return p;
                }).collect(Collectors.toList());
        return outOfAHatInfoConnector.updateParticipants(userId, updatedParticipants);

    }
}
