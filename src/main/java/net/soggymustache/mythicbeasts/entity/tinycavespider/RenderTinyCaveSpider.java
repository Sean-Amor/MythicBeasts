package net.soggymustache.mythicbeasts.entity.tinycavespider;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.client.renderer.entity.RenderCaveSpider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.soggymustache.mythicbeasts.Reference;
import net.soggymustache.mythicbeasts.entity.tinycavespider.EntityTinyCaveSpider;
import net.soggymustache.mythicbeasts.entity.tinycavespider.RenderTinyCaveSpider;
import net.soggymustache.mythicbeasts.model.ModelTinyCaveSpider;

@SideOnly(Side.CLIENT)
public class RenderTinyCaveSpider extends RenderSpider<EntityTinyCaveSpider>
{
    private static final ResourceLocation CAVE_SPIDER_TEXTURES = new ResourceLocation("textures/entity/spider/cave_spider.png");

    public RenderTinyCaveSpider(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn)
    {
        super(renderManagerIn);
        this.shadowSize *= 0.3F;
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityTinyCaveSpider entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(0.3F, 0.3F, 0.3F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityTinyCaveSpider entity)
    {
        return CAVE_SPIDER_TEXTURES;
    }
}