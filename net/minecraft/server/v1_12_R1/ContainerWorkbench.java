/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryCrafting;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class ContainerWorkbench
/*     */   extends Container {
/*     */   public InventoryCrafting craftInventory;
/*     */   public InventoryCraftResult resultInventory;
/*     */   private final World g;
/*     */   private final BlockPosition h;
/*     */   private final EntityHuman i;
/*  16 */   private CraftInventoryView bukkitEntity = null;
/*     */   
/*     */   private PlayerInventory player;
/*     */ 
/*     */   
/*     */   public ContainerWorkbench(PlayerInventory playerinventory, World world, BlockPosition blockposition) {
/*  22 */     this.resultInventory = new InventoryCraftResult();
/*  23 */     this.craftInventory = new InventoryCrafting(this, 3, 3, playerinventory.player);
/*  24 */     this.craftInventory.resultInventory = this.resultInventory;
/*  25 */     this.player = playerinventory;
/*     */     
/*  27 */     this.g = world;
/*  28 */     this.h = blockposition;
/*  29 */     this.i = playerinventory.player;
/*  30 */     a(new SlotResult(playerinventory.player, this.craftInventory, this.resultInventory, 0, 124, 35));
/*     */ 
/*     */     
/*     */     int i;
/*     */     
/*  35 */     for (i = 0; i < 3; i++) {
/*  36 */       for (int j = 0; j < 3; j++) {
/*  37 */         a(new Slot(this.craftInventory, j + i * 3, 30 + j * 18, 17 + i * 18));
/*     */       }
/*     */     } 
/*     */     
/*  41 */     for (i = 0; i < 3; i++) {
/*  42 */       for (int j = 0; j < 9; j++) {
/*  43 */         a(new Slot(playerinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     } 
/*     */     
/*  47 */     for (i = 0; i < 9; i++) {
/*  48 */       a(new Slot(playerinventory, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IInventory iinventory) {
/*  54 */     a(this.g, this.i, this.craftInventory, this.resultInventory);
/*     */   }
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/*  58 */     super.b(entityhuman);
/*  59 */     if (!this.g.isClientSide) {
/*  60 */       a(entityhuman, this.g, this.craftInventory);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/*  65 */     if (!this.checkReachable) return true; 
/*  66 */     return (this.g.getType(this.h).getBlock() != Blocks.CRAFTING_TABLE) ? false : ((entityhuman.d(this.h.getX() + 0.5D, this.h.getY() + 0.5D, this.h.getZ() + 0.5D) <= 64.0D));
/*     */   }
/*     */   
/*     */   public ItemStack b(EntityHuman entityhuman, int i) {
/*  70 */     ItemStack itemstack = ItemStack.a;
/*  71 */     Slot slot = this.c.get(i);
/*     */     
/*  73 */     if (slot != null && slot.hasItem()) {
/*  74 */       ItemStack itemstack1 = slot.getItem();
/*     */       
/*  76 */       itemstack = itemstack1.cloneItemStack();
/*  77 */       if (i == 0) {
/*  78 */         itemstack1.getItem().b(itemstack1, this.g, entityhuman);
/*  79 */         if (!a(itemstack1, 10, 46, true)) {
/*  80 */           return ItemStack.a;
/*     */         }
/*     */         
/*  83 */         slot.a(itemstack1, itemstack);
/*  84 */       } else if (i >= 10 && i < 37) {
/*  85 */         if (!a(itemstack1, 37, 46, false)) {
/*  86 */           return ItemStack.a;
/*     */         }
/*  88 */       } else if (i >= 37 && i < 46) {
/*  89 */         if (!a(itemstack1, 10, 37, false)) {
/*  90 */           return ItemStack.a;
/*     */         }
/*  92 */       } else if (!a(itemstack1, 10, 46, false)) {
/*  93 */         return ItemStack.a;
/*     */       } 
/*     */       
/*  96 */       if (itemstack1.isEmpty()) {
/*  97 */         slot.set(ItemStack.a);
/*     */       } else {
/*  99 */         slot.f();
/*     */       } 
/*     */       
/* 102 */       if (itemstack1.getCount() == itemstack.getCount()) {
/* 103 */         return ItemStack.a;
/*     */       }
/*     */       
/* 106 */       ItemStack itemstack2 = slot.a(entityhuman, itemstack1);
/*     */       
/* 108 */       if (i == 0) {
/* 109 */         entityhuman.drop(itemstack2, false);
/*     */       }
/*     */     } 
/*     */     
/* 113 */     return itemstack;
/*     */   }
/*     */   
/*     */   public boolean a(ItemStack itemstack, Slot slot) {
/* 117 */     return (slot.inventory != this.resultInventory && super.a(itemstack, slot));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/* 123 */     if (this.bukkitEntity != null) {
/* 124 */       return this.bukkitEntity;
/*     */     }
/*     */     
/* 127 */     CraftInventoryCrafting inventory = new CraftInventoryCrafting(this.craftInventory, this.resultInventory);
/* 128 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), (Inventory)inventory, this);
/* 129 */     return this.bukkitEntity;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ContainerWorkbench.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */