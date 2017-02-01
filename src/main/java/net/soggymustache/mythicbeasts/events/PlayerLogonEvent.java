package net.soggymustache.mythicbeasts.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.soggymustache.mythicbeasts.init.items.MythicItems;

public class PlayerLogonEvent {

	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent e)
	{
		EntityPlayer player = (EntityPlayer) e.player;
        
			if (!player.worldObj.isRemote && !player.inventory.hasItemStack(new ItemStack(MythicItems.book_of_beasts)))
			{
				player.inventory.addItemStackToInventory(new ItemStack(MythicItems.book_of_beasts));
			}
	}
	
}
