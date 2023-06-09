package net.castava.api.elements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Clan extends DatabaseElement {

    private String id = UUID.randomUUID().toString().split("-")[0];
    private String name;
    private String tag;
    private Collection<UUID> members;
    private Collection<UUID> invites;
    private Collection<UUID> operators;
    private UUID owner;

}
