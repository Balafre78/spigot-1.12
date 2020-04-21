/*    */ package net.minecraft.server.v1_12_R1;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*    */ import org.bukkit.entity.Hanging;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.hanging.HangingPlaceEvent;
/*    */ 
/*    */ public class ItemLeash extends Item {
/*    */   public ItemLeash() {
/* 11 */     b(CreativeModeTab.i);
/*    */   }
/*    */   
/*    */   public EnumInteractionResult a(EntityHuman entityhuman, World world, BlockPosition blockposition, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/* 15 */     Block block = world.getType(blockposition).getBlock();
/*    */     
/* 17 */     if (!(block instanceof BlockFence)) {
/* 18 */       return EnumInteractionResult.PASS;
/*    */     }
/* 20 */     if (!world.isClientSide) {
/* 21 */       a(entityhuman, world, blockposition);
/*    */     }
/*    */     
/* 24 */     return EnumInteractionResult.SUCCESS;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean a(EntityHuman entityhuman, World world, BlockPosition blockposition) {
/* 29 */     EntityLeash entityleash = EntityLeash.b(world, blockposition);
/* 30 */     boolean flag = false;
/*    */     
/* 32 */     int i = blockposition.getX();
/* 33 */     int j = blockposition.getY();
/* 34 */     int k = blockposition.getZ();
/* 35 */     List<EntityInsentient> list = world.a(EntityInsentient.class, new AxisAlignedBB(i - 7.0D, j - 7.0D, k - 7.0D, i + 7.0D, j + 7.0D, k + 7.0D));
/* 36 */     Iterator<EntityInsentient> iterator = list.iterator();
/*    */     
/* 38 */     while (iterator.hasNext()) {
/* 39 */       EntityInsentient entityinsentient = iterator.next();
/*    */       
/* 41 */       if (entityinsentient.isLeashed() && entityinsentient.getLeashHolder() == entityhuman) {
/* 42 */         if (entityleash == null) {
/* 43 */           entityleash = EntityLeash.a(world, blockposition);
/*    */ 
/*    */           
/* 46 */           HangingPlaceEvent event = new HangingPlaceEvent((Hanging)entityleash.getBukkitEntity(), (entityhuman != null) ? (Player)entityhuman.getBukkitEntity() : null, world.getWorld().getBlockAt(i, j, k), BlockFace.SELF);
/* 47 */           world.getServer().getPluginManager().callEvent((Event)event);
/*    */           
/* 49 */           if (event.isCancelled()) {
/* 50 */             entityleash.die();
/* 51 */             return false;
/*    */           } 
/*    */         } 
/*    */ 
/*    */ 
/*    */         
/* 57 */         if (CraftEventFactory.callPlayerLeashEntityEvent(entityinsentient, entityleash, entityhuman).isCancelled()) {
/*    */           continue;
/*    */         }
/*    */ 
/*    */         
/* 62 */         entityinsentient.setLeashHolder(entityleash, true);
/* 63 */         flag = true;
/*    */       } 
/*    */     } 
/*    */     
/* 67 */     return flag;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemLeash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */