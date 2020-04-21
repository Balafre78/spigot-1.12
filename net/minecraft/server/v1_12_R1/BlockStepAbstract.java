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
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BlockStepAbstract
/*     */   extends Block
/*     */ {
/*  21 */   public static final BlockStateEnum<EnumSlabHalf> HALF = BlockStateEnum.of("half", EnumSlabHalf.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  26 */   protected static final AxisAlignedBB b = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
/*  27 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */   
/*     */   public BlockStepAbstract(Material paramMaterial) {
/*  30 */     this(paramMaterial, paramMaterial.r());
/*     */   }
/*     */   
/*     */   public BlockStepAbstract(Material paramMaterial, MaterialMapColor paramMaterialMapColor) {
/*  34 */     super(paramMaterial, paramMaterialMapColor);
/*     */     
/*  36 */     this.l = e();
/*  37 */     e(255);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean n() {
/*  42 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  47 */     if (e()) {
/*  48 */       return j;
/*     */     }
/*     */     
/*  51 */     return (paramIBlockData.get(HALF) == EnumSlabHalf.TOP) ? c : b;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean k(IBlockData paramIBlockData) {
/*  56 */     return (((BlockStepAbstract)paramIBlockData.getBlock()).e() || paramIBlockData.get(HALF) == EnumSlabHalf.TOP);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/*  61 */     if (((BlockStepAbstract)paramIBlockData.getBlock()).e())
/*  62 */       return EnumBlockFaceShape.SOLID; 
/*  63 */     if (paramEnumDirection == EnumDirection.UP && paramIBlockData.get(HALF) == EnumSlabHalf.TOP)
/*  64 */       return EnumBlockFaceShape.SOLID; 
/*  65 */     if (paramEnumDirection == EnumDirection.DOWN && paramIBlockData.get(HALF) == EnumSlabHalf.BOTTOM) {
/*  66 */       return EnumBlockFaceShape.SOLID;
/*     */     }
/*  68 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/*  73 */     return e();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, EntityLiving paramEntityLiving) {
/*  78 */     IBlockData iBlockData = super.getPlacedState(paramWorld, paramBlockPosition, paramEnumDirection, paramFloat1, paramFloat2, paramFloat3, paramInt, paramEntityLiving).set(HALF, EnumSlabHalf.BOTTOM);
/*     */     
/*  80 */     if (e()) {
/*  81 */       return iBlockData;
/*     */     }
/*     */     
/*  84 */     if (paramEnumDirection == EnumDirection.DOWN || (paramEnumDirection != EnumDirection.UP && paramFloat2 > 0.5D)) {
/*  85 */       return iBlockData.set(HALF, EnumSlabHalf.TOP);
/*     */     }
/*  87 */     return iBlockData;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(Random paramRandom) {
/*  92 */     if (e()) {
/*  93 */       return 2;
/*     */     }
/*  95 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/* 100 */     return e();
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
/*     */   public abstract String b(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean e();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract IBlockState<?> g();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Comparable<?> a(ItemStack paramItemStack);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum EnumSlabHalf
/*     */     implements INamable
/*     */   {
/* 153 */     TOP("top"),
/* 154 */     BOTTOM("bottom");
/*     */     
/*     */     private final String c;
/*     */ 
/*     */     
/*     */     EnumSlabHalf(String param1String1) {
/* 160 */       this.c = param1String1;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 165 */       return this.c;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 170 */       return this.c;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockStepAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */