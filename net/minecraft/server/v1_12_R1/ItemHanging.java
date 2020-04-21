/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.block.CraftBlock;
/*    */ import org.bukkit.entity.Hanging;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.hanging.HangingPlaceEvent;
/*    */ 
/*    */ public class ItemHanging extends Item {
/*    */   public ItemHanging(Class<? extends EntityHanging> oclass) {
/* 14 */     this.a = oclass;
/* 15 */     b(CreativeModeTab.c);
/*    */   }
/*    */   private final Class<? extends EntityHanging> a;
/*    */   public EnumInteractionResult a(EntityHuman entityhuman, World world, BlockPosition blockposition, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/* 19 */     ItemStack itemstack = entityhuman.b(enumhand);
/* 20 */     BlockPosition blockposition1 = blockposition.shift(enumdirection);
/*    */     
/* 22 */     if (enumdirection != EnumDirection.DOWN && enumdirection != EnumDirection.UP && entityhuman.a(blockposition1, enumdirection, itemstack)) {
/* 23 */       EntityHanging entityhanging = a(world, blockposition1, enumdirection);
/*    */       
/* 25 */       if (entityhanging != null && entityhanging.survives()) {
/* 26 */         if (!world.isClientSide) {
/*    */           
/* 28 */           Player who = (entityhuman == null) ? null : (Player)entityhuman.getBukkitEntity();
/* 29 */           Block blockClicked = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 30 */           BlockFace blockFace = CraftBlock.notchToBlockFace(enumdirection);
/*    */           
/* 32 */           HangingPlaceEvent event = new HangingPlaceEvent((Hanging)entityhanging.getBukkitEntity(), who, blockClicked, blockFace);
/* 33 */           world.getServer().getPluginManager().callEvent((Event)event);
/*    */           
/* 35 */           if (event.isCancelled()) {
/* 36 */             return EnumInteractionResult.FAIL;
/*    */           }
/*    */           
/* 39 */           entityhanging.p();
/* 40 */           world.addEntity(entityhanging);
/*    */         } 
/*    */         
/* 43 */         itemstack.subtract(1);
/*    */       } 
/*    */       
/* 46 */       return EnumInteractionResult.SUCCESS;
/*    */     } 
/* 48 */     return EnumInteractionResult.FAIL;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   private EntityHanging a(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/* 54 */     return (this.a == EntityPainting.class) ? new EntityPainting(world, blockposition, enumdirection) : ((this.a == EntityItemFrame.class) ? new EntityItemFrame(world, blockposition, enumdirection) : null);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemHanging.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */