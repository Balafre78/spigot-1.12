/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
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
/*     */ public class BlockTorch
/*     */   extends Block
/*     */ {
/*  23 */   public static final BlockStateDirection FACING = BlockStateDirection.of("facing", new Predicate<EnumDirection>()
/*     */       {
/*     */         public boolean a(@Nullable EnumDirection param1EnumDirection) {
/*  26 */           return (param1EnumDirection != EnumDirection.DOWN);
/*     */         }
/*     */       });
/*     */ 
/*     */   
/*  31 */   protected static final AxisAlignedBB b = new AxisAlignedBB(0.4000000059604645D, 0.0D, 0.4000000059604645D, 0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
/*     */   
/*  33 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.3499999940395355D, 0.20000000298023224D, 0.699999988079071D, 0.6499999761581421D, 0.800000011920929D, 1.0D);
/*  34 */   protected static final AxisAlignedBB d = new AxisAlignedBB(0.3499999940395355D, 0.20000000298023224D, 0.0D, 0.6499999761581421D, 0.800000011920929D, 0.30000001192092896D);
/*  35 */   protected static final AxisAlignedBB e = new AxisAlignedBB(0.699999988079071D, 0.20000000298023224D, 0.3499999940395355D, 1.0D, 0.800000011920929D, 0.6499999761581421D);
/*  36 */   protected static final AxisAlignedBB f = new AxisAlignedBB(0.0D, 0.20000000298023224D, 0.3499999940395355D, 0.30000001192092896D, 0.800000011920929D, 0.6499999761581421D);
/*     */   
/*     */   protected BlockTorch() {
/*  39 */     super(Material.ORIENTABLE);
/*  40 */     w(this.blockStateList.getBlockData().set(FACING, EnumDirection.UP));
/*  41 */     a(true);
/*  42 */     a(CreativeModeTab.c);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  47 */     switch (null.a[((EnumDirection)paramIBlockData.get(FACING)).ordinal()]) {
/*     */       case 1:
/*  49 */         return f;
/*     */       case 2:
/*  51 */         return e;
/*     */       case 3:
/*  53 */         return d;
/*     */       case 4:
/*  55 */         return c;
/*     */     } 
/*  57 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB a(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  64 */     return k;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/*  74 */     return false;
/*     */   }
/*     */   
/*     */   private boolean b(World paramWorld, BlockPosition paramBlockPosition) {
/*  78 */     Block block = paramWorld.getType(paramBlockPosition).getBlock();
/*  79 */     boolean bool1 = (block == Blocks.END_GATEWAY || block == Blocks.LIT_PUMPKIN) ? true : false;
/*  80 */     if (paramWorld.getType(paramBlockPosition).q()) {
/*  81 */       return !bool1;
/*     */     }
/*  83 */     boolean bool2 = (block instanceof BlockFence || block == Blocks.GLASS || block == Blocks.COBBLESTONE_WALL || block == Blocks.STAINED_GLASS) ? true : false;
/*  84 */     return (bool2 && !bool1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(World paramWorld, BlockPosition paramBlockPosition) {
/*  89 */     for (EnumDirection enumDirection : FACING.c()) {
/*  90 */       if (a(paramWorld, paramBlockPosition, enumDirection)) {
/*  91 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  95 */     return false;
/*     */   }
/*     */   
/*     */   private boolean a(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/*  99 */     BlockPosition blockPosition = paramBlockPosition.shift(paramEnumDirection.opposite());
/* 100 */     IBlockData iBlockData = paramWorld.getType(blockPosition);
/* 101 */     Block block = iBlockData.getBlock();
/* 102 */     EnumBlockFaceShape enumBlockFaceShape = iBlockData.d(paramWorld, blockPosition, paramEnumDirection);
/* 103 */     if (paramEnumDirection.equals(EnumDirection.UP) && b(paramWorld, blockPosition))
/* 104 */       return true; 
/* 105 */     if (paramEnumDirection == EnumDirection.UP || paramEnumDirection == EnumDirection.DOWN) {
/* 106 */       return false;
/*     */     }
/*     */     
/* 109 */     return (!c(block) && enumBlockFaceShape == EnumBlockFaceShape.SOLID);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, EntityLiving paramEntityLiving) {
/* 114 */     if (a(paramWorld, paramBlockPosition, paramEnumDirection)) {
/* 115 */       return getBlockData().set(FACING, paramEnumDirection);
/*     */     }
/*     */     
/* 118 */     for (EnumDirection enumDirection : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/* 119 */       if (a(paramWorld, paramBlockPosition, enumDirection)) {
/* 120 */         return getBlockData().set(FACING, enumDirection);
/*     */       }
/*     */     } 
/*     */     
/* 124 */     return getBlockData();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 129 */     f(paramWorld, paramBlockPosition, paramIBlockData);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/* 134 */     e(paramWorld, paramBlockPosition1, paramIBlockData);
/*     */   }
/*     */   
/*     */   protected boolean e(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 138 */     if (!f(paramWorld, paramBlockPosition, paramIBlockData)) {
/* 139 */       return true;
/*     */     }
/*     */     
/* 142 */     EnumDirection enumDirection1 = paramIBlockData.<EnumDirection>get(FACING);
/* 143 */     EnumDirection.EnumAxis enumAxis = enumDirection1.k();
/* 144 */     EnumDirection enumDirection2 = enumDirection1.opposite();
/*     */     
/* 146 */     BlockPosition blockPosition = paramBlockPosition.shift(enumDirection2);
/*     */     
/* 148 */     boolean bool = false;
/* 149 */     if (enumAxis.c() && paramWorld.getType(blockPosition).d(paramWorld, blockPosition, enumDirection1) != EnumBlockFaceShape.SOLID) {
/* 150 */       bool = true;
/* 151 */     } else if (enumAxis.b() && !b(paramWorld, blockPosition)) {
/* 152 */       bool = true;
/*     */     } 
/*     */     
/* 155 */     if (bool) {
/* 156 */       b(paramWorld, paramBlockPosition, paramIBlockData, 0);
/* 157 */       paramWorld.setAir(paramBlockPosition);
/* 158 */       return true;
/*     */     } 
/*     */     
/* 161 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean f(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 165 */     if (paramIBlockData.getBlock() == this && 
/* 166 */       a(paramWorld, paramBlockPosition, paramIBlockData.<EnumDirection>get(FACING))) {
/* 167 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 171 */     if (paramWorld.getType(paramBlockPosition).getBlock() == this) {
/* 172 */       b(paramWorld, paramBlockPosition, paramIBlockData, 0);
/* 173 */       paramWorld.setAir(paramBlockPosition);
/*     */     } 
/*     */     
/* 176 */     return false;
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
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 205 */     IBlockData iBlockData = getBlockData();
/* 206 */     switch (paramInt)
/*     */     { case 1:
/* 208 */         iBlockData = iBlockData.set(FACING, EnumDirection.EAST);
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
/* 225 */         return iBlockData;case 2: iBlockData = iBlockData.set(FACING, EnumDirection.WEST); return iBlockData;case 3: iBlockData = iBlockData.set(FACING, EnumDirection.SOUTH); return iBlockData;case 4: iBlockData = iBlockData.set(FACING, EnumDirection.NORTH); return iBlockData; }  iBlockData = iBlockData.set(FACING, EnumDirection.UP); return iBlockData;
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 230 */     int i = 0;
/*     */     
/* 232 */     switch (null.a[((EnumDirection)paramIBlockData.get(FACING)).ordinal()])
/*     */     { case 1:
/* 234 */         i |= 0x1;
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
/* 252 */         return i;case 2: i |= 0x2; return i;case 3: i |= 0x3; return i;case 4: i |= 0x4; return i; }  i |= 0x5; return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/* 257 */     return paramIBlockData.set(FACING, paramEnumBlockRotation.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/* 262 */     return paramIBlockData.a(paramEnumBlockMirror.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 267 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING });
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 272 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockTorch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */