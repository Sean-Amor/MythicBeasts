package net.soggymustache.mythicbeasts.entity.wasp;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.soggymustache.mythicbeasts.Reference;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderWasp extends RenderLiving<EntityWasp>
{
    private static final ResourceLocation WASP = new ResourceLocation(Reference.MOD_ID + ":textures/entity/wasp.png");

    public RenderWasp(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn)
    {
        super(renderManagerIn, modelBaseIn, shadowSizeIn);
    }
    
    protected ResourceLocation getEntityTexture(EntityWasp entity)
    {
        return WASP;
    }
}