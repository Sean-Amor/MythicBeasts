package net.soggymustache.mythicbeasts.init.items.type;

import net.minecraft.item.Item;
import net.soggymustache.mythicbeasts.MythicBeastsMain;

public class MythicBaseItem extends Item {

	public MythicBaseItem(String unlocalized){
		this.setUnlocalizedName(unlocalized);
		this.setCreativeTab(MythicBeastsMain.tabMain);
	}

}
