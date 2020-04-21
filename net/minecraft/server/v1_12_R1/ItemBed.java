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
/*    */ 
/*    */ 
/*    */ public class ItemBed
/*    */   extends Item
/*    */ {
/*    */   public ItemBed() {
/* 24 */     b(CreativeModeTab.c);
/*    */     
/* 26 */     setMaxDurability(0);
/* 27 */     a(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(EntityHuman paramEntityHuman, World paramWorld, BlockPosition paramBlockPosition, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 32 */     if (paramWorld.isClientSide) {
/* 33 */       return EnumInteractionResult.SUCCESS;
/*    */     }
/*    */     
/* 36 */     if (paramEnumDirection != EnumDirection.UP) {
/* 37 */       return EnumInteractionResult.FAIL;
/*    */     }
/*    */     
/* 40 */     IBlockData iBlockData1 = paramWorld.getType(paramBlockPosition);
/* 41 */     Block block = iBlockData1.getBlock();
/* 42 */     boolean bool1 = block.a(paramWorld, paramBlockPosition);
/*    */ 
/*    */     
/* 45 */     if (!bool1) {
/* 46 */       paramBlockPosition = paramBlockPosition.up();
/*    */     }
/*    */     
/* 49 */     int i = MathHelper.floor((paramEntityHuman.yaw * 4.0F / 360.0F) + 0.5D) & 0x3;
/* 50 */     EnumDirection enumDirection = EnumDirection.fromType2(i);
/* 51 */     BlockPosition blockPosition = paramBlockPosition.shift(enumDirection);
/* 52 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/*    */     
/* 54 */     if (!paramEntityHuman.a(paramBlockPosition, paramEnumDirection, itemStack) || !paramEntityHuman.a(blockPosition, paramEnumDirection, itemStack)) {
/* 55 */       return EnumInteractionResult.FAIL;
/*    */     }
/*    */     
/* 58 */     IBlockData iBlockData2 = paramWorld.getType(blockPosition);
/* 59 */     boolean bool2 = iBlockData2.getBlock().a(paramWorld, blockPosition);
/* 60 */     boolean bool3 = (bool1 || paramWorld.isEmpty(paramBlockPosition)) ? true : false;
/* 61 */     boolean bool4 = (bool2 || paramWorld.isEmpty(blockPosition)) ? true : false;
/*    */     
/* 63 */     if (!bool3 || !bool4 || !paramWorld.getType(paramBlockPosition.down()).q() || !paramWorld.getType(blockPosition.down()).q()) {
/* 64 */       return EnumInteractionResult.FAIL;
/*    */     }
/*    */     
/* 67 */     IBlockData iBlockData3 = Blocks.BED.getBlockData().<Comparable, Boolean>set(BlockBed.OCCUPIED, Boolean.valueOf(false)).<Comparable, EnumDirection>set(BlockBed.FACING, enumDirection).set(BlockBed.PART, BlockBed.EnumBedPart.FOOT);
/* 68 */     paramWorld.setTypeAndData(paramBlockPosition, iBlockData3, 10);
/* 69 */     paramWorld.setTypeAndData(blockPosition, iBlockData3.set(BlockBed.PART, BlockBed.EnumBedPart.HEAD), 10);
/*    */     
/* 71 */     SoundEffectType soundEffectType = iBlockData3.getBlock().getStepSound();
/* 72 */     paramWorld.a(null, paramBlockPosition, soundEffectType.e(), SoundCategory.BLOCKS, (soundEffectType.a() + 1.0F) / 2.0F, soundEffectType.b() * 0.8F);
/*    */     
/* 74 */     TileEntity tileEntity1 = paramWorld.getTileEntity(blockPosition);
/* 75 */     if (tileEntity1 instanceof TileEntityBed) {
/* 76 */       ((TileEntityBed)tileEntity1).a(itemStack);
/*    */     }
/*    */     
/* 79 */     TileEntity tileEntity2 = paramWorld.getTileEntity(paramBlockPosition);
/* 80 */     if (tileEntity2 instanceof TileEntityBed) {
/* 81 */       ((TileEntityBed)tileEntity2).a(itemStack);
/*    */     }
/*    */     
/* 84 */     paramWorld.update(paramBlockPosition, block, false);
/* 85 */     paramWorld.update(blockPosition, iBlockData2.getBlock(), false);
/*    */     
/* 87 */     if (paramEntityHuman instanceof EntityPlayer) {
/* 88 */       CriterionTriggers.x.a((EntityPlayer)paramEntityHuman, paramBlockPosition, itemStack);
/*    */     }
/* 90 */     itemStack.subtract(1);
/* 91 */     return EnumInteractionResult.SUCCESS;
/*    */   }
/*    */ 
/*    */   
/*    */   public String a(ItemStack paramItemStack) {
/* 96 */     return getName() + "." + EnumColor.fromColorIndex(paramItemStack.getData()).d();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */