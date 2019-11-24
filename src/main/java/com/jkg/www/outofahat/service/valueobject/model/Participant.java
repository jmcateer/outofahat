package com.jkg.www.outofahat.service.valueobject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Participant {

    @NonNull
    private String id;
    @NonNull
    private ContactInfo contactInfo;
    @NonNull
    private Boolean active;
    private List<String> ineligibles;
    private List<String> previous;
}
