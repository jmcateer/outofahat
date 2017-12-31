package com.jkg.www.outofahat.database.dbObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
@Setter
public class ContactDbo {
    private String id;
    private String first;
    private String last;
    private String email;
    private String phone;
    private List<String> ineligibles;
    private List<String> previous;
}
