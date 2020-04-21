/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockWallSign
/*    */   extends BlockSign
/*    */ {
/* 13 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 19 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.0D, 0.28125D, 0.0D, 0.125D, 0.78125D, 1.0D);
/* 20 */   protected static final AxisAlignedBB d = new AxisAlignedBB(0.875D, 0.28125D, 0.0D, 1.0D, 0.78125D, 1.0D);
/* 21 */   protected static final AxisAlignedBB e = new AxisAlignedBB(0.0D, 0.28125D, 0.0D, 1.0D, 0.78125D, 0.125D);
/* 22 */   protected static final AxisAlignedBB f = new AxisAlignedBB(0.0D, 0.28125D, 0.875D, 1.0D, 0.78125D, 1.0D);
/*    */   
/*    */   public BlockWallSign() {
/* 25 */     w(this.blockStateList.getBlockData().set(FACING, EnumDirection.NORTH));
/*    */   }
/*    */ 
/*    */   
/*    */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 30 */     switch (null.a[((EnumDirection)paramIBlockData.get(FACING)).ordinal()])
/*    */     
/*    */     { default:
/* 33 */         return f;
/*    */       case 2:
/* 35 */         return e;
/*    */       case 3:
/* 37 */         return d;
/*    */       case 4:
/* 39 */         break; }  return c;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/* 45 */     EnumDirection enumDirection = paramIBlockData.<EnumDirection>get(FACING);
/*    */     
/* 47 */     if (!paramWorld.getType(paramBlockPosition1.shift(enumDirection.opposite())).getMaterial().isBuildable()) {
/* 48 */       b(paramWorld, paramBlockPosition1, paramIBlockData, 0);
/* 49 */       paramWorld.setAir(paramBlockPosition1);
/*    */     } 
/*    */     
/* 52 */     super.a(paramIBlockData, paramWorld, paramBlockPosition1, paramBlock, paramBlockPosition2);
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData fromLegacyData(int paramInt) {
/* 57 */     EnumDirection enumDirection = EnumDirection.fromType1(paramInt);
/* 58 */     if (enumDirection.k() == EnumDirection.EnumAxis.Y) {
/* 59 */       enumDirection = EnumDirection.NORTH;
/*    */     }
/* 61 */     return getBlockData()
/* 62 */       .set(FACING, enumDirection);
/*    */   }
/*    */ 
/*    */   
/*    */   public int toLegacyData(IBlockData paramIBlockData) {
/* 67 */     return ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).a();
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/* 72 */     return paramIBlockData.set(FACING, paramEnumBlockRotation.a(paramIBlockData.<EnumDirection>get(FACING)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/* 77 */     return paramIBlockData.a(paramEnumBlockMirror.a(paramIBlockData.<EnumDirection>get(FACING)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockStateList getStateList() {
/* 82 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING });
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockWallSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */