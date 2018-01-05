package com.jkg.www.outofahat.database.impl;

import com.jkg.www.outofahat.database.IOutOfAHatInfoConnector;
import com.jkg.www.outofahat.database.dbObjects.OutOfAHatInfoDbo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OutOfAHatInfoConnector implements IOutOfAHatInfoConnector {
    private Datastore datastore;

    private String mongoUri = "mongodb://outofahat:OvFXee5gInT0KnB3JymVLUnyXEnCX65Z96ak3A97RkVBVDfKMOhtG889ykNmJoCQiSoCdLZoVURrXPX9Nk8vVw==@outofahat.documents.azure.com:10255/?ssl=true&replicaSet=globaldb";
    private String dbName = "outofahat";

    @Autowired
    public OutOfAHatInfoConnector() {
        final MongoClientURI mongoClientURI = new MongoClientURI(mongoUri);
        final MongoClient mongoClient = new MongoClient(mongoClientURI);
        final Morphia morphia = new Morphia();
        datastore = morphia.mapPackageFromClass(OutOfAHatInfoDbo.class)
                .createDatastore(mongoClient, dbName);
        datastore.ensureIndexes();
    }

    public String createUser(final OutOfAHatInfoDbo outOfAHatInfoDbo) {
        Key<OutOfAHatInfoDbo> key = datastore.save(outOfAHatInfoDbo);
        String result = null;
        if (key != null) {
            result = key.getId().toString();
        }
        return result;
    }
}
