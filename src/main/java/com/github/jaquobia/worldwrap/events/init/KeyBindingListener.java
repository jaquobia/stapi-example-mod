package com.github.jaquobia.worldwrap.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.options.KeyBinding;
import net.modificationstation.stationapi.api.client.event.option.KeyBindingRegisterEvent;
import org.lwjgl.input.Keyboard;

import java.util.*;

public class KeyBindingListener {

    public static KeyBinding keyBinding;
    public static KeyBinding keyBinding2;
    public static KeyBinding keyBinding3;

    @EventListener
    public void registerKeyBindings(KeyBindingRegisterEvent event) {
        List<KeyBinding> list = event.keyBindings;
        list.add(keyBinding = new KeyBinding("key.worldwrap.test", Keyboard.KEY_Y));
        list.add(keyBinding2 = new KeyBinding("key.worldwrap.test2", Keyboard.KEY_U));
        list.add(keyBinding3 = new KeyBinding("key.worldwrap.hurtme", Keyboard.KEY_I));
    }
}
