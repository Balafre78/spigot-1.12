/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryCrafting;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class ContainerPlayer extends Container {
/*  10 */   private static final EnumItemSlot[] h = new EnumItemSlot[] { EnumItemSlot.HEAD, EnumItemSlot.CHEST, EnumItemSlot.LEGS, EnumItemSlot.FEET };
/*  11 */   public InventoryCrafting craftInventory = new InventoryCrafting(this, 2, 2);
/*  12 */   public InventoryCraftResult resultInventory = new InventoryCraftResult();
/*     */   
/*     */   public boolean g;
/*     */   private final EntityHuman owner;
/*  16 */   private CraftInventoryView bukkitEntity = null;
/*     */   
/*     */   private PlayerInventory player;
/*     */   
/*     */   public ContainerPlayer(PlayerInventory playerinventory, boolean flag, EntityHuman entityhuman) {
/*  21 */     this.g = flag;
/*  22 */     this.owner = entityhuman;
/*     */     
/*  24 */     this.resultInventory = new InventoryCraftResult();
/*  25 */     this.craftInventory = new InventoryCrafting(this, 2, 2, playerinventory.player);
/*  26 */     this.craftInventory.resultInventory = this.resultInventory;
/*  27 */     this.player = playerinventory;
/*     */     
/*  29 */     a(new SlotResult(playerinventory.player, this.craftInventory, this.resultInventory, 0, 154, 28));
/*     */ 
/*     */     
/*     */     int i;
/*     */     
/*  34 */     for (i = 0; i < 2; i++) {
/*  35 */       for (int j = 0; j < 2; j++) {
/*  36 */         a(new Slot(this.craftInventory, j + i * 2, 98 + j * 18, 18 + i * 18));
/*     */       }
/*     */     } 
/*     */     
/*  40 */     for (i = 0; i < 4; i++) {
/*  41 */       final EnumItemSlot enumitemslot1 = h[i];
/*     */       
/*  43 */       a(new Slot(playerinventory, 36 + 3 - i, 8, 8 + i * 18) {
/*     */             public int getMaxStackSize() {
/*  45 */               return 1;
/*     */             }
/*     */             
/*     */             public boolean isAllowed(ItemStack itemstack) {
/*  49 */               return (enumitemslot1 == EntityInsentient.d(itemstack));
/*     */             }
/*     */             
/*     */             public boolean isAllowed(EntityHuman entityhuman) {
/*  53 */               ItemStack itemstack = getItem();
/*     */               
/*  55 */               return (!itemstack.isEmpty() && !entityhuman.z() && EnchantmentManager.d(itemstack)) ? false : super.isAllowed(entityhuman);
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/*  60 */     for (i = 0; i < 3; i++) {
/*  61 */       for (int j = 0; j < 9; j++) {
/*  62 */         a(new Slot(playerinventory, j + (i + 1) * 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     } 
/*     */     
/*  66 */     for (i = 0; i < 9; i++) {
/*  67 */       a(new Slot(playerinventory, i, 8 + i * 18, 142));
/*     */     }
/*     */     
/*  70 */     a(new Slot(playerinventory, 40, 77, 62) {  }
/*     */       );
/*     */   }
/*     */   
/*     */   public void a(IInventory iinventory) {
/*  75 */     a(this.owner.world, this.owner, this.craftInventory, this.resultInventory);
/*     */   }
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/*  79 */     super.b(entityhuman);
/*  80 */     this.resultInventory.clear();
/*  81 */     if (!entityhuman.world.isClientSide) {
/*  82 */       a(entityhuman, entityhuman.world, this.craftInventory);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/*  87 */     return true;
/*     */   }
/*     */   
/*     */   public ItemStack b(EntityHuman entityhuman, int i) {
/*  91 */     ItemStack itemstack = ItemStack.a;
/*  92 */     Slot slot = this.c.get(i);
/*     */     
/*  94 */     if (slot != null && slot.hasItem()) {
/*  95 */       ItemStack itemstack1 = slot.getItem();
/*     */       
/*  97 */       itemstack = itemstack1.cloneItemStack();
/*  98 */       EnumItemSlot enumitemslot = EntityInsentient.d(itemstack);
/*     */       
/* 100 */       if (i == 0) {
/* 101 */         if (!a(itemstack1, 9, 45, true)) {
/* 102 */           return ItemStack.a;
/*     */         }
/*     */         
/* 105 */         slot.a(itemstack1, itemstack);
/* 106 */       } else if (i >= 1 && i < 5) {
/* 107 */         if (!a(itemstack1, 9, 45, false)) {
/* 108 */           return ItemStack.a;
/*     */         }
/* 110 */       } else if (i >= 5 && i < 9) {
/* 111 */         if (!a(itemstack1, 9, 45, false)) {
/* 112 */           return ItemStack.a;
/*     */         }
/* 114 */       } else if (enumitemslot.a() == EnumItemSlot.Function.ARMOR && !((Slot)this.c.get(8 - enumitemslot.b())).hasItem()) {
/* 115 */         int j = 8 - enumitemslot.b();
/*     */         
/* 117 */         if (!a(itemstack1, j, j + 1, false)) {
/* 118 */           return ItemStack.a;
/*     */         }
/* 120 */       } else if (enumitemslot == EnumItemSlot.OFFHAND && !((Slot)this.c.get(45)).hasItem()) {
/* 121 */         if (!a(itemstack1, 45, 46, false)) {
/* 122 */           return ItemStack.a;
/*     */         }
/* 124 */       } else if (i >= 9 && i < 36) {
/* 125 */         if (!a(itemstack1, 36, 45, false)) {
/* 126 */           return ItemStack.a;
/*     */         }
/* 128 */       } else if (i >= 36 && i < 45) {
/* 129 */         if (!a(itemstack1, 9, 36, false)) {
/* 130 */           return ItemStack.a;
/*     */         }
/* 132 */       } else if (!a(itemstack1, 9, 45, false)) {
/* 133 */         return ItemStack.a;
/*     */       } 
/*     */       
/* 136 */       if (itemstack1.isEmpty()) {
/* 137 */         slot.set(ItemStack.a);
/*     */       } else {
/* 139 */         slot.f();
/*     */       } 
/*     */       
/* 142 */       if (itemstack1.getCount() == itemstack.getCount()) {
/* 143 */         return ItemStack.a;
/*     */       }
/*     */       
/* 146 */       ItemStack itemstack2 = slot.a(entityhuman, itemstack1);
/*     */       
/* 148 */       if (i == 0) {
/* 149 */         entityhuman.drop(itemstack2, false);
/*     */       }
/*     */     } 
/*     */     
/* 153 */     return itemstack;
/*     */   }
/*     */   
/*     */   public boolean a(ItemStack itemstack, Slot slot) {
/* 157 */     return (slot.inventory != this.resultInventory && super.a(itemstack, slot));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/* 163 */     if (this.bukkitEntity != null) {
/* 164 */       return this.bukkitEntity;
/*     */     }
/*     */     
/* 167 */     CraftInventoryCrafting inventory = new CraftInventoryCrafting(this.craftInventory, this.resultInventory);
/* 168 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), (Inventory)inventory, this);
/* 169 */     return this.bukkitEntity;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ContainerPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */