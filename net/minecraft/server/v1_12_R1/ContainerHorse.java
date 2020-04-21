/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerHorse
/*     */   extends Container
/*     */ {
/*     */   private final IInventory a;
/*     */   private final EntityHorseAbstract f;
/*     */   CraftInventoryView bukkitEntity;
/*     */   PlayerInventory player;
/*     */   
/*     */   public InventoryView getBukkitView() {
/*  19 */     if (this.bukkitEntity != null) {
/*  20 */       return (InventoryView)this.bukkitEntity;
/*     */     }
/*     */     
/*  23 */     return (InventoryView)(this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), this.a.getOwner().getInventory(), this));
/*     */   }
/*     */   
/*     */   public ContainerHorse(IInventory iinventory, IInventory iinventory1, final EntityHorseAbstract entityhorseabstract, EntityHuman entityhuman) {
/*  27 */     this.player = (PlayerInventory)iinventory;
/*     */     
/*  29 */     this.a = iinventory1;
/*  30 */     this.f = entityhorseabstract;
/*     */ 
/*     */     
/*  33 */     iinventory1.startOpen(entityhuman);
/*     */ 
/*     */     
/*  36 */     a(new Slot(iinventory1, 0, 8, 18) {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  38 */             return (itemstack.getItem() == Items.SADDLE && !hasItem() && entityhorseabstract.dF());
/*     */           }
/*     */         });
/*  41 */     a(new Slot(iinventory1, 1, 8, 36) {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  43 */             return entityhorseabstract.f(itemstack);
/*     */           }
/*     */           
/*     */           public int getMaxStackSize() {
/*  47 */             return 1;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/*  53 */     if (entityhorseabstract instanceof EntityHorseChestedAbstract && ((EntityHorseChestedAbstract)entityhorseabstract).isCarryingChest()) {
/*  54 */       for (int j = 0; j < 3; j++) {
/*  55 */         for (int k = 0; k < ((EntityHorseChestedAbstract)entityhorseabstract).dt(); k++) {
/*  56 */           a(new Slot(iinventory1, 2 + k + j * ((EntityHorseChestedAbstract)entityhorseabstract).dt(), 80 + k * 18, 18 + j * 18));
/*     */         }
/*     */       } 
/*     */     }
/*     */     int i;
/*  61 */     for (i = 0; i < 3; i++) {
/*  62 */       for (int j = 0; j < 9; j++) {
/*  63 */         a(new Slot(iinventory, j + i * 9 + 9, 8 + j * 18, 102 + i * 18 + -18));
/*     */       }
/*     */     } 
/*     */     
/*  67 */     for (i = 0; i < 9; i++) {
/*  68 */       a(new Slot(iinventory, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/*  74 */     return (this.a.a(entityhuman) && this.f.isAlive() && this.f.g(entityhuman) < 8.0F);
/*     */   }
/*     */   
/*     */   public ItemStack b(EntityHuman entityhuman, int i) {
/*  78 */     ItemStack itemstack = ItemStack.a;
/*  79 */     Slot slot = this.c.get(i);
/*     */     
/*  81 */     if (slot != null && slot.hasItem()) {
/*  82 */       ItemStack itemstack1 = slot.getItem();
/*     */       
/*  84 */       itemstack = itemstack1.cloneItemStack();
/*  85 */       if (i < this.a.getSize()) {
/*  86 */         if (!a(itemstack1, this.a.getSize(), this.c.size(), true)) {
/*  87 */           return ItemStack.a;
/*     */         }
/*  89 */       } else if (getSlot(1).isAllowed(itemstack1) && !getSlot(1).hasItem()) {
/*  90 */         if (!a(itemstack1, 1, 2, false)) {
/*  91 */           return ItemStack.a;
/*     */         }
/*  93 */       } else if (getSlot(0).isAllowed(itemstack1)) {
/*  94 */         if (!a(itemstack1, 0, 1, false)) {
/*  95 */           return ItemStack.a;
/*     */         }
/*  97 */       } else if (this.a.getSize() <= 2 || !a(itemstack1, 2, this.a.getSize(), false)) {
/*  98 */         return ItemStack.a;
/*     */       } 
/*     */       
/* 101 */       if (itemstack1.isEmpty()) {
/* 102 */         slot.set(ItemStack.a);
/*     */       } else {
/* 104 */         slot.f();
/*     */       } 
/*     */     } 
/*     */     
/* 108 */     return itemstack;
/*     */   }
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/* 112 */     super.b(entityhuman);
/* 113 */     this.a.closeContainer(entityhuman);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ContainerHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */