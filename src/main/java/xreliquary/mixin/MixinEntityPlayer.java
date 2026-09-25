package xreliquary.mixin;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xreliquary.api.INbtSyncTolerant;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer {

    @Shadow
    private ItemStack itemInUse;

    @Shadow
    public InventoryPlayer inventory;

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void reliquary$syncItemInUseOnHead(CallbackInfo ci) {
        if (this.itemInUse == null || this.inventory == null) {
            return;
        }
        ItemStack current = this.inventory.getCurrentItem();
        if (current == null) {
            return;
        }
        if (!(current.getItem() instanceof INbtSyncTolerant)) {
            return;
        }
        if (this.itemInUse.getItem() != current.getItem()) {
            return;
        }
        this.itemInUse = current;
    }

    @Inject(
        method = "onUpdate",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/player/EntityPlayer;clearItemInUse()V"
        ),
        cancellable = true
    )
    private void reliquary$keepUsingAcrossNbtSync(CallbackInfo ci) {
        ItemStack current;
        if (this.itemInUse == null || this.inventory == null
                || (current = this.inventory.getCurrentItem()) == null) {
            return;
        }
        if (!(current.getItem() instanceof INbtSyncTolerant)) {
            return;
        }
        if (this.itemInUse.getItem() != current.getItem()) {
            return;
        }
        this.itemInUse = current;
        ci.cancel();
    }
}
