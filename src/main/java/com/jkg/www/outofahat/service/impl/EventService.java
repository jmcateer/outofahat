package com.jkg.www.outofahat.service.impl;

import com.jkg.www.outofahat.repository.IEventRepository;
import com.jkg.www.outofahat.repository.IParticipantRepository;
import com.jkg.www.outofahat.service.IEventService;
import com.jkg.www.outofahat.service.valueobject.NewEventRequest;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;
import com.jkg.www.outofahat.service.valueobject.model.EventInfo;
import com.jkg.www.outofahat.service.valueobject.model.Participant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class EventService implements IEventService {

    private static final int LIMIT = 50;
    private Logger logger = LoggerFactory.getLogger(EventService.class);
    private final IParticipantRepository participantRepository;
    private final IEventRepository eventRepository;
    private final EmailService emailService;

    @Autowired
    public EventService(
            IParticipantRepository participantRepository,
            IEventRepository eventRepository,
            EmailService emailService) {
        this.participantRepository = participantRepository;
        this.eventRepository = eventRepository;
        this.emailService = emailService;
    }

    @Override
    public ServiceResponse<List<EventInfo>> getEvents(String userId) {
        try {
            List<EventInfo> events = eventRepository.getEvents(userId);
            return ServiceResponse.success(events);
        } catch (Exception ex) {
            return ServiceResponseMapper.failure(logger, SystemEvent.EVENT_FIND_FAIL, ex);
        }
    }

    @Override
    public ServiceResponse<EventInfo> createEvent(
            String userId,
            NewEventRequest eventRequest) {
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
    public ServiceResponse<String> saveEvent(
            String userId,
            EventInfo eventInfo) {
        try {
            String result = eventRepository.saveEventInfo(userId, eventInfo);
            return ServiceResponse.success(result);
        } catch (Exception ex) {
            return ServiceResponseMapper.failure(logger, SystemEvent.EVENT_SAVE_FAIL, ex);
        }
    }

    @Override
    public ServiceResponse<String> createAndSaveEvent(
            String userName,
            NewEventRequest eventRequest) {
        ServiceResponse<EventInfo> eventResponse = this.createEvent(userName, eventRequest);
        if (!eventResponse.isSuccessful()) {
            return ServiceResponse.failure(eventResponse.getErrorDetails());
        }
        return this.saveEvent(userName, eventResponse.getValue());
    }

    @Override
    public ServiceResponse<List<String>> sendInvites(
            String userName,
            String eventName) {
        try {
            List<Participant> participants = participantRepository.getParticipants(userName);
            List<EventInfo> getEvents = eventRepository.getEvents(userName);
            EventInfo eventInfo = getEvents.stream()
                    .filter(e -> e.getName().equals(eventName))
                    .findFirst()
                    .orElse(null);
            Assert.notNull(eventInfo, "Unable to find event : " + eventName);
            Map<String, String> map = eventInfo.getMapping();
            List<Participant> participantsFiltered = participants.stream()
                    .filter(p -> map.containsKey(p.getId()))
                    .collect(Collectors.toList());

            List<String> result = participantsFiltered.stream()
                    .map(p -> emailService.sendInviteEmail(p.getContactInfo(), map.get(p.getId())))
                    .collect(Collectors.toList());
            return ServiceResponse.success(result);
        } catch (Exception ex) {
            return ServiceResponseMapper.failure(logger, SystemEvent.EVENT_SAVE_FAIL, ex);
        }
    }

    private List<Participant> retrieveParticipants(
            String userId,
            List<String> ids) {
        List<Participant> participants = participantRepository.getParticipants(userId);
        return participants.stream()
                .filter(participant -> ids.contains(participant.getId()))
                .collect(Collectors.toList());
    }

    private Map<String, String> createMapping(
            List<String> keys,
            List<Participant> participants) {
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

    private boolean isValidMapping(
            List<Participant> participants,
            Map<String, String> map) {
        boolean result;
        if (map == null || map.isEmpty() || participants == null || participants.isEmpty()) {
            result = false;
        } else {
            result = !participants.stream()
                    .anyMatch(participant -> isEligible(participant, map.get(participant.getId())) != true);
        }
        return result;
    }

    private boolean isEligible(
            Participant participant,
            String ineligible) {
        return !participant.getId().equals(ineligible) &&
                !isInList(participant, ineligible);
    }

    private boolean isInList(
            Participant participant,
            String ineligible) {
        boolean inList = false;
        if (participant.getIneligibles() != null) {
            inList = participant.getIneligibles().contains(ineligible);
        }
        return inList;
    }
}
