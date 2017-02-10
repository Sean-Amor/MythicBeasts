package net.soggymustache.mythicbeasts.entity.snail;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.Block;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.soggymustache.mythicbeasts.init.achieve.MythicAchievements;
import net.soggymustache.mythicbeasts.init.items.MythicItems;

public class EntitySnail extends EntityAnimal{
    private EntityAIAvoidEntity<EntityPlayer> avoidEntity;
    
    public EntitySnail(World worldIn) {
        super(worldIn);
        this.setSize(0.5F, 0.5F);
        this.tasks.addTask(1, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.tasks.addTask(4, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIPanic(this, 0.38F));
        this.targetTasks.addTask(6, new EntityAIHurtByTarget(this, false, new Class[0]));
        
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand, ItemStack stack) {
        ItemStack itemstack = player.inventory.getCurrentItem();
        
        if(itemstack.getItem() == MythicItems.book_of_beasts){
            player.addStat(MythicAchievements.achievementTrailblazing);
        }
        return super.processInteract(player, hand, stack);
    }
    
    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if(!this.worldObj.isRemote && this.ticksExisted % 5 == 0) {
            Block block = this.worldObj.getBlockState(this.getPosition().add(0, -1, 0)).getBlock();
            if(block == Blocks.GRASS)
                this.worldObj.setBlockState(this.getPosition().add(0, -1, 0), Blocks.DIRT.getDefaultState());}
    }

    public boolean attackEntityAsMob(Entity entity)
    {
        this.worldObj.setEntityState(this, (byte)4);

        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 0.5F);
    }
    
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.05D);
    }
    
    @Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
        this.dropItem(Items.SLIME_BALL, 1);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {

        return null;
    }
}
