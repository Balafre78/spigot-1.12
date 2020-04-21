/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventory;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryView;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ 
/*    */ public class ContainerDispenser
/*    */   extends Container {
/*    */   public final IInventory items;
/* 12 */   private CraftInventoryView bukkitEntity = null;
/*    */   
/*    */   private PlayerInventory player;
/*    */   
/*    */   public ContainerDispenser(IInventory iinventory, IInventory iinventory1) {
/* 17 */     this.items = iinventory1;
/*    */ 
/*    */     
/* 20 */     this.player = (PlayerInventory)iinventory;
/*    */ 
/*    */     
/*    */     int i;
/*    */ 
/*    */     
/* 26 */     for (i = 0; i < 3; i++) {
/* 27 */       for (int j = 0; j < 3; j++) {
/* 28 */         a(new Slot(iinventory1, j + i * 3, 62 + j * 18, 17 + i * 18));
/*    */       }
/*    */     } 
/*    */     
/* 32 */     for (i = 0; i < 3; i++) {
/* 33 */       for (int j = 0; j < 9; j++) {
/* 34 */         a(new Slot(iinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*    */       }
/*    */     } 
/*    */     
/* 38 */     for (i = 0; i < 9; i++) {
/* 39 */       a(new Slot(iinventory, i, 8 + i * 18, 142));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(EntityHuman entityhuman) {
/* 45 */     if (!this.checkReachable) return true; 
/* 46 */     return this.items.a(entityhuman);
/*    */   }
/*    */   
/*    */   public ItemStack b(EntityHuman entityhuman, int i) {
/* 50 */     ItemStack itemstack = ItemStack.a;
/* 51 */     Slot slot = this.c.get(i);
/*    */     
/* 53 */     if (slot != null && slot.hasItem()) {
/* 54 */       ItemStack itemstack1 = slot.getItem();
/*    */       
/* 56 */       itemstack = itemstack1.cloneItemStack();
/* 57 */       if (i < 9) {
/* 58 */         if (!a(itemstack1, 9, 45, true)) {
/* 59 */           return ItemStack.a;
/*    */         }
/* 61 */       } else if (!a(itemstack1, 0, 9, false)) {
/* 62 */         return ItemStack.a;
/*    */       } 
/*    */       
/* 65 */       if (itemstack1.isEmpty()) {
/* 66 */         slot.set(ItemStack.a);
/*    */       } else {
/* 68 */         slot.f();
/*    */       } 
/*    */       
/* 71 */       if (itemstack1.getCount() == itemstack.getCount()) {
/* 72 */         return ItemStack.a;
/*    */       }
/*    */       
/* 75 */       slot.a(entityhuman, itemstack1);
/*    */     } 
/*    */     
/* 78 */     return itemstack;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CraftInventoryView getBukkitView() {
/* 84 */     if (this.bukkitEntity != null) {
/* 85 */       return this.bukkitEntity;
/*    */     }
/*    */     
/* 88 */     CraftInventory inventory = new CraftInventory(this.items);
/* 89 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), (Inventory)inventory, this);
/* 90 */     return this.bukkitEntity;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ContainerDispenser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */