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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockPistonExtension
/*     */   extends BlockDirectional
/*     */ {
/*  30 */   public static final BlockStateEnum<EnumPistonType> TYPE = BlockStateEnum.of("type", EnumPistonType.class);
/*  31 */   public static final BlockStateBoolean SHORT = BlockStateBoolean.of("short");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.75D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  38 */   protected static final AxisAlignedBB d = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.25D, 1.0D, 1.0D);
/*  39 */   protected static final AxisAlignedBB e = new AxisAlignedBB(0.0D, 0.0D, 0.75D, 1.0D, 1.0D, 1.0D);
/*  40 */   protected static final AxisAlignedBB f = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.25D);
/*  41 */   protected static final AxisAlignedBB g = new AxisAlignedBB(0.0D, 0.75D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  42 */   protected static final AxisAlignedBB B = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   protected static final AxisAlignedBB C = new AxisAlignedBB(0.375D, -0.25D, 0.375D, 0.625D, 0.75D, 0.625D);
/*  49 */   protected static final AxisAlignedBB D = new AxisAlignedBB(0.375D, 0.25D, 0.375D, 0.625D, 1.25D, 0.625D);
/*  50 */   protected static final AxisAlignedBB E = new AxisAlignedBB(0.375D, 0.375D, -0.25D, 0.625D, 0.625D, 0.75D);
/*  51 */   protected static final AxisAlignedBB F = new AxisAlignedBB(0.375D, 0.375D, 0.25D, 0.625D, 0.625D, 1.25D);
/*  52 */   protected static final AxisAlignedBB G = new AxisAlignedBB(-0.25D, 0.375D, 0.375D, 0.75D, 0.625D, 0.625D);
/*  53 */   protected static final AxisAlignedBB I = new AxisAlignedBB(0.25D, 0.375D, 0.375D, 1.25D, 0.625D, 0.625D);
/*     */   
/*  55 */   protected static final AxisAlignedBB J = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.75D, 0.625D);
/*  56 */   protected static final AxisAlignedBB K = new AxisAlignedBB(0.375D, 0.25D, 0.375D, 0.625D, 1.0D, 0.625D);
/*  57 */   protected static final AxisAlignedBB L = new AxisAlignedBB(0.375D, 0.375D, 0.0D, 0.625D, 0.625D, 0.75D);
/*  58 */   protected static final AxisAlignedBB M = new AxisAlignedBB(0.375D, 0.375D, 0.25D, 0.625D, 0.625D, 1.0D);
/*  59 */   protected static final AxisAlignedBB N = new AxisAlignedBB(0.0D, 0.375D, 0.375D, 0.75D, 0.625D, 0.625D);
/*  60 */   protected static final AxisAlignedBB O = new AxisAlignedBB(0.25D, 0.375D, 0.375D, 1.0D, 0.625D, 0.625D);
/*     */   
/*     */   public BlockPistonExtension() {
/*  63 */     super(Material.PISTON);
/*  64 */     w(this.blockStateList.getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.NORTH).<EnumPistonType, EnumPistonType>set(TYPE, EnumPistonType.DEFAULT).set(SHORT, Boolean.valueOf(false)));
/*  65 */     a(SoundEffectType.d);
/*  66 */     c(0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  71 */     switch (null.a[((EnumDirection)paramIBlockData.get(FACING)).ordinal()])
/*     */     
/*     */     { default:
/*  74 */         return B;
/*     */       case 2:
/*  76 */         return g;
/*     */       case 3:
/*  78 */         return f;
/*     */       case 4:
/*  80 */         return e;
/*     */       case 5:
/*  82 */         return d;
/*     */       case 6:
/*  84 */         break; }  return c;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition, AxisAlignedBB paramAxisAlignedBB, List<AxisAlignedBB> paramList, @Nullable Entity paramEntity, boolean paramBoolean) {
/*  90 */     a(paramBlockPosition, paramAxisAlignedBB, paramList, paramIBlockData.e(paramWorld, paramBlockPosition));
/*  91 */     a(paramBlockPosition, paramAxisAlignedBB, paramList, x(paramIBlockData));
/*     */   }
/*     */   
/*     */   private AxisAlignedBB x(IBlockData paramIBlockData) {
/*  95 */     boolean bool = ((Boolean)paramIBlockData.<Boolean>get(SHORT)).booleanValue();
/*  96 */     switch (null.a[((EnumDirection)paramIBlockData.get(FACING)).ordinal()])
/*     */     
/*     */     { default:
/*  99 */         return bool ? K : D;
/*     */       case 2:
/* 101 */         return bool ? J : C;
/*     */       case 3:
/* 103 */         return bool ? M : F;
/*     */       case 4:
/* 105 */         return bool ? L : E;
/*     */       case 5:
/* 107 */         return bool ? O : I;
/*     */       case 6:
/* 109 */         break; }  return bool ? N : G;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean k(IBlockData paramIBlockData) {
/* 115 */     return (paramIBlockData.get(FACING) == EnumDirection.UP);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman) {
/* 120 */     if (paramEntityHuman.abilities.canInstantlyBuild) {
/* 121 */       BlockPosition blockPosition = paramBlockPosition.shift(((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).opposite());
/* 122 */       Block block = paramWorld.getType(blockPosition).getBlock();
/* 123 */       if (block == Blocks.PISTON || block == Blocks.STICKY_PISTON) {
/* 124 */         paramWorld.setAir(blockPosition);
/*     */       }
/*     */     } 
/* 127 */     super.a(paramWorld, paramBlockPosition, paramIBlockData, paramEntityHuman);
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 132 */     super.remove(paramWorld, paramBlockPosition, paramIBlockData);
/* 133 */     EnumDirection enumDirection = ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).opposite();
/* 134 */     paramBlockPosition = paramBlockPosition.shift(enumDirection);
/*     */     
/* 136 */     IBlockData iBlockData = paramWorld.getType(paramBlockPosition);
/* 137 */     if ((iBlockData.getBlock() == Blocks.PISTON || iBlockData.getBlock() == Blocks.STICKY_PISTON) && (
/* 138 */       (Boolean)iBlockData.<Boolean>get(BlockPiston.EXTENDED)).booleanValue()) {
/* 139 */       iBlockData.getBlock().b(paramWorld, paramBlockPosition, iBlockData, 0);
/* 140 */       paramWorld.setAir(paramBlockPosition);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/* 147 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/* 152 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(World paramWorld, BlockPosition paramBlockPosition) {
/* 157 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 162 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(Random paramRandom) {
/* 167 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/* 172 */     EnumDirection enumDirection = paramIBlockData.<EnumDirection>get(FACING);
/* 173 */     BlockPosition blockPosition = paramBlockPosition1.shift(enumDirection.opposite());
/* 174 */     IBlockData iBlockData = paramWorld.getType(blockPosition);
/* 175 */     if (iBlockData.getBlock() == Blocks.PISTON || iBlockData.getBlock() == Blocks.STICKY_PISTON) {
/* 176 */       iBlockData.doPhysics(paramWorld, blockPosition, paramBlock, paramBlockPosition2);
/*     */     } else {
/* 178 */       paramWorld.setAir(paramBlockPosition1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static EnumDirection b(int paramInt) {
/* 189 */     int i = paramInt & 0x7;
/* 190 */     if (i > 5) {
/* 191 */       return null;
/*     */     }
/* 193 */     return EnumDirection.fromType1(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 198 */     return new ItemStack((paramIBlockData.get(TYPE) == EnumPistonType.STICKY) ? Blocks.STICKY_PISTON : Blocks.PISTON);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 203 */     return getBlockData()
/* 204 */       .<Comparable, EnumDirection>set(FACING, b(paramInt))
/* 205 */       .set(TYPE, ((paramInt & 0x8) > 0) ? EnumPistonType.STICKY : EnumPistonType.DEFAULT);
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 210 */     int i = 0;
/*     */     
/* 212 */     i |= ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).a();
/*     */     
/* 214 */     if (paramIBlockData.get(TYPE) == EnumPistonType.STICKY) {
/* 215 */       i |= 0x8;
/*     */     }
/*     */     
/* 218 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/* 223 */     return paramIBlockData.set(FACING, paramEnumBlockRotation.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/* 228 */     return paramIBlockData.a(paramEnumBlockMirror.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 233 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING, TYPE, SHORT });
/*     */   }
/*     */   
/*     */   public enum EnumPistonType implements INamable {
/* 237 */     DEFAULT("normal"),
/* 238 */     STICKY("sticky");
/*     */     private final String c;
/*     */     
/*     */     EnumPistonType(String param1String1) {
/* 242 */       this.c = param1String1;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 247 */       return this.c;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 252 */       return this.c;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 258 */     if (paramEnumDirection == paramIBlockData.get(FACING)) {
/* 259 */       return EnumBlockFaceShape.SOLID;
/*     */     }
/* 261 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockPistonExtension.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */