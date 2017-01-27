package net.soggymustache.mythicbeasts;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.soggymustache.mythicbeasts.init.items.MythicItems;
import net.soggymustache.mythicbeasts.proxy.CommonProxy;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class MythicBeastsMain {

	public static final CreativeTabs tabMain = new CreativeTabs("tabMain"){
		public net.minecraft.item.Item getTabIconItem() {
			return MythicItems.book_of_beasts;
		};
	};
	
	@Instance(Reference.MOD_ID)
	public static MythicBeastsMain modInstance;
	@SidedProxy(clientSide = "net.soggymustache.mythicbeasts.proxy.ClientProxy", serverSide = "net.soggymustache.mythicbeasts.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public MythicBeastsMain() {
		modInstance = this;
	}
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit(event);
	}
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerRenders();
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}
}
