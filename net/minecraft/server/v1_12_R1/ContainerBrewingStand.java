/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryBrewer;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class ContainerBrewingStand
/*     */   extends Container
/*     */ {
/*     */   private final IInventory brewingStand;
/*     */   private final Slot f;
/*     */   private int g;
/*     */   private int h;
/*  16 */   private CraftInventoryView bukkitEntity = null;
/*     */   
/*     */   private PlayerInventory player;
/*     */   
/*     */   public ContainerBrewingStand(PlayerInventory playerinventory, IInventory iinventory) {
/*  21 */     this.player = playerinventory;
/*  22 */     this.brewingStand = iinventory;
/*  23 */     a(new SlotPotionBottle(iinventory, 0, 56, 51));
/*  24 */     a(new SlotPotionBottle(iinventory, 1, 79, 58));
/*  25 */     a(new SlotPotionBottle(iinventory, 2, 102, 51));
/*  26 */     this.f = a(new SlotBrewing(iinventory, 3, 79, 17));
/*  27 */     a(new a(iinventory, 4, 17, 17));
/*     */     
/*     */     int i;
/*     */     
/*  31 */     for (i = 0; i < 3; i++) {
/*  32 */       for (int j = 0; j < 9; j++) {
/*  33 */         a(new Slot(playerinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     } 
/*     */     
/*  37 */     for (i = 0; i < 9; i++) {
/*  38 */       a(new Slot(playerinventory, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addSlotListener(ICrafting icrafting) {
/*  44 */     super.addSlotListener(icrafting);
/*  45 */     icrafting.setContainerData(this, this.brewingStand);
/*     */   }
/*     */   
/*     */   public void b() {
/*  49 */     super.b();
/*     */     
/*  51 */     for (int i = 0; i < this.listeners.size(); i++) {
/*  52 */       ICrafting icrafting = this.listeners.get(i);
/*     */       
/*  54 */       if (this.g != this.brewingStand.getProperty(0)) {
/*  55 */         icrafting.setContainerData(this, 0, this.brewingStand.getProperty(0));
/*     */       }
/*     */       
/*  58 */       if (this.h != this.brewingStand.getProperty(1)) {
/*  59 */         icrafting.setContainerData(this, 1, this.brewingStand.getProperty(1));
/*     */       }
/*     */     } 
/*     */     
/*  63 */     this.g = this.brewingStand.getProperty(0);
/*  64 */     this.h = this.brewingStand.getProperty(1);
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/*  68 */     if (!this.checkReachable) return true; 
/*  69 */     return this.brewingStand.a(entityhuman);
/*     */   }
/*     */   
/*     */   public ItemStack b(EntityHuman entityhuman, int i) {
/*  73 */     ItemStack itemstack = ItemStack.a;
/*  74 */     Slot slot = this.c.get(i);
/*     */     
/*  76 */     if (slot != null && slot.hasItem()) {
/*  77 */       ItemStack itemstack1 = slot.getItem();
/*     */       
/*  79 */       itemstack = itemstack1.cloneItemStack();
/*  80 */       if ((i < 0 || i > 2) && i != 3 && i != 4) {
/*  81 */         if (this.f.isAllowed(itemstack1)) {
/*  82 */           if (!a(itemstack1, 3, 4, false)) {
/*  83 */             return ItemStack.a;
/*     */           }
/*  85 */         } else if (SlotPotionBottle.c_(itemstack) && itemstack.getCount() == 1) {
/*  86 */           if (!a(itemstack1, 0, 3, false)) {
/*  87 */             return ItemStack.a;
/*     */           }
/*  89 */         } else if (a.b_(itemstack)) {
/*  90 */           if (!a(itemstack1, 4, 5, false)) {
/*  91 */             return ItemStack.a;
/*     */           }
/*  93 */         } else if (i >= 5 && i < 32) {
/*  94 */           if (!a(itemstack1, 32, 41, false)) {
/*  95 */             return ItemStack.a;
/*     */           }
/*  97 */         } else if (i >= 32 && i < 41) {
/*  98 */           if (!a(itemstack1, 5, 32, false)) {
/*  99 */             return ItemStack.a;
/*     */           }
/* 101 */         } else if (!a(itemstack1, 5, 41, false)) {
/* 102 */           return ItemStack.a;
/*     */         } 
/*     */       } else {
/* 105 */         if (!a(itemstack1, 5, 41, true)) {
/* 106 */           return ItemStack.a;
/*     */         }
/*     */         
/* 109 */         slot.a(itemstack1, itemstack);
/*     */       } 
/*     */       
/* 112 */       if (itemstack1.isEmpty()) {
/* 113 */         slot.set(ItemStack.a);
/*     */       } else {
/* 115 */         slot.f();
/*     */       } 
/*     */       
/* 118 */       if (itemstack1.getCount() == itemstack.getCount()) {
/* 119 */         return ItemStack.a;
/*     */       }
/*     */       
/* 122 */       slot.a(entityhuman, itemstack1);
/*     */     } 
/*     */     
/* 125 */     return itemstack;
/*     */   }
/*     */   
/*     */   static class a
/*     */     extends Slot {
/*     */     public a(IInventory iinventory, int i, int j, int k) {
/* 131 */       super(iinventory, i, j, k);
/*     */     }
/*     */     
/*     */     public boolean isAllowed(ItemStack itemstack) {
/* 135 */       return b_(itemstack);
/*     */     }
/*     */     
/*     */     public static boolean b_(ItemStack itemstack) {
/* 139 */       return (itemstack.getItem() == Items.BLAZE_POWDER);
/*     */     }
/*     */     
/*     */     public int getMaxStackSize() {
/* 143 */       return 64;
/*     */     }
/*     */   }
/*     */   
/*     */   static class SlotBrewing
/*     */     extends Slot {
/*     */     public SlotBrewing(IInventory iinventory, int i, int j, int k) {
/* 150 */       super(iinventory, i, j, k);
/*     */     }
/*     */     
/*     */     public boolean isAllowed(ItemStack itemstack) {
/* 154 */       return PotionBrewer.a(itemstack);
/*     */     }
/*     */     
/*     */     public int getMaxStackSize() {
/* 158 */       return 64;
/*     */     }
/*     */   }
/*     */   
/*     */   static class SlotPotionBottle
/*     */     extends Slot {
/*     */     public SlotPotionBottle(IInventory iinventory, int i, int j, int k) {
/* 165 */       super(iinventory, i, j, k);
/*     */     }
/*     */     
/*     */     public boolean isAllowed(ItemStack itemstack) {
/* 169 */       return c_(itemstack);
/*     */     }
/*     */     
/*     */     public int getMaxStackSize() {
/* 173 */       return 1;
/*     */     }
/*     */     
/*     */     public ItemStack a(EntityHuman entityhuman, ItemStack itemstack) {
/* 177 */       PotionRegistry potionregistry = PotionUtil.d(itemstack);
/*     */       
/* 179 */       if (entityhuman instanceof EntityPlayer) {
/* 180 */         CriterionTriggers.j.a((EntityPlayer)entityhuman, potionregistry);
/*     */       }
/*     */       
/* 183 */       super.a(entityhuman, itemstack);
/* 184 */       return itemstack;
/*     */     }
/*     */     
/*     */     public static boolean c_(ItemStack itemstack) {
/* 188 */       Item item = itemstack.getItem();
/*     */       
/* 190 */       return !(item != Items.POTION && item != Items.SPLASH_POTION && item != Items.LINGERING_POTION && item != Items.GLASS_BOTTLE);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/* 197 */     if (this.bukkitEntity != null) {
/* 198 */       return this.bukkitEntity;
/*     */     }
/*     */     
/* 201 */     CraftInventoryBrewer inventory = new CraftInventoryBrewer(this.brewingStand);
/* 202 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), (Inventory)inventory, this);
/* 203 */     return this.bukkitEntity;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ContainerBrewingStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */