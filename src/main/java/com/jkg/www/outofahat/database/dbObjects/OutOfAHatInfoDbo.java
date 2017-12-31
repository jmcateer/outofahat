package com.jkg.www.outofahat.database.dbObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.mongodb.morphia.annotations.Entity;

import java.util.List;

@Entity(value = "OutOfAHatInfo", noClassnameStored = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
@Setter
public class OutOfAHatInfoDbo {
    private String userName;
    private String password;
    private ContactDbo contactInfo;
    private List<ContactDbo> contacts;
    private List<EventInfoDbo> events;
}
