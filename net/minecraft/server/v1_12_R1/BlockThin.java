/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
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
/*     */ public class BlockThin
/*     */   extends Block
/*     */ {
/*  24 */   public static final BlockStateBoolean NORTH = BlockStateBoolean.of("north");
/*  25 */   public static final BlockStateBoolean EAST = BlockStateBoolean.of("east");
/*  26 */   public static final BlockStateBoolean SOUTH = BlockStateBoolean.of("south");
/*  27 */   public static final BlockStateBoolean WEST = BlockStateBoolean.of("west");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   protected static final AxisAlignedBB[] f = new AxisAlignedBB[] { new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean a;
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
/*     */   protected BlockThin(Material paramMaterial, boolean paramBoolean) {
/*  59 */     super(paramMaterial);
/*  60 */     w(this.blockStateList.getBlockData().<Comparable, Boolean>set(NORTH, Boolean.valueOf(false)).<Comparable, Boolean>set(EAST, Boolean.valueOf(false)).<Comparable, Boolean>set(SOUTH, Boolean.valueOf(false)).set(WEST, Boolean.valueOf(false)));
/*  61 */     this.a = paramBoolean;
/*  62 */     a(CreativeModeTab.c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition, AxisAlignedBB paramAxisAlignedBB, List<AxisAlignedBB> paramList, @Nullable Entity paramEntity, boolean paramBoolean) {
/*  67 */     if (!paramBoolean) {
/*  68 */       paramIBlockData = updateState(paramIBlockData, paramWorld, paramBlockPosition);
/*     */     }
/*     */     
/*  71 */     a(paramBlockPosition, paramAxisAlignedBB, paramList, f[0]);
/*  72 */     if (((Boolean)paramIBlockData.<Boolean>get(NORTH)).booleanValue()) {
/*  73 */       a(paramBlockPosition, paramAxisAlignedBB, paramList, f[a(EnumDirection.NORTH)]);
/*     */     }
/*  75 */     if (((Boolean)paramIBlockData.<Boolean>get(SOUTH)).booleanValue()) {
/*  76 */       a(paramBlockPosition, paramAxisAlignedBB, paramList, f[a(EnumDirection.SOUTH)]);
/*     */     }
/*  78 */     if (((Boolean)paramIBlockData.<Boolean>get(EAST)).booleanValue()) {
/*  79 */       a(paramBlockPosition, paramAxisAlignedBB, paramList, f[a(EnumDirection.EAST)]);
/*     */     }
/*  81 */     if (((Boolean)paramIBlockData.<Boolean>get(WEST)).booleanValue()) {
/*  82 */       a(paramBlockPosition, paramAxisAlignedBB, paramList, f[a(EnumDirection.WEST)]);
/*     */     }
/*     */   }
/*     */   
/*     */   private static int a(EnumDirection paramEnumDirection) {
/*  87 */     return 1 << paramEnumDirection.get2DRotationValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  92 */     paramIBlockData = updateState(paramIBlockData, paramIBlockAccess, paramBlockPosition);
/*  93 */     return f[x(paramIBlockData)];
/*     */   }
/*     */   
/*     */   private static int x(IBlockData paramIBlockData) {
/*  97 */     int i = 0;
/*  98 */     if (((Boolean)paramIBlockData.<Boolean>get(NORTH)).booleanValue()) {
/*  99 */       i |= a(EnumDirection.NORTH);
/*     */     }
/* 101 */     if (((Boolean)paramIBlockData.<Boolean>get(EAST)).booleanValue()) {
/* 102 */       i |= a(EnumDirection.EAST);
/*     */     }
/* 104 */     if (((Boolean)paramIBlockData.<Boolean>get(SOUTH)).booleanValue()) {
/* 105 */       i |= a(EnumDirection.SOUTH);
/*     */     }
/* 107 */     if (((Boolean)paramIBlockData.<Boolean>get(WEST)).booleanValue()) {
/* 108 */       i |= a(EnumDirection.WEST);
/*     */     }
/* 110 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 115 */     return paramIBlockData
/* 116 */       .<Comparable, Boolean>set(NORTH, Boolean.valueOf(b(paramIBlockAccess, paramIBlockAccess.getType(paramBlockPosition.north()), paramBlockPosition.north(), EnumDirection.SOUTH)))
/* 117 */       .<Comparable, Boolean>set(SOUTH, Boolean.valueOf(b(paramIBlockAccess, paramIBlockAccess.getType(paramBlockPosition.south()), paramBlockPosition.south(), EnumDirection.NORTH)))
/* 118 */       .<Comparable, Boolean>set(WEST, Boolean.valueOf(b(paramIBlockAccess, paramIBlockAccess.getType(paramBlockPosition.west()), paramBlockPosition.west(), EnumDirection.EAST)))
/* 119 */       .set(EAST, Boolean.valueOf(b(paramIBlockAccess, paramIBlockAccess.getType(paramBlockPosition.east()), paramBlockPosition.east(), EnumDirection.WEST)));
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/* 124 */     if (!this.a) {
/* 125 */       return Items.a;
/*     */     }
/* 127 */     return super.getDropType(paramIBlockData, paramRandom, paramInt);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/* 132 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/* 137 */     return false;
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
/*     */   public final boolean b(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 149 */     Block block = paramIBlockData.getBlock();
/* 150 */     EnumBlockFaceShape enumBlockFaceShape = paramIBlockData.d(paramIBlockAccess, paramBlockPosition, paramEnumDirection);
/* 151 */     return ((!e(block) && enumBlockFaceShape == EnumBlockFaceShape.SOLID) || enumBlockFaceShape == EnumBlockFaceShape.MIDDLE_POLE_THIN);
/*     */   }
/*     */   
/*     */   protected static boolean e(Block paramBlock) {
/* 155 */     return (paramBlock instanceof BlockShulkerBox || paramBlock instanceof BlockLeaves || paramBlock == Blocks.BEACON || paramBlock == Blocks.cauldron || paramBlock == Blocks.GLOWSTONE || paramBlock == Blocks.ICE || paramBlock == Blocks.SEA_LANTERN || paramBlock == Blocks.PISTON || paramBlock == Blocks.STICKY_PISTON || paramBlock == Blocks.PISTON_HEAD || paramBlock == Blocks.MELON_BLOCK || paramBlock == Blocks.PUMPKIN || paramBlock == Blocks.LIT_PUMPKIN || paramBlock == Blocks.BARRIER);
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
/*     */   protected boolean n() {
/* 173 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 183 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/* 188 */     switch (null.a[paramEnumBlockRotation.ordinal()]) {
/*     */       case 1:
/* 190 */         return paramIBlockData.<Comparable, Comparable>set(NORTH, paramIBlockData.get(SOUTH)).<Comparable, Comparable>set(EAST, paramIBlockData.get(WEST)).<Comparable, Comparable>set(SOUTH, paramIBlockData.get(NORTH)).set(WEST, paramIBlockData.get(EAST));
/*     */       case 2:
/* 192 */         return paramIBlockData.<Comparable, Comparable>set(NORTH, paramIBlockData.get(EAST)).<Comparable, Comparable>set(EAST, paramIBlockData.get(SOUTH)).<Comparable, Comparable>set(SOUTH, paramIBlockData.get(WEST)).set(WEST, paramIBlockData.get(NORTH));
/*     */       case 3:
/* 194 */         return paramIBlockData.<Comparable, Comparable>set(NORTH, paramIBlockData.get(WEST)).<Comparable, Comparable>set(EAST, paramIBlockData.get(NORTH)).<Comparable, Comparable>set(SOUTH, paramIBlockData.get(EAST)).set(WEST, paramIBlockData.get(SOUTH));
/*     */     } 
/* 196 */     return paramIBlockData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/* 202 */     switch (null.b[paramEnumBlockMirror.ordinal()]) {
/*     */       case 1:
/* 204 */         return paramIBlockData.<Comparable, Comparable>set(NORTH, paramIBlockData.get(SOUTH)).set(SOUTH, paramIBlockData.get(NORTH));
/*     */       case 2:
/* 206 */         return paramIBlockData.<Comparable, Comparable>set(EAST, paramIBlockData.get(WEST)).set(WEST, paramIBlockData.get(EAST));
/*     */     } 
/*     */ 
/*     */     
/* 210 */     return super.a(paramIBlockData, paramEnumBlockMirror);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 215 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { NORTH, EAST, WEST, SOUTH });
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 220 */     if (paramEnumDirection == EnumDirection.UP || paramEnumDirection == EnumDirection.DOWN) {
/* 221 */       return EnumBlockFaceShape.CENTER_SMALL;
/*     */     }
/* 223 */     return EnumBlockFaceShape.MIDDLE_POLE_THIN;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockThin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */