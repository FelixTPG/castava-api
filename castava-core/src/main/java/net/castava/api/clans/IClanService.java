package net.castava.api.clans;

import net.castava.api.elements.Clan;
import net.castava.api.services.IServiceProvider;
import org.bson.conversions.Bson;

import java.util.Collection;
import java.util.function.Consumer;

public interface IClanService extends IServiceProvider {

    Collection<Clan> getClans();
    Clan getClan(Bson filter);
    void registerClan(Clan clan, Consumer<Boolean> success);
    void updateClan(Bson filter, Consumer<Clan> update);
    void deleteClan(Bson filter);

}
