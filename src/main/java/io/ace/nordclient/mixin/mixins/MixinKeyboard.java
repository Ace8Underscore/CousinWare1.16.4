package io.ace.nordclient.mixin.mixins;

import io.ace.nordclient.managers.HackManager;
import net.minecraft.client.KeyboardListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author evaan
 * https://github.com/evaan
 */
@Mixin(KeyboardListener.class)
public class MixinKeyboard {
    @Inject(method = "onKeyEvent", at = @At(value = "INVOKE", target = "net/minecraft/client/util/InputUtil.isKeyPressed(JI)Z", ordinal = 5), cancellable = true)
    private void onKeyEvent(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo callbackInfo) {
        HackManager.getHacks().forEach(h -> { if (h.getBind() == key) h.toggle(); });
    }
}