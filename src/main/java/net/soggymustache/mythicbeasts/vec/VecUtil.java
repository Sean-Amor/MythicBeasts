package net.soggymustache.mythicbeasts.vec;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class VecUtil {

	public static BlockPos bpos(double x, double y, double z) {
		return bpos((int)x, (int)y, (int)z);
	}
	
  public static BlockPos bpos(int x, int y, int z) {
	 return new BlockPos(x, y, z); 
  }
	
  public static Vec3d scale(Vec3d vec, double scale) {
	  return new Vec3d(vec.xCoord * scale, vec.yCoord * scale, vec.zCoord * scale);
  }

  public static Vec3d copy(Vec3d vec) {
    return new Vec3d(vec.xCoord, vec.yCoord, vec.zCoord);
  }

  public static Vec3d subtract(Vec3d a, Vec3d b) {    
    return new Vec3d(a.xCoord - b.xCoord, a.yCoord - b.yCoord, a.zCoord - b.zCoord);
  }

  public static Vec3d add(Vec3d a, Vec3d b) {    
    return new Vec3d(a.xCoord + b.xCoord, a.yCoord+ b.yCoord, a.zCoord+ b.zCoord);
  }

}
