package org.reavershark.centeredinventory.mixin;

import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.util.math.MatrixStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractInventoryScreen.class)
public class MixinAbstractInventoryScreen {

    @Shadow
    protected boolean drawStatusEffects;

    @ModifyVariable(method = "drawStatusEffects(Lnet/minecraft/client/util/math/MatrixStack;)V", at = @At("STORE"), ordinal = 0)
    private int moveEffectsRight(int i) {
        return i + 304;
    }

    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V", at = @At(value = "HEAD"))
    public void allwaysRenderEffects(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        this.drawStatusEffects = true;
    }
}
