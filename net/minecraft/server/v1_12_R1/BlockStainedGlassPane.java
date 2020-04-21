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
/*     */ public class BlockStainedGlassPane
/*     */   extends BlockThin
/*     */ {
/*  18 */   public static final BlockStateEnum<EnumColor> COLOR = BlockStateEnum.of("color", EnumColor.class);
/*     */   
/*     */   public BlockStainedGlassPane() {
/*  21 */     super(Material.SHATTERABLE, false);
/*  22 */     w(this.blockStateList.getBlockData().<Comparable, Boolean>set(NORTH, Boolean.valueOf(false)).<Comparable, Boolean>set(EAST, Boolean.valueOf(false)).<Comparable, Boolean>set(SOUTH, Boolean.valueOf(false)).<Comparable, Boolean>set(WEST, Boolean.valueOf(false)).set(COLOR, EnumColor.WHITE));
/*  23 */     a(CreativeModeTab.c);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData paramIBlockData) {
/*  28 */     return ((EnumColor)paramIBlockData.<EnumColor>get(COLOR)).getColorIndex();
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
/*     */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  40 */     return MaterialMapColor.a(paramIBlockData.<EnumColor>get(COLOR));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/*  50 */     return getBlockData()
/*  51 */       .set(COLOR, EnumColor.fromColorIndex(paramInt));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  56 */     return ((EnumColor)paramIBlockData.<EnumColor>get(COLOR)).getColorIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/*  61 */     switch (null.a[paramEnumBlockRotation.ordinal()]) {
/*     */       case 1:
/*  63 */         return paramIBlockData.<Comparable, Comparable>set(NORTH, paramIBlockData.get(SOUTH)).<Comparable, Comparable>set(EAST, paramIBlockData.get(WEST)).<Comparable, Comparable>set(SOUTH, paramIBlockData.get(NORTH)).set(WEST, paramIBlockData.get(EAST));
/*     */       case 2:
/*  65 */         return paramIBlockData.<Comparable, Comparable>set(NORTH, paramIBlockData.get(EAST)).<Comparable, Comparable>set(EAST, paramIBlockData.get(SOUTH)).<Comparable, Comparable>set(SOUTH, paramIBlockData.get(WEST)).set(WEST, paramIBlockData.get(NORTH));
/*     */       case 3:
/*  67 */         return paramIBlockData.<Comparable, Comparable>set(NORTH, paramIBlockData.get(WEST)).<Comparable, Comparable>set(EAST, paramIBlockData.get(NORTH)).<Comparable, Comparable>set(SOUTH, paramIBlockData.get(EAST)).set(WEST, paramIBlockData.get(SOUTH));
/*     */     } 
/*  69 */     return paramIBlockData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/*  75 */     switch (null.b[paramEnumBlockMirror.ordinal()]) {
/*     */       case 1:
/*  77 */         return paramIBlockData.<Comparable, Comparable>set(NORTH, paramIBlockData.get(SOUTH)).set(SOUTH, paramIBlockData.get(NORTH));
/*     */       case 2:
/*  79 */         return paramIBlockData.<Comparable, Comparable>set(EAST, paramIBlockData.get(WEST)).set(WEST, paramIBlockData.get(EAST));
/*     */     } 
/*     */ 
/*     */     
/*  83 */     return super.a(paramIBlockData, paramEnumBlockMirror);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/*  88 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { NORTH, EAST, WEST, SOUTH, COLOR });
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  93 */     if (!paramWorld.isClientSide) {
/*  94 */       BlockBeacon.c(paramWorld, paramBlockPosition);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 100 */     if (!paramWorld.isClientSide)
/* 101 */       BlockBeacon.c(paramWorld, paramBlockPosition); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockStainedGlassPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */