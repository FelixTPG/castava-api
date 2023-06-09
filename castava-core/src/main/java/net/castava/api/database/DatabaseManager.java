package net.castava.api.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.CursorType;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.UpdateOptions;
import lombok.Getter;
import net.castava.api.elements.DatabaseElement;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

@Getter
public class DatabaseManager implements IDatabaseManager {

    protected MongoClient mongoClient;
    protected Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    @Override
    public DatabaseManager buildConnection(String mongodbUri) {
        this.mongoClient = MongoClients.create(mongodbUri);
        this.gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        return this;
    }

    @Override
    public void closeConnection() {
        this.mongoClient.close();
    }

    @Override
    public <T extends DatabaseElement> Collection<T> getEntries(String database, String collection, Class<T> clazz) {
        MongoCollection<Document> storage = mongoClient.getDatabase(database).getCollection(collection);
        MongoCursor<Document> cursor = storage.find().cursorType(CursorType.NonTailable).cursor();
        Collection<T> list = new ArrayList<>();
        cursor.forEachRemaining(document -> list.add(gson.fromJson(document.toJson(), clazz)));
        return list;
    }

    @Override
    public <T extends DatabaseElement> T getEntry(String database, String collection, Bson filter, Class<T> clazz) {
        return gson.fromJson(mongoClient.getDatabase(database).getCollection(collection).find(filter).first().toJson(), clazz);
    }

    @Override
    public <T extends DatabaseElement> T getEntry(String database, String collection, Class<T> clazz) {
        return gson.fromJson(mongoClient.getDatabase(database).getCollection(collection).find().first().toJson(), clazz);
    }
    @Override
    public boolean exist(String database, String collection) {
        return (mongoClient.getDatabase(database).getCollection(collection).find().first() != null);
    }

    @Override
    public boolean exist(String database, String collection, Bson filter) {
        return (mongoClient.getDatabase(database).getCollection(collection).find(filter).first() != null);
    }

    @Override
    public <T extends DatabaseElement> void insertOne(String database, String collection, T object) {
        mongoClient.getDatabase(database).getCollection(collection).insertOne(gson.fromJson(gson.toJson(object), Document.class));
    }

    @Override
    public <T extends DatabaseElement> void updateOne(String database, String collection, Bson filter, Class<T> clazz, Consumer<T> updateCallback) {
        MongoCollection<Document> storage = mongoClient.getDatabase(database).getCollection(collection);
        T custom = getEntry(database, collection, filter, clazz);
        updateCallback.accept(custom);
        storage.updateOne(filter, new BasicDBObject("$set", gson.fromJson(gson.toJson(custom), Document.class)), new UpdateOptions().upsert(true));
    }

    @Override
    public void deleteOne(String database, String collection, Bson filter, Consumer<Boolean> deleteCallback) {
        if(exist(database, collection, filter)) {
            mongoClient.getDatabase(database).getCollection(collection).deleteOne(filter);
            deleteCallback.accept(true);
        } else deleteCallback.accept(false);
    }

}
