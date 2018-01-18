package com.jkg.www.outofahat.service.valueobject;

import com.jkg.www.outofahat.service.valueobject.model.Participant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateEventRequest {
    private String eventName;
    private LocalDateTime eventDateTime;
    private List<Participant> participants;
}
