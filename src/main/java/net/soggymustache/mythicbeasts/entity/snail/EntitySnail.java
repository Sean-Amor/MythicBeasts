package net.soggymustache.mythicbeasts.entity.snail;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;

import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;

import net.minecraft.item.ItemStack;

import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import net.minecraft.init.Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;

import net.minecraft.block.Block;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.ResourceLocation;

import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import net.minecraftforge.fml.common.registry.GameRegistry;

import net.soggymustache.mythicbeasts.entity.projectiles.EntitySnailPoisonJet;
import net.soggymustache.mythicbeasts.entity.IEntityBL;
import net.soggymustache.mythicbeasts.init.achieve.MythicAchievements;
import net.soggymustache.mythicbeasts.init.items.MythicItems;

import soundapi.api.sound.MBSounds;

public class EntitySnail extends EntityMob implements IEntityBL {
private static final DataParameter<Integer> RANGE_ATTACK_TIMER = EntityDataManager.createKey(EntitySnail.class, DataSerializers.VARINT);

private static final SoundEvent SND_LIVING = new SoundEvent(new ResourceLocation("mythic:entity.snail.living"));
private static final SoundEvent SND_HURT = new SoundEvent(new ResourceLocation("mythic:entity.snail.hurt"));
private static final SoundEvent SND_DEATH = new SoundEvent(new ResourceLocation("mythic:entity.snail.death"));


    public EntitySnail(World world) {
        super(world);
        setSize(0.7F, 0.5F);
        stepHeight = 0.0F;
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIAttackMelee(this, 0.5D, false));
        tasks.addTask(2, new EntityAIWander(this, 0.5D));
        tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(4, new EntityAILookIdle(this));
        targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, false, true));
    }
    
    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if(!this.worldObj.isRemote && this.ticksExisted % 5 == 0) {
            Block block = this.worldObj.getBlockState(this.getPosition().add(0, -1, 0)).getBlock();
            if(block == Blocks.GRASS)
                this.worldObj.setBlockState(this.getPosition().add(0, -1, 0), Blocks.DIRT.getDefaultState());}
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
    protected void entityInit() {
        super.entityInit();
        dataManager.register(RANGE_ATTACK_TIMER, 0);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
        getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 3;
    }

    @Override
    protected void dropFewItems(boolean recentlyHit, int looting) {
        if (isBurning())
            entityDropItem(new ItemStack(MythicItems.snail_flesh_cooked, 1, 0), 0.0F);
        else
            entityDropItem(new ItemStack(MythicItems.snail_flesh_raw, 1, 0), 0.0F);

        if (rand.nextBoolean())
            entityDropItem(new ItemStack(MythicItems.snail_shell, 1, 0), 0.0F);
        else
            entityDropItem(new ItemStack(MythicItems.poison_gland, 1, 0), 0.0F);
    }

    @Override
    public boolean isAIDisabled() {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SND_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound() {
        return SND_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SND_DEATH;
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (super.attackEntityAsMob(entity)) {
            if (entity instanceof EntityLiving) {
                byte duration = 0;
                if (worldObj.getDifficulty() == EnumDifficulty.NORMAL)
                    duration = 7;
                else if (worldObj.getDifficulty() == EnumDifficulty.HARD)
                    duration = 15;

                if (duration > 0) {
                    ((EntityLiving) entity).addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("poison"), duration * 20, 0));
                    ((EntityLiving) entity).addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("nausea"), duration * 20, 0));
                }
            }
            return true;
        } else
            return false;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (getAttackTarget() != null && this.isEntityAlive()) {
            float distance = (float) getDistance(getAttackTarget().posX, getAttackTarget().getEntityBoundingBox().minY, getAttackTarget().posZ);
            if (getRangeAttackTimer() < 100 && distance > 3)
                setRangeAttackTimer(getRangeAttackTimer() + 2);
            if (getRangeAttackTimer() == 100 && distance > 3)
                shootMissile(getAttackTarget(), distance);
        }
    }

    public void shootMissile(EntityLivingBase entity, float distance) {
        setRangeAttackTimer(0);
        if (canEntityBeSeen(entity)) {
            EntityThrowable missile = new EntitySnailPoisonJet(worldObj, this);
            missile.rotationPitch -= -20.0F;
            double targetX = entity.posX + entity.motionX - posX;
            double targetY = entity.posY + entity.getEyeHeight() - 1.100000023841858D - posY;
            double targetZ = entity.posZ + entity.motionZ - posZ;
            float target = MathHelper.sqrt_double(targetX * targetX + targetZ * targetZ);
            missile.setThrowableHeading(targetX, targetY + target * 0.1F, targetZ, 0.75F, 8.0F);
            worldObj.spawnEntityInWorld(missile);
        }
    }

    public int getRangeAttackTimer() {
        return dataManager.get(RANGE_ATTACK_TIMER);
    }

    public void setRangeAttackTimer(int size) {
        dataManager.set(RANGE_ATTACK_TIMER, size);
    }
}