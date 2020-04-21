/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftHumanEntity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventoryCrafting
/*     */   implements IInventory
/*     */ {
/*     */   private final NonNullList<ItemStack> items;
/*     */   private final int b;
/*     */   private final int c;
/*     */   public final Container container;
/*  21 */   public List<HumanEntity> transaction = new ArrayList<>();
/*     */   public IRecipe currentRecipe;
/*     */   public IInventory resultInventory;
/*     */   private EntityHuman owner;
/*  25 */   private int maxStack = 64;
/*     */   
/*     */   public List<ItemStack> getContents() {
/*  28 */     return this.items;
/*     */   }
/*     */   
/*     */   public void onOpen(CraftHumanEntity who) {
/*  32 */     this.transaction.add(who);
/*     */   }
/*     */   
/*     */   public InventoryType getInvType() {
/*  36 */     return (this.items.size() == 4) ? InventoryType.CRAFTING : InventoryType.WORKBENCH;
/*     */   }
/*     */   
/*     */   public void onClose(CraftHumanEntity who) {
/*  40 */     this.transaction.remove(who);
/*     */   }
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/*  44 */     return this.transaction;
/*     */   }
/*     */   
/*     */   public InventoryHolder getOwner() {
/*  48 */     return (this.owner == null) ? null : (InventoryHolder)this.owner.getBukkitEntity();
/*     */   }
/*     */   
/*     */   public void setMaxStackSize(int size) {
/*  52 */     this.maxStack = size;
/*  53 */     this.resultInventory.setMaxStackSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getLocation() {
/*  58 */     return this.owner.getBukkitEntity().getLocation();
/*     */   }
/*     */   
/*     */   public InventoryCrafting(Container container, int i, int j, EntityHuman player) {
/*  62 */     this(container, i, j);
/*  63 */     this.owner = player;
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryCrafting(Container container, int i, int j) {
/*  68 */     this.items = NonNullList.a(i * j, ItemStack.a);
/*  69 */     this.container = container;
/*  70 */     this.b = i;
/*  71 */     this.c = j;
/*     */   }
/*     */   
/*     */   public int getSize() {
/*  75 */     return this.items.size();
/*     */   }
/*     */   public boolean x_() {
/*     */     ItemStack itemstack;
/*  79 */     Iterator<ItemStack> iterator = this.items.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  84 */       if (!iterator.hasNext()) {
/*  85 */         return true;
/*     */       }
/*     */       
/*  88 */       itemstack = iterator.next();
/*  89 */     } while (itemstack.isEmpty());
/*     */     
/*  91 */     return false;
/*     */   }
/*     */   
/*     */   public ItemStack getItem(int i) {
/*  95 */     return (i >= getSize()) ? ItemStack.a : this.items.get(i);
/*     */   }
/*     */   
/*     */   public ItemStack c(int i, int j) {
/*  99 */     return (i >= 0 && i < this.b && j >= 0 && j <= this.c) ? getItem(i + j * this.b) : ItemStack.a;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 103 */     return "container.crafting";
/*     */   }
/*     */   
/*     */   public boolean hasCustomName() {
/* 107 */     return false;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent getScoreboardDisplayName() {
/* 111 */     return hasCustomName() ? new ChatComponentText(getName()) : new ChatMessage(getName(), new Object[0]);
/*     */   }
/*     */   
/*     */   public ItemStack splitWithoutUpdate(int i) {
/* 115 */     return ContainerUtil.a(this.items, i);
/*     */   }
/*     */   
/*     */   public ItemStack splitStack(int i, int j) {
/* 119 */     ItemStack itemstack = ContainerUtil.a(this.items, i, j);
/*     */     
/* 121 */     if (!itemstack.isEmpty()) {
/* 122 */       this.container.a(this);
/*     */     }
/*     */     
/* 125 */     return itemstack;
/*     */   }
/*     */   
/*     */   public void setItem(int i, ItemStack itemstack) {
/* 129 */     this.items.set(i, itemstack);
/* 130 */     this.container.a(this);
/*     */   }
/*     */   
/*     */   public int getMaxStackSize() {
/* 134 */     return 64;
/*     */   }
/*     */   
/*     */   public void update() {}
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 140 */     return true;
/*     */   }
/*     */   
/*     */   public void startOpen(EntityHuman entityhuman) {}
/*     */   
/*     */   public void closeContainer(EntityHuman entityhuman) {}
/*     */   
/*     */   public boolean b(int i, ItemStack itemstack) {
/* 148 */     return true;
/*     */   }
/*     */   
/*     */   public int getProperty(int i) {
/* 152 */     return 0;
/*     */   }
/*     */   
/*     */   public void setProperty(int i, int j) {}
/*     */   
/*     */   public int h() {
/* 158 */     return 0;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 162 */     this.items.clear();
/*     */   }
/*     */   
/*     */   public int i() {
/* 166 */     return this.c;
/*     */   }
/*     */   
/*     */   public int j() {
/* 170 */     return this.b;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\InventoryCrafting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */