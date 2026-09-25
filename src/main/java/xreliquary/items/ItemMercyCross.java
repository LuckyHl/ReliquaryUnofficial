package xreliquary.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lib.enderwizards.sandstone.init.ContentInit;
import lib.enderwizards.sandstone.util.LanguageHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import org.lwjgl.input.Keyboard;
import xreliquary.Reliquary;
import xreliquary.lib.Names;
import xreliquary.lib.Reference;

import java.util.List;

@ContentInit
public class ItemMercyCross extends ItemSword {

    public ItemMercyCross() {
        super(ToolMaterial.GOLD);
        this.setMaxDamage(64);
        this.setMaxStackSize(1);
        canRepair = true;
        this.setCreativeTab(Reliquary.CREATIVE_TAB);
        this.setUnlocalizedName(Names.mercy_cross);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {

        itemIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.epic;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && !Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
            return;
        String value = LanguageHelper.getLocalization("item." + Names.mercy_cross + ".tooltip");
        for (String descriptionLine : value.split(";")) {
            if (descriptionLine != null && descriptionLine.length() > 0)
                list.add(descriptionLine);
        }
    }

    /**
     */
    @Override
    public float func_150931_i() {
        return (float) getNormalDamage();
    }

    private boolean isUndead(EntityLivingBase e) {
        return e.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD;
    }

    private static int getUndeadDamage() { return Reliquary.CONFIG.getInt(Names.mercy_cross, "undead_damage"); }

    private static int getNormalDamage() { return Reliquary.CONFIG.getInt(Names.mercy_cross, "normal_damage"); }

    private static boolean showParticles() { return Reliquary.CONFIG.getBool(Names.mercy_cross, "show_particles"); }

    /**
     * Returns the strength of the stack against a given block. 1.0F base,
     * (Quality+1)*2 if correct blocktype, 1.5F if sword
     */
    @Override
    public float func_150893_a(ItemStack stack, Block block) {
        return block == Blocks.web ? 15.0F : 1.5F;
    }

    /**
     *
     */
    @Override
    public Multimap getItemAttributeModifiers() {
        Multimap multimap = HashMultimap.create();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
                new AttributeModifier(field_111210_e, "Weapon modifier", (double) getNormalDamage(), 0));
        return multimap;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity monster) {
        if (showParticles() && monster instanceof EntityLiving) {
            if (isUndead((EntityLiving)monster)) {
                monster.worldObj.spawnParticle("largeexplode", monster.posX + (itemRand.nextFloat() - 0.5F), monster.posY + (itemRand.nextFloat() - 0.5F) + (monster.height / 2), monster.posZ + (itemRand.nextFloat() - 0.5F), 0.0F, 0.0F, 0.0F);
            }
        }
        return super.onLeftClickEntity(stack, player, monster);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase monster, EntityLivingBase player) {
        stack.damageItem(1, player);

        if (!(player instanceof EntityPlayer)) {
            return true;
        }
        EntityPlayer p = (EntityPlayer) player;

        int base = isUndead(monster) ? getUndeadDamage() : getNormalDamage();
        float ench = EnchantmentHelper.getEnchantmentModifierLiving(p, monster);

        //   （hurtResistantTime > maxHurtResistantTime / 2）：
        monster.attackEntityFrom(DamageSource.causePlayerDamage(p), (float) base + ench);
        return true;
    }
}
