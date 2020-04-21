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
/*     */ public class BlockChorusFruit
/*     */   extends Block
/*     */ {
/*  25 */   public static final BlockStateBoolean a = BlockStateBoolean.of("north");
/*  26 */   public static final BlockStateBoolean b = BlockStateBoolean.of("east");
/*  27 */   public static final BlockStateBoolean c = BlockStateBoolean.of("south");
/*  28 */   public static final BlockStateBoolean d = BlockStateBoolean.of("west");
/*  29 */   public static final BlockStateBoolean e = BlockStateBoolean.of("up");
/*  30 */   public static final BlockStateBoolean f = BlockStateBoolean.of("down");
/*     */   
/*     */   protected BlockChorusFruit() {
/*  33 */     super(Material.PLANT, MaterialMapColor.A);
/*  34 */     a(CreativeModeTab.c);
/*  35 */     w(this.blockStateList.getBlockData().<Comparable, Boolean>set(a, Boolean.valueOf(false)).<Comparable, Boolean>set(b, Boolean.valueOf(false)).<Comparable, Boolean>set(c, Boolean.valueOf(false)).<Comparable, Boolean>set(d, Boolean.valueOf(false)).<Comparable, Boolean>set(e, Boolean.valueOf(false)).set(f, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  40 */     Block block1 = paramIBlockAccess.getType(paramBlockPosition.down()).getBlock();
/*  41 */     Block block2 = paramIBlockAccess.getType(paramBlockPosition.up()).getBlock();
/*  42 */     Block block3 = paramIBlockAccess.getType(paramBlockPosition.north()).getBlock();
/*  43 */     Block block4 = paramIBlockAccess.getType(paramBlockPosition.east()).getBlock();
/*  44 */     Block block5 = paramIBlockAccess.getType(paramBlockPosition.south()).getBlock();
/*  45 */     Block block6 = paramIBlockAccess.getType(paramBlockPosition.west()).getBlock();
/*     */     
/*  47 */     return paramIBlockData
/*  48 */       .<Comparable, Boolean>set(f, Boolean.valueOf((block1 == this || block1 == Blocks.CHORUS_FLOWER || block1 == Blocks.END_STONE)))
/*  49 */       .<Comparable, Boolean>set(e, Boolean.valueOf((block2 == this || block2 == Blocks.CHORUS_FLOWER)))
/*  50 */       .<Comparable, Boolean>set(a, Boolean.valueOf((block3 == this || block3 == Blocks.CHORUS_FLOWER)))
/*  51 */       .<Comparable, Boolean>set(b, Boolean.valueOf((block4 == this || block4 == Blocks.CHORUS_FLOWER)))
/*  52 */       .<Comparable, Boolean>set(c, Boolean.valueOf((block5 == this || block5 == Blocks.CHORUS_FLOWER)))
/*  53 */       .set(d, Boolean.valueOf((block6 == this || block6 == Blocks.CHORUS_FLOWER)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  59 */     paramIBlockData = paramIBlockData.c(paramIBlockAccess, paramBlockPosition);
/*     */     
/*  61 */     float f1 = 0.1875F;
/*     */     
/*  63 */     float f2 = ((Boolean)paramIBlockData.<Boolean>get(d)).booleanValue() ? 0.0F : 0.1875F;
/*  64 */     float f3 = ((Boolean)paramIBlockData.<Boolean>get(f)).booleanValue() ? 0.0F : 0.1875F;
/*  65 */     float f4 = ((Boolean)paramIBlockData.<Boolean>get(a)).booleanValue() ? 0.0F : 0.1875F;
/*  66 */     float f5 = ((Boolean)paramIBlockData.<Boolean>get(b)).booleanValue() ? 1.0F : 0.8125F;
/*  67 */     float f6 = ((Boolean)paramIBlockData.<Boolean>get(e)).booleanValue() ? 1.0F : 0.8125F;
/*  68 */     float f7 = ((Boolean)paramIBlockData.<Boolean>get(c)).booleanValue() ? 1.0F : 0.8125F;
/*     */     
/*  70 */     return new AxisAlignedBB(f2, f3, f4, f5, f6, f7);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition, AxisAlignedBB paramAxisAlignedBB, List<AxisAlignedBB> paramList, @Nullable Entity paramEntity, boolean paramBoolean) {
/*  75 */     if (!paramBoolean) {
/*  76 */       paramIBlockData = paramIBlockData.c(paramWorld, paramBlockPosition);
/*     */     }
/*     */     
/*  79 */     float f1 = 0.1875F;
/*  80 */     float f2 = 0.8125F;
/*  81 */     a(paramBlockPosition, paramAxisAlignedBB, paramList, new AxisAlignedBB(0.1875D, 0.1875D, 0.1875D, 0.8125D, 0.8125D, 0.8125D));
/*     */     
/*  83 */     if (((Boolean)paramIBlockData.<Boolean>get(d)).booleanValue()) {
/*  84 */       a(paramBlockPosition, paramAxisAlignedBB, paramList, new AxisAlignedBB(0.0D, 0.1875D, 0.1875D, 0.1875D, 0.8125D, 0.8125D));
/*     */     }
/*  86 */     if (((Boolean)paramIBlockData.<Boolean>get(b)).booleanValue()) {
/*  87 */       a(paramBlockPosition, paramAxisAlignedBB, paramList, new AxisAlignedBB(0.8125D, 0.1875D, 0.1875D, 1.0D, 0.8125D, 0.8125D));
/*     */     }
/*     */     
/*  90 */     if (((Boolean)paramIBlockData.<Boolean>get(e)).booleanValue()) {
/*  91 */       a(paramBlockPosition, paramAxisAlignedBB, paramList, new AxisAlignedBB(0.1875D, 0.8125D, 0.1875D, 0.8125D, 1.0D, 0.8125D));
/*     */     }
/*  93 */     if (((Boolean)paramIBlockData.<Boolean>get(f)).booleanValue()) {
/*  94 */       a(paramBlockPosition, paramAxisAlignedBB, paramList, new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.1875D, 0.8125D));
/*     */     }
/*     */     
/*  97 */     if (((Boolean)paramIBlockData.<Boolean>get(a)).booleanValue()) {
/*  98 */       a(paramBlockPosition, paramAxisAlignedBB, paramList, new AxisAlignedBB(0.1875D, 0.1875D, 0.0D, 0.8125D, 0.8125D, 0.1875D));
/*     */     }
/* 100 */     if (((Boolean)paramIBlockData.<Boolean>get(c)).booleanValue()) {
/* 101 */       a(paramBlockPosition, paramAxisAlignedBB, paramList, new AxisAlignedBB(0.1875D, 0.1875D, 0.8125D, 0.8125D, 0.8125D, 1.0D));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 107 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, Random paramRandom) {
/* 112 */     if (!b(paramWorld, paramBlockPosition)) {
/* 113 */       paramWorld.setAir(paramBlockPosition, true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/* 119 */     return Items.CHORUS_FRUIT;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(Random paramRandom) {
/* 124 */     return paramRandom.nextInt(2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/* 129 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/* 134 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(World paramWorld, BlockPosition paramBlockPosition) {
/* 139 */     if (super.canPlace(paramWorld, paramBlockPosition)) {
/* 140 */       return b(paramWorld, paramBlockPosition);
/*     */     }
/*     */     
/* 143 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/* 148 */     if (!b(paramWorld, paramBlockPosition1)) {
/* 149 */       paramWorld.a(paramBlockPosition1, this, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b(World paramWorld, BlockPosition paramBlockPosition) {
/* 156 */     boolean bool1 = paramWorld.isEmpty(paramBlockPosition.up());
/* 157 */     boolean bool2 = paramWorld.isEmpty(paramBlockPosition.down());
/* 158 */     for (EnumDirection enumDirection : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/* 159 */       BlockPosition blockPosition = paramBlockPosition.shift(enumDirection);
/* 160 */       Block block1 = paramWorld.getType(blockPosition).getBlock();
/* 161 */       if (block1 == this) {
/* 162 */         if (!bool1 && !bool2) {
/* 163 */           return false;
/*     */         }
/* 165 */         Block block2 = paramWorld.getType(blockPosition.down()).getBlock();
/* 166 */         if (block2 == this || block2 == Blocks.END_STONE) {
/* 167 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 171 */     Block block = paramWorld.getType(paramBlockPosition.down()).getBlock();
/* 172 */     return (block == this || block == Blocks.END_STONE);
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
/*     */   protected BlockStateList getStateList() {
/* 188 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { a, b, c, d, e, f });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 193 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 198 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockChorusFruit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */