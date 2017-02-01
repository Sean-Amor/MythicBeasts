package net.soggymustache.mythicbeasts.entity.demiguise;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.soggymustache.mythicbeasts.Reference;

@SideOnly(Side.CLIENT)
public class RenderDemiguise extends RenderLiving<EntityDemiguise>
{
    private static final ResourceLocation DEMIGUISE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/demiguise.png");

    public RenderDemiguise(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn)
    {
        super(renderManagerIn, modelBaseIn, shadowSizeIn);
    }
    
    protected ResourceLocation getEntityTexture(EntityDemiguise entity)
    {
        return DEMIGUISE;
    }
}