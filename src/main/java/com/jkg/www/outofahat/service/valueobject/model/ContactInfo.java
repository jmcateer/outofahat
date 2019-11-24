package com.jkg.www.outofahat.service.valueobject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ContactInfo {

    @NonNull
    private String first;
    @NonNull
    private String last;
    @NonNull
    private String email;
    @NonNull
    private String phone;
}
