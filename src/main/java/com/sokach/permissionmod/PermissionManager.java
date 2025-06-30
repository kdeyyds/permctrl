package com.sokach.permissionmod;

import com.google.gson.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class PermissionManager {
    private static final Map<String, Set<String>> playerPermissions = new HashMap<>();

    public static void loadPermissions(Path configPath) {
        try {
            if (!Files.exists(configPath)) {
                Files.createDirectories(configPath.getParent());
                String defaultJson = """
                    {
                      "permissions": {
                        "SokachTang": ["tp"]
                      }
                    }
                    """;
                Files.writeString(configPath, defaultJson);
            }

            String json = Files.readString(configPath);
            JsonObject root = JsonParser.parseString(json).getAsJsonObject();
            JsonObject perms = root.getAsJsonObject("permissions");

            for (String player : perms.keySet()) {
                JsonArray array = perms.getAsJsonArray(player);
                Set<String> permissionSet = new HashSet<>();
                for (JsonElement e : array) {
                    permissionSet.add(e.getAsString().toLowerCase());
                }
                playerPermissions.put(player.toLowerCase(), permissionSet);
            }

            System.out.println("[PermissionControl] Permissions loaded.");

        } catch (IOException | JsonParseException e) {
            System.err.println("[PermissionControl] Failed to load permissions: " + e.getMessage());
        }
    }

    public static boolean hasPermission(String playerName, String permission) {
        Set<String> perms = playerPermissions.get(playerName.toLowerCase());
        return perms != null && (perms.contains("*") || perms.contains(permission.toLowerCase()));
    }
}