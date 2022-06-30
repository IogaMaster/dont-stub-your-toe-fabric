package com.iogamaster.dsyt;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class Dsyt implements ModInitializer {

    private static boolean changed = false;

    @Override
    public void onInitialize() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if(MinecraftClient.getInstance().world != null)
			if(!changed) {
				if(!MinecraftClient.getInstance().options.getAutoJump().getValue()) { 
                    if(MinecraftClient.getInstance().player.isSprinting()) {
                        changed = true;
                        MinecraftClient.getInstance().options.getAutoJump().setValue(true);
                    }
                }
            } else if(!MinecraftClient.getInstance().player.isSprinting()) {
                MinecraftClient.getInstance().options.getAutoJump().setValue(false);
                changed = false;
            }
        });
    }
}
