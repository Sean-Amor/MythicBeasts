package net.soggymustache.mythicbeasts.entity.niffler;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.soggymustache.mythicbeasts.init.achieve.MythicAchievements;
import net.soggymustache.mythicbeasts.init.items.MythicItems;

public class EntityNiffler extends EntityAnimal{
	
	public boolean sit = true, stand = false;
	public int sitNum, standNum;
	
	public EntityNiffler(World worldIn) {
		super(worldIn);
        this.setSize(0.7F, 0.4F);	
        this.tasks.addTask(1, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 0.25D, true));
        this.targetTasks.addTask(5, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.tasks.addTask(6, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
        this.tasks.addTask(7, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(8, new EntityAISwimming(this));
        this.tasks.addTask(9, new EntityAIPanic(this, 0.38F));
        this.setCanPickUpLoot(true);
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand, ItemStack stack) {
        ItemStack itemstack = player.inventory.getCurrentItem();
		
		if(itemstack.getItem() == MythicItems.book_of_beasts){
			player.addStat(MythicAchievements.achievementShiny);
		}
		return super.processInteract(player, hand, stack);
	}
	
	@Override
	public void onLivingUpdate() {
		EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, 1.0D);
		
		if(this.worldObj.getClosestPlayerToEntity(this, 1.0D) != null){
			player.inventory.dropAllItems();
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

    public boolean attackEntityAsMob(Entity p_70652_1_)
    {
        this.worldObj.setEntityState(this, (byte)4);

        return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 0.5F);
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
		Random rand = new Random();
        int i = rand.nextInt(3) + 1;
        
        if(i == 1){
        	this.dropItem(Items.GOLD_INGOT, 1);
        }
        else if(i == 2){
        	this.dropItem(Items.DIAMOND, 1);
        	}
        else{
    		this.dropItem(Items.GOLD_NUGGET, 1);
        }
	}
	
	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}
}
