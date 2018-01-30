package com.jkg.www.outofahat.database;

import com.jkg.www.outofahat.database.objects.EventInfoDbo;
import com.jkg.www.outofahat.database.objects.OutOfAHatInfoDbo;
import com.jkg.www.outofahat.database.objects.ParticipantDbo;

import java.util.List;

public interface IOutOfAHatInfoConnector {
    String createUser(final OutOfAHatInfoDbo outOfAHatInfoDbo);

    OutOfAHatInfoDbo findByUserId(final String userId);

    boolean addParticipant(String userId, ParticipantDbo participantDbo);

    List<ParticipantDbo> getParticipants(final String userId);

    List<EventInfoDbo> getEvents(String userId);

    boolean saveEvent(final String userId, final EventInfoDbo eventInfoDbo);
}
