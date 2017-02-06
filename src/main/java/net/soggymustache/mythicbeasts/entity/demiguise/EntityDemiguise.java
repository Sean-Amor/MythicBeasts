package net.soggymustache.mythicbeasts.entity.demiguise;

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
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.soggymustache.mythicbeasts.init.achieve.MythicAchievements;
import net.soggymustache.mythicbeasts.init.items.MythicItems;

public class EntityDemiguise extends EntityAnimal{
	
	public boolean sit = true, stand = false;
	public int sitNum, standNum;
	
	public EntityDemiguise(World worldIn) {
		super(worldIn);
        this.setSize(0.7F, 0.7F);		
        this.tasks.addTask(1, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
		this.tasks.addTask(4, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIPanic(this, 0.38F));
		this.tasks.addTask(6, new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 0.3F, 0.35F));
		this.targetTasks.addTask(7, new EntityAIHurtByTarget(this, false, new Class[0]));
		
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand, ItemStack stack) {
        ItemStack itemstack = player.inventory.getCurrentItem();
		
		if(itemstack.getItem() == MythicItems.book_of_beasts){
			player.addStat(MythicAchievements.achievementUnseen);
		}
		return super.processInteract(player, hand, stack);
	}
	
	private int q = 0;
	
	@Override
	public void onLivingUpdate() {
		EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, 8.0D);

		if(this.worldObj.getClosestPlayerToEntity(this, 8.0D) != null){
			while(q < 2){
				q++;
				for (int i = 0; i < 7; ++i)
				{
					double d0 = this.rand.nextGaussian() * 0.02D;
					double d1 = this.rand.nextGaussian() * 0.02D;
					double d2 = this.rand.nextGaussian() * 0.02D;
					this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX + (double)(this.rand.nextFloat() * this.width * 1.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 1.0F) - (double)this.width, d0, d1, d2, new int[0]);
					this.worldObj.spawnParticle(EnumParticleTypes.SPELL, this.posX + (double)(this.rand.nextFloat() * this.width * 1.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 1.0F) - (double)this.width, d0, d1, d2, new int[0]);
				}
				this.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 0.15F, 1.0F);
			}
			
			this.setInvisible(true);
		}
		else{
			this.setInvisible(false);
			q = 0;
		}	
		
		Random rand = new Random();
        int i = 0;
        if(sitNum != 40 || standNum != 40){
        standNum = rand.nextInt(60) + 1;
        sitNum = rand.nextInt(60) + 1;
        }
        else
        {
            standNum = rand.nextInt(60) + 1;
            sitNum = rand.nextInt(60) + 1;
        }
        if(sitNum == 40)
        {
        	sit = true;
        	stand = false;
        }
        else if(sit && standNum == 40)
        {
        	stand = true;
        	sit = false;
        }
		
		super.onLivingUpdate();
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
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25000000298023224D);
    }
	
	@Override
	protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
		this.dropItem(MythicItems.demiguise_pelt, 1);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {

		return null;
	}
}
