package com.maxi.simpleworldcount;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.util.Identifier;

public class SimpleWorldCountClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudElementRegistry.addLast(
                Identifier.of("simpleworldcount", "day_counter"),
                (drawContext, tickCounter) -> {

                    MinecraftClient client = MinecraftClient.getInstance();

                    if (client.player == null || client.world == null || client.options.hudHidden) {
                        return;
                    }

                    long worldTime = client.world.getTimeOfDay();
                    long days = worldTime / 24000L;
                    String text = "Day: " + days;

                    TextRenderer textRenderer = client.textRenderer;
                    int width = client.getWindow().getScaledWidth();
                    int textWidth = textRenderer.getWidth(text);
                    int padding = 4;

                    int x = width - textWidth - padding;
                    int y = padding;

                    int color = 0xFFFFFFFF;

                    drawContext.drawText(textRenderer, text, x, y, color, true);
                }
        );
    }
}