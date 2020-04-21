/*    */ package net.minecraft.server.v1_12_R1;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.projectiles.CraftBlockProjectileSource;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.block.BlockDispenseEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.projectiles.ProjectileSource;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public abstract class DispenseBehaviorProjectile extends DispenseBehaviorItem {
/*    */   public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
/* 13 */     World world = isourceblock.getWorld();
/* 14 */     IPosition iposition = BlockDispenser.a(isourceblock);
/* 15 */     EnumDirection enumdirection = isourceblock.e().<EnumDirection>get(BlockDispenser.FACING);
/* 16 */     IProjectile iprojectile = a(world, iposition, itemstack);
/*    */ 
/*    */ 
/*    */     
/* 20 */     ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/* 21 */     Block block = world.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 22 */     CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack1);
/*    */     
/* 24 */     BlockDispenseEvent event = new BlockDispenseEvent(block, (ItemStack)craftItem.clone(), new Vector(enumdirection.getAdjacentX(), (enumdirection.getAdjacentY() + 0.1F), enumdirection.getAdjacentZ()));
/* 25 */     if (!BlockDispenser.eventFired) {
/* 26 */       world.getServer().getPluginManager().callEvent((Event)event);
/*    */     }
/*    */     
/* 29 */     if (event.isCancelled()) {
/* 30 */       itemstack.add(1);
/* 31 */       return itemstack;
/*    */     } 
/*    */     
/* 34 */     if (!event.getItem().equals(craftItem)) {
/* 35 */       itemstack.add(1);
/*    */       
/* 37 */       ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 38 */       IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 39 */       if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 40 */         idispensebehavior.a(isourceblock, eventStack);
/* 41 */         return itemstack;
/*    */       } 
/*    */     } 
/*    */     
/* 45 */     iprojectile.shoot(event.getVelocity().getX(), event.getVelocity().getY(), event.getVelocity().getZ(), getPower(), a());
/* 46 */     ((Entity)iprojectile).projectileSource = (ProjectileSource)new CraftBlockProjectileSource(isourceblock.<TileEntityDispenser>getTileEntity());
/*    */     
/* 48 */     world.addEntity((Entity)iprojectile);
/*    */     
/* 50 */     return itemstack;
/*    */   }
/*    */   
/*    */   protected void a(ISourceBlock isourceblock) {
/* 54 */     isourceblock.getWorld().triggerEffect(1002, isourceblock.getBlockPosition(), 0);
/*    */   }
/*    */   
/*    */   protected abstract IProjectile a(World paramWorld, IPosition paramIPosition, ItemStack paramItemStack);
/*    */   
/*    */   protected float a() {
/* 60 */     return 6.0F;
/*    */   }
/*    */   
/*    */   protected float getPower() {
/* 64 */     return 1.1F;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DispenseBehaviorProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */