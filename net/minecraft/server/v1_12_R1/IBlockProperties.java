package net.minecraft.server.v1_12_R1;

import java.util.List;
import javax.annotation.Nullable;

public interface IBlockProperties {
  Material getMaterial();
  
  boolean b();
  
  boolean a(Entity paramEntity);
  
  int c();
  
  int d();
  
  boolean f();
  
  MaterialMapColor a(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition);
  
  IBlockData a(EnumBlockRotation paramEnumBlockRotation);
  
  IBlockData a(EnumBlockMirror paramEnumBlockMirror);
  
  boolean g();
  
  EnumRenderType i();
  
  boolean k();
  
  boolean l();
  
  boolean m();
  
  int a(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection);
  
  boolean n();
  
  int a(World paramWorld, BlockPosition paramBlockPosition);
  
  float b(World paramWorld, BlockPosition paramBlockPosition);
  
  float a(EntityHuman paramEntityHuman, World paramWorld, BlockPosition paramBlockPosition);
  
  int b(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection);
  
  EnumPistonReaction o();
  
  IBlockData c(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition);
  
  boolean p();
  
  @Nullable
  AxisAlignedBB d(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition);
  
  void a(World paramWorld, BlockPosition paramBlockPosition, AxisAlignedBB paramAxisAlignedBB, List<AxisAlignedBB> paramList, @Nullable Entity paramEntity, boolean paramBoolean);
  
  AxisAlignedBB e(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition);
  
  MovingObjectPosition a(World paramWorld, BlockPosition paramBlockPosition, Vec3D paramVec3D1, Vec3D paramVec3D2);
  
  boolean q();
  
  Vec3D f(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition);
  
  boolean r();
  
  EnumBlockFaceShape d(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection);
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IBlockProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */