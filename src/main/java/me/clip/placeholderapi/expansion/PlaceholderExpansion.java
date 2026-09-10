package me.clip.placeholderapi.expansion;

import org.bukkit.OfflinePlayer;

// STUB compile-only (bi loai khoi jar boi maven-jar-plugin excludes).
// Luc chay that, server dung lop that tu PlaceholderAPI.jar (cung FQN + signatures).
public abstract class PlaceholderExpansion {

    public abstract String getIdentifier();

    public abstract String getAuthor();

    public abstract String getVersion();

    public boolean persist() {
        return false;
    }

    public String onRequest(OfflinePlayer p, String id) {
        return null;
    }

    public boolean register() {
        return false;
    }
}
