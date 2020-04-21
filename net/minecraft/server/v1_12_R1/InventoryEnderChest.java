/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.inventory.InventoryHolder;
/*    */ 
/*    */ public class InventoryEnderChest
/*    */   extends InventorySubcontainer {
/*    */   private TileEntityEnderChest a;
/*    */   private final EntityHuman owner;
/*    */   
/*    */   public InventoryHolder getBukkitOwner() {
/* 13 */     return (InventoryHolder)this.owner.getBukkitEntity();
/*    */   }
/*    */ 
/*    */   
/*    */   public Location getLocation() {
/* 18 */     return new Location((World)this.a.getWorld().getWorld(), this.a.getPosition().getX(), this.a.getPosition().getY(), this.a.getPosition().getZ());
/*    */   }
/*    */   
/*    */   public InventoryEnderChest(EntityHuman owner) {
/* 22 */     super("container.enderchest", false, 27);
/* 23 */     this.owner = owner;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(TileEntityEnderChest tileentityenderchest) {
/* 28 */     this.a = tileentityenderchest;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(NBTTagList nbttaglist) {
/*    */     int i;
/* 34 */     for (i = 0; i < getSize(); i++) {
/* 35 */       setItem(i, ItemStack.a);
/*    */     }
/*    */     
/* 38 */     for (i = 0; i < nbttaglist.size(); i++) {
/* 39 */       NBTTagCompound nbttagcompound = nbttaglist.get(i);
/* 40 */       int j = nbttagcompound.getByte("Slot") & 0xFF;
/*    */       
/* 42 */       if (j >= 0 && j < getSize()) {
/* 43 */         setItem(j, new ItemStack(nbttagcompound));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagList i() {
/* 50 */     NBTTagList nbttaglist = new NBTTagList();
/*    */     
/* 52 */     for (int i = 0; i < getSize(); i++) {
/* 53 */       ItemStack itemstack = getItem(i);
/*    */       
/* 55 */       if (!itemstack.isEmpty()) {
/* 56 */         NBTTagCompound nbttagcompound = new NBTTagCompound();
/*    */         
/* 58 */         nbttagcompound.setByte("Slot", (byte)i);
/* 59 */         itemstack.save(nbttagcompound);
/* 60 */         nbttaglist.add(nbttagcompound);
/*    */       } 
/*    */     } 
/*    */     
/* 64 */     return nbttaglist;
/*    */   }
/*    */   
/*    */   public boolean a(EntityHuman entityhuman) {
/* 68 */     return (this.a != null && !this.a.a(entityhuman)) ? false : super.a(entityhuman);
/*    */   }
/*    */   
/*    */   public void startOpen(EntityHuman entityhuman) {
/* 72 */     if (this.a != null) {
/* 73 */       this.a.a();
/*    */     }
/*    */     
/* 76 */     super.startOpen(entityhuman);
/*    */   }
/*    */   
/*    */   public void closeContainer(EntityHuman entityhuman) {
/* 80 */     if (this.a != null) {
/* 81 */       this.a.f();
/*    */     }
/*    */     
/* 84 */     super.closeContainer(entityhuman);
/* 85 */     this.a = null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\InventoryEnderChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */