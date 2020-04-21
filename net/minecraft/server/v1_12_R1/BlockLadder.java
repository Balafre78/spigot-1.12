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
/*     */ public class BlockLadder
/*     */   extends Block
/*     */ {
/*  18 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*     */   
/*  20 */   protected static final AxisAlignedBB b = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1875D, 1.0D, 1.0D);
/*  21 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.8125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  22 */   protected static final AxisAlignedBB d = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1875D);
/*  23 */   protected static final AxisAlignedBB e = new AxisAlignedBB(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);
/*     */   
/*     */   protected BlockLadder() {
/*  26 */     super(Material.ORIENTABLE);
/*  27 */     w(this.blockStateList.getBlockData().set(FACING, EnumDirection.NORTH));
/*  28 */     a(CreativeModeTab.c);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  33 */     switch (null.a[((EnumDirection)paramIBlockData.get(FACING)).ordinal()]) {
/*     */       case 1:
/*  35 */         return e;
/*     */       case 2:
/*  37 */         return d;
/*     */       case 3:
/*  39 */         return c;
/*     */     } 
/*     */     
/*  42 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/*  48 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/*  53 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/*  58 */     if (a(paramWorld, paramBlockPosition.west(), paramEnumDirection))
/*  59 */       return true; 
/*  60 */     if (a(paramWorld, paramBlockPosition.east(), paramEnumDirection))
/*  61 */       return true; 
/*  62 */     if (a(paramWorld, paramBlockPosition.north(), paramEnumDirection))
/*  63 */       return true; 
/*  64 */     if (a(paramWorld, paramBlockPosition.south(), paramEnumDirection)) {
/*  65 */       return true;
/*     */     }
/*  67 */     return false;
/*     */   }
/*     */   
/*     */   private boolean a(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/*  71 */     IBlockData iBlockData = paramWorld.getType(paramBlockPosition);
/*  72 */     boolean bool = c(iBlockData.getBlock());
/*  73 */     return (!bool && iBlockData.d(paramWorld, paramBlockPosition, paramEnumDirection) == EnumBlockFaceShape.SOLID && !iBlockData.m());
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, EntityLiving paramEntityLiving) {
/*  78 */     if (paramEnumDirection.k().c() && a(paramWorld, paramBlockPosition.shift(paramEnumDirection.opposite()), paramEnumDirection)) {
/*  79 */       return getBlockData().set(FACING, paramEnumDirection);
/*     */     }
/*     */     
/*  82 */     for (EnumDirection enumDirection : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/*  83 */       if (a(paramWorld, paramBlockPosition.shift(enumDirection.opposite()), enumDirection)) {
/*  84 */         return getBlockData().set(FACING, enumDirection);
/*     */       }
/*     */     } 
/*     */     
/*  88 */     return getBlockData();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/*  93 */     EnumDirection enumDirection = paramIBlockData.<EnumDirection>get(FACING);
/*  94 */     if (!a(paramWorld, paramBlockPosition1.shift(enumDirection.opposite()), enumDirection)) {
/*  95 */       b(paramWorld, paramBlockPosition1, paramIBlockData, 0);
/*  96 */       paramWorld.setAir(paramBlockPosition1);
/*     */     } 
/*     */     
/*  99 */     super.a(paramIBlockData, paramWorld, paramBlockPosition1, paramBlock, paramBlockPosition2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 109 */     EnumDirection enumDirection = EnumDirection.fromType1(paramInt);
/* 110 */     if (enumDirection.k() == EnumDirection.EnumAxis.Y) {
/* 111 */       enumDirection = EnumDirection.NORTH;
/*     */     }
/* 113 */     return getBlockData()
/* 114 */       .set(FACING, enumDirection);
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 119 */     return ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/* 124 */     return paramIBlockData.set(FACING, paramEnumBlockRotation.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/* 129 */     return paramIBlockData.a(paramEnumBlockMirror.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 134 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING });
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 139 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockLadder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */