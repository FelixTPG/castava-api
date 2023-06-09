package net.castava.api.players;

import net.castava.api.database.IDatabaseManager;
import net.castava.api.elements.Player;
import net.castava.api.services.ServiceProvider;
import org.bson.conversions.Bson;

import java.util.Collection;
import java.util.function.Consumer;

public class PlayerServiceImpl implements IPlayerService {

    private final String DATABASE = "castava", COLLECTION = "playerData";
    private final IDatabaseManager databaseManager = ServiceProvider.getService(IDatabaseManager.class);

    @Override
    public Collection<Player> getPlayers() {
        return databaseManager.getEntries(DATABASE, COLLECTION, Player.class);
    }

    @Override
    public Player getPlayer(Bson filter) {
        return null;
    }

    @Override
    public void registerPlayer(Player clan, Consumer<Boolean> success) {

    }

    @Override
    public void updatePlayer(Bson filter, Consumer<Player> update) {

    }

    @Override
    public void deletePlayer(Bson filter) {

    }

}
