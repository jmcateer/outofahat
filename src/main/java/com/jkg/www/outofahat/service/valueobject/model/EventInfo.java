package com.jkg.www.outofahat.service.valueobject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
@Setter
public class EventInfo {

    private String name;
    private LocalDateTime createDate;
    private LocalDateTime eventDateTime;
    private Map<String, String> mapping;
}
