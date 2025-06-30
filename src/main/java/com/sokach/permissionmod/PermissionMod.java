package com.sokach.permissionmod;

import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;

import java.nio.file.Path;

@Mod(PermissionMod.MODID)
@EventBusSubscriber(modid = PermissionMod.MODID)
public class PermissionMod {
    public static final String MODID = "permctrl";

    public PermissionMod() {
        System.out.println("[PermissionControl] Mod loaded.");
    }

    @SubscribeEvent
    public static void onSetup(FMLCommonSetupEvent event) {
        Path configPath = Path.of("config/permctrl.json");
        PermissionManager.loadPermissions(configPath);
    }
}