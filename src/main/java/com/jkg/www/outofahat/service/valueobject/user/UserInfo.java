package com.jkg.www.outofahat.service.valueobject.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jkg.www.outofahat.service.valueobject.user.ContactInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
public class UserInfo {
    @NonNull
    private String userId;
    @NonNull
    private String userName;
    @NonNull
    private ContactInfo contactInfo;
}
