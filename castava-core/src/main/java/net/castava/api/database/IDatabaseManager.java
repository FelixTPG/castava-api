package net.castava.api.database;

import net.castava.api.elements.DatabaseElement;
import net.castava.api.services.IServiceProvider;
import org.bson.conversions.Bson;

import java.util.Collection;
import java.util.function.Consumer;

public interface IDatabaseManager extends IServiceProvider {

    IDatabaseManager buildConnection(String connectionString);

    void closeConnection();

    <T extends DatabaseElement> Collection<T> getEntries(String database, String collection, Class<T> clazz);

    <T extends DatabaseElement> T getEntry(String database, String collection, Bson filters, Class<T> clazz);

    <T extends DatabaseElement> T getEntry(String database, String collection, Class<T> clazz);

    boolean exist(String database, String collection);

    boolean exist(String database, String collection, Bson filter);

    <T extends DatabaseElement> void insertOne(String database, String collection, T object);

    <T extends DatabaseElement> void updateOne(String database, String collection, Bson filter, Class<T> clazz, Consumer<T> updateCallback);

    void deleteOne(String database, String collection, Bson filter, Consumer<Boolean> deleteCallback);


}
