/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventory;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryView;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ 
/*    */ public class ContainerShulkerBox
/*    */   extends Container
/*    */ {
/*    */   private final IInventory a;
/*    */   private CraftInventoryView bukkitEntity;
/*    */   private PlayerInventory player;
/*    */   
/*    */   public CraftInventoryView getBukkitView() {
/* 17 */     if (this.bukkitEntity != null) {
/* 18 */       return this.bukkitEntity;
/*    */     }
/*    */     
/* 21 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), (Inventory)new CraftInventory(this.a), this);
/* 22 */     return this.bukkitEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   public ContainerShulkerBox(PlayerInventory playerinventory, IInventory iinventory, EntityHuman entityhuman) {
/* 27 */     this.a = iinventory;
/* 28 */     this.player = playerinventory;
/* 29 */     iinventory.startOpen(entityhuman);
/*    */ 
/*    */ 
/*    */     
/*    */     int i;
/*    */ 
/*    */     
/* 36 */     for (i = 0; i < 3; i++) {
/* 37 */       for (int j = 0; j < 9; j++) {
/* 38 */         a(new SlotShulkerBox(iinventory, j + i * 9, 8 + j * 18, 18 + i * 18));
/*    */       }
/*    */     } 
/*    */     
/* 42 */     for (i = 0; i < 3; i++) {
/* 43 */       for (int j = 0; j < 9; j++) {
/* 44 */         a(new Slot(playerinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*    */       }
/*    */     } 
/*    */     
/* 48 */     for (i = 0; i < 9; i++) {
/* 49 */       a(new Slot(playerinventory, i, 8 + i * 18, 142));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(EntityHuman entityhuman) {
/* 55 */     return this.a.a(entityhuman);
/*    */   }
/*    */   
/*    */   public ItemStack b(EntityHuman entityhuman, int i) {
/* 59 */     ItemStack itemstack = ItemStack.a;
/* 60 */     Slot slot = this.c.get(i);
/*    */     
/* 62 */     if (slot != null && slot.hasItem()) {
/* 63 */       ItemStack itemstack1 = slot.getItem();
/*    */       
/* 65 */       itemstack = itemstack1.cloneItemStack();
/* 66 */       if (i < this.a.getSize()) {
/* 67 */         if (!a(itemstack1, this.a.getSize(), this.c.size(), true)) {
/* 68 */           return ItemStack.a;
/*    */         }
/* 70 */       } else if (!a(itemstack1, 0, this.a.getSize(), false)) {
/* 71 */         return ItemStack.a;
/*    */       } 
/*    */       
/* 74 */       if (itemstack1.isEmpty()) {
/* 75 */         slot.set(ItemStack.a);
/*    */       } else {
/* 77 */         slot.f();
/*    */       } 
/*    */     } 
/*    */     
/* 81 */     return itemstack;
/*    */   }
/*    */   
/*    */   public void b(EntityHuman entityhuman) {
/* 85 */     super.b(entityhuman);
/* 86 */     this.a.closeContainer(entityhuman);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ContainerShulkerBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */