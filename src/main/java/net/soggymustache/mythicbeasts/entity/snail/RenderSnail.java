package net.soggymustache.mythicbeasts.entity.snail;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.monster.EntityMob;
import net.soggymustache.mythicbeasts.entity.snail.EntitySnail;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.soggymustache.mythicbeasts.Reference;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderSnail extends RenderLiving<EntitySnail>
{
    private static final ResourceLocation SNAIL = new ResourceLocation(Reference.MOD_ID + ":textures/entity/snail.png");
    private static final ResourceLocation SNAIL_ATTACK = new ResourceLocation(Reference.MOD_ID + ":textures/entity/snail_attack.png");

    public RenderSnail(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn)
    {
        super(renderManagerIn, modelBaseIn, shadowSizeIn);
    }

    protected ResourceLocation getEntityTexture(EntitySnail entity)
    {
        return entity.isBurning() ? SNAIL : SNAIL_ATTACK;
    }
}