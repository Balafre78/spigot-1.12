package net.minecraft.server.v1_12_R1;

public interface IWorldInventory extends IInventory {
  int[] getSlotsForFace(EnumDirection paramEnumDirection);
  
  boolean canPlaceItemThroughFace(int paramInt, ItemStack paramItemStack, EnumDirection paramEnumDirection);
  
  boolean canTakeItemThroughFace(int paramInt, ItemStack paramItemStack, EnumDirection paramEnumDirection);
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IWorldInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */