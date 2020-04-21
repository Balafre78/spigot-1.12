package net.minecraft.server.v1_12_R1;

public interface ISourceBlock extends ILocationSource {
  double getX();
  
  double getY();
  
  double getZ();
  
  BlockPosition getBlockPosition();
  
  IBlockData e();
  
  <T extends TileEntity> T getTileEntity();
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ISourceBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */