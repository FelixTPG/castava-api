package net.castava.api.clans;

import com.mongodb.client.model.Filters;
import net.castava.api.database.IDatabaseManager;
import net.castava.api.elements.Clan;
import net.castava.api.services.ServiceProvider;
import org.bson.conversions.Bson;

import java.util.Collection;
import java.util.function.Consumer;

public class ClanServiceImpl implements IClanService {

    private final String DATABASE = "castava", COLLECTION = "clans";
    private final IDatabaseManager databaseManager = ServiceProvider.getService(IDatabaseManager.class);

    @Override
    public Collection<Clan> getClans() {
        return databaseManager.getEntries(DATABASE, COLLECTION, Clan.class);
    }

    @Override
    public Clan getClan(Bson filter) {
        return databaseManager.getEntry(DATABASE, COLLECTION, filter, Clan.class);
    }

    @Override
    public void registerClan(Clan clan, Consumer<Boolean> success) {
        boolean register = databaseManager.exist(DATABASE, COLLECTION, Filters.eq("name", clan.getName()));
        if(!register) {
            databaseManager.insertOne(DATABASE, COLLECTION, clan);
            success.accept(false);
        }
    }

    @Override
    public void updateClan(Bson filter, Consumer<Clan> update) {
        databaseManager.updateOne(DATABASE, COLLECTION, filter, Clan.class, update);
    }

    @Override
    public void deleteClan(Bson filter) {
        databaseManager.deleteOne(DATABASE, COLLECTION, filter, aBoolean -> {});
    }

}
