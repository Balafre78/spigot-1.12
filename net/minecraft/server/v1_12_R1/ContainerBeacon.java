/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryBeacon;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class ContainerBeacon extends Container {
/*  10 */   private CraftInventoryView bukkitEntity = null; private final IInventory beacon;
/*     */   private final SlotBeacon f;
/*     */   private PlayerInventory player;
/*     */   
/*     */   public ContainerBeacon(IInventory iinventory, IInventory iinventory1) {
/*  15 */     this.player = (PlayerInventory)iinventory;
/*  16 */     this.beacon = iinventory1;
/*  17 */     this.f = new SlotBeacon(iinventory1, 0, 136, 110);
/*  18 */     a(this.f);
/*     */ 
/*     */     
/*     */     int i;
/*     */ 
/*     */     
/*  24 */     for (i = 0; i < 3; i++) {
/*  25 */       for (int j = 0; j < 9; j++) {
/*  26 */         a(new Slot(iinventory, j + i * 9 + 9, 36 + j * 18, 137 + i * 18));
/*     */       }
/*     */     } 
/*     */     
/*  30 */     for (i = 0; i < 9; i++) {
/*  31 */       a(new Slot(iinventory, i, 36 + i * 18, 195));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addSlotListener(ICrafting icrafting) {
/*  37 */     super.addSlotListener(icrafting);
/*  38 */     icrafting.setContainerData(this, this.beacon);
/*     */   }
/*     */   
/*     */   public IInventory e() {
/*  42 */     return this.beacon;
/*     */   }
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/*  46 */     super.b(entityhuman);
/*  47 */     if (!entityhuman.world.isClientSide) {
/*  48 */       ItemStack itemstack = this.f.a(this.f.getMaxStackSize());
/*     */       
/*  50 */       if (!itemstack.isEmpty()) {
/*  51 */         entityhuman.drop(itemstack, false);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/*  58 */     if (!this.checkReachable) return true; 
/*  59 */     return this.beacon.a(entityhuman);
/*     */   }
/*     */   
/*     */   public ItemStack b(EntityHuman entityhuman, int i) {
/*  63 */     ItemStack itemstack = ItemStack.a;
/*  64 */     Slot slot = this.c.get(i);
/*     */     
/*  66 */     if (slot != null && slot.hasItem()) {
/*  67 */       ItemStack itemstack1 = slot.getItem();
/*     */       
/*  69 */       itemstack = itemstack1.cloneItemStack();
/*  70 */       if (i == 0) {
/*  71 */         if (!a(itemstack1, 1, 37, true)) {
/*  72 */           return ItemStack.a;
/*     */         }
/*     */         
/*  75 */         slot.a(itemstack1, itemstack);
/*  76 */       } else if (!this.f.hasItem() && this.f.isAllowed(itemstack1) && itemstack1.getCount() == 1) {
/*  77 */         if (!a(itemstack1, 0, 1, false)) {
/*  78 */           return ItemStack.a;
/*     */         }
/*  80 */       } else if (i >= 1 && i < 28) {
/*  81 */         if (!a(itemstack1, 28, 37, false)) {
/*  82 */           return ItemStack.a;
/*     */         }
/*  84 */       } else if (i >= 28 && i < 37) {
/*  85 */         if (!a(itemstack1, 1, 28, false)) {
/*  86 */           return ItemStack.a;
/*     */         }
/*  88 */       } else if (!a(itemstack1, 1, 37, false)) {
/*  89 */         return ItemStack.a;
/*     */       } 
/*     */       
/*  92 */       if (itemstack1.isEmpty()) {
/*  93 */         slot.set(ItemStack.a);
/*     */       } else {
/*  95 */         slot.f();
/*     */       } 
/*     */       
/*  98 */       if (itemstack1.getCount() == itemstack.getCount()) {
/*  99 */         return ItemStack.a;
/*     */       }
/*     */       
/* 102 */       slot.a(entityhuman, itemstack1);
/*     */     } 
/*     */     
/* 105 */     return itemstack;
/*     */   }
/*     */   
/*     */   class SlotBeacon
/*     */     extends Slot {
/*     */     public SlotBeacon(IInventory iinventory, int i, int j, int k) {
/* 111 */       super(iinventory, i, j, k);
/*     */     }
/*     */     
/*     */     public boolean isAllowed(ItemStack itemstack) {
/* 115 */       Item item = itemstack.getItem();
/*     */       
/* 117 */       return !(item != Items.EMERALD && item != Items.DIAMOND && item != Items.GOLD_INGOT && item != Items.IRON_INGOT);
/*     */     }
/*     */     
/*     */     public int getMaxStackSize() {
/* 121 */       return 1;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/* 128 */     if (this.bukkitEntity != null) {
/* 129 */       return this.bukkitEntity;
/*     */     }
/*     */     
/* 132 */     CraftInventoryBeacon craftInventoryBeacon = new CraftInventoryBeacon((TileEntityBeacon)this.beacon);
/* 133 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), (Inventory)craftInventoryBeacon, this);
/* 134 */     return this.bukkitEntity;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ContainerBeacon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */