/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventory;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryDoubleChest;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryPlayer;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class ContainerChest extends Container {
/*     */   private final IInventory container;
/*  13 */   private CraftInventoryView bukkitEntity = null; private final int f;
/*     */   private PlayerInventory player;
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/*     */     CraftInventory inventory;
/*  18 */     if (this.bukkitEntity != null) {
/*  19 */       return this.bukkitEntity;
/*     */     }
/*     */ 
/*     */     
/*  23 */     if (this.container instanceof PlayerInventory) {
/*  24 */       CraftInventoryPlayer craftInventoryPlayer = new CraftInventoryPlayer((PlayerInventory)this.container);
/*  25 */     } else if (this.container instanceof InventoryLargeChest) {
/*  26 */       CraftInventoryDoubleChest craftInventoryDoubleChest = new CraftInventoryDoubleChest((InventoryLargeChest)this.container);
/*     */     } else {
/*  28 */       inventory = new CraftInventory(this.container);
/*     */     } 
/*     */     
/*  31 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), (Inventory)inventory, this);
/*  32 */     return this.bukkitEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContainerChest(IInventory iinventory, IInventory iinventory1, EntityHuman entityhuman) {
/*  37 */     this.container = iinventory1;
/*  38 */     this.f = iinventory1.getSize() / 9;
/*  39 */     iinventory1.startOpen(entityhuman);
/*  40 */     int i = (this.f - 4) * 18;
/*     */ 
/*     */ 
/*     */     
/*  44 */     this.player = (PlayerInventory)iinventory;
/*     */ 
/*     */     
/*     */     int j;
/*     */ 
/*     */     
/*  50 */     for (j = 0; j < this.f; j++) {
/*  51 */       for (int k = 0; k < 9; k++) {
/*  52 */         a(new Slot(iinventory1, k + j * 9, 8 + k * 18, 18 + j * 18));
/*     */       }
/*     */     } 
/*     */     
/*  56 */     for (j = 0; j < 3; j++) {
/*  57 */       for (int k = 0; k < 9; k++) {
/*  58 */         a(new Slot(iinventory, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
/*     */       }
/*     */     } 
/*     */     
/*  62 */     for (j = 0; j < 9; j++) {
/*  63 */       a(new Slot(iinventory, j, 8 + j * 18, 161 + i));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/*  69 */     if (!this.checkReachable) return true; 
/*  70 */     return this.container.a(entityhuman);
/*     */   }
/*     */   
/*     */   public ItemStack b(EntityHuman entityhuman, int i) {
/*  74 */     ItemStack itemstack = ItemStack.a;
/*  75 */     Slot slot = this.c.get(i);
/*     */     
/*  77 */     if (slot != null && slot.hasItem()) {
/*  78 */       ItemStack itemstack1 = slot.getItem();
/*     */       
/*  80 */       itemstack = itemstack1.cloneItemStack();
/*  81 */       if (i < this.f * 9) {
/*  82 */         if (!a(itemstack1, this.f * 9, this.c.size(), true)) {
/*  83 */           return ItemStack.a;
/*     */         }
/*  85 */       } else if (!a(itemstack1, 0, this.f * 9, false)) {
/*  86 */         return ItemStack.a;
/*     */       } 
/*     */       
/*  89 */       if (itemstack1.isEmpty()) {
/*  90 */         slot.set(ItemStack.a);
/*     */       } else {
/*  92 */         slot.f();
/*     */       } 
/*     */     } 
/*     */     
/*  96 */     return itemstack;
/*     */   }
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/* 100 */     super.b(entityhuman);
/* 101 */     this.container.closeContainer(entityhuman);
/*     */   }
/*     */   
/*     */   public IInventory e() {
/* 105 */     return this.container;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ContainerChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */