package com.jkg.www.outofahat.database.impl;

import com.jkg.www.outofahat.database.IOutOfAHatInfoConnector;
import com.jkg.www.outofahat.database.objects.EventInfoDbo;
import com.jkg.www.outofahat.database.objects.OutOfAHatInfoDbo;
import com.jkg.www.outofahat.database.objects.ParticipantDbo;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

@Repository
@ConfigurationProperties("mongo")
public class OutOfAHatInfoConnector extends MongoConnector<OutOfAHatInfoDbo> implements IOutOfAHatInfoConnector {
    private String uri;
    private String dbName;

    @Override
    protected String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    @Override
    protected String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    protected Class<OutOfAHatInfoDbo> getDboClass() {
        return OutOfAHatInfoDbo.class;
    }

    @Override
    public String createUser(final OutOfAHatInfoDbo outOfAHatInfoDbo) {
        Key<OutOfAHatInfoDbo> key = getDatastore().save(outOfAHatInfoDbo);
        String result = null;
        if (key != null) {
            result = key.getId().toString();
        }
        return result;
    }

    @Override
    public OutOfAHatInfoDbo findByUserId(final String userId) {
        Query<OutOfAHatInfoDbo> query = getUserIdQuery(userId);
        return query.get();
    }

    @Override
    public boolean addParticipant(String userId, ParticipantDbo participantDbo) {
        UpdateOperations<OutOfAHatInfoDbo> updateOperations = getDatastore()
            .createUpdateOperations(OutOfAHatInfoDbo.class);
        updateOperations.addToSet("participants", participantDbo);

        return updateByUserId(userId, updateOperations);
    }

    @Override
    public List<ParticipantDbo> getParticipants(final String userId) {
        Query<OutOfAHatInfoDbo> query = getUserIdQuery(userId);
        query.project("participants", true);
        OutOfAHatInfoDbo outOfAHatInfoDbo = query.get();
        Assert.notNull(outOfAHatInfoDbo, "unable to retrieve participants list for " + userId);
        return outOfAHatInfoDbo.getParticipants();
    }

    @Override
    public List<EventInfoDbo> getEvents(String userId) {
        Query<OutOfAHatInfoDbo> query = getUserIdQuery(userId);
        query.project("events", true);
        OutOfAHatInfoDbo outOfAHatInfoDbo = query.get();
        Assert.notNull(outOfAHatInfoDbo, "unable to retrieve events list for " + userId);
        return outOfAHatInfoDbo.getEvents();
    }

    @Override
    public boolean saveEvent(String userId, EventInfoDbo eventInfoDbo) {
        UpdateOperations<OutOfAHatInfoDbo> updateOperations = getDatastore()
            .createUpdateOperations(OutOfAHatInfoDbo.class);
        updateOperations.addToSet("events", eventInfoDbo);

        return updateByUserId(userId, updateOperations);
    }

    private boolean updateByUserId(final String userId, UpdateOperations<OutOfAHatInfoDbo> updateOperations) {
        final Query<OutOfAHatInfoDbo> query = getUserIdQuery(userId);
        UpdateResults updateResults = getDatastore().update(query, updateOperations);
        return updateResults.getUpdatedCount() > 0;
    }

    private Query<OutOfAHatInfoDbo> getUserIdQuery(final String userId) {
        Assert.isTrue(ObjectId.isValid(userId), "userId is not valid.");
        Query<OutOfAHatInfoDbo> query = getDatastore().createQuery(OutOfAHatInfoDbo.class);
        query.field("_id").equal(new ObjectId(userId));
        return query;
    }
}
