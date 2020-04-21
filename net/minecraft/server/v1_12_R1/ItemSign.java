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
/*    */ 
/*    */ 
/*    */ public class ItemSign
/*    */   extends Item
/*    */ {
/*    */   public ItemSign() {
/* 22 */     this.maxStackSize = 16;
/* 23 */     b(CreativeModeTab.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(EntityHuman paramEntityHuman, World paramWorld, BlockPosition paramBlockPosition, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 28 */     IBlockData iBlockData = paramWorld.getType(paramBlockPosition);
/* 29 */     boolean bool = iBlockData.getBlock().a(paramWorld, paramBlockPosition);
/*    */     
/* 31 */     if (paramEnumDirection == EnumDirection.DOWN || (!iBlockData.getMaterial().isBuildable() && !bool) || (bool && paramEnumDirection != EnumDirection.UP)) {
/* 32 */       return EnumInteractionResult.FAIL;
/*    */     }
/*    */     
/* 35 */     paramBlockPosition = paramBlockPosition.shift(paramEnumDirection);
/*    */     
/* 37 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/* 38 */     if (!paramEntityHuman.a(paramBlockPosition, paramEnumDirection, itemStack) || !Blocks.STANDING_SIGN.canPlace(paramWorld, paramBlockPosition)) {
/* 39 */       return EnumInteractionResult.FAIL;
/*    */     }
/*    */     
/* 42 */     if (paramWorld.isClientSide) {
/* 43 */       return EnumInteractionResult.SUCCESS;
/*    */     }
/*    */     
/* 46 */     paramBlockPosition = bool ? paramBlockPosition.down() : paramBlockPosition;
/*    */     
/* 48 */     if (paramEnumDirection == EnumDirection.UP) {
/* 49 */       int i = MathHelper.floor(((paramEntityHuman.yaw + 180.0F) * 16.0F / 360.0F) + 0.5D) & 0xF;
/* 50 */       paramWorld.setTypeAndData(paramBlockPosition, Blocks.STANDING_SIGN.getBlockData().set(BlockFloorSign.ROTATION, Integer.valueOf(i)), 11);
/*    */     } else {
/* 52 */       paramWorld.setTypeAndData(paramBlockPosition, Blocks.WALL_SIGN.getBlockData().set(BlockWallSign.FACING, paramEnumDirection), 11);
/*    */     } 
/*    */     
/* 55 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/* 56 */     if (tileEntity instanceof TileEntitySign && 
/* 57 */       !ItemBlock.a(paramWorld, paramEntityHuman, paramBlockPosition, itemStack)) {
/* 58 */       paramEntityHuman.openSign((TileEntitySign)tileEntity);
/*    */     }
/*    */     
/* 61 */     if (paramEntityHuman instanceof EntityPlayer) {
/* 62 */       CriterionTriggers.x.a((EntityPlayer)paramEntityHuman, paramBlockPosition, itemStack);
/*    */     }
/* 64 */     itemStack.subtract(1);
/* 65 */     return EnumInteractionResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */