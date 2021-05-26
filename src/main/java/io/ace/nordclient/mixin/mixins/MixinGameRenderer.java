package io.ace.nordclient.mixin.mixins;

import io.ace.nordclient.hacks.misc.NoEntityTrace;
import io.ace.nordclient.managers.HackManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.item.Items;
import net.minecraft.util.math.RayTraceResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GameRenderer.class, priority = 2147483647)
public class MixinGameRenderer {

    @Final
    public Minecraft mc;

    @Inject(method = "getMouseOver", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/ProjectileHelper;rayTraceEntities(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/vector/Vector3d;Lnet/minecraft/util/math/vector/Vector3d;Lnet/minecraft/util/math/AxisAlignedBB;Ljava/util/function/Predicate;D)Lnet/minecraft/util/math/EntityRayTraceResult;"), cancellable = true)
    public void getEntitiesInAABBexcluding(float partialTicks, CallbackInfo ci) {
        if (((NoEntityTrace) HackManager.getHackByName("NoEntityTrace")).enabled && mc.player.getHeldItemMainhand().getItem().equals(Items.DIAMOND_PICKAXE) && mc.objectMouseOver.getType().equals(RayTraceResult.Type.BLOCK)) {
            ci.cancel();
            //
        }

    }
}
