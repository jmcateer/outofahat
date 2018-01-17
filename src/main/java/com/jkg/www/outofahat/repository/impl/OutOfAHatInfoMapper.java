package com.jkg.www.outofahat.repository.impl;

import com.jkg.www.outofahat.database.objects.ContactInfoDbo;
import com.jkg.www.outofahat.database.objects.OutOfAHatInfoDbo;
import com.jkg.www.outofahat.database.objects.ParticipantDbo;
import com.jkg.www.outofahat.service.valueobject.NewParticipantRequest;
import com.jkg.www.outofahat.service.valueobject.NewUserRequest;
import com.jkg.www.outofahat.service.valueobject.model.ContactInfo;
import com.jkg.www.outofahat.service.valueobject.model.Participant;
import com.jkg.www.outofahat.service.valueobject.model.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class OutOfAHatInfoMapper {

    /**
     * Create an OutOfAHatInfoDbo from a NewUserRequest.
     * @param userRequest A new user request.
     * @return new OutOfAHatInfoDbo.
     */
    public OutOfAHatInfoDbo mapFromNewUserRequest(NewUserRequest userRequest) {
        ContactInfoDbo contactInfoDbo = new ContactInfoDbo(
            userRequest.getFirst(),
            userRequest.getLast(),
            userRequest.getEmail(),
            userRequest.getPhone());
        ParticipantDbo participantDbo = new ParticipantDbo(
            createParticipantId(contactInfoDbo.getFirst(), contactInfoDbo.getLast(), null),
            contactInfoDbo,
            true);
        List<ParticipantDbo> participants = Arrays.asList(participantDbo);

        return new OutOfAHatInfoDbo(
            userRequest.getUserName(),
            userRequest.getPassword(),
            contactInfoDbo,
            participants,
            new ArrayList<>()
        );
    }

    /**
     * Create UserInfo from OutOfAHatInfoDbo.
     * @param outOfAHatInfoDbo A OutOfAHatINfoDbo.
     * @return new UserInfo.
     */
    public UserInfo mapToUserInfo(final OutOfAHatInfoDbo outOfAHatInfoDbo) {
        ContactInfo contactInfo = new ContactInfo(
            outOfAHatInfoDbo.getContactInfo().getFirst(),
            outOfAHatInfoDbo.getContactInfo().getLast(),
            outOfAHatInfoDbo.getContactInfo().getEmail(),
            outOfAHatInfoDbo.getContactInfo().getPhone());

        return new UserInfo(
            outOfAHatInfoDbo.getId().toString(),
            outOfAHatInfoDbo.getUserName(),
            contactInfo);
    }

    /**
     * Create a new participantId from the name.  If the participantId already exists in the list of ParticipantDbo,
     * a random value will be added to the result to make it unique.
     * @param first First name of the user.
     * @param last Last name of the user.
     * @param participants List of participants to check new id against for uniqueness.
     * @return new participantId.
     */
    public String createParticipantId(String first, String last, List<ParticipantDbo> participants) {
        String completedId = first + "_" + last;
        final String id = completedId;
        if (participants != null) {
            Optional<ParticipantDbo> optional = participants.stream()
                .filter(participant -> participant.getId().equals(id))
                .findFirst();
            if (optional.isPresent()) {
                Random ran = new Random();
                completedId = id + "-" + ran.nextInt(9999);
            }
        }
        return completedId;
    }

    /**
     * Create a ParticipantDbo from an NewParticipantRequest.  The participantId for the new object will be
     * generated checking the uniqueness from the list of ParticipantDbo.
     * @param participantRequest New participant request.
     * @param participants List of participants to check new id against for uniqueness.
     * @return new ParticipantDbo object.
     */
    public ParticipantDbo mapFromNewParticipantRequest(NewParticipantRequest participantRequest,
                                                       List<ParticipantDbo> participants) {
        Assert.notNull(participantRequest, "request cannot be null.");
        Assert.notNull(participantRequest.getFirst(), "first cannot be null.");
        Assert.notNull(participantRequest.getLast(), "last cannot be null.");
        Assert.notNull(participantRequest.getEmail(), "email cannot be null.");
        Assert.notNull(participantRequest.getPhone(), "phone cannot be null.");

        ContactInfoDbo contactInfoDbo = new ContactInfoDbo(
            participantRequest.getFirst(),
            participantRequest.getLast(),
            participantRequest.getEmail(),
            participantRequest.getPhone());
        return new ParticipantDbo(
            createParticipantId(contactInfoDbo.getFirst(), contactInfoDbo.getLast(), participants),
            contactInfoDbo,
            true);
    }

    /**
     * Create a Participant from ParticipantDbo.
     * @param participantDbo ParticipantDbo.
     * @return new Participant object.
     */
    public Participant mapToParticipant(final ParticipantDbo participantDbo) {
        return new Participant(
            participantDbo.getId(),
            mapToContractInfo(participantDbo.getContactInfo()),
            participantDbo.getActive(),
            participantDbo.getIneligibles(),
            participantDbo.getPrevious());
    }

    /**
     * Create a ContactInfo from ContactInfoDbo.
     * @param contactInfoDbo ContactInfoDbo.
     * @return new ContactInfo ojbect.
     */
    public ContactInfo mapToContractInfo(final ContactInfoDbo contactInfoDbo) {
        return new ContactInfo(
            contactInfoDbo.getFirst(),
            contactInfoDbo.getLast(),
            contactInfoDbo.getEmail(),
            contactInfoDbo.getPhone());
    }
}
