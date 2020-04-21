/*    */ package net.minecraft.server.v1_12_R1;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*    */ 
/*    */ public class ItemFireball extends Item {
/*    */   public ItemFireball() {
/*  6 */     b(CreativeModeTab.f);
/*    */   }
/*    */   
/*    */   public EnumInteractionResult a(EntityHuman entityhuman, World world, BlockPosition blockposition, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/* 10 */     if (world.isClientSide) {
/* 11 */       return EnumInteractionResult.SUCCESS;
/*    */     }
/* 13 */     blockposition = blockposition.shift(enumdirection);
/* 14 */     ItemStack itemstack = entityhuman.b(enumhand);
/*    */     
/* 16 */     if (!entityhuman.a(blockposition, enumdirection, itemstack)) {
/* 17 */       return EnumInteractionResult.FAIL;
/*    */     }
/* 19 */     if (world.getType(blockposition).getMaterial() == Material.AIR) {
/*    */       
/* 21 */       if (CraftEventFactory.callBlockIgniteEvent(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), BlockIgniteEvent.IgniteCause.FIREBALL, entityhuman).isCancelled()) {
/* 22 */         if (!entityhuman.abilities.canInstantlyBuild) {
/* 23 */           itemstack.subtract(1);
/*    */         }
/* 25 */         return EnumInteractionResult.PASS;
/*    */       } 
/*    */       
/* 28 */       world.a(null, blockposition, SoundEffects.bD, SoundCategory.BLOCKS, 1.0F, (j.nextFloat() - j.nextFloat()) * 0.2F + 1.0F);
/* 29 */       world.setTypeUpdate(blockposition, Blocks.FIRE.getBlockData());
/*    */     } 
/*    */     
/* 32 */     if (!entityhuman.abilities.canInstantlyBuild) {
/* 33 */       itemstack.subtract(1);
/*    */     }
/*    */     
/* 36 */     return EnumInteractionResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */