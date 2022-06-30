package com.iogamaster.dsyt;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class Dsyt implements ModInitializer {
    private static MinecraftClient client = MinecraftClient.getInstance();
    private static boolean changed = false;

    @Override
    public void onInitialize() {
        onTick();
    }

    private void onTick() {
        ClientTickEvents.START_CLIENT_TICK.register(listener -> {
            if(client.world != null)
			if(!changed) {
				if(!client.options.getAutoJump().getValue()) { 
                    if(MinecraftClient.getInstance().player.isSprinting()) {
                        changed = true;
                        MinecraftClient.getInstance().options.getAutoJump().setValue(true);
                    }
                }
            } else if(!client.player.isSprinting()) {
                client.options.getAutoJump().setValue(false);
                changed = false;
            }
        });
    }
}
