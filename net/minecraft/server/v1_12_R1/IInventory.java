package net.minecraft.server.v1_12_R1;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryHolder;

public interface IInventory extends INamableTileEntity {
  public static final int MAX_STACK = 64;
  
  int getSize();
  
  boolean x_();
  
  ItemStack getItem(int paramInt);
  
  ItemStack splitStack(int paramInt1, int paramInt2);
  
  ItemStack splitWithoutUpdate(int paramInt);
  
  void setItem(int paramInt, ItemStack paramItemStack);
  
  int getMaxStackSize();
  
  void update();
  
  boolean a(EntityHuman paramEntityHuman);
  
  void startOpen(EntityHuman paramEntityHuman);
  
  void closeContainer(EntityHuman paramEntityHuman);
  
  boolean b(int paramInt, ItemStack paramItemStack);
  
  int getProperty(int paramInt);
  
  void setProperty(int paramInt1, int paramInt2);
  
  int h();
  
  void clear();
  
  List<ItemStack> getContents();
  
  void onOpen(CraftHumanEntity paramCraftHumanEntity);
  
  void onClose(CraftHumanEntity paramCraftHumanEntity);
  
  List<HumanEntity> getViewers();
  
  InventoryHolder getOwner();
  
  void setMaxStackSize(int paramInt);
  
  Location getLocation();
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */