/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class ItemReed
/*    */   extends Item {
/*    */   public final Block a;
/*    */   
/*    */   public ItemReed(Block block) {
/*  8 */     this.a = block;
/*    */   }
/*    */   
/*    */   public EnumInteractionResult a(EntityHuman entityhuman, World world, BlockPosition blockposition, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/* 12 */     IBlockData iblockdata = world.getType(blockposition);
/* 13 */     Block block = iblockdata.getBlock();
/*    */     
/* 15 */     if (block == Blocks.SNOW_LAYER && ((Integer)iblockdata.get(BlockSnow.LAYERS)).intValue() < 1) {
/* 16 */       enumdirection = EnumDirection.UP;
/* 17 */     } else if (!block.a(world, blockposition)) {
/* 18 */       blockposition = blockposition.shift(enumdirection);
/*    */     } 
/*    */     
/* 21 */     ItemStack itemstack = entityhuman.b(enumhand);
/*    */     
/* 23 */     if (!itemstack.isEmpty() && entityhuman.a(blockposition, enumdirection, itemstack) && world.a(this.a, blockposition, false, enumdirection, (Entity)null)) {
/* 24 */       IBlockData iblockdata1 = this.a.getPlacedState(world, blockposition, enumdirection, f, f1, f2, 0, entityhuman);
/*    */       
/* 26 */       if (!world.setTypeAndData(blockposition, iblockdata1, 11)) {
/* 27 */         return EnumInteractionResult.FAIL;
/*    */       }
/* 29 */       iblockdata1 = world.getType(blockposition);
/* 30 */       if (iblockdata1.getBlock() == this.a) {
/* 31 */         ItemBlock.a(world, entityhuman, blockposition, itemstack);
/* 32 */         iblockdata1.getBlock().postPlace(world, blockposition, iblockdata1, entityhuman, itemstack);
/* 33 */         if (entityhuman instanceof EntityPlayer) {
/* 34 */           CriterionTriggers.x.a((EntityPlayer)entityhuman, blockposition, itemstack);
/*    */         }
/*    */       } 
/*    */       
/* 38 */       SoundEffectType soundeffecttype = this.a.getStepSound();
/*    */       
/* 40 */       world.a(entityhuman, blockposition, soundeffecttype.e(), SoundCategory.BLOCKS, (soundeffecttype.a() + 1.0F) / 2.0F, soundeffecttype.b() * 0.8F);
/* 41 */       itemstack.subtract(1);
/* 42 */       return EnumInteractionResult.SUCCESS;
/*    */     } 
/*    */     
/* 45 */     return EnumInteractionResult.FAIL;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemReed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */