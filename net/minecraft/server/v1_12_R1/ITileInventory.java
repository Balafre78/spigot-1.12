package net.minecraft.server.v1_12_R1;

public interface ITileInventory extends IInventory, ITileEntityContainer {
  boolean isLocked();
  
  void setLock(ChestLock paramChestLock);
  
  ChestLock getLock();
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ITileInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */