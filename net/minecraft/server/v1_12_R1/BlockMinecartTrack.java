/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockMinecartTrack
/*     */   extends BlockMinecartTrackAbstract
/*     */ {
/*  11 */   public static final BlockStateEnum<BlockMinecartTrackAbstract.EnumTrackPosition> SHAPE = BlockStateEnum.of("shape", BlockMinecartTrackAbstract.EnumTrackPosition.class);
/*     */   
/*     */   protected BlockMinecartTrack() {
/*  14 */     super(false);
/*  15 */     w(this.blockStateList.getBlockData().set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition, Block paramBlock) {
/*  20 */     if (paramBlock.getBlockData().m() && (
/*  21 */       new BlockMinecartTrackAbstract.MinecartTrackLogic(this, paramWorld, paramBlockPosition, paramIBlockData)).b() == 3) {
/*  22 */       a(paramWorld, paramBlockPosition, paramIBlockData, false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState<BlockMinecartTrackAbstract.EnumTrackPosition> g() {
/*  29 */     return SHAPE;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/*  34 */     return getBlockData()
/*  35 */       .set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.a(paramInt));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  40 */     return ((BlockMinecartTrackAbstract.EnumTrackPosition)paramIBlockData.<BlockMinecartTrackAbstract.EnumTrackPosition>get(SHAPE)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/*  45 */     switch (null.b[paramEnumBlockRotation.ordinal()]) {
/*     */       case 1:
/*  47 */         switch (null.a[((BlockMinecartTrackAbstract.EnumTrackPosition)paramIBlockData.get((IBlockState)SHAPE)).ordinal()]) {
/*     */           case 1:
/*  49 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_WEST);
/*     */           case 2:
/*  51 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_EAST);
/*     */           case 3:
/*  53 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_SOUTH);
/*     */           case 4:
/*  55 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_NORTH);
/*     */           case 5:
/*  57 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST);
/*     */           case 6:
/*  59 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST);
/*     */           case 7:
/*  61 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST);
/*     */           case 8:
/*  63 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST);
/*     */         } 
/*     */       case 2:
/*  66 */         switch (null.a[((BlockMinecartTrackAbstract.EnumTrackPosition)paramIBlockData.get((IBlockState)SHAPE)).ordinal()]) {
/*     */           case 9:
/*  68 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.EAST_WEST);
/*     */           case 10:
/*  70 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH);
/*     */           case 1:
/*  72 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_NORTH);
/*     */           case 2:
/*  74 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_SOUTH);
/*     */           case 3:
/*  76 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_WEST);
/*     */           case 4:
/*  78 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_EAST);
/*     */           case 5:
/*  80 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST);
/*     */           case 6:
/*  82 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST);
/*     */           case 7:
/*  84 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST);
/*     */           case 8:
/*  86 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST);
/*     */         } 
/*     */       case 3:
/*  89 */         switch (null.a[((BlockMinecartTrackAbstract.EnumTrackPosition)paramIBlockData.get((IBlockState)SHAPE)).ordinal()]) {
/*     */           case 9:
/*  91 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.EAST_WEST);
/*     */           case 10:
/*  93 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH);
/*     */           case 1:
/*  95 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_SOUTH);
/*     */           case 2:
/*  97 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_NORTH);
/*     */           case 3:
/*  99 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_EAST);
/*     */           case 4:
/* 101 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_WEST);
/*     */           case 5:
/* 103 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST);
/*     */           case 6:
/* 105 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST);
/*     */           case 7:
/* 107 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST);
/*     */           case 8:
/* 109 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST);
/*     */         }  break;
/*     */     } 
/* 112 */     return paramIBlockData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/* 118 */     BlockMinecartTrackAbstract.EnumTrackPosition enumTrackPosition = paramIBlockData.<BlockMinecartTrackAbstract.EnumTrackPosition>get(SHAPE);
/* 119 */     switch (null.c[paramEnumBlockMirror.ordinal()]) {
/*     */       case 1:
/* 121 */         switch (null.a[enumTrackPosition.ordinal()]) {
/*     */           case 3:
/* 123 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_SOUTH);
/*     */           case 4:
/* 125 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_NORTH);
/*     */           case 5:
/* 127 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST);
/*     */           case 6:
/* 129 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST);
/*     */           case 7:
/* 131 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST);
/*     */           case 8:
/* 133 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST);
/*     */         } 
/*     */         
/*     */         break;
/*     */       
/*     */       case 2:
/* 139 */         switch (null.a[enumTrackPosition.ordinal()]) {
/*     */           case 1:
/* 141 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_WEST);
/*     */           case 2:
/* 143 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_EAST);
/*     */           case 5:
/* 145 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST);
/*     */           case 6:
/* 147 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST);
/*     */           case 7:
/* 149 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST);
/*     */           case 8:
/* 151 */             return paramIBlockData.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST);
/*     */         } 
/*     */ 
/*     */         
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 159 */     return super.a(paramIBlockData, paramEnumBlockMirror);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 164 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { SHAPE });
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockMinecartTrack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */