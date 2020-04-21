package net.minecraft.server.v1_12_R1;

import javax.annotation.Nullable;

public interface IWorldAccess {
  void a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData1, IBlockData paramIBlockData2, int paramInt);
  
  void a(BlockPosition paramBlockPosition);
  
  void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
  
  void a(@Nullable EntityHuman paramEntityHuman, SoundEffect paramSoundEffect, SoundCategory paramSoundCategory, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2);
  
  void a(SoundEffect paramSoundEffect, BlockPosition paramBlockPosition);
  
  void a(int paramInt, boolean paramBoolean, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, int... paramVarArgs);
  
  void a(int paramInt, boolean paramBoolean1, boolean paramBoolean2, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, int... paramVarArgs);
  
  void a(Entity paramEntity);
  
  void b(Entity paramEntity);
  
  void a(int paramInt1, BlockPosition paramBlockPosition, int paramInt2);
  
  void a(EntityHuman paramEntityHuman, int paramInt1, BlockPosition paramBlockPosition, int paramInt2);
  
  void b(int paramInt1, BlockPosition paramBlockPosition, int paramInt2);
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IWorldAccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */