package net.soggymustache.mythicbeasts.entity;

import net.soggymustache.mythicbeasts.entity.navigate.FlyingPathNavigate;
import net.minecraft.entity.EntityCreature;

public interface IFlyingMob extends IMythicMob {

  float getMaxTurnRate();
  
  float getMaxClimbRate();
  
  FlyingPathNavigate getFlyingNavigator();
  
  EntityCreature asEntityCreature();
}
