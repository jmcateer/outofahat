package com.jkg.www.outofahat.service.valueobject.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
public class ContactInfo {
    @NonNull
    private Integer contactId;
    @NonNull
    private String first;
    @NonNull
    private String last;
    @NonNull
    private String email;
    @NonNull
    private Boolean active;
}
