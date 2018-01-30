package com.jkg.www.outofahat.repository;


import com.jkg.www.outofahat.service.valueobject.model.EventInfo;

import java.util.List;

public interface IEventRepository {
    String saveEventInfo(final String userId, final EventInfo eventInfo);

    List<EventInfo> getEvents(final String userId);
}
