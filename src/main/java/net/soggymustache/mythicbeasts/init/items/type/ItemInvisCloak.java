package net.soggymustache.mythicbeasts.init.items.type;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.soggymustache.mythicbeasts.MythicBeastsMain;

public class ItemInvisCloak extends Item {

	public ItemInvisCloak(String unlocalized){
		this.setUnlocalizedName(unlocalized);
		this.setCreativeTab(MythicBeastsMain.tabMain);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 200, 3));
		--itemStackIn.stackSize;
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}

}
