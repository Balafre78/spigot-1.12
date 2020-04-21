/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftHumanEntity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventoryLargeChest
/*     */   implements ITileInventory
/*     */ {
/*     */   private final String a;
/*     */   public final ITileInventory left;
/*     */   public final ITileInventory right;
/*  19 */   public List<HumanEntity> transaction = new ArrayList<>();
/*     */   
/*     */   public List<ItemStack> getContents() {
/*  22 */     List<ItemStack> result = new ArrayList<>(getSize());
/*  23 */     for (int i = 0; i < getSize(); i++) {
/*  24 */       result.add(getItem(i));
/*     */     }
/*  26 */     return result;
/*     */   }
/*     */   
/*     */   public void onOpen(CraftHumanEntity who) {
/*  30 */     this.left.onOpen(who);
/*  31 */     this.right.onOpen(who);
/*  32 */     this.transaction.add(who);
/*     */   }
/*     */   
/*     */   public void onClose(CraftHumanEntity who) {
/*  36 */     this.left.onClose(who);
/*  37 */     this.right.onClose(who);
/*  38 */     this.transaction.remove(who);
/*     */   }
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/*  42 */     return this.transaction;
/*     */   }
/*     */   
/*     */   public InventoryHolder getOwner() {
/*  46 */     return null;
/*     */   }
/*     */   
/*     */   public void setMaxStackSize(int size) {
/*  50 */     this.left.setMaxStackSize(size);
/*  51 */     this.right.setMaxStackSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getLocation() {
/*  56 */     return this.left.getLocation();
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryLargeChest(String s, ITileInventory itileinventory, ITileInventory itileinventory1) {
/*  61 */     this.a = s;
/*  62 */     if (itileinventory == null) {
/*  63 */       itileinventory = itileinventory1;
/*     */     }
/*     */     
/*  66 */     if (itileinventory1 == null) {
/*  67 */       itileinventory1 = itileinventory;
/*     */     }
/*     */     
/*  70 */     this.left = itileinventory;
/*  71 */     this.right = itileinventory1;
/*  72 */     if (itileinventory.isLocked()) {
/*  73 */       itileinventory1.setLock(itileinventory.getLock());
/*  74 */     } else if (itileinventory1.isLocked()) {
/*  75 */       itileinventory.setLock(itileinventory1.getLock());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  81 */     return this.left.getSize() + this.right.getSize();
/*     */   }
/*     */   
/*     */   public boolean x_() {
/*  85 */     return (this.left.x_() && this.right.x_());
/*     */   }
/*     */   
/*     */   public boolean a(IInventory iinventory) {
/*  89 */     return !(this.left != iinventory && this.right != iinventory);
/*     */   }
/*     */   
/*     */   public String getName() {
/*  93 */     return this.left.hasCustomName() ? this.left.getName() : (this.right.hasCustomName() ? this.right.getName() : this.a);
/*     */   }
/*     */   
/*     */   public boolean hasCustomName() {
/*  97 */     return !(!this.left.hasCustomName() && !this.right.hasCustomName());
/*     */   }
/*     */   
/*     */   public IChatBaseComponent getScoreboardDisplayName() {
/* 101 */     return hasCustomName() ? new ChatComponentText(getName()) : new ChatMessage(getName(), new Object[0]);
/*     */   }
/*     */   
/*     */   public ItemStack getItem(int i) {
/* 105 */     return (i >= this.left.getSize()) ? this.right.getItem(i - this.left.getSize()) : this.left.getItem(i);
/*     */   }
/*     */   
/*     */   public ItemStack splitStack(int i, int j) {
/* 109 */     return (i >= this.left.getSize()) ? this.right.splitStack(i - this.left.getSize(), j) : this.left.splitStack(i, j);
/*     */   }
/*     */   
/*     */   public ItemStack splitWithoutUpdate(int i) {
/* 113 */     return (i >= this.left.getSize()) ? this.right.splitWithoutUpdate(i - this.left.getSize()) : this.left.splitWithoutUpdate(i);
/*     */   }
/*     */   
/*     */   public void setItem(int i, ItemStack itemstack) {
/* 117 */     if (i >= this.left.getSize()) {
/* 118 */       this.right.setItem(i - this.left.getSize(), itemstack);
/*     */     } else {
/* 120 */       this.left.setItem(i, itemstack);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxStackSize() {
/* 126 */     return Math.min(this.left.getMaxStackSize(), this.right.getMaxStackSize());
/*     */   }
/*     */   
/*     */   public void update() {
/* 130 */     this.left.update();
/* 131 */     this.right.update();
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 135 */     return (this.left.a(entityhuman) && this.right.a(entityhuman));
/*     */   }
/*     */   
/*     */   public void startOpen(EntityHuman entityhuman) {
/* 139 */     this.left.startOpen(entityhuman);
/* 140 */     this.right.startOpen(entityhuman);
/*     */   }
/*     */   
/*     */   public void closeContainer(EntityHuman entityhuman) {
/* 144 */     this.left.closeContainer(entityhuman);
/* 145 */     this.right.closeContainer(entityhuman);
/*     */   }
/*     */   
/*     */   public boolean b(int i, ItemStack itemstack) {
/* 149 */     return true;
/*     */   }
/*     */   
/*     */   public int getProperty(int i) {
/* 153 */     return 0;
/*     */   }
/*     */   
/*     */   public void setProperty(int i, int j) {}
/*     */   
/*     */   public int h() {
/* 159 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean isLocked() {
/* 163 */     return !(!this.left.isLocked() && !this.right.isLocked());
/*     */   }
/*     */   
/*     */   public void setLock(ChestLock chestlock) {
/* 167 */     this.left.setLock(chestlock);
/* 168 */     this.right.setLock(chestlock);
/*     */   }
/*     */   
/*     */   public ChestLock getLock() {
/* 172 */     return this.left.getLock();
/*     */   }
/*     */   
/*     */   public String getContainerName() {
/* 176 */     return this.left.getContainerName();
/*     */   }
/*     */   
/*     */   public Container createContainer(PlayerInventory playerinventory, EntityHuman entityhuman) {
/* 180 */     return new ContainerChest(playerinventory, this, entityhuman);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 184 */     this.left.clear();
/* 185 */     this.right.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\InventoryLargeChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */