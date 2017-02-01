package net.soggymustache.mythicbeasts.init.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.soggymustache.mythicbeasts.Reference;
import net.soggymustache.mythicbeasts.MythicBeastsMain;

public class MythicBlocks {
	
//	public static Block Asphalt_Simple;
//	public static Block Asphalt_Lined;
//	public static Block Asphalt_Lined_Alt;
//	public static Block Asphalt_Simple_Slab;
//	public static Block Guard_Rail;
//	public static Block Guard_Rail_Alt;
//	public static Block Asphalt_Slab_N;
//	public static Block Asphalt_Slab_E;
//	public static Block checkered_block;
//	public static Block Balloon;
//	public static Block wheel;
	
	public static void init() {

//		Asphalt_Simple = new RoadBlock(Material.ROCK).setUnlocalizedName("Asphalt_Simple");
//		Asphalt_Lined = new RoadBlock(Material.ROCK).setUnlocalizedName("Asphalt_Lined");
//		Asphalt_Lined_Alt = new RoadBlock(Material.ROCK).setUnlocalizedName("Asphalt_Lined_Alt");
//		Asphalt_Slab_N = new RoadSlab(Material.ROCK).setUnlocalizedName("Asphalt_Slab_N");
//		Asphalt_Slab_E = new RoadSlab(Material.ROCK).setUnlocalizedName("Asphalt_Slab_E");
//		Asphalt_Simple_Slab = new RoadSlab(Material.ROCK).setUnlocalizedName("Asphalt_Simple_Slab");
//		Guard_Rail_Alt = new RoadBarrier(Material.ANVIL).setUnlocalizedName("Guard_Rail_Alt");
//		Guard_Rail = new RoadBarrier(Material.ANVIL).setUnlocalizedName("Guard_Rail");
//		checkered_block = new Block(Material.ROCK).setUnlocalizedName("checkered_block").setCreativeTab(MythicBeastsMain.tabRoad);
//		Balloon = new Block(Material.CLOTH).setUnlocalizedName("Balloon").setCreativeTab(MythicBeastsMain.tabRoad);
//		wheel  = new BlockVP(Material.IRON, "wheel");
	}
	
	public static void register()
	{
//		GameRegistry.registerBlock(wheel, wheel.getUnlocalizedName().substring(5));
//		GameRegistry.registerBlock(Guard_Rail_Alt, Guard_Rail_Alt.getUnlocalizedName().substring(5));
//		GameRegistry.registerBlock(Guard_Rail, Guard_Rail.getUnlocalizedName().substring(5));
//		GameRegistry.registerBlock(Asphalt_Simple, Asphalt_Simple.getUnlocalizedName().substring(5));
//		GameRegistry.registerBlock(Asphalt_Lined, Asphalt_Lined.getUnlocalizedName().substring(5));
//		GameRegistry.registerBlock(Asphalt_Lined_Alt, Asphalt_Lined_Alt.getUnlocalizedName().substring(5));
//		GameRegistry.registerBlock(Asphalt_Slab_E, Asphalt_Slab_E.getUnlocalizedName().substring(5));
//		GameRegistry.registerBlock(Asphalt_Slab_N, Asphalt_Slab_N.getUnlocalizedName().substring(5));
//		GameRegistry.registerBlock(Asphalt_Simple_Slab, Asphalt_Simple_Slab.getUnlocalizedName().substring(5));
//		GameRegistry.registerBlock(checkered_block, checkered_block.getUnlocalizedName().substring(5));
//		GameRegistry.registerBlock(Balloon, Balloon.getUnlocalizedName().substring(5));
	}
	
	public static void registerRenders()
	{
//		registerRenders(wheel);
//		registerRenders(Balloon);
//		registerRenders(checkered_block);
//		registerRenders(Guard_Rail_Alt);
//		registerRenders(Guard_Rail);
//		registerRenders(Asphalt_Simple);
//		registerRenders(Asphalt_Lined);
//		registerRenders(Asphalt_Lined_Alt);
//		registerRenders(Asphalt_Slab_E);
//		registerRenders(Asphalt_Slab_N);
//		registerRenders(Asphalt_Simple_Slab);
	}
	
	public static void registerRenders(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory")); 						
	}
}
