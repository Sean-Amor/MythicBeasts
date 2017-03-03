package net.soggymustache.mythicbeasts.init.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.soggymustache.mythicbeasts.Reference;
import net.soggymustache.mythicbeasts.init.items.type.ItemInvisCloak;
import net.soggymustache.mythicbeasts.init.items.type.MythicBaseItem;

public class MythicItems
{
	public static Item invis_cloak = new ItemInvisCloak("invis_cloak");
	public static Item demiguise_pelt = new MythicBaseItem("demiguise_pelt");
    public static Item snail_flesh_cooked = new MythicBaseItem("snail_flesh_cooked");
	public static Item snail_flesh_raw = new MythicBaseItem("snail_flesh_raw");
	public static Item snail_shell = new MythicBaseItem("snail_shell");
	public static Item poison_gland = new MythicBaseItem("poison_gland");
	public static Item book_of_beasts = new MythicBaseItem("book_of_beasts"){
		public void addInformation(net.minecraft.item.ItemStack stack, net.minecraft.entity.player.EntityPlayer playerIn, java.util.List<String> tooltip, boolean advanced) {
			tooltip.add("Interact with newly discovered beasts");
		};
	};

	public static void init()
	{ 
		register(demiguise_pelt);
        register(snail_flesh_cooked);
		register(snail_flesh_raw);
		register(snail_shell);
		register(poison_gland);       
		register(invis_cloak);
		register(book_of_beasts);
	}

	private static void register(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}

	public static void registerRenderers()
	{
		registerItemRenderer(demiguise_pelt);
		registerItemRenderer(snail_flesh_cooked);
		registerItemRenderer(snail_flesh_raw);
		registerItemRenderer(snail_shell);
		registerItemRenderer(poison_gland);
		registerItemRenderer(invis_cloak);
		registerItemRenderer(book_of_beasts);
	}

	public static void registerItemRenderer(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}