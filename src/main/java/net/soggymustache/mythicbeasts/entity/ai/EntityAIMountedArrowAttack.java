package net.soggymustache.mythicbeasts.entity.ai;

import net.soggymustache.mythicbeasts.entity.EntityUtil;
import net.soggymustache.mythicbeasts.entity.SpawnUtil;
import net.soggymustache.mythicbeasts.vec.Point3i;
import net.soggymustache.mythicbeasts.vec.VecUtil;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityAIMountedArrowAttack extends EntityAIBase {

  private final EntityLiving entityHost;

  private final IRangedAttackMob rangedAttackEntityHost;
  private EntityLivingBase attackTarget;
  private double entityMoveSpeed;
  private double mountedEntityMoveSpeed;

  private int timeUntilNextAttack;
  private int timeTargetVisible;

  private int minRangedAttackTime;
  private int maxRangedAttackTime;

  private float attackRange;
  private float attackRangeSq;

  private int runAwayTimer = 0;

  private PathPoint runningAwayTo;

  private boolean useRunAwayTactic;
  
  public EntityAIMountedArrowAttack(IRangedAttackMob host, double moveSpeed, double mountedEntityMoveSpeed, int minAttackTime, int maxAttackTime,
      float attackRange, boolean useRunAwayTactic) {
    timeUntilNextAttack = -1;
    rangedAttackEntityHost = host;
    entityHost = (EntityLiving) host;
    entityMoveSpeed = moveSpeed;
    this.mountedEntityMoveSpeed = mountedEntityMoveSpeed;
    minRangedAttackTime = minAttackTime;
    maxRangedAttackTime = maxAttackTime;
    this.attackRange = attackRange;
    attackRangeSq = attackRange * attackRange;
    this.useRunAwayTactic = useRunAwayTactic;
    setMutexBits(3);
  }

  @Override
  public boolean shouldExecute() {
    EntityLivingBase toAttack = entityHost.getAttackTarget();
    if(toAttack == null) {
      return false;
    } else {
      attackTarget = toAttack;
      return true;
    }
  }

  @Override
  public boolean continueExecuting() {
    return shouldExecute() || !getNavigator().noPath();
  }

  @Override
  public void resetTask() {
    attackTarget = null;
    timeTargetVisible = 0;
    timeUntilNextAttack = -1;
    runAwayTimer = 0;
    runningAwayTo = null;
  }

  /**
   * Updates the task
   */
  @Override
  public void updateTask() {
    double distToTargetSq = entityHost.getDistanceSq(attackTarget.posX, attackTarget.getEntityBoundingBox().minY, attackTarget.posZ);
    boolean canSeeTarget = entityHost.getEntitySenses().canSee(attackTarget);

    if(canSeeTarget) {
      ++timeTargetVisible;
    } else {
      timeTargetVisible = 0;
    }

    boolean runningAway = isRunningAway(); 
    if(!runningAway) {
      runAwayTimer--;
    }
    
    if(!runningAway && distToTargetSq <= attackRangeSq && timeTargetVisible >= 20) {          
      getNavigator().clearPathEntity();
    } else if(distToTargetSq > (attackRangeSq * 0.9)) {
      getNavigator().tryMoveToEntityLiving(attackTarget, getMoveSpeed());
    }

    if(canSeeTarget && entityHost.isRiding() && distToTargetSq < 36 && runAwayTimer <= 0 && runAway()) {
      --timeUntilNextAttack;
      return;
    }
    
    if(runningAway) {
      --timeUntilNextAttack;
      return;
    }
    
    entityHost.getLookHelper().setLookPositionWithEntity(attackTarget, 30.0F, 30.0F);

    if(--timeUntilNextAttack == 0) {
      if(distToTargetSq > attackRangeSq || !canSeeTarget) {
        return;
      }
      float rangeRatio = MathHelper.sqrt_double(distToTargetSq) / attackRange;
      rangeRatio = MathHelper.clamp_float(rangeRatio, 0.1f, 1);
      rangedAttackEntityHost.attackEntityWithRangedAttack(attackTarget, rangeRatio);
      timeUntilNextAttack = MathHelper.floor_float(rangeRatio * (maxRangedAttackTime - minRangedAttackTime) + minRangedAttackTime);
    } else if(timeUntilNextAttack < 0) {
      float rangeRatio = MathHelper.sqrt_double(distToTargetSq) / attackRange;
      timeUntilNextAttack = MathHelper.floor_float(rangeRatio * (maxRangedAttackTime - minRangedAttackTime) + minRangedAttackTime);
    }
  }

  private boolean isRunningAway() {

    if(runningAwayTo == null) {
      return false;
    }
    if(getNavigator().noPath()) {
      runningAwayTo = null;
      return false;
    }
    PathPoint dest = getNavigator().getPath().getFinalPathPoint();
    return dest.equals(runningAwayTo);
  }

  private boolean runAway() {
    if(!useRunAwayTactic) {
      return false;
    }
    
    runAwayTimer = 40;
    Vec3d targetDir = new Vec3d(attackTarget.posX, attackTarget.getEntityBoundingBox().minY, attackTarget.posZ);
    Vec3d entityPos = EntityUtil.getEntityPosition(entityHost);
    targetDir = VecUtil.subtract(targetDir, entityPos);        
    targetDir = VecUtil.scale(targetDir, -1);
    targetDir = targetDir.normalize();

    double distance = attackRange * 0.9;
    targetDir = VecUtil.scale(targetDir, distance);
    targetDir = VecUtil.add(targetDir, entityPos);
    

    Point3i probePoint = new Point3i((int) Math.round(targetDir.xCoord), (int) Math.round(entityHost.posY), (int) Math.round(targetDir.zCoord));
    Point3i target = new Point3i(probePoint);

    World world = entityHost.worldObj;

    if(!SpawnUtil.findClearGround(world, target, probePoint)) {
      return false;
    }

    boolean res = getNavigator().tryMoveToXYZ(probePoint.x, probePoint.y, probePoint.z, mountedEntityMoveSpeed);
    if(getNavigator().noPath()) {
      runningAwayTo = null;
    } else {
      runningAwayTo = getNavigator().getPath().getFinalPathPoint();
    }
    return res;
  }
  
  private double getMoveSpeed() {
    if(entityHost.isRiding()) {
      return mountedEntityMoveSpeed;
    }
    return entityMoveSpeed;
  }

  protected PathNavigate getNavigator() {
    return entityHost.getNavigator();
  }
}
