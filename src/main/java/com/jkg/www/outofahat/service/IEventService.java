package com.jkg.www.outofahat.service;

import com.jkg.www.outofahat.service.valueobject.NewEventRequest;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;
import com.jkg.www.outofahat.service.valueobject.model.EventInfo;

import java.util.List;

public interface IEventService {
    ServiceResponse<List<EventInfo>> getEvents(final String userId);

    ServiceResponse<EventInfo> createEvent(final String userId, final NewEventRequest eventRequest);

    ServiceResponse<String> saveEvent(final String userId, final EventInfo eventInfo);
}
