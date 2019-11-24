package com.jkg.www.outofahat.database.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class ContactInfoDbo {

    @NonNull
    private String first;
    @NonNull
    private String last;
    @NonNull
    private String email;
    @NonNull
    private String phone;
}
