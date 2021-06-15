package org.reavershark.centeredinventory.mixin;

import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.client.gui.screen.recipebook.RecipeDisplayListener;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeGridAligner;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecipeBookWidget.class)
public abstract class MixinRecipeBookWidget extends DrawableHelper implements Drawable, Element, Selectable, RecipeDisplayListener, RecipeGridAligner<Ingredient> {

    @Shadow
    private int leftOffset;

    @Inject(method = "findLeftEdge(II)I", at = @At("RETURN"), cancellable = true)
    private void staticPosition(int i, int j, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue((i - j) / 2);
    }

    @Inject(method = "reset", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/recipebook/RecipeBookWidget;leftOffset:I"))
    public void increaseLeftOffset(CallbackInfo ci) {
        this.leftOffset = 165;
    }

    @Inject(method = "isWide()Z", at = @At("RETURN"), cancellable = true)
    private void fixWideCheck(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(this.leftOffset == 165);
    }
}
