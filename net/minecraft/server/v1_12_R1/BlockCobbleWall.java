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
/*     */ public class BlockCobbleWall
/*     */   extends Block
/*     */ {
/*  24 */   public static final BlockStateBoolean UP = BlockStateBoolean.of("up");
/*  25 */   public static final BlockStateBoolean NORTH = BlockStateBoolean.of("north");
/*  26 */   public static final BlockStateBoolean EAST = BlockStateBoolean.of("east");
/*  27 */   public static final BlockStateBoolean SOUTH = BlockStateBoolean.of("south");
/*  28 */   public static final BlockStateBoolean WEST = BlockStateBoolean.of("west");
/*  29 */   public static final BlockStateEnum<EnumCobbleVariant> VARIANT = BlockStateEnum.of("variant", EnumCobbleVariant.class);
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
/*  44 */   protected static final AxisAlignedBB[] g = new AxisAlignedBB[] { new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D), new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D), new AxisAlignedBB(0.0D, 0.0D, 0.25D, 0.75D, 1.0D, 1.0D), new AxisAlignedBB(0.25D, 0.0D, 0.0D, 0.75D, 1.0D, 0.75D), new AxisAlignedBB(0.3125D, 0.0D, 0.0D, 0.6875D, 0.875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.75D, 1.0D, 0.75D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.75D, 1.0D, 1.0D), new AxisAlignedBB(0.25D, 0.0D, 0.25D, 1.0D, 1.0D, 0.75D), new AxisAlignedBB(0.25D, 0.0D, 0.25D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.3125D, 1.0D, 0.875D, 0.6875D), new AxisAlignedBB(0.0D, 0.0D, 0.25D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.25D, 0.0D, 0.0D, 1.0D, 1.0D, 0.75D), new AxisAlignedBB(0.25D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.75D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) };
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
/*  63 */   protected static final AxisAlignedBB[] B = new AxisAlignedBB[] { g[0]
/*  64 */       .e(1.5D), g[1]
/*  65 */       .e(1.5D), g[2]
/*  66 */       .e(1.5D), g[3]
/*  67 */       .e(1.5D), g[4]
/*  68 */       .e(1.5D), g[5]
/*  69 */       .e(1.5D), g[6]
/*  70 */       .e(1.5D), g[7]
/*  71 */       .e(1.5D), g[8]
/*  72 */       .e(1.5D), g[9]
/*  73 */       .e(1.5D), g[10]
/*  74 */       .e(1.5D), g[11]
/*  75 */       .e(1.5D), g[12]
/*  76 */       .e(1.5D), g[13]
/*  77 */       .e(1.5D), g[14]
/*  78 */       .e(1.5D), g[15]
/*  79 */       .e(1.5D) };
/*     */ 
/*     */   
/*     */   public BlockCobbleWall(Block paramBlock) {
/*  83 */     super(paramBlock.material);
/*  84 */     w(this.blockStateList.getBlockData().<Comparable, Boolean>set(UP, Boolean.valueOf(false)).<Comparable, Boolean>set(NORTH, Boolean.valueOf(false)).<Comparable, Boolean>set(EAST, Boolean.valueOf(false)).<Comparable, Boolean>set(SOUTH, Boolean.valueOf(false)).<Comparable, Boolean>set(WEST, Boolean.valueOf(false)).set(VARIANT, EnumCobbleVariant.NORMAL));
/*  85 */     c(paramBlock.strength);
/*  86 */     b(paramBlock.durability / 3.0F);
/*  87 */     a(paramBlock.stepSound);
/*  88 */     a(CreativeModeTab.c);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  93 */     paramIBlockData = updateState(paramIBlockData, paramIBlockAccess, paramBlockPosition);
/*     */     
/*  95 */     return g[x(paramIBlockData)];
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition, AxisAlignedBB paramAxisAlignedBB, List<AxisAlignedBB> paramList, @Nullable Entity paramEntity, boolean paramBoolean) {
/* 100 */     if (!paramBoolean) {
/* 101 */       paramIBlockData = updateState(paramIBlockData, paramWorld, paramBlockPosition);
/*     */     }
/*     */     
/* 104 */     a(paramBlockPosition, paramAxisAlignedBB, paramList, B[x(paramIBlockData)]);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB a(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 110 */     paramIBlockData = updateState(paramIBlockData, paramIBlockAccess, paramBlockPosition);
/*     */     
/* 112 */     return B[x(paramIBlockData)];
/*     */   }
/*     */   
/*     */   private static int x(IBlockData paramIBlockData) {
/* 116 */     int i = 0;
/* 117 */     if (((Boolean)paramIBlockData.<Boolean>get(NORTH)).booleanValue()) {
/* 118 */       i |= 1 << EnumDirection.NORTH.get2DRotationValue();
/*     */     }
/* 120 */     if (((Boolean)paramIBlockData.<Boolean>get(EAST)).booleanValue()) {
/* 121 */       i |= 1 << EnumDirection.EAST.get2DRotationValue();
/*     */     }
/* 123 */     if (((Boolean)paramIBlockData.<Boolean>get(SOUTH)).booleanValue()) {
/* 124 */       i |= 1 << EnumDirection.SOUTH.get2DRotationValue();
/*     */     }
/* 126 */     if (((Boolean)paramIBlockData.<Boolean>get(WEST)).booleanValue()) {
/* 127 */       i |= 1 << EnumDirection.WEST.get2DRotationValue();
/*     */     }
/* 129 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 134 */     return LocaleI18n.get(a() + "." + EnumCobbleVariant.NORMAL.c() + ".name");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/* 139 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 144 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/* 149 */     return false;
/*     */   }
/*     */   
/*     */   private boolean a(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 153 */     IBlockData iBlockData = paramIBlockAccess.getType(paramBlockPosition);
/* 154 */     Block block = iBlockData.getBlock();
/* 155 */     EnumBlockFaceShape enumBlockFaceShape = iBlockData.d(paramIBlockAccess, paramBlockPosition, paramEnumDirection);
/* 156 */     boolean bool = (enumBlockFaceShape == EnumBlockFaceShape.MIDDLE_POLE_THICK || (enumBlockFaceShape == EnumBlockFaceShape.MIDDLE_POLE && block instanceof BlockFenceGate)) ? true : false;
/* 157 */     return ((!e(block) && enumBlockFaceShape == EnumBlockFaceShape.SOLID) || bool);
/*     */   }
/*     */   
/*     */   protected static boolean e(Block paramBlock) {
/* 161 */     return (Block.c(paramBlock) || paramBlock == Blocks.BARRIER || paramBlock == Blocks.MELON_BLOCK || paramBlock == Blocks.PUMPKIN || paramBlock == Blocks.LIT_PUMPKIN);
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
/*     */   public int getDropData(IBlockData paramIBlockData) {
/* 177 */     return ((EnumCobbleVariant)paramIBlockData.<EnumCobbleVariant>get(VARIANT)).a();
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
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 190 */     return getBlockData()
/* 191 */       .set(VARIANT, EnumCobbleVariant.a(paramInt));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 196 */     return ((EnumCobbleVariant)paramIBlockData.<EnumCobbleVariant>get(VARIANT)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 201 */     boolean bool1 = a(paramIBlockAccess, paramBlockPosition.north(), EnumDirection.SOUTH);
/* 202 */     boolean bool2 = a(paramIBlockAccess, paramBlockPosition.east(), EnumDirection.WEST);
/* 203 */     boolean bool3 = a(paramIBlockAccess, paramBlockPosition.south(), EnumDirection.NORTH);
/* 204 */     boolean bool4 = a(paramIBlockAccess, paramBlockPosition.west(), EnumDirection.EAST);
/* 205 */     boolean bool = ((bool1 && !bool2 && bool3 && !bool4) || (!bool1 && bool2 && !bool3 && bool4)) ? true : false;
/*     */     
/* 207 */     return paramIBlockData
/* 208 */       .<Comparable, Boolean>set(UP, Boolean.valueOf((!bool || !paramIBlockAccess.isEmpty(paramBlockPosition.up()))))
/* 209 */       .<Comparable, Boolean>set(NORTH, Boolean.valueOf(bool1))
/* 210 */       .<Comparable, Boolean>set(EAST, Boolean.valueOf(bool2))
/* 211 */       .<Comparable, Boolean>set(SOUTH, Boolean.valueOf(bool3))
/* 212 */       .set(WEST, Boolean.valueOf(bool4));
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 217 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { UP, NORTH, EAST, WEST, SOUTH, VARIANT });
/*     */   }
/*     */   
/*     */   public enum EnumCobbleVariant implements INamable {
/* 221 */     NORMAL(0, "cobblestone", "normal"),
/* 222 */     MOSSY(1, "mossy_cobblestone", "mossy");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 231 */     private static final EnumCobbleVariant[] c = new EnumCobbleVariant[(values()).length]; private final int d;
/*     */     static {
/* 233 */       for (EnumCobbleVariant enumCobbleVariant : values())
/* 234 */         c[enumCobbleVariant.a()] = enumCobbleVariant; 
/*     */     }
/*     */     private final String e; private final String f;
/*     */     
/*     */     EnumCobbleVariant(int param1Int1, String param1String1, String param1String2) {
/* 239 */       this.d = param1Int1;
/* 240 */       this.e = param1String1;
/* 241 */       this.f = param1String2;
/*     */     }
/*     */     
/*     */     public int a() {
/* 245 */       return this.d;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 254 */       return this.e;
/*     */     }
/*     */     
/*     */     public static EnumCobbleVariant a(int param1Int) {
/* 258 */       if (param1Int < 0 || param1Int >= c.length) {
/* 259 */         param1Int = 0;
/*     */       }
/* 261 */       return c[param1Int];
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 266 */       return this.e;
/*     */     }
/*     */     
/*     */     public String c() {
/* 270 */       return this.f;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 276 */     if (paramEnumDirection == EnumDirection.UP || paramEnumDirection == EnumDirection.DOWN) {
/* 277 */       return EnumBlockFaceShape.CENTER_BIG;
/*     */     }
/* 279 */     return EnumBlockFaceShape.MIDDLE_POLE_THICK;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockCobbleWall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */