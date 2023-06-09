package net.castava.api.discord;

import net.castava.api.services.IServiceProvider;

import java.util.function.Consumer;

public interface WebHookService extends IServiceProvider {

    void sendMessage(DiscordChannel channel, Consumer<DiscordWebhook.EmbedObject> message);

}
