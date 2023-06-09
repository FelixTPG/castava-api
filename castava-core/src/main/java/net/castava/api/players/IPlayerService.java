package net.castava.api.players;

import net.castava.api.elements.Player;
import net.castava.api.services.IServiceProvider;
import org.bson.conversions.Bson;

import java.util.Collection;
import java.util.function.Consumer;

public interface IPlayerService extends IServiceProvider {

    Collection<Player> getPlayers();
    Player getPlayer(Bson filter);
    void registerPlayer(Player clan, Consumer<Boolean> success);
    void updatePlayer(Bson filter, Consumer<Player> update);
    void deletePlayer(Bson filter);

}