package com.jkg.www.outofahat.service.valueobject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.mongodb.morphia.annotations.Embedded;

import java.time.LocalDateTime;
import java.util.List;

@Embedded
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
@Setter
public class NewEventRequest {
    private String name;
    private LocalDateTime eventDateTime;
    private List<String> participantIds;
}
