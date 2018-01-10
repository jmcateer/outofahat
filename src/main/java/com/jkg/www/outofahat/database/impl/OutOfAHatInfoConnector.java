package com.jkg.www.outofahat.database.impl;

import com.jkg.www.outofahat.database.IOutOfAHatInfoConnector;
import com.jkg.www.outofahat.database.dbObjects.OutOfAHatInfoDbo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class OutOfAHatInfoConnector implements IOutOfAHatInfoConnector {
    private Datastore datastore;

    @Autowired
    public OutOfAHatInfoConnector(@Value("${mongo.uri}") final String mongoUri, @Value("${mongo.dbName}") final String dbName) {
        final MongoClientURI mongoClientURI = new MongoClientURI(mongoUri);
        final MongoClient mongoClient = new MongoClient(mongoClientURI);
        final Morphia morphia = new Morphia();
        datastore = morphia.mapPackageFromClass(OutOfAHatInfoDbo.class)
                .createDatastore(mongoClient, dbName);
        datastore.ensureIndexes();
    }

    @Override
    public String createUser(final OutOfAHatInfoDbo outOfAHatInfoDbo) {
        Key<OutOfAHatInfoDbo> key = datastore.save(outOfAHatInfoDbo);
        String result = null;
        if (key != null) {
            result = key.getId().toString();
        }
        return result;
    }

    @Override
    public OutOfAHatInfoDbo findByUserId(final String userId) {
        Query<OutOfAHatInfoDbo> query = datastore.createQuery(OutOfAHatInfoDbo.class);
        query.field("id").equal(new ObjectId(userId));
        return query.get();
    }

}
