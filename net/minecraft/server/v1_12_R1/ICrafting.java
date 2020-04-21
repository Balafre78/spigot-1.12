package net.minecraft.server.v1_12_R1;

public interface ICrafting {
  void a(Container paramContainer, NonNullList<ItemStack> paramNonNullList);
  
  void a(Container paramContainer, int paramInt, ItemStack paramItemStack);
  
  void setContainerData(Container paramContainer, int paramInt1, int paramInt2);
  
  void setContainerData(Container paramContainer, IInventory paramIInventory);
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ICrafting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */