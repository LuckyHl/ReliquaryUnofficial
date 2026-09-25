package xreliquary.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xreliquary.api.INbtSyncTolerant;

@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer {

    @Shadow
    private Minecraft mc;

    @Shadow
    private ItemStack itemToRender;

    @Shadow
    private float equippedProgress;

    @Inject(method = "updateEquippedItem", at = @At("HEAD"))
    private void reliquary$syncItemToRenderOnHead(CallbackInfo ci) {
        if (this.itemToRender == null || this.mc == null || this.mc.thePlayer == null) {
            return;
        }
        ItemStack current = this.mc.thePlayer.inventory.getCurrentItem();
        if (current == null) {
            return;
        }
        if (!(current.getItem() instanceof INbtSyncTolerant)) {
            return;
        }
        if (this.itemToRender.getItem() != current.getItem()) {
            return;
        }
        this.itemToRender = current;
        this.equippedProgress = 1.0F;
    }
}
