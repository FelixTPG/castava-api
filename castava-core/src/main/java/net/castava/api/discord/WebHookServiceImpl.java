package net.castava.api.discord;

import lombok.SneakyThrows;

import java.util.function.Consumer;

public class WebHookServiceImpl implements WebHookService {

    @Override
    @SneakyThrows
    public void sendMessage(DiscordChannel channel, Consumer<DiscordWebhook.EmbedObject> message) {
        switch (channel) {
            case DEFAULT -> {
                DiscordWebhook webhook = new DiscordWebhook(channel.getUrl());
                DiscordWebhook.EmbedObject object = new DiscordWebhook.EmbedObject();
                message.accept(object);
                webhook.addEmbed(object);
                webhook.execute();
            }
        }
    }

}
