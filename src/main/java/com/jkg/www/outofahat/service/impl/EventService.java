package com.jkg.www.outofahat.service.impl;

import com.jkg.www.outofahat.repository.IParticipantRepository;
import com.jkg.www.outofahat.service.valueobject.CreateEventRequest;
import com.jkg.www.outofahat.service.IEventService;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;
import com.jkg.www.outofahat.service.valueobject.model.EventInfo;
import com.jkg.www.outofahat.service.valueobject.model.Participant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class EventService implements IEventService {
    private Logger logger = LoggerFactory.getLogger(EventService.class);
    private IParticipantRepository participantRepository;

    @Autowired
    public EventService(IParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public ServiceResponse<EventInfo> createEvent(String userId) {
        try {
            List<Participant> participants = participantRepository.getParticipants(userId);
            Map<String, String> map = shuffle(participants);
            EventInfo eventInfo = new EventInfo("event", LocalDateTime.now(), LocalDateTime.now(), map);
            return ServiceResponse.success(eventInfo);
        } catch (Exception ex) {
            return ServiceResponseMapper.failure(logger, SystemEvent.EVENT_CREATE_FAIL, ex);
        }
    }

    private Map<String, String> shuffle(List<Participant> participants) {
        List<String> keys = participants.stream()
            .map(participant -> participant.getId())
            .collect(Collectors.toList());
        List<String> values = new ArrayList<>(keys);
        Collections.shuffle(values);

        return IntStream.range(0, keys.size()).boxed()
            .collect(Collectors.toMap(keys::get, values::get));
    }
}
