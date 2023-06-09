package net.castava.api.elements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.castava.api.elements.DatabaseElement;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Player extends DatabaseElement {

    private String username;
    private UUID uniqueId;
    private int coins;
    private double playtime;
    private Collection<UUID> friends;
    private Collection<UUID> friendRequests;
    private Map<String, String> settings;
    private long lastOnlineStamp;
    private long creationStamp;
    private boolean vanished;

}