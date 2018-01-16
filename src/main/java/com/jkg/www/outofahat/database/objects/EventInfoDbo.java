package com.jkg.www.outofahat.database.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.mongodb.morphia.annotations.Embedded;

import java.time.LocalDate;
import java.util.Map;

@Embedded
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
@Setter
public class EventInfoDbo {
    private String name;
    private LocalDate createDate;
    private LocalDate eventDate;
    private Map<String, String> mapping;
}
