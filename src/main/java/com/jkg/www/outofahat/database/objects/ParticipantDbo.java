package com.jkg.www.outofahat.database.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.mongodb.morphia.annotations.Embedded;

import java.util.List;

@Embedded
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class ParticipantDbo {
    @NonNull
    private String id;
    @NonNull
    private ContactInfoDbo contactInfo;
    @NonNull
    private Boolean active;
    private List<String> ineligibles;
    private List<String> previous;
}
