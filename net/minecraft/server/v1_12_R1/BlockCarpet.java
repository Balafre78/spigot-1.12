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
/*     */ public class BlockCarpet
/*     */   extends Block
/*     */ {
/*  20 */   public static final BlockStateEnum<EnumColor> COLOR = BlockStateEnum.of("color", EnumColor.class);
/*  21 */   protected static final AxisAlignedBB b = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);
/*     */   
/*     */   protected BlockCarpet() {
/*  24 */     super(Material.WOOL);
/*  25 */     w(this.blockStateList.getBlockData().set(COLOR, EnumColor.WHITE));
/*  26 */     a(true);
/*  27 */     a(CreativeModeTab.c);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  32 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  37 */     return MaterialMapColor.a(paramIBlockData.<EnumColor>get(COLOR));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/*  42 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/*  47 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(World paramWorld, BlockPosition paramBlockPosition) {
/*  52 */     return (super.canPlace(paramWorld, paramBlockPosition) && b(paramWorld, paramBlockPosition));
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/*  57 */     e(paramWorld, paramBlockPosition1, paramIBlockData);
/*     */   }
/*     */   
/*     */   private boolean e(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  61 */     if (!b(paramWorld, paramBlockPosition)) {
/*  62 */       b(paramWorld, paramBlockPosition, paramIBlockData, 0);
/*  63 */       paramWorld.setAir(paramBlockPosition);
/*  64 */       return false;
/*     */     } 
/*  66 */     return true;
/*     */   }
/*     */   
/*     */   private boolean b(World paramWorld, BlockPosition paramBlockPosition) {
/*  70 */     return !paramWorld.isEmpty(paramBlockPosition.down());
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
/*     */   public int getDropData(IBlockData paramIBlockData) {
/*  88 */     return ((EnumColor)paramIBlockData.<EnumColor>get(COLOR)).getColorIndex();
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
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 100 */     return getBlockData()
/* 101 */       .set(COLOR, EnumColor.fromColorIndex(paramInt));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 106 */     return ((EnumColor)paramIBlockData.<EnumColor>get(COLOR)).getColorIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 111 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { COLOR });
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 116 */     if (paramEnumDirection == EnumDirection.DOWN) {
/* 117 */       return EnumBlockFaceShape.SOLID;
/*     */     }
/* 119 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockCarpet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */