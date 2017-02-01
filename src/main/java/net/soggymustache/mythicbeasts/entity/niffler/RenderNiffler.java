package net.soggymustache.mythicbeasts.entity.niffler;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.soggymustache.mythicbeasts.Reference;

@SideOnly(Side.CLIENT)
public class RenderNiffler extends RenderLiving<EntityNiffler>
{
    private static final ResourceLocation NIFFLER = new ResourceLocation(Reference.MOD_ID + ":textures/entity/niffler.png");

    public RenderNiffler(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn)
    {
        super(renderManagerIn, modelBaseIn, shadowSizeIn);
    }
    
    protected ResourceLocation getEntityTexture(EntityNiffler entity)
    {
        return NIFFLER;
    }
}