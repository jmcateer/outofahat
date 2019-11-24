package com.jkg.www.outofahat.database.impl;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import javax.annotation.PostConstruct;

public abstract class MongoConnector<T> {

    private Datastore datastore;

    protected abstract String getDbName();

    protected abstract String getUri();

    protected abstract Class<T> getDboClass();

    @PostConstruct
    public void setup() {
        MongoClientURI uri = new MongoClientURI(getUri());
        final MongoClient mongoClient = new MongoClient(uri);

        final Morphia morphia = new Morphia();
        morphia.mapPackageFromClass(getDboClass());
        datastore = morphia.createDatastore(mongoClient, getDbName());
        datastore.ensureIndexes();
    }

    public Datastore getDatastore() {
        return datastore;
    }
}
