/*package io.ace.nordclient.mixin.mixins;

import io.ace.nordclient.mixin.accessor.ISPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SPacketEntityVelocity.class)
public abstract class MixinSPacketEntityVelocity implements ISPacketEntityVelocity {

    @Shadow
    protected int entityID;

    @Shadow
    protected int motionX;

    @Shadow
    protected int motionY;

    @Shadow
    protected int motionZ;

    public int getMotionX() {
        return this.motionX;
    }

    @Override
    public void setMotionX(int x) {
        this.motionX = x;
    }

    public int getMotionY() {
        return this.motionY;
    }

    @Override
    public void setMotionY(int y) {
        this.motionY = y;
    }

    public int getMotionZ() {
        return this.motionZ;
    }

    @Override
    public void setMotionZ(int z) {
        this.motionZ = z;
    }

    public int getEntityID() {
        return this.entityID;
    }
} */
