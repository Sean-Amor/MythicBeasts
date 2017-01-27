package net.soggymustache.mythicbeasts.proxy;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.soggymustache.mythicbeasts.entity.Entity;
import net.soggymustache.mythicbeasts.entity.demiguise.EntityDemiguise;
import net.soggymustache.mythicbeasts.entity.niffler.EntityNiffler;
import net.soggymustache.mythicbeasts.events.PlayerLogonEvent;
import net.soggymustache.mythicbeasts.init.achieve.MythicAchievements;
import net.soggymustache.mythicbeasts.init.achieve.event.CraftingEvent;
import net.soggymustache.mythicbeasts.init.blocks.MythicBlocks;
import net.soggymustache.mythicbeasts.init.items.MythicItems;
import net.soggymustache.mythicbeasts.init.items.crafting.MythicCrafting;

public class CommonProxy {
	
	PlayerLogonEvent handler = new PlayerLogonEvent();
	
	public void preInit(FMLPreInitializationEvent event)
	{
		MythicItems.init();
		MythicBlocks.init();
		MythicBlocks.register();
		Entity.MythicBeasts();
		FMLCommonHandler.instance().bus().register(new CraftingEvent());
		FMLCommonHandler.instance().bus().register(new PlayerLogonEvent());
		
		EntityRegistry.addSpawn(EntityNiffler.class, 10, 1, 2, EnumCreatureType.CREATURE, Biomes.FOREST, Biomes.FOREST_HILLS);
		EntityRegistry.addSpawn(EntityDemiguise.class, 10, 1, 2, EnumCreatureType.CREATURE, Biomes.JUNGLE);
	}
	 
	public void init(FMLInitializationEvent event)	
	{
		MythicCrafting.init(event);
		MythicAchievements.init(event);
	}
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	public void registerRenders() {

	}

}
