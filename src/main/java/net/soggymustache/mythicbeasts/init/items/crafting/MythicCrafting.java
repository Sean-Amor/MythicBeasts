package net.soggymustache.mythicbeasts.init.items.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.soggymustache.mythicbeasts.init.items.MythicItems;

public class MythicCrafting {
	public static void init(FMLInitializationEvent event) {
		GameRegistry.addShapelessRecipe(new ItemStack(MythicItems.invis_cloak, 1), new Object[] {Items.STRING, Items.STRING, Items.STRING, Items.STRING, Items.STRING, MythicItems.demiguise_pelt, MythicItems.demiguise_pelt, MythicItems.demiguise_pelt, MythicItems.demiguise_pelt});
	}
}
 