package net.soggymustache.mythicbeasts.init.achieve;

import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class MythicAchievements {
	
	public static Achievement achievementUnseen, achievementShiny;
	
	
	public static void init(FMLInitializationEvent event)
	{
		achievementUnseen = new Achievement("achievement.Unseen", "Unseen", 0, 1, Items.ENDER_PEARL, (Achievement)null).registerStat();
		achievementShiny = new Achievement("achievement.Shiny", "Shiny", 1, -2, Items.GOLD_INGOT, (Achievement)null).registerStat();


		AchievementPage.registerAchievementPage(new AchievementPage("MythicBeasts", new Achievement[]{achievementUnseen, achievementShiny}));
	}
}
