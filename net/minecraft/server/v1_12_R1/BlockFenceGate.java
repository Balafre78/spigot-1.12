/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
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
/*     */ public class BlockFenceGate
/*     */   extends BlockFacingHorizontal
/*     */ {
/*  21 */   public static final BlockStateBoolean OPEN = BlockStateBoolean.of("open");
/*  22 */   public static final BlockStateBoolean POWERED = BlockStateBoolean.of("powered");
/*  23 */   public static final BlockStateBoolean IN_WALL = BlockStateBoolean.of("in_wall");
/*     */   
/*  25 */   protected static final AxisAlignedBB d = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D);
/*  26 */   protected static final AxisAlignedBB e = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D);
/*     */   
/*  28 */   protected static final AxisAlignedBB f = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 0.8125D, 0.625D);
/*  29 */   protected static final AxisAlignedBB g = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 0.8125D, 1.0D);
/*     */   
/*  31 */   protected static final AxisAlignedBB B = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.5D, 0.625D);
/*  32 */   protected static final AxisAlignedBB C = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.5D, 1.0D);
/*     */   
/*     */   public BlockFenceGate(BlockWood.EnumLogVariant paramEnumLogVariant) {
/*  35 */     super(Material.WOOD, paramEnumLogVariant.c());
/*     */     
/*  37 */     w(this.blockStateList.getBlockData().<Comparable, Boolean>set(OPEN, Boolean.valueOf(false)).<Comparable, Boolean>set(POWERED, Boolean.valueOf(false)).set(IN_WALL, Boolean.valueOf(false)));
/*  38 */     a(CreativeModeTab.d);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  43 */     paramIBlockData = updateState(paramIBlockData, paramIBlockAccess, paramBlockPosition);
/*  44 */     if (((Boolean)paramIBlockData.<Boolean>get(IN_WALL)).booleanValue()) {
/*  45 */       return (((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).k() == EnumDirection.EnumAxis.X) ? g : f;
/*     */     }
/*  47 */     return (((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).k() == EnumDirection.EnumAxis.X) ? e : d;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  52 */     EnumDirection.EnumAxis enumAxis = ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).k();
/*  53 */     if ((enumAxis == EnumDirection.EnumAxis.Z && (paramIBlockAccess
/*  54 */       .getType(paramBlockPosition.west()).getBlock() == Blocks.COBBLESTONE_WALL || paramIBlockAccess.getType(paramBlockPosition.east()).getBlock() == Blocks.COBBLESTONE_WALL)) || (enumAxis == EnumDirection.EnumAxis.X && (paramIBlockAccess
/*  55 */       .getType(paramBlockPosition.north()).getBlock() == Blocks.COBBLESTONE_WALL || paramIBlockAccess.getType(paramBlockPosition.south()).getBlock() == Blocks.COBBLESTONE_WALL)))
/*     */     {
/*  57 */       paramIBlockData = paramIBlockData.set(IN_WALL, Boolean.valueOf(true));
/*     */     }
/*     */     
/*  60 */     return paramIBlockData;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/*  65 */     return paramIBlockData.set(FACING, paramEnumBlockRotation.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/*  70 */     return paramIBlockData.a(paramEnumBlockMirror.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(World paramWorld, BlockPosition paramBlockPosition) {
/*  75 */     if (paramWorld.getType(paramBlockPosition.down()).getMaterial().isBuildable()) {
/*  76 */       return super.canPlace(paramWorld, paramBlockPosition);
/*     */     }
/*     */     
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB a(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  85 */     if (((Boolean)paramIBlockData.<Boolean>get(OPEN)).booleanValue()) {
/*  86 */       return k;
/*     */     }
/*     */     
/*  89 */     return (((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).k() == EnumDirection.EnumAxis.Z) ? B : C;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/*  94 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 104 */     return ((Boolean)paramIBlockAccess.getType(paramBlockPosition).<Boolean>get(OPEN)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, EntityLiving paramEntityLiving) {
/* 109 */     boolean bool = paramWorld.isBlockIndirectlyPowered(paramBlockPosition);
/* 110 */     return getBlockData().<Comparable, EnumDirection>set(FACING, paramEntityLiving.getDirection()).<Comparable, Boolean>set(OPEN, Boolean.valueOf(bool)).<Comparable, Boolean>set(POWERED, Boolean.valueOf(bool)).set(IN_WALL, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean interact(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 115 */     if (((Boolean)paramIBlockData.<Boolean>get(OPEN)).booleanValue()) {
/* 116 */       paramIBlockData = paramIBlockData.set(OPEN, Boolean.valueOf(false));
/* 117 */       paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData, 10);
/*     */     } else {
/*     */       
/* 120 */       EnumDirection enumDirection = EnumDirection.fromAngle(paramEntityHuman.yaw);
/* 121 */       if (paramIBlockData.get(FACING) == enumDirection.opposite()) {
/* 122 */         paramIBlockData = paramIBlockData.set(FACING, enumDirection);
/*     */       }
/* 124 */       paramIBlockData = paramIBlockData.set(OPEN, Boolean.valueOf(true));
/* 125 */       paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData, 10);
/*     */     } 
/*     */     
/* 128 */     paramWorld.a(paramEntityHuman, ((Boolean)paramIBlockData.<Boolean>get(OPEN)).booleanValue() ? 1008 : 1014, paramBlockPosition, 0);
/* 129 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/* 134 */     if (paramWorld.isClientSide) {
/*     */       return;
/*     */     }
/*     */     
/* 138 */     boolean bool = paramWorld.isBlockIndirectlyPowered(paramBlockPosition1);
/* 139 */     if (((Boolean)paramIBlockData.<Boolean>get(POWERED)).booleanValue() != bool) {
/* 140 */       paramWorld.setTypeAndData(paramBlockPosition1, paramIBlockData.<Comparable, Boolean>set(POWERED, Boolean.valueOf(bool)).set(OPEN, Boolean.valueOf(bool)), 2);
/* 141 */       if (((Boolean)paramIBlockData.<Boolean>get(OPEN)).booleanValue() != bool) {
/* 142 */         paramWorld.a((EntityHuman)null, bool ? 1008 : 1014, paramBlockPosition1, 0);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 154 */     return getBlockData()
/* 155 */       .<Comparable, EnumDirection>set(FACING, EnumDirection.fromType2(paramInt))
/* 156 */       .<Comparable, Boolean>set(OPEN, Boolean.valueOf(((paramInt & 0x4) != 0)))
/* 157 */       .set(POWERED, Boolean.valueOf(((paramInt & 0x8) != 0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 162 */     int i = 0;
/*     */     
/* 164 */     i |= ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).get2DRotationValue();
/*     */     
/* 166 */     if (((Boolean)paramIBlockData.<Boolean>get(POWERED)).booleanValue()) {
/* 167 */       i |= 0x8;
/*     */     }
/*     */     
/* 170 */     if (((Boolean)paramIBlockData.<Boolean>get(OPEN)).booleanValue()) {
/* 171 */       i |= 0x4;
/*     */     }
/*     */     
/* 174 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 179 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING, OPEN, POWERED, IN_WALL });
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 184 */     if (paramEnumDirection == EnumDirection.UP || paramEnumDirection == EnumDirection.DOWN) {
/* 185 */       return EnumBlockFaceShape.UNDEFINED;
/*     */     }
/* 187 */     return (((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).k() == paramEnumDirection.e().k()) ? EnumBlockFaceShape.MIDDLE_POLE : EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockFenceGate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */