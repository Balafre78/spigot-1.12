/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftHumanEntity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventoryMerchant
/*     */   implements IInventory
/*     */ {
/*     */   private final IMerchant merchant;
/*     */   private final NonNullList<ItemStack> itemsInSlots;
/*     */   private final EntityHuman player;
/*     */   private MerchantRecipe recipe;
/*     */   public int selectedIndex;
/*  21 */   public List<HumanEntity> transaction = new ArrayList<>();
/*  22 */   private int maxStack = 64;
/*     */   
/*     */   public List<ItemStack> getContents() {
/*  25 */     return this.itemsInSlots;
/*     */   }
/*     */   
/*     */   public void onOpen(CraftHumanEntity who) {
/*  29 */     this.transaction.add(who);
/*     */   }
/*     */   
/*     */   public void onClose(CraftHumanEntity who) {
/*  33 */     this.transaction.remove(who);
/*     */   }
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/*  37 */     return this.transaction;
/*     */   }
/*     */   
/*     */   public void setMaxStackSize(int i) {
/*  41 */     this.maxStack = i;
/*     */   }
/*     */   
/*     */   public InventoryHolder getOwner() {
/*  45 */     return (this.merchant instanceof EntityVillager) ? (InventoryHolder)((EntityVillager)this.merchant).getBukkitEntity() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getLocation() {
/*  50 */     return (this.merchant instanceof EntityVillager) ? ((EntityVillager)this.merchant).getBukkitEntity().getLocation() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryMerchant(EntityHuman entityhuman, IMerchant imerchant) {
/*  55 */     this.itemsInSlots = NonNullList.a(3, ItemStack.a);
/*  56 */     this.player = entityhuman;
/*  57 */     this.merchant = imerchant;
/*     */   }
/*     */   
/*     */   public int getSize() {
/*  61 */     return this.itemsInSlots.size();
/*     */   }
/*     */   public boolean x_() {
/*     */     ItemStack itemstack;
/*  65 */     Iterator<ItemStack> iterator = this.itemsInSlots.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  70 */       if (!iterator.hasNext()) {
/*  71 */         return true;
/*     */       }
/*     */       
/*  74 */       itemstack = iterator.next();
/*  75 */     } while (itemstack.isEmpty());
/*     */     
/*  77 */     return false;
/*     */   }
/*     */   
/*     */   public ItemStack getItem(int i) {
/*  81 */     return this.itemsInSlots.get(i);
/*     */   }
/*     */   
/*     */   public ItemStack splitStack(int i, int j) {
/*  85 */     ItemStack itemstack = this.itemsInSlots.get(i);
/*     */     
/*  87 */     if (i == 2 && !itemstack.isEmpty()) {
/*  88 */       return ContainerUtil.a(this.itemsInSlots, i, itemstack.getCount());
/*     */     }
/*  90 */     ItemStack itemstack1 = ContainerUtil.a(this.itemsInSlots, i, j);
/*     */     
/*  92 */     if (!itemstack1.isEmpty() && e(i)) {
/*  93 */       i();
/*     */     }
/*     */     
/*  96 */     return itemstack1;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean e(int i) {
/* 101 */     return !(i != 0 && i != 1);
/*     */   }
/*     */   
/*     */   public ItemStack splitWithoutUpdate(int i) {
/* 105 */     return ContainerUtil.a(this.itemsInSlots, i);
/*     */   }
/*     */   
/*     */   public void setItem(int i, ItemStack itemstack) {
/* 109 */     this.itemsInSlots.set(i, itemstack);
/* 110 */     if (!itemstack.isEmpty() && itemstack.getCount() > getMaxStackSize()) {
/* 111 */       itemstack.setCount(getMaxStackSize());
/*     */     }
/*     */     
/* 114 */     if (e(i)) {
/* 115 */       i();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 121 */     return "mob.villager";
/*     */   }
/*     */   
/*     */   public boolean hasCustomName() {
/* 125 */     return false;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent getScoreboardDisplayName() {
/* 129 */     return hasCustomName() ? new ChatComponentText(getName()) : new ChatMessage(getName(), new Object[0]);
/*     */   }
/*     */   
/*     */   public int getMaxStackSize() {
/* 133 */     return this.maxStack;
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 137 */     return (this.merchant.getTrader() == entityhuman);
/*     */   }
/*     */   
/*     */   public void startOpen(EntityHuman entityhuman) {}
/*     */   
/*     */   public void closeContainer(EntityHuman entityhuman) {}
/*     */   
/*     */   public boolean b(int i, ItemStack itemstack) {
/* 145 */     return true;
/*     */   }
/*     */   
/*     */   public void update() {
/* 149 */     i();
/*     */   }
/*     */   
/*     */   public void i() {
/* 153 */     this.recipe = null;
/* 154 */     ItemStack itemstack = this.itemsInSlots.get(0);
/* 155 */     ItemStack itemstack1 = this.itemsInSlots.get(1);
/*     */     
/* 157 */     if (itemstack.isEmpty()) {
/* 158 */       itemstack = itemstack1;
/* 159 */       itemstack1 = ItemStack.a;
/*     */     } 
/*     */     
/* 162 */     if (itemstack.isEmpty()) {
/* 163 */       setItem(2, ItemStack.a);
/*     */     } else {
/* 165 */       MerchantRecipeList merchantrecipelist = this.merchant.getOffers(this.player);
/*     */       
/* 167 */       if (merchantrecipelist != null) {
/* 168 */         MerchantRecipe merchantrecipe = merchantrecipelist.a(itemstack, itemstack1, this.selectedIndex);
/*     */         
/* 170 */         if (merchantrecipe != null && !merchantrecipe.h()) {
/* 171 */           this.recipe = merchantrecipe;
/* 172 */           setItem(2, merchantrecipe.getBuyItem3().cloneItemStack());
/* 173 */         } else if (!itemstack1.isEmpty()) {
/* 174 */           merchantrecipe = merchantrecipelist.a(itemstack1, itemstack, this.selectedIndex);
/* 175 */           if (merchantrecipe != null && !merchantrecipe.h()) {
/* 176 */             this.recipe = merchantrecipe;
/* 177 */             setItem(2, merchantrecipe.getBuyItem3().cloneItemStack());
/*     */           } else {
/* 179 */             setItem(2, ItemStack.a);
/*     */           } 
/*     */         } else {
/* 182 */           setItem(2, ItemStack.a);
/*     */         } 
/*     */       } 
/*     */       
/* 186 */       this.merchant.a(getItem(2));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public MerchantRecipe getRecipe() {
/* 192 */     return this.recipe;
/*     */   }
/*     */   
/*     */   public void d(int i) {
/* 196 */     this.selectedIndex = i;
/* 197 */     i();
/*     */   }
/*     */   
/*     */   public int getProperty(int i) {
/* 201 */     return 0;
/*     */   }
/*     */   
/*     */   public void setProperty(int i, int j) {}
/*     */   
/*     */   public int h() {
/* 207 */     return 0;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 211 */     this.itemsInSlots.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\InventoryMerchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */