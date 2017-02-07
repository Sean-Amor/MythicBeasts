package net.soggymustache.mythicbeasts.entity;

import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.soggymustache.mythicbeasts.MythicBeastsMain;
import net.soggymustache.mythicbeasts.entity.demiguise.EntityDemiguise;
import net.soggymustache.mythicbeasts.entity.niffler.EntityNiffler;
import net.soggymustache.mythicbeasts.entity.wasp.EntityWasp;
import net.soggymustache.mythicbeasts.entity.snail.EntitySnail;

public class Entity {
	public static void MythicBeasts(){
		registerEntity();
	}
		 public static void registerEntity(){
			 	createVehicle(EntityDemiguise.class, "Demiguise", 0xB3B3B3, 0xF2F2F2);
			 	createVehicle(EntityWasp.class, "Wasp", 0xB3B3B3, 0xF2F2F2);
			 	createVehicle(EntityNiffler.class, "Niffler", 0x825C09, 0x181D1F);
			 	createVehicle(EntitySnail.class, "Snail", 0x825C09, 0x181D1F);
		 }
		 
		 public static int id = 0;

		    public static void createVehicle(Class entityClass, String entityName, int primary, int secondary)
		    {
		        EntityRegistry.registerModEntity(entityClass, entityName, id++, MythicBeastsMain.modInstance, 214, 1, true);
		        EntityRegistry.registerEgg(entityClass, primary, secondary);
		    }
}