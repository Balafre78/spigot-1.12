/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
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
/*     */ 
/*     */ public class BlockEnderPortalFrame
/*     */   extends Block
/*     */ {
/*  31 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*  32 */   public static final BlockStateBoolean EYE = BlockStateBoolean.of("eye");
/*  33 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D);
/*  34 */   protected static final AxisAlignedBB d = new AxisAlignedBB(0.3125D, 0.8125D, 0.3125D, 0.6875D, 1.0D, 0.6875D);
/*     */   private static ShapeDetector e;
/*     */   
/*     */   public BlockEnderPortalFrame() {
/*  38 */     super(Material.STONE, MaterialMapColor.D);
/*  39 */     w(this.blockStateList.getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.NORTH).set(EYE, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/*  44 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  49 */     return c;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition, AxisAlignedBB paramAxisAlignedBB, List<AxisAlignedBB> paramList, @Nullable Entity paramEntity, boolean paramBoolean) {
/*  54 */     a(paramBlockPosition, paramAxisAlignedBB, paramList, c);
/*     */     
/*  56 */     if (((Boolean)paramWorld.getType(paramBlockPosition).<Boolean>get(EYE)).booleanValue()) {
/*  57 */       a(paramBlockPosition, paramAxisAlignedBB, paramList, d);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/*  63 */     return Items.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, EntityLiving paramEntityLiving) {
/*  68 */     return getBlockData().<Comparable, EnumDirection>set(FACING, paramEntityLiving.getDirection().opposite()).set(EYE, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData paramIBlockData) {
/*  73 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int c(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition) {
/*  78 */     if (((Boolean)paramIBlockData.<Boolean>get(EYE)).booleanValue()) {
/*  79 */       return 15;
/*     */     }
/*     */     
/*  82 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/*  87 */     return getBlockData()
/*  88 */       .<Comparable, Boolean>set(EYE, Boolean.valueOf(((paramInt & 0x4) != 0)))
/*  89 */       .set(FACING, EnumDirection.fromType2(paramInt & 0x3));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  94 */     int i = 0;
/*     */     
/*  96 */     i |= ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).get2DRotationValue();
/*     */     
/*  98 */     if (((Boolean)paramIBlockData.<Boolean>get(EYE)).booleanValue()) {
/*  99 */       i |= 0x4;
/*     */     }
/*     */     
/* 102 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/* 107 */     return paramIBlockData.set(FACING, paramEnumBlockRotation.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/* 112 */     return paramIBlockData.a(paramEnumBlockMirror.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 117 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING, EYE });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/* 122 */     return false;
/*     */   }
/*     */   
/*     */   public static ShapeDetector e() {
/* 126 */     if (e == null)
/*     */     {
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
/* 140 */       e = ShapeDetectorBuilder.a().a(new String[] { "?vvv?", ">???<", ">???<", ">???<", "?^^^?" }).a('?', ShapeDetectorBlock.a(BlockStatePredicate.a)).a('^', ShapeDetectorBlock.a(BlockStatePredicate.a(Blocks.END_PORTAL_FRAME).<Comparable>a(EYE, Predicates.equalTo(Boolean.valueOf(true))).a(FACING, Predicates.equalTo(EnumDirection.SOUTH)))).a('>', ShapeDetectorBlock.a(BlockStatePredicate.a(Blocks.END_PORTAL_FRAME).<Comparable>a(EYE, Predicates.equalTo(Boolean.valueOf(true))).a(FACING, Predicates.equalTo(EnumDirection.WEST)))).a('v', ShapeDetectorBlock.a(BlockStatePredicate.a(Blocks.END_PORTAL_FRAME).<Comparable>a(EYE, Predicates.equalTo(Boolean.valueOf(true))).a(FACING, Predicates.equalTo(EnumDirection.NORTH)))).a('<', ShapeDetectorBlock.a(BlockStatePredicate.a(Blocks.END_PORTAL_FRAME).<Comparable>a(EYE, Predicates.equalTo(Boolean.valueOf(true))).a(FACING, Predicates.equalTo(EnumDirection.EAST)))).b();
/*     */     }
/* 142 */     return e;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 147 */     if (paramEnumDirection == EnumDirection.DOWN) {
/* 148 */       return EnumBlockFaceShape.SOLID;
/*     */     }
/* 150 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockEnderPortalFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */