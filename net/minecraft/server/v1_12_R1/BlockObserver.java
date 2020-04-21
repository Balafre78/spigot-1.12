/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
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
/*     */ public class BlockObserver
/*     */   extends BlockDirectional
/*     */ {
/*  18 */   public static final BlockStateBoolean a = BlockStateBoolean.of("powered");
/*     */   
/*     */   public BlockObserver() {
/*  21 */     super(Material.STONE);
/*     */     
/*  23 */     w(this.blockStateList.getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.SOUTH).set(a, Boolean.valueOf(false)));
/*  24 */     a(CreativeModeTab.d);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/*  29 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING, a });
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/*  34 */     return paramIBlockData.set(FACING, paramEnumBlockRotation.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/*  39 */     return paramIBlockData.a(paramEnumBlockMirror.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, Random paramRandom) {
/*  44 */     if (((Boolean)paramIBlockData.<Boolean>get(a)).booleanValue()) {
/*  45 */       paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData.set(a, Boolean.valueOf(false)), 2);
/*     */     } else {
/*  47 */       paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData.set(a, Boolean.valueOf(true)), 2);
/*  48 */       paramWorld.a(paramBlockPosition, this, 2);
/*     */     } 
/*  50 */     e(paramWorld, paramBlockPosition, paramIBlockData);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {}
/*     */ 
/*     */   
/*     */   public void b(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/*  59 */     if (!paramWorld.isClientSide && paramBlockPosition1.shift(paramIBlockData.<EnumDirection>get(FACING)).equals(paramBlockPosition2)) {
/*  60 */       d(paramIBlockData, paramWorld, paramBlockPosition1);
/*     */     }
/*     */   }
/*     */   
/*     */   private void d(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition) {
/*  65 */     if (((Boolean)paramIBlockData.<Boolean>get(a)).booleanValue()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  70 */     if (!paramWorld.b(paramBlockPosition, this)) {
/*  71 */       paramWorld.a(paramBlockPosition, this, 2);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void e(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  76 */     EnumDirection enumDirection = paramIBlockData.<EnumDirection>get(FACING);
/*  77 */     BlockPosition blockPosition = paramBlockPosition.shift(enumDirection.opposite());
/*     */     
/*  79 */     paramWorld.a(blockPosition, this, paramBlockPosition);
/*  80 */     paramWorld.a(blockPosition, this, enumDirection);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPowerSource(IBlockData paramIBlockData) {
/*  85 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/*  90 */     return paramIBlockData.a(paramIBlockAccess, paramBlockPosition, paramEnumDirection);
/*     */   }
/*     */ 
/*     */   
/*     */   public int b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/*  95 */     if (((Boolean)paramIBlockData.<Boolean>get(a)).booleanValue() && paramIBlockData.get(FACING) == paramEnumDirection) {
/*  96 */       return 15;
/*     */     }
/*  98 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 103 */     if (!paramWorld.isClientSide) {
/* 104 */       if (((Boolean)paramIBlockData.<Boolean>get(a)).booleanValue()) {
/* 105 */         b(paramWorld, paramBlockPosition, paramIBlockData, paramWorld.random);
/*     */       }
/* 107 */       d(paramIBlockData, paramWorld, paramBlockPosition);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 113 */     if (((Boolean)paramIBlockData.<Boolean>get(a)).booleanValue() && paramWorld.b(paramBlockPosition, this))
/*     */     {
/* 115 */       e(paramWorld, paramBlockPosition, paramIBlockData.set(a, Boolean.valueOf(false)));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, EntityLiving paramEntityLiving) {
/* 121 */     return getBlockData().set(FACING, EnumDirection.a(paramBlockPosition, paramEntityLiving).opposite());
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 126 */     int i = 0;
/*     */     
/* 128 */     i |= ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).a();
/*     */     
/* 130 */     if (((Boolean)paramIBlockData.<Boolean>get(a)).booleanValue()) {
/* 131 */       i |= 0x8;
/*     */     }
/*     */     
/* 134 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 139 */     return getBlockData().set(FACING, EnumDirection.fromType1(paramInt & 0x7));
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */