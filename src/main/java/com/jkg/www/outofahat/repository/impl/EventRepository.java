package com.jkg.www.outofahat.repository.impl;

import com.jkg.www.outofahat.database.IOutOfAHatInfoConnector;
import com.jkg.www.outofahat.database.objects.EventInfoDbo;
import com.jkg.www.outofahat.repository.IEventRepository;
import com.jkg.www.outofahat.service.valueobject.model.EventInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class EventRepository implements IEventRepository {

    private OutOfAHatInfoMapper outOfAHatInfoMapper;
    private IOutOfAHatInfoConnector outOfAHatInfoConnector;

    @Autowired
    public EventRepository(
            OutOfAHatInfoMapper outOfAHatInfoMapper,
            IOutOfAHatInfoConnector outOfAHatInfoConnector) {
        this.outOfAHatInfoMapper = outOfAHatInfoMapper;
        this.outOfAHatInfoConnector = outOfAHatInfoConnector;
    }

    @Override
    public String saveEventInfo(
            String userId,
            EventInfo eventInfo) {
        EventInfoDbo eventInfoDbo = outOfAHatInfoMapper.mapFromEventInfo(eventInfo);
        List<EventInfoDbo> events = outOfAHatInfoConnector.getEvents(userId);
        if (events != null) {
            Optional<EventInfoDbo> opt = events.stream()
                    .filter(event -> event.getName().equals(eventInfoDbo.getName()))
                    .findAny();
            Assert.isTrue(!opt.isPresent(), "an event already exists with that name");
        }

        boolean result = outOfAHatInfoConnector.saveEvent(userId, eventInfoDbo);
        Assert.isTrue(result, "failed to save event");
        return eventInfoDbo.getName();
    }

    @Override
    public List<EventInfo> getEvents(String userId) {
        List<EventInfoDbo> events = outOfAHatInfoConnector.getEvents(userId);
        List<EventInfo> result = new ArrayList<>();
        if (events != null) {
            result = events.stream()
                    .map(eventInfoDbo -> outOfAHatInfoMapper.mapToEventInfo(eventInfoDbo))
                    .collect(Collectors.toList());
        }
        return result;
    }
}
