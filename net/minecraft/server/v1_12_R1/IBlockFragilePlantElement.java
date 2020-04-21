package net.minecraft.server.v1_12_R1;

import java.util.Random;

public interface IBlockFragilePlantElement {
  boolean a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, boolean paramBoolean);
  
  boolean a(World paramWorld, Random paramRandom, BlockPosition paramBlockPosition, IBlockData paramIBlockData);
  
  void b(World paramWorld, Random paramRandom, BlockPosition paramBlockPosition, IBlockData paramIBlockData);
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IBlockFragilePlantElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */