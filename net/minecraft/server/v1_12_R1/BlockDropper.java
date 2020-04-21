/*    */ package net.minecraft.server.v1_12_R1;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryDoubleChest;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.inventory.InventoryMoveItemEvent;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class BlockDropper extends BlockDispenser {
/* 10 */   private final IDispenseBehavior e = new DispenseBehaviorItem();
/*    */ 
/*    */ 
/*    */   
/*    */   protected IDispenseBehavior a(ItemStack itemstack) {
/* 15 */     return this.e;
/*    */   }
/*    */   
/*    */   public TileEntity a(World world, int i) {
/* 19 */     return new TileEntityDropper();
/*    */   }
/*    */   
/*    */   public void dispense(World world, BlockPosition blockposition) {
/* 23 */     SourceBlock sourceblock = new SourceBlock(world, blockposition);
/* 24 */     TileEntityDispenser tileentitydispenser = sourceblock.<TileEntityDispenser>getTileEntity();
/*    */     
/* 26 */     if (tileentitydispenser != null) {
/* 27 */       int i = tileentitydispenser.o();
/*    */       
/* 29 */       if (i < 0) {
/* 30 */         world.triggerEffect(1001, blockposition, 0);
/*    */       } else {
/* 32 */         ItemStack itemstack = tileentitydispenser.getItem(i);
/*    */         
/* 34 */         if (!itemstack.isEmpty()) {
/* 35 */           ItemStack itemstack1; EnumDirection enumdirection = world.getType(blockposition).<EnumDirection>get(FACING);
/* 36 */           BlockPosition blockposition1 = blockposition.shift(enumdirection);
/* 37 */           IInventory iinventory = TileEntityHopper.b(world, blockposition1.getX(), blockposition1.getY(), blockposition1.getZ());
/*    */ 
/*    */           
/* 40 */           if (iinventory == null) {
/* 41 */             itemstack1 = this.e.a(sourceblock, itemstack);
/*    */           } else {
/*    */             Inventory destinationInventory;
/* 44 */             CraftItemStack oitemstack = CraftItemStack.asCraftMirror(itemstack.cloneItemStack().cloneAndSubtract(1));
/*    */ 
/*    */ 
/*    */             
/* 48 */             if (iinventory instanceof InventoryLargeChest) {
/* 49 */               CraftInventoryDoubleChest craftInventoryDoubleChest = new CraftInventoryDoubleChest((InventoryLargeChest)iinventory);
/*    */             } else {
/* 51 */               destinationInventory = iinventory.getOwner().getInventory();
/*    */             } 
/*    */             
/* 54 */             InventoryMoveItemEvent event = new InventoryMoveItemEvent(tileentitydispenser.getOwner().getInventory(), (ItemStack)oitemstack.clone(), destinationInventory, true);
/* 55 */             world.getServer().getPluginManager().callEvent((Event)event);
/* 56 */             if (event.isCancelled()) {
/*    */               return;
/*    */             }
/* 59 */             itemstack1 = TileEntityHopper.addItem(tileentitydispenser, iinventory, CraftItemStack.asNMSCopy(event.getItem()), enumdirection.opposite());
/* 60 */             if (event.getItem().equals(oitemstack) && itemstack1.isEmpty()) {
/*    */               
/* 62 */               itemstack1 = itemstack.cloneItemStack();
/* 63 */               itemstack1.subtract(1);
/*    */             } else {
/* 65 */               itemstack1 = itemstack.cloneItemStack();
/*    */             } 
/*    */           } 
/*    */           
/* 69 */           tileentitydispenser.setItem(i, itemstack1);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockDropper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */