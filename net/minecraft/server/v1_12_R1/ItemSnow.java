/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemSnow
/*    */   extends ItemBlock
/*    */ {
/*    */   public ItemSnow(Block paramBlock) {
/* 20 */     super(paramBlock);
/*    */     
/* 22 */     setMaxDurability(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(EntityHuman paramEntityHuman, World paramWorld, BlockPosition paramBlockPosition, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 27 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/* 28 */     if (itemStack.isEmpty() || !paramEntityHuman.a(paramBlockPosition, paramEnumDirection, itemStack)) {
/* 29 */       return EnumInteractionResult.FAIL;
/*    */     }
/*    */     
/* 32 */     IBlockData iBlockData = paramWorld.getType(paramBlockPosition);
/* 33 */     Block block = iBlockData.getBlock();
/* 34 */     BlockPosition blockPosition = paramBlockPosition;
/*    */     
/* 36 */     if ((paramEnumDirection != EnumDirection.UP || block != this.a) && !block.a(paramWorld, paramBlockPosition)) {
/* 37 */       blockPosition = paramBlockPosition.shift(paramEnumDirection);
/* 38 */       iBlockData = paramWorld.getType(blockPosition);
/* 39 */       block = iBlockData.getBlock();
/*    */     } 
/*    */     
/* 42 */     if (block == this.a) {
/* 43 */       int i = ((Integer)iBlockData.<Integer>get(BlockSnow.LAYERS)).intValue();
/*    */       
/* 45 */       if (i < 8) {
/* 46 */         IBlockData iBlockData1 = iBlockData.set(BlockSnow.LAYERS, Integer.valueOf(i + 1));
/* 47 */         AxisAlignedBB axisAlignedBB = iBlockData1.d(paramWorld, blockPosition);
/* 48 */         if (axisAlignedBB != Block.k && paramWorld.b(axisAlignedBB.a(blockPosition)) && paramWorld.setTypeAndData(blockPosition, iBlockData1, 10)) {
/* 49 */           SoundEffectType soundEffectType = this.a.getStepSound();
/* 50 */           paramWorld.a(paramEntityHuman, blockPosition, soundEffectType.e(), SoundCategory.BLOCKS, (soundEffectType.a() + 1.0F) / 2.0F, soundEffectType.b() * 0.8F);
/* 51 */           if (paramEntityHuman instanceof EntityPlayer) {
/* 52 */             CriterionTriggers.x.a((EntityPlayer)paramEntityHuman, paramBlockPosition, itemStack);
/*    */           }
/* 54 */           itemStack.subtract(1);
/* 55 */           return EnumInteractionResult.SUCCESS;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 60 */     return super.a(paramEntityHuman, paramWorld, paramBlockPosition, paramEnumHand, paramEnumDirection, paramFloat1, paramFloat2, paramFloat3);
/*    */   }
/*    */ 
/*    */   
/*    */   public int filterData(int paramInt) {
/* 65 */     return paramInt;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemSnow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */