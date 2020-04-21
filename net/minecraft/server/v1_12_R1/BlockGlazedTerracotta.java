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
/*    */ public class BlockGlazedTerracotta
/*    */   extends BlockFacingHorizontal
/*    */ {
/*    */   public BlockGlazedTerracotta(EnumColor paramEnumColor) {
/* 17 */     super(Material.STONE, MaterialMapColor.a(paramEnumColor));
/* 18 */     c(1.4F);
/* 19 */     a(SoundEffectType.d);
/* 20 */     String str = paramEnumColor.d();
/* 21 */     if (str.length() > 1) {
/* 22 */       String str1 = str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
/* 23 */       c("glazedTerracotta" + str1);
/*    */     } 
/* 25 */     a(CreativeModeTab.c);
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockStateList getStateList() {
/* 30 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING });
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/* 35 */     return paramIBlockData.set(FACING, paramEnumBlockRotation.a(paramIBlockData.<EnumDirection>get(FACING)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/* 40 */     return paramIBlockData.a(paramEnumBlockMirror.a(paramIBlockData.<EnumDirection>get(FACING)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, EntityLiving paramEntityLiving) {
/* 45 */     return getBlockData().set(FACING, paramEntityLiving.getDirection().opposite());
/*    */   }
/*    */ 
/*    */   
/*    */   public int toLegacyData(IBlockData paramIBlockData) {
/* 50 */     int i = 0;
/*    */     
/* 52 */     i |= ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).get2DRotationValue();
/*    */     
/* 54 */     return i;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData fromLegacyData(int paramInt) {
/* 59 */     return getBlockData().set(FACING, EnumDirection.fromType2(paramInt));
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumPistonReaction h(IBlockData paramIBlockData) {
/* 64 */     return EnumPistonReaction.PUSH_ONLY;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockGlazedTerracotta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */