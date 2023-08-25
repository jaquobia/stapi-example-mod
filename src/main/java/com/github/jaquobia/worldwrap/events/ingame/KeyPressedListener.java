package com.github.jaquobia.worldwrap.events.ingame;

import com.github.jaquobia.worldwrap.events.init.KeyBindingListener;
import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.Minecraft;
import net.modificationstation.stationapi.api.client.event.keyboard.KeyStateChangedEvent;
import org.lwjgl.input.Keyboard;

public class KeyPressedListener {

    @EventListener
    public void keyPressed(KeyStateChangedEvent event) {
        Minecraft mc = (Minecraft)FabricLoader.getInstance().getGameInstance();
        if (Keyboard.getEventKeyState() && Keyboard.isKeyDown(KeyBindingListener.keyBinding.key)) {
//            mc.player.increaseStat(AchievementListener.achievement, 1);
        }
        if (Keyboard.getEventKeyState() && Keyboard.isKeyDown(KeyBindingListener.keyBinding2.key)) {
//            mc.player.increaseStat(AchievementListener.achievement2, 1);
        }
        if (Keyboard.getEventKeyState() && Keyboard.isKeyDown(KeyBindingListener.keyBinding3.key)) {
//            mc.player.damage(null, 1000000);
        }
    }
}