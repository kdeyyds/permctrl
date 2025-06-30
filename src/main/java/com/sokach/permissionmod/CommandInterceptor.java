package com.sokach.permissionmod;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.event.ServerChatEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PermissionMod.MODID)
public class CommandInterceptor {

    @SubscribeEvent
    public static void onChat(ServerChatEvent event) {
        String msg = event.getRawText();
        ServerPlayer player = event.getPlayer();
        String name = player.getName().getString();

        if (msg.startsWith("/tp")) {
            if (!PermissionManager.hasPermission(name, "tp")) {
                player.sendSystemMessage(Component.literal("§c你没有权限使用 /tp"));
                event.setCanceled(true);
            }
        }
    }
}