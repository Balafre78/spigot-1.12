/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.List;
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
/*     */ public class BlockFence
/*     */   extends Block
/*     */ {
/*  26 */   public static final BlockStateBoolean NORTH = BlockStateBoolean.of("north");
/*  27 */   public static final BlockStateBoolean EAST = BlockStateBoolean.of("east");
/*  28 */   public static final BlockStateBoolean SOUTH = BlockStateBoolean.of("south");
/*  29 */   public static final BlockStateBoolean WEST = BlockStateBoolean.of("west");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   protected static final AxisAlignedBB[] e = new AxisAlignedBB[] { new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D), new AxisAlignedBB(0.0D, 0.0D, 0.375D, 0.625D, 1.0D, 1.0D), new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.625D, 1.0D, 0.625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D), new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.375D, 0.0D, 0.0D, 1.0D, 1.0D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) };
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
/*  55 */   public static final AxisAlignedBB f = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.5D, 0.625D);
/*  56 */   public static final AxisAlignedBB g = new AxisAlignedBB(0.375D, 0.0D, 0.625D, 0.625D, 1.5D, 1.0D);
/*  57 */   public static final AxisAlignedBB B = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 0.375D, 1.5D, 0.625D);
/*  58 */   public static final AxisAlignedBB C = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.5D, 0.375D);
/*  59 */   public static final AxisAlignedBB D = new AxisAlignedBB(0.625D, 0.0D, 0.375D, 1.0D, 1.5D, 0.625D);
/*     */   
/*     */   public BlockFence(Material paramMaterial, MaterialMapColor paramMaterialMapColor) {
/*  62 */     super(paramMaterial, paramMaterialMapColor);
/*  63 */     w(this.blockStateList.getBlockData().<Comparable, Boolean>set(NORTH, Boolean.valueOf(false)).<Comparable, Boolean>set(EAST, Boolean.valueOf(false)).<Comparable, Boolean>set(SOUTH, Boolean.valueOf(false)).set(WEST, Boolean.valueOf(false)));
/*  64 */     a(CreativeModeTab.c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition, AxisAlignedBB paramAxisAlignedBB, List<AxisAlignedBB> paramList, @Nullable Entity paramEntity, boolean paramBoolean) {
/*  69 */     if (!paramBoolean) {
/*  70 */       paramIBlockData = paramIBlockData.c(paramWorld, paramBlockPosition);
/*     */     }
/*     */     
/*  73 */     a(paramBlockPosition, paramAxisAlignedBB, paramList, f);
/*  74 */     if (((Boolean)paramIBlockData.<Boolean>get(NORTH)).booleanValue()) {
/*  75 */       a(paramBlockPosition, paramAxisAlignedBB, paramList, C);
/*     */     }
/*  77 */     if (((Boolean)paramIBlockData.<Boolean>get(EAST)).booleanValue()) {
/*  78 */       a(paramBlockPosition, paramAxisAlignedBB, paramList, D);
/*     */     }
/*  80 */     if (((Boolean)paramIBlockData.<Boolean>get(SOUTH)).booleanValue()) {
/*  81 */       a(paramBlockPosition, paramAxisAlignedBB, paramList, g);
/*     */     }
/*  83 */     if (((Boolean)paramIBlockData.<Boolean>get(WEST)).booleanValue()) {
/*  84 */       a(paramBlockPosition, paramAxisAlignedBB, paramList, B);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  90 */     paramIBlockData = updateState(paramIBlockData, paramIBlockAccess, paramBlockPosition);
/*     */     
/*  92 */     return e[x(paramIBlockData)];
/*     */   }
/*     */   
/*     */   private static int x(IBlockData paramIBlockData) {
/*  96 */     int i = 0;
/*  97 */     if (((Boolean)paramIBlockData.<Boolean>get(NORTH)).booleanValue()) {
/*  98 */       i |= 1 << EnumDirection.NORTH.get2DRotationValue();
/*     */     }
/* 100 */     if (((Boolean)paramIBlockData.<Boolean>get(EAST)).booleanValue()) {
/* 101 */       i |= 1 << EnumDirection.EAST.get2DRotationValue();
/*     */     }
/* 103 */     if (((Boolean)paramIBlockData.<Boolean>get(SOUTH)).booleanValue()) {
/* 104 */       i |= 1 << EnumDirection.SOUTH.get2DRotationValue();
/*     */     }
/* 106 */     if (((Boolean)paramIBlockData.<Boolean>get(WEST)).booleanValue()) {
/* 107 */       i |= 1 << EnumDirection.WEST.get2DRotationValue();
/*     */     }
/* 109 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/* 119 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 124 */     return false;
/*     */   }
/*     */   
/*     */   public boolean a(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 128 */     IBlockData iBlockData = paramIBlockAccess.getType(paramBlockPosition);
/* 129 */     EnumBlockFaceShape enumBlockFaceShape = iBlockData.d(paramIBlockAccess, paramBlockPosition, paramEnumDirection);
/*     */     
/* 131 */     Block block = iBlockData.getBlock();
/* 132 */     boolean bool = (enumBlockFaceShape == EnumBlockFaceShape.MIDDLE_POLE && (iBlockData.getMaterial() == this.material || block instanceof BlockFenceGate)) ? true : false;
/* 133 */     return ((!e(block) && enumBlockFaceShape == EnumBlockFaceShape.SOLID) || bool);
/*     */   }
/*     */   
/*     */   protected static boolean e(Block paramBlock) {
/* 137 */     return (Block.c(paramBlock) || paramBlock == Blocks.BARRIER || paramBlock == Blocks.MELON_BLOCK || paramBlock == Blocks.PUMPKIN || paramBlock == Blocks.LIT_PUMPKIN);
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
/*     */   public boolean interact(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 151 */     if (paramWorld.isClientSide) {
/* 152 */       ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/* 153 */       return (itemStack.getItem() == Items.LEAD || itemStack.isEmpty());
/*     */     } 
/*     */     
/* 156 */     return ItemLeash.a(paramEntityHuman, paramWorld, paramBlockPosition);
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 161 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 166 */     return paramIBlockData
/* 167 */       .<Comparable, Boolean>set(NORTH, Boolean.valueOf(a(paramIBlockAccess, paramBlockPosition.north(), EnumDirection.SOUTH)))
/* 168 */       .<Comparable, Boolean>set(EAST, Boolean.valueOf(a(paramIBlockAccess, paramBlockPosition.east(), EnumDirection.WEST)))
/* 169 */       .<Comparable, Boolean>set(SOUTH, Boolean.valueOf(a(paramIBlockAccess, paramBlockPosition.south(), EnumDirection.NORTH)))
/* 170 */       .set(WEST, Boolean.valueOf(a(paramIBlockAccess, paramBlockPosition.west(), EnumDirection.EAST)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/* 175 */     switch (null.a[paramEnumBlockRotation.ordinal()]) {
/*     */       case 1:
/* 177 */         return paramIBlockData.<Comparable, Comparable>set(NORTH, paramIBlockData.get(SOUTH)).<Comparable, Comparable>set(EAST, paramIBlockData.get(WEST)).<Comparable, Comparable>set(SOUTH, paramIBlockData.get(NORTH)).set(WEST, paramIBlockData.get(EAST));
/*     */       case 2:
/* 179 */         return paramIBlockData.<Comparable, Comparable>set(NORTH, paramIBlockData.get(EAST)).<Comparable, Comparable>set(EAST, paramIBlockData.get(SOUTH)).<Comparable, Comparable>set(SOUTH, paramIBlockData.get(WEST)).set(WEST, paramIBlockData.get(NORTH));
/*     */       case 3:
/* 181 */         return paramIBlockData.<Comparable, Comparable>set(NORTH, paramIBlockData.get(WEST)).<Comparable, Comparable>set(EAST, paramIBlockData.get(NORTH)).<Comparable, Comparable>set(SOUTH, paramIBlockData.get(EAST)).set(WEST, paramIBlockData.get(SOUTH));
/*     */     } 
/* 183 */     return paramIBlockData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/* 189 */     switch (null.b[paramEnumBlockMirror.ordinal()]) {
/*     */       case 1:
/* 191 */         return paramIBlockData.<Comparable, Comparable>set(NORTH, paramIBlockData.get(SOUTH)).set(SOUTH, paramIBlockData.get(NORTH));
/*     */       case 2:
/* 193 */         return paramIBlockData.<Comparable, Comparable>set(EAST, paramIBlockData.get(WEST)).set(WEST, paramIBlockData.get(EAST));
/*     */     } 
/*     */ 
/*     */     
/* 197 */     return super.a(paramIBlockData, paramEnumBlockMirror);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 202 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { NORTH, EAST, WEST, SOUTH });
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 207 */     if (paramEnumDirection == EnumDirection.UP || paramEnumDirection == EnumDirection.DOWN) {
/* 208 */       return EnumBlockFaceShape.CENTER;
/*     */     }
/* 210 */     return EnumBlockFaceShape.MIDDLE_POLE;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockFence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */