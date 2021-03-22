package io.ace.nordclient.hacks.misc;

import com.mojang.authlib.GameProfile;
import io.ace.nordclient.hacks.Hack;
import net.minecraft.client.entity.player.RemoteClientPlayerEntity;
import net.minecraft.entity.Entity;

import java.util.UUID;

public class FakePlayer extends Hack {

    RemoteClientPlayerEntity entity;

    public FakePlayer() {
        super("FakePlayer", Category.MISC, 3119447, null);
    }

    @Override
    public void onEnable() {
        entity = new RemoteClientPlayerEntity(mc.world, new GameProfile(UUID.fromString("d8d5a923-7b20-43d8-883b-1150148d6955"), "Test"));
        entity.copyLocationAndAnglesFrom(mc.player);
        entity.rotationYaw = mc.player.rotationYaw;
        entity.rotationYawHead = mc.player.rotationYawHead;
        mc.world.addEntity(69698067, entity);
    }

    @Override
    public void onDisable() {
        for (Entity entity1 : mc.world.getAllEntities()) {
            if (entity1.getEntity() == entity) {
                mc.world.removeEntity(entity);
            }
        }

    }

}
