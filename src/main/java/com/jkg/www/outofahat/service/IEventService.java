package com.jkg.www.outofahat.service;

import com.jkg.www.outofahat.service.valueobject.CreateEventRequest;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;
import com.jkg.www.outofahat.service.valueobject.model.EventInfo;

public interface IEventService {
    ServiceResponse<EventInfo> createEvent(final String userId); //, final CreateEventRequest createRequest);
}
