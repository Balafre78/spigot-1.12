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
/*    */ public class BlockRotatable
/*    */   extends Block
/*    */ {
/* 16 */   public static final BlockStateEnum<EnumDirection.EnumAxis> AXIS = BlockStateEnum.of("axis", EnumDirection.EnumAxis.class);
/*    */   
/*    */   protected BlockRotatable(Material paramMaterial) {
/* 19 */     super(paramMaterial, paramMaterial.r());
/*    */   }
/*    */   
/*    */   protected BlockRotatable(Material paramMaterial, MaterialMapColor paramMaterialMapColor) {
/* 23 */     super(paramMaterial, paramMaterialMapColor);
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/* 28 */     switch (null.b[paramEnumBlockRotation.ordinal()]) {
/*    */       case 1:
/*    */       case 2:
/* 31 */         switch (null.a[((EnumDirection.EnumAxis)paramIBlockData.get((IBlockState)AXIS)).ordinal()]) {
/*    */           case 1:
/* 33 */             return paramIBlockData.set(AXIS, EnumDirection.EnumAxis.Z);
/*    */           case 2:
/* 35 */             return paramIBlockData.set(AXIS, EnumDirection.EnumAxis.X);
/*    */         } 
/* 37 */         return paramIBlockData;
/*    */     } 
/*    */     
/* 40 */     return paramIBlockData;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockData fromLegacyData(int paramInt) {
/* 46 */     EnumDirection.EnumAxis enumAxis = EnumDirection.EnumAxis.Y;
/* 47 */     int i = paramInt & 0xC;
/* 48 */     if (i == 4) {
/* 49 */       enumAxis = EnumDirection.EnumAxis.X;
/* 50 */     } else if (i == 8) {
/* 51 */       enumAxis = EnumDirection.EnumAxis.Z;
/*    */     } 
/* 53 */     return getBlockData().set(AXIS, enumAxis);
/*    */   }
/*    */ 
/*    */   
/*    */   public int toLegacyData(IBlockData paramIBlockData) {
/* 58 */     int i = 0;
/*    */     
/* 60 */     EnumDirection.EnumAxis enumAxis = paramIBlockData.<EnumDirection.EnumAxis>get(AXIS);
/* 61 */     if (enumAxis == EnumDirection.EnumAxis.X) {
/* 62 */       i |= 0x4;
/* 63 */     } else if (enumAxis == EnumDirection.EnumAxis.Z) {
/* 64 */       i |= 0x8;
/*    */     } 
/*    */     
/* 67 */     return i;
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockStateList getStateList() {
/* 72 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { AXIS });
/*    */   }
/*    */ 
/*    */   
/*    */   protected ItemStack u(IBlockData paramIBlockData) {
/* 77 */     return new ItemStack(Item.getItemOf(this));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, EntityLiving paramEntityLiving) {
/* 82 */     return super.getPlacedState(paramWorld, paramBlockPosition, paramEnumDirection, paramFloat1, paramFloat2, paramFloat3, paramInt, paramEntityLiving).set(AXIS, paramEnumDirection.k());
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockRotatable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */