/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class ItemRedstone
/*    */   extends Item {
/*    */   public ItemRedstone() {
/*  6 */     b(CreativeModeTab.d);
/*    */   }
/*    */   
/*    */   public EnumInteractionResult a(EntityHuman entityhuman, World world, BlockPosition blockposition, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/* 10 */     boolean flag = world.getType(blockposition).getBlock().a(world, blockposition);
/* 11 */     BlockPosition blockposition1 = flag ? blockposition : blockposition.shift(enumdirection);
/* 12 */     ItemStack itemstack = entityhuman.b(enumhand);
/*    */     
/* 14 */     if (!itemstack.isEmpty() && entityhuman.a(blockposition1, enumdirection, itemstack) && world.a(world.getType(blockposition1).getBlock(), blockposition1, false, enumdirection, (Entity)null) && Blocks.REDSTONE_WIRE.canPlace(world, blockposition1)) {
/* 15 */       world.setTypeUpdate(blockposition1, Blocks.REDSTONE_WIRE.getBlockData());
/* 16 */       if (entityhuman instanceof EntityPlayer) {
/* 17 */         CriterionTriggers.x.a((EntityPlayer)entityhuman, blockposition1, itemstack);
/*    */       }
/*    */       
/* 20 */       itemstack.subtract(1);
/* 21 */       return EnumInteractionResult.SUCCESS;
/*    */     } 
/* 23 */     return EnumInteractionResult.FAIL;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemRedstone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */