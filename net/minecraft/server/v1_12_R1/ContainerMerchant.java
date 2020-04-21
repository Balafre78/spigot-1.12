/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryMerchant;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class ContainerMerchant
/*     */   extends Container {
/*     */   private final IMerchant merchant;
/*  12 */   private CraftInventoryView bukkitEntity = null; private final InventoryMerchant f;
/*     */   private final World g;
/*     */   private PlayerInventory player;
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/*  17 */     if (this.bukkitEntity == null) {
/*  18 */       this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), (Inventory)new CraftInventoryMerchant(this.f), this);
/*     */     }
/*  20 */     return this.bukkitEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContainerMerchant(PlayerInventory playerinventory, IMerchant imerchant, World world) {
/*  25 */     this.merchant = imerchant;
/*  26 */     this.g = world;
/*  27 */     this.f = new InventoryMerchant(playerinventory.player, imerchant);
/*  28 */     a(new Slot(this.f, 0, 36, 53));
/*  29 */     a(new Slot(this.f, 1, 62, 53));
/*  30 */     a(new SlotMerchantResult(playerinventory.player, imerchant, this.f, 2, 120, 53));
/*  31 */     this.player = playerinventory;
/*     */     
/*     */     int i;
/*     */     
/*  35 */     for (i = 0; i < 3; i++) {
/*  36 */       for (int j = 0; j < 9; j++) {
/*  37 */         a(new Slot(playerinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     } 
/*     */     
/*  41 */     for (i = 0; i < 9; i++) {
/*  42 */       a(new Slot(playerinventory, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryMerchant e() {
/*  48 */     return this.f;
/*     */   }
/*     */   
/*     */   public void a(IInventory iinventory) {
/*  52 */     this.f.i();
/*  53 */     super.a(iinventory);
/*     */   }
/*     */   
/*     */   public void d(int i) {
/*  57 */     this.f.d(i);
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/*  61 */     return (this.merchant.getTrader() == entityhuman);
/*     */   }
/*     */   
/*     */   public ItemStack b(EntityHuman entityhuman, int i) {
/*  65 */     ItemStack itemstack = ItemStack.a;
/*  66 */     Slot slot = this.c.get(i);
/*     */     
/*  68 */     if (slot != null && slot.hasItem()) {
/*  69 */       ItemStack itemstack1 = slot.getItem();
/*     */       
/*  71 */       itemstack = itemstack1.cloneItemStack();
/*  72 */       if (i == 2) {
/*  73 */         if (!a(itemstack1, 3, 39, true)) {
/*  74 */           return ItemStack.a;
/*     */         }
/*     */         
/*  77 */         slot.a(itemstack1, itemstack);
/*  78 */       } else if (i != 0 && i != 1) {
/*  79 */         if (i >= 3 && i < 30) {
/*  80 */           if (!a(itemstack1, 30, 39, false)) {
/*  81 */             return ItemStack.a;
/*     */           }
/*  83 */         } else if (i >= 30 && i < 39 && !a(itemstack1, 3, 30, false)) {
/*  84 */           return ItemStack.a;
/*     */         } 
/*  86 */       } else if (!a(itemstack1, 3, 39, false)) {
/*  87 */         return ItemStack.a;
/*     */       } 
/*     */       
/*  90 */       if (itemstack1.isEmpty()) {
/*  91 */         slot.set(ItemStack.a);
/*     */       } else {
/*  93 */         slot.f();
/*     */       } 
/*     */       
/*  96 */       if (itemstack1.getCount() == itemstack.getCount()) {
/*  97 */         return ItemStack.a;
/*     */       }
/*     */       
/* 100 */       slot.a(entityhuman, itemstack1);
/*     */     } 
/*     */     
/* 103 */     return itemstack;
/*     */   }
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/* 107 */     super.b(entityhuman);
/* 108 */     this.merchant.setTradingPlayer(null);
/* 109 */     super.b(entityhuman);
/* 110 */     if (!this.g.isClientSide) {
/* 111 */       ItemStack itemstack = this.f.splitWithoutUpdate(0);
/*     */       
/* 113 */       if (!itemstack.isEmpty()) {
/* 114 */         entityhuman.drop(itemstack, false);
/*     */       }
/*     */       
/* 117 */       itemstack = this.f.splitWithoutUpdate(1);
/* 118 */       if (!itemstack.isEmpty())
/* 119 */         entityhuman.drop(itemstack, false); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ContainerMerchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */