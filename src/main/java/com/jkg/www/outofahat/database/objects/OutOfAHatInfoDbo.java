package com.jkg.www.outofahat.database.objects;

import static dev.morphia.utils.IndexType.ASC;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Field;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Index;
import dev.morphia.annotations.Indexes;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@Entity(value = "OutOfAHatInfo", noClassnameStored = true)
@Indexes({
        @Index(fields = @Field(value = "id", type = ASC)),
})
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
