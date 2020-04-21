/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftHumanEntity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ 
/*     */ public class InventoryCraftResult
/*     */   implements IInventory
/*     */ {
/*     */   private final NonNullList<ItemStack> items;
/*     */   private IRecipe b;
/*  17 */   private int maxStack = 64;
/*     */   
/*     */   public List<ItemStack> getContents() {
/*  20 */     return this.items;
/*     */   }
/*     */   
/*     */   public InventoryHolder getOwner() {
/*  24 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onOpen(CraftHumanEntity who) {}
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/*  31 */     return new ArrayList<>();
/*     */   }
/*     */   public void onClose(CraftHumanEntity who) {}
/*     */   public void setMaxStackSize(int size) {
/*  35 */     this.maxStack = size;
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getLocation() {
/*  40 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryCraftResult() {
/*  45 */     this.items = NonNullList.a(1, ItemStack.a);
/*     */   }
/*     */   
/*     */   public int getSize() {
/*  49 */     return 1;
/*     */   }
/*     */   public boolean x_() {
/*     */     ItemStack itemstack;
/*  53 */     Iterator<ItemStack> iterator = this.items.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  58 */       if (!iterator.hasNext()) {
/*  59 */         return true;
/*     */       }
/*     */       
/*  62 */       itemstack = iterator.next();
/*  63 */     } while (itemstack.isEmpty());
/*     */     
/*  65 */     return false;
/*     */   }
/*     */   
/*     */   public ItemStack getItem(int i) {
/*  69 */     return this.items.get(0);
/*     */   }
/*     */   
/*     */   public String getName() {
/*  73 */     return "Result";
/*     */   }
/*     */   
/*     */   public boolean hasCustomName() {
/*  77 */     return false;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent getScoreboardDisplayName() {
/*  81 */     return hasCustomName() ? new ChatComponentText(getName()) : new ChatMessage(getName(), new Object[0]);
/*     */   }
/*     */   
/*     */   public ItemStack splitStack(int i, int j) {
/*  85 */     return ContainerUtil.a(this.items, 0);
/*     */   }
/*     */   
/*     */   public ItemStack splitWithoutUpdate(int i) {
/*  89 */     return ContainerUtil.a(this.items, 0);
/*     */   }
/*     */   
/*     */   public void setItem(int i, ItemStack itemstack) {
/*  93 */     this.items.set(0, itemstack);
/*     */   }
/*     */   
/*     */   public int getMaxStackSize() {
/*  97 */     return this.maxStack;
/*     */   }
/*     */   
/*     */   public void update() {}
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 103 */     return true;
/*     */   }
/*     */   
/*     */   public void startOpen(EntityHuman entityhuman) {}
/*     */   
/*     */   public void closeContainer(EntityHuman entityhuman) {}
/*     */   
/*     */   public boolean b(int i, ItemStack itemstack) {
/* 111 */     return true;
/*     */   }
/*     */   
/*     */   public int getProperty(int i) {
/* 115 */     return 0;
/*     */   }
/*     */   
/*     */   public void setProperty(int i, int j) {}
/*     */   
/*     */   public int h() {
/* 121 */     return 0;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 125 */     this.items.clear();
/*     */   }
/*     */   
/*     */   public void a(@Nullable IRecipe irecipe) {
/* 129 */     this.b = irecipe;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public IRecipe i() {
/* 134 */     return this.b;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\InventoryCraftResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */