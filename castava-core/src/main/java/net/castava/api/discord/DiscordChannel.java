package net.castava.api.discord;

public enum DiscordChannel {

    DEFAULT("");

    private final String url;

    DiscordChannel(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
