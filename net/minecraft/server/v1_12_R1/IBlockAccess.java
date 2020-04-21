package net.minecraft.server.v1_12_R1;

import javax.annotation.Nullable;

public interface IBlockAccess {
  @Nullable
  TileEntity getTileEntity(BlockPosition paramBlockPosition);
  
  IBlockData getType(BlockPosition paramBlockPosition);
  
  boolean isEmpty(BlockPosition paramBlockPosition);
  
  int getBlockPower(BlockPosition paramBlockPosition, EnumDirection paramEnumDirection);
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IBlockAccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */