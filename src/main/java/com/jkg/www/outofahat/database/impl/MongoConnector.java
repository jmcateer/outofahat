package com.jkg.www.outofahat.database.impl;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import javax.annotation.PostConstruct;

public abstract class MongoConnector<T> {

    private Datastore datastore;

    protected abstract String getDbName();

    protected abstract String getUri();

    protected abstract Class<T> getDboClass();

    @PostConstruct
    public void setup() {
        final MongoClientURI mongoClientUri = new MongoClientURI(getUri());
        final MongoClient mongoClient = new MongoClient(mongoClientUri);
        final Morphia morphia = new Morphia();
        datastore = morphia.mapPackageFromClass(getDboClass()).createDatastore(mongoClient, getDbName());
        datastore.ensureIndexes();
    }

    public Datastore getDatastore() {
        return datastore;
    }
}
