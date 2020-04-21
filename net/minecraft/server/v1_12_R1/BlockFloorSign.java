/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockFloorSign
/*    */   extends BlockSign
/*    */ {
/* 10 */   public static final BlockStateInteger ROTATION = BlockStateInteger.of("rotation", 0, 15);
/*    */   
/*    */   public BlockFloorSign() {
/* 13 */     w(this.blockStateList.getBlockData().set(ROTATION, Integer.valueOf(0)));
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/* 18 */     if (!paramWorld.getType(paramBlockPosition1.down()).getMaterial().isBuildable()) {
/* 19 */       b(paramWorld, paramBlockPosition1, paramIBlockData, 0);
/* 20 */       paramWorld.setAir(paramBlockPosition1);
/*    */     } 
/*    */     
/* 23 */     super.a(paramIBlockData, paramWorld, paramBlockPosition1, paramBlock, paramBlockPosition2);
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData fromLegacyData(int paramInt) {
/* 28 */     return getBlockData()
/* 29 */       .set(ROTATION, Integer.valueOf(paramInt));
/*    */   }
/*    */ 
/*    */   
/*    */   public int toLegacyData(IBlockData paramIBlockData) {
/* 34 */     return ((Integer)paramIBlockData.<Integer>get(ROTATION)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/* 39 */     return paramIBlockData.set(ROTATION, Integer.valueOf(paramEnumBlockRotation.a(((Integer)paramIBlockData.<Integer>get(ROTATION)).intValue(), 16)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/* 44 */     return paramIBlockData.set(ROTATION, Integer.valueOf(paramEnumBlockMirror.a(((Integer)paramIBlockData.<Integer>get(ROTATION)).intValue(), 16)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockStateList getStateList() {
/* 49 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { ROTATION });
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockFloorSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */