package com.jkg.www.outofahat.database.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

@Entity(value = "OutOfAHatInfo", noClassnameStored = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class OutOfAHatInfoDbo {
    @Id
    private ObjectId id;
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @NonNull
    private ContactInfoDbo contactInfo;
    @NonNull
    private List<ParticipantDbo> participants;
    @NonNull
    private List<EventInfoDbo> events;
}