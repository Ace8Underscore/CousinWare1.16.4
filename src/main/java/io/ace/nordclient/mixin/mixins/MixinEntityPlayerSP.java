package io.ace.nordclient.mixin.mixins;

import io.ace.nordclient.CousinWare;
import io.ace.nordclient.event.EventStageable;
import io.ace.nordclient.event.PlayerMoveEvent;
import io.ace.nordclient.event.UpdateEvent;
import io.ace.nordclient.managers.HackManager;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ClientPlayerEntity.class, priority = 998)
public abstract class MixinEntityPlayerSP extends AbstractClientPlayerEntity {
    public MixinEntityPlayerSP() {
        super(null, null);
    }

    @Redirect(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/player/AbstractClientPlayerEntity;move(Lnet/minecraft/entity/MoverType;Lnet/minecraft/util/math/vector/Vector3d;)V"))
    public void move(AbstractClientPlayerEntity abstractClientPlayerEntity, MoverType typeIn, Vector3d pos) {
        PlayerMoveEvent moveEvent = new PlayerMoveEvent(typeIn, pos.getX(), pos.getY(), pos.getZ());
        CousinWare.EVENT_BUS.post(moveEvent);
        super.move(typeIn, pos);
    }

    @Inject(method = "tick()V", at = @At(value = "FIELD", target = "Lnet/minecraft/client/entity/player/ClientPlayerEntity;connection:Lnet/minecraft/client/network/play/ClientPlayNetHandler;", ordinal = 0, shift = At.Shift.BEFORE), cancellable = true)
    public void onUpdatePre(CallbackInfo ci) { //support for haram pigs: makes it so that the event still runs when riding entities, or bad shit will happen lol
        UpdateEvent event = new UpdateEvent(EventStageable.EventStage.PRE, this.rotationYaw, this.rotationPitch, this.getPosX(), this.getBoundingBox().minY, this.getPosZ(), this.onGround);
        CousinWare.EVENT_BUS.post(event);
        if (event.isCanceled()) ci.cancel();
    }

    @Inject(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/play/ClientPlayNetHandler;sendPacket(Lnet/minecraft/network/IPacket;)V", ordinal = 0, shift = At.Shift.AFTER))
    public void onUpdatePost(CallbackInfo ci) {
        UpdateEvent event = new UpdateEvent(EventStageable.EventStage.POST, this.rotationYaw, this.rotationPitch, this.getPosX(), this.getBoundingBox().minY, this.getPosZ(), this.onGround);
        CousinWare.EVENT_BUS.post(event);

    }


    @Inject(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/player/ClientPlayerEntity;onUpdateWalkingPlayer()V", ordinal = 0, shift = At.Shift.AFTER))
    //death by sex
    public void onUpdateElse(CallbackInfo ci) {
        UpdateEvent event = new UpdateEvent(EventStageable.EventStage.POST, this.rotationYaw, this.rotationPitch, this.getPosX(), this.getBoundingBox().minY, this.getPosZ(), this.onGround);
        CousinWare.EVENT_BUS.post(event);
    }

    @Inject(method = "shouldBlockPushPlayer", at = @At(value = "HEAD"), cancellable = true)
    protected void pushOutOfBlocks(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (HackManager.getHackByName("Velocity").isEnabled()) {
            cir.setReturnValue(false);
        }
    }


}
