package com.jkg.www.outofahat.service.impl;

import com.jkg.www.outofahat.repository.IEventRepository;
import com.jkg.www.outofahat.repository.IParticipantRepository;
import com.jkg.www.outofahat.service.IEventService;
import com.jkg.www.outofahat.service.valueobject.NewEventRequest;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;
import com.jkg.www.outofahat.service.valueobject.model.EventInfo;
import com.jkg.www.outofahat.service.valueobject.model.Participant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class EventService implements IEventService {
    private Logger logger = LoggerFactory.getLogger(EventService.class);
    private IParticipantRepository participantRepository;
    private IEventRepository eventRepository;

    private static final int LIMIT = 50;

    @Override
    public ServiceResponse<List<EventInfo>> getEvents(String userId) {
        try {
            List<EventInfo> events = eventRepository.getEvents(userId);
            return ServiceResponse.success(events);
        } catch (Exception ex) {
            return ServiceResponseMapper.failure(logger, SystemEvent.EVENT_FIND_FAIL, ex);
        }
    }

    @Autowired
    public EventService(IParticipantRepository participantRepository, IEventRepository eventRepository) {
        this.participantRepository = participantRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public ServiceResponse<EventInfo> createEvent(String userId, NewEventRequest eventRequest) {
        try {
            List<String> keys = eventRequest.getParticipantIds();
            List<Participant> participants = retrieveParticipants(userId, keys);
            Map<String, String> map = createMapping(keys, participants);

            EventInfo eventInfo = new EventInfo(eventRequest.getName(),
                LocalDateTime.now(),
                eventRequest.getEventDateTime(),
                map);

            return ServiceResponse.success(eventInfo);
        } catch (Exception ex) {
            return ServiceResponseMapper.failure(logger, SystemEvent.EVENT_CREATE_FAIL, ex);
        }
    }

    @Override
    public ServiceResponse<String> saveEvent(String userId, EventInfo eventInfo) {
        try {
            String result = eventRepository.saveEventInfo(userId, eventInfo);
            return ServiceResponse.success(result);
        } catch (Exception ex) {
            return ServiceResponseMapper.failure(logger, SystemEvent.EVENT_SAVE_FAIL, ex);
        }
    }

    private List<Participant> retrieveParticipants(String userId, List<String> ids) {
        List<Participant> participants = participantRepository.getParticipants(userId);
        return participants.stream()
            .filter(participant -> ids.contains(participant.getId()))
            .collect(Collectors.toList());
    }

    private Map<String, String> createMapping(List<String> keys, List<Participant> participants) {
        List<String> values = new ArrayList<>(keys);

        Map<String, String> map = null;
        int count = 0;
        while (!isValidMapping(participants, map)) {
            Collections.shuffle(values);
            map = IntStream.range(0, keys.size()).boxed()
                .collect(Collectors.toMap(keys::get, values::get));
            Assert.isTrue(count < LIMIT, "hit the max attempts to create a mapping.");
            count++;
        }
        return map;
    }

    private boolean isValidMapping(List<Participant> participants, Map<String, String> map) {
        boolean result;
        if (map == null || map.isEmpty() || participants == null || participants.isEmpty()) {
            result = false;
        } else {
            result = !participants.stream()
                .anyMatch(participant -> isEligible(participant, map.get(participant.getId())) != true);
        }
        return result;
    }

    private boolean isEligible(Participant participant, String ineligible) {
        return !participant.getId().equals(ineligible) &&
            !isInList(participant, ineligible);
    }

    private boolean isInList(Participant participant, String ineligible) {
        boolean inList = false;
        if (participant.getIneligibles() != null) {
            inList = participant.getIneligibles().contains(ineligible);
        }
        return inList;
    }
}
