package net.soggymustache.mythicbeasts.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelSpider;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderManager;
import net.soggymustache.mythicbeasts.entity.demiguise.EntityDemiguise;
import net.soggymustache.mythicbeasts.entity.demiguise.RenderDemiguise;
import net.soggymustache.mythicbeasts.entity.niffler.EntityNiffler;
import net.soggymustache.mythicbeasts.entity.niffler.RenderNiffler;
import net.soggymustache.mythicbeasts.entity.wasp.EntityWasp;
import net.soggymustache.mythicbeasts.entity.wasp.RenderWasp;
import net.soggymustache.mythicbeasts.entity.snail.EntitySnail;
import net.soggymustache.mythicbeasts.entity.snail.RenderSnail;
import net.soggymustache.mythicbeasts.entity.projectiles.EntitySnailPoisonJet;
import net.soggymustache.mythicbeasts.entity.projectiles.RenderSnailPoisonJet;
import net.soggymustache.mythicbeasts.entity.tinycavespider.EntityTinyCaveSpider;
import net.soggymustache.mythicbeasts.entity.tinycavespider.RenderTinyCaveSpider;
import net.soggymustache.mythicbeasts.init.blocks.MythicBlocks;
import net.soggymustache.mythicbeasts.init.items.MythicItems;
import net.soggymustache.mythicbeasts.model.ModelDemiguise;
import net.soggymustache.mythicbeasts.model.ModelNiffler;
import net.soggymustache.mythicbeasts.model.ModelWasp;
import net.soggymustache.mythicbeasts.model.ModelSnail;
import net.soggymustache.mythicbeasts.model.ModelTinyCaveSpider;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.EnumParticleTypes;

public class ClientProxy extends CommonProxy{
	@Override
	public void registerRenders() {
	
		RenderingRegistry.registerEntityRenderingHandler(EntityDemiguise.class, new RenderDemiguise(Minecraft.getMinecraft().getRenderManager(), new ModelDemiguise(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityNiffler.class, new RenderNiffler(Minecraft.getMinecraft().getRenderManager(), new ModelNiffler(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityWasp.class, new RenderWasp(Minecraft.getMinecraft().getRenderManager(), new ModelWasp(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntitySnail.class, new RenderSnail(Minecraft.getMinecraft().getRenderManager(), new ModelSnail(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntitySnailPoisonJet.class, new RenderSnailPoisonJet(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTinyCaveSpider.class, new RenderTinyCaveSpider(Minecraft.getMinecraft().getRenderManager(), new ModelTinyCaveSpider(), 0));

		
		MythicBlocks.registerRenders();
		MythicItems.registerRenderers();
	}
}
 