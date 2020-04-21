/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockEndRod
/*     */   extends BlockDirectional
/*     */ {
/*  24 */   protected static final AxisAlignedBB a = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
/*  25 */   protected static final AxisAlignedBB b = new AxisAlignedBB(0.375D, 0.375D, 0.0D, 0.625D, 0.625D, 1.0D);
/*  26 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.0D, 0.375D, 0.375D, 1.0D, 0.625D, 0.625D);
/*     */   
/*     */   protected BlockEndRod() {
/*  29 */     super(Material.ORIENTABLE);
/*  30 */     w(this.blockStateList.getBlockData().set(FACING, EnumDirection.UP));
/*  31 */     a(CreativeModeTab.c);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/*  36 */     return paramIBlockData.set(FACING, paramEnumBlockRotation.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/*  41 */     return paramIBlockData.set(FACING, paramEnumBlockMirror.b(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  46 */     switch (null.a[((EnumDirection)paramIBlockData.get(FACING)).k().ordinal()])
/*     */     
/*     */     { default:
/*  49 */         return c;
/*     */       case 2:
/*  51 */         return b;
/*     */       case 3:
/*  53 */         break; }  return a;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(World paramWorld, BlockPosition paramBlockPosition) {
/*  69 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, EntityLiving paramEntityLiving) {
/*  74 */     IBlockData iBlockData = paramWorld.getType(paramBlockPosition.shift(paramEnumDirection.opposite()));
/*  75 */     if (iBlockData.getBlock() == Blocks.END_ROD) {
/*  76 */       EnumDirection enumDirection = iBlockData.<EnumDirection>get(FACING);
/*  77 */       if (enumDirection == paramEnumDirection) {
/*  78 */         return getBlockData().set(FACING, paramEnumDirection.opposite());
/*     */       }
/*     */     } 
/*     */     
/*  82 */     return getBlockData().set(FACING, paramEnumDirection);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 105 */     IBlockData iBlockData = getBlockData();
/* 106 */     iBlockData = iBlockData.set(FACING, EnumDirection.fromType1(paramInt));
/*     */     
/* 108 */     return iBlockData;
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 113 */     return ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 118 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING });
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumPistonReaction h(IBlockData paramIBlockData) {
/* 123 */     return EnumPistonReaction.NORMAL;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 128 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockEndRod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */