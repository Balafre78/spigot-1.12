/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftMerchantRecipe;
/*     */ 
/*     */ 
/*     */ public class MerchantRecipe
/*     */ {
/*     */   public ItemStack buyingItem1;
/*     */   public ItemStack buyingItem2;
/*     */   public ItemStack sellingItem;
/*     */   public int uses;
/*     */   public int maxUses;
/*     */   public boolean rewardExp;
/*     */   private CraftMerchantRecipe bukkitHandle;
/*     */   
/*     */   public CraftMerchantRecipe asBukkit() {
/*  17 */     return (this.bukkitHandle == null) ? (this.bukkitHandle = new CraftMerchantRecipe(this)) : this.bukkitHandle;
/*     */   }
/*     */   
/*     */   public MerchantRecipe(ItemStack itemstack, ItemStack itemstack1, ItemStack itemstack2, int i, int j, CraftMerchantRecipe bukkit) {
/*  21 */     this(itemstack, itemstack1, itemstack2, i, j);
/*  22 */     this.bukkitHandle = bukkit;
/*     */   }
/*     */ 
/*     */   
/*     */   public MerchantRecipe(NBTTagCompound nbttagcompound) {
/*  27 */     this.buyingItem1 = ItemStack.a;
/*  28 */     this.buyingItem2 = ItemStack.a;
/*  29 */     this.sellingItem = ItemStack.a;
/*  30 */     a(nbttagcompound);
/*     */   }
/*     */   
/*     */   public MerchantRecipe(ItemStack itemstack, ItemStack itemstack1, ItemStack itemstack2) {
/*  34 */     this(itemstack, itemstack1, itemstack2, 0, 7);
/*     */   }
/*     */   
/*     */   public MerchantRecipe(ItemStack itemstack, ItemStack itemstack1, ItemStack itemstack2, int i, int j) {
/*  38 */     this.buyingItem1 = ItemStack.a;
/*  39 */     this.buyingItem2 = ItemStack.a;
/*  40 */     this.sellingItem = ItemStack.a;
/*  41 */     this.buyingItem1 = itemstack;
/*  42 */     this.buyingItem2 = itemstack1;
/*  43 */     this.sellingItem = itemstack2;
/*  44 */     this.uses = i;
/*  45 */     this.maxUses = j;
/*  46 */     this.rewardExp = true;
/*     */   }
/*     */   
/*     */   public MerchantRecipe(ItemStack itemstack, ItemStack itemstack1) {
/*  50 */     this(itemstack, ItemStack.a, itemstack1);
/*     */   }
/*     */   
/*     */   public MerchantRecipe(ItemStack itemstack, Item item) {
/*  54 */     this(itemstack, new ItemStack(item));
/*     */   }
/*     */   
/*     */   public ItemStack getBuyItem1() {
/*  58 */     return this.buyingItem1;
/*     */   }
/*     */   
/*     */   public ItemStack getBuyItem2() {
/*  62 */     return this.buyingItem2;
/*     */   }
/*     */   
/*     */   public boolean hasSecondItem() {
/*  66 */     return !this.buyingItem2.isEmpty();
/*     */   }
/*     */   
/*     */   public ItemStack getBuyItem3() {
/*  70 */     return this.sellingItem;
/*     */   }
/*     */   
/*     */   public int e() {
/*  74 */     return this.uses;
/*     */   }
/*     */   
/*     */   public int f() {
/*  78 */     return this.maxUses;
/*     */   }
/*     */   
/*     */   public void increaseUses() {
/*  82 */     this.uses++;
/*     */   }
/*     */   
/*     */   public void a(int i) {
/*  86 */     this.maxUses += i;
/*     */   }
/*     */   
/*     */   public boolean h() {
/*  90 */     return (this.uses >= this.maxUses);
/*     */   }
/*     */   
/*     */   public boolean j() {
/*  94 */     return this.rewardExp;
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/*  98 */     NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("buy");
/*     */     
/* 100 */     this.buyingItem1 = new ItemStack(nbttagcompound1);
/* 101 */     NBTTagCompound nbttagcompound2 = nbttagcompound.getCompound("sell");
/*     */     
/* 103 */     this.sellingItem = new ItemStack(nbttagcompound2);
/* 104 */     if (nbttagcompound.hasKeyOfType("buyB", 10)) {
/* 105 */       this.buyingItem2 = new ItemStack(nbttagcompound.getCompound("buyB"));
/*     */     }
/*     */     
/* 108 */     if (nbttagcompound.hasKeyOfType("uses", 99)) {
/* 109 */       this.uses = nbttagcompound.getInt("uses");
/*     */     }
/*     */     
/* 112 */     if (nbttagcompound.hasKeyOfType("maxUses", 99)) {
/* 113 */       this.maxUses = nbttagcompound.getInt("maxUses");
/*     */     } else {
/* 115 */       this.maxUses = 7;
/*     */     } 
/*     */     
/* 118 */     if (nbttagcompound.hasKeyOfType("rewardExp", 1)) {
/* 119 */       this.rewardExp = nbttagcompound.getBoolean("rewardExp");
/*     */     } else {
/* 121 */       this.rewardExp = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound k() {
/* 127 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */     
/* 129 */     nbttagcompound.set("buy", this.buyingItem1.save(new NBTTagCompound()));
/* 130 */     nbttagcompound.set("sell", this.sellingItem.save(new NBTTagCompound()));
/* 131 */     if (!this.buyingItem2.isEmpty()) {
/* 132 */       nbttagcompound.set("buyB", this.buyingItem2.save(new NBTTagCompound()));
/*     */     }
/*     */     
/* 135 */     nbttagcompound.setInt("uses", this.uses);
/* 136 */     nbttagcompound.setInt("maxUses", this.maxUses);
/* 137 */     nbttagcompound.setBoolean("rewardExp", this.rewardExp);
/* 138 */     return nbttagcompound;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MerchantRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */