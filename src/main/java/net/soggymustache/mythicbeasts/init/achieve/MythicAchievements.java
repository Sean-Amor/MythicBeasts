package net.soggymustache.mythicbeasts.init.achieve;

import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class MythicAchievements {
	
	public static Achievement achievementUnseen, achievementShiny, achievementBuzz, achievementTrailblazing, achievementHoot;
	
	
	public static void init(FMLInitializationEvent event)
	{
		achievementUnseen = new Achievement("achievement.Unseen", "Unseen", 0, 1, Items.ENDER_PEARL, (Achievement)null).registerStat();
		achievementShiny = new Achievement("achievement.Shiny", "Shiny", 0, 2, Items.ENDER_PEARL, (Achievement)null).registerStat();
		achievementBuzz = new Achievement("achievement.Buzz", "Buzz", 0, 3, Items.ENDER_PEARL, (Achievement)null).registerStat();
		achievementTrailblazing = new Achievement("achievement.Trailblazing", "Trailblazing", 0, 4, Items.ENDER_PEARL, (Achievement)null).registerStat();
		achievementHoot = new Achievement("achievement.Hoot", "Trailblazing", 0, 5, Items.ENDER_PEARL, (Achievement)null).registerStat();


		AchievementPage.registerAchievementPage(new AchievementPage("MythicBeasts", new Achievement[]{achievementUnseen, achievementShiny, achievementBuzz, achievementTrailblazing, achievementHoot}));
	}
}
