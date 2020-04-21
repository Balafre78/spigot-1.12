/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
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
/*     */ public class BlockStairs
/*     */   extends Block
/*     */ {
/*  31 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*  32 */   public static final BlockStateEnum<EnumHalf> HALF = BlockStateEnum.of("half", EnumHalf.class);
/*  33 */   public static final BlockStateEnum<EnumStairShape> SHAPE = BlockStateEnum.of("shape", EnumStairShape.class);
/*     */   
/*  35 */   protected static final AxisAlignedBB d = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  36 */   protected static final AxisAlignedBB e = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 0.5D, 1.0D, 1.0D);
/*  37 */   protected static final AxisAlignedBB f = new AxisAlignedBB(0.5D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  38 */   protected static final AxisAlignedBB g = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 0.5D);
/*  39 */   protected static final AxisAlignedBB B = new AxisAlignedBB(0.0D, 0.5D, 0.5D, 1.0D, 1.0D, 1.0D);
/*     */   
/*  41 */   protected static final AxisAlignedBB C = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 0.5D, 1.0D, 0.5D);
/*  42 */   protected static final AxisAlignedBB D = new AxisAlignedBB(0.5D, 0.5D, 0.0D, 1.0D, 1.0D, 0.5D);
/*  43 */   protected static final AxisAlignedBB E = new AxisAlignedBB(0.0D, 0.5D, 0.5D, 0.5D, 1.0D, 1.0D);
/*  44 */   protected static final AxisAlignedBB F = new AxisAlignedBB(0.5D, 0.5D, 0.5D, 1.0D, 1.0D, 1.0D);
/*     */   
/*  46 */   protected static final AxisAlignedBB G = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
/*  47 */   protected static final AxisAlignedBB H = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5D, 0.5D, 1.0D);
/*  48 */   protected static final AxisAlignedBB I = new AxisAlignedBB(0.5D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
/*  49 */   protected static final AxisAlignedBB J = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 0.5D);
/*  50 */   protected static final AxisAlignedBB K = new AxisAlignedBB(0.0D, 0.0D, 0.5D, 1.0D, 0.5D, 1.0D);
/*     */   
/*  52 */   protected static final AxisAlignedBB L = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5D, 0.5D, 0.5D);
/*  53 */   protected static final AxisAlignedBB M = new AxisAlignedBB(0.5D, 0.0D, 0.0D, 1.0D, 0.5D, 0.5D);
/*  54 */   protected static final AxisAlignedBB N = new AxisAlignedBB(0.0D, 0.0D, 0.5D, 0.5D, 0.5D, 1.0D);
/*  55 */   protected static final AxisAlignedBB O = new AxisAlignedBB(0.5D, 0.0D, 0.5D, 1.0D, 0.5D, 1.0D);
/*     */   
/*     */   private final Block P;
/*     */   private final IBlockData Q;
/*     */   
/*     */   protected BlockStairs(IBlockData paramIBlockData) {
/*  61 */     super((paramIBlockData.getBlock()).material);
/*  62 */     w(this.blockStateList.getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.NORTH).<EnumHalf, EnumHalf>set(HALF, EnumHalf.BOTTOM).set(SHAPE, EnumStairShape.STRAIGHT));
/*  63 */     this.P = paramIBlockData.getBlock();
/*  64 */     this.Q = paramIBlockData;
/*  65 */     c(this.P.strength);
/*  66 */     b(this.P.durability / 3.0F);
/*  67 */     a(this.P.stepSound);
/*  68 */     e(255);
/*  69 */     a(CreativeModeTab.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition, AxisAlignedBB paramAxisAlignedBB, List<AxisAlignedBB> paramList, @Nullable Entity paramEntity, boolean paramBoolean) {
/*  74 */     if (!paramBoolean) {
/*  75 */       paramIBlockData = updateState(paramIBlockData, paramWorld, paramBlockPosition);
/*     */     }
/*     */     
/*  78 */     for (AxisAlignedBB axisAlignedBB : y(paramIBlockData)) {
/*  79 */       a(paramBlockPosition, paramAxisAlignedBB, paramList, axisAlignedBB);
/*     */     }
/*     */   }
/*     */   
/*     */   private static List<AxisAlignedBB> y(IBlockData paramIBlockData) {
/*  84 */     ArrayList<AxisAlignedBB> arrayList = Lists.newArrayList();
/*     */     
/*  86 */     boolean bool = (paramIBlockData.get(HALF) == EnumHalf.TOP) ? true : false;
/*  87 */     arrayList.add(bool ? d : G);
/*     */     
/*  89 */     EnumStairShape enumStairShape = paramIBlockData.<EnumStairShape>get(SHAPE);
/*  90 */     if (enumStairShape == EnumStairShape.STRAIGHT || enumStairShape == EnumStairShape.INNER_LEFT || enumStairShape == EnumStairShape.INNER_RIGHT) {
/*  91 */       arrayList.add(z(paramIBlockData));
/*     */     }
/*     */     
/*  94 */     if (enumStairShape != EnumStairShape.STRAIGHT) {
/*  95 */       arrayList.add(A(paramIBlockData));
/*     */     }
/*  97 */     return arrayList;
/*     */   }
/*     */   
/*     */   private static AxisAlignedBB z(IBlockData paramIBlockData) {
/* 101 */     boolean bool = (paramIBlockData.get(HALF) == EnumHalf.TOP) ? true : false;
/*     */     
/* 103 */     switch (null.a[((EnumDirection)paramIBlockData.get(FACING)).ordinal()])
/*     */     
/*     */     { default:
/* 106 */         return bool ? J : g;
/*     */       case 2:
/* 108 */         return bool ? K : B;
/*     */       case 3:
/* 110 */         return bool ? H : e;
/*     */       case 4:
/* 112 */         break; }  return bool ? I : f;
/*     */   }
/*     */ 
/*     */   
/*     */   private static AxisAlignedBB A(IBlockData paramIBlockData) {
/* 117 */     EnumDirection enumDirection2, enumDirection1 = paramIBlockData.<EnumDirection>get(FACING);
/*     */     
/* 119 */     switch (null.b[((EnumStairShape)paramIBlockData.get((IBlockState)SHAPE)).ordinal()]) {
/*     */       
/*     */       default:
/* 122 */         enumDirection2 = enumDirection1;
/*     */         break;
/*     */       case 2:
/* 125 */         enumDirection2 = enumDirection1.e();
/*     */         break;
/*     */       case 3:
/* 128 */         enumDirection2 = enumDirection1.opposite();
/*     */         break;
/*     */       case 4:
/* 131 */         enumDirection2 = enumDirection1.f();
/*     */         break;
/*     */     } 
/*     */     
/* 135 */     boolean bool = (paramIBlockData.get(HALF) == EnumHalf.TOP) ? true : false;
/* 136 */     switch (null.a[enumDirection2.ordinal()])
/*     */     
/*     */     { default:
/* 139 */         return bool ? L : C;
/*     */       case 4:
/* 141 */         return bool ? M : D;
/*     */       case 2:
/* 143 */         return bool ? O : F;
/*     */       case 3:
/* 145 */         break; }  return bool ? N : E;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 151 */     paramIBlockData = updateState(paramIBlockData, paramIBlockAccess, paramBlockPosition);
/* 152 */     if (paramEnumDirection.k() == EnumDirection.EnumAxis.Y) {
/* 153 */       return (((paramEnumDirection == EnumDirection.UP) ? true : false) == ((paramIBlockData.get(HALF) == EnumHalf.TOP) ? true : false)) ? EnumBlockFaceShape.SOLID : EnumBlockFaceShape.UNDEFINED;
/*     */     }
/*     */     
/* 156 */     EnumStairShape enumStairShape = paramIBlockData.<EnumStairShape>get(SHAPE);
/* 157 */     if (enumStairShape == EnumStairShape.OUTER_LEFT || enumStairShape == EnumStairShape.OUTER_RIGHT) {
/* 158 */       return EnumBlockFaceShape.UNDEFINED;
/*     */     }
/*     */     
/* 161 */     EnumDirection enumDirection = paramIBlockData.<EnumDirection>get(FACING);
/* 162 */     switch (null.b[enumStairShape.ordinal()]) {
/*     */       case 5:
/* 164 */         return (enumDirection == paramEnumDirection) ? EnumBlockFaceShape.SOLID : EnumBlockFaceShape.UNDEFINED;
/*     */       case 4:
/* 166 */         return (enumDirection == paramEnumDirection || enumDirection == paramEnumDirection.e()) ? EnumBlockFaceShape.SOLID : EnumBlockFaceShape.UNDEFINED;
/*     */       case 3:
/* 168 */         return (enumDirection == paramEnumDirection || enumDirection == paramEnumDirection.f()) ? EnumBlockFaceShape.SOLID : EnumBlockFaceShape.UNDEFINED;
/*     */     } 
/*     */     
/* 171 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/* 176 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/* 181 */     return false;
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
/*     */   public void attack(World paramWorld, BlockPosition paramBlockPosition, EntityHuman paramEntityHuman) {
/* 196 */     this.P.attack(paramWorld, paramBlockPosition, paramEntityHuman);
/*     */   }
/*     */ 
/*     */   
/*     */   public void postBreak(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 201 */     this.P.postBreak(paramWorld, paramBlockPosition, paramIBlockData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float a(Entity paramEntity) {
/* 211 */     return this.P.a(paramEntity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(World paramWorld) {
/* 221 */     return this.P.a(paramWorld);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3D a(World paramWorld, BlockPosition paramBlockPosition, Entity paramEntity, Vec3D paramVec3D) {
/* 231 */     return this.P.a(paramWorld, paramBlockPosition, paramEntity, paramVec3D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m() {
/* 236 */     return this.P.m();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData paramIBlockData, boolean paramBoolean) {
/* 241 */     return this.P.a(paramIBlockData, paramBoolean);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(World paramWorld, BlockPosition paramBlockPosition) {
/* 246 */     return this.P.canPlace(paramWorld, paramBlockPosition);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 251 */     this.Q.doPhysics(paramWorld, paramBlockPosition, Blocks.AIR, paramBlockPosition);
/* 252 */     this.P.onPlace(paramWorld, paramBlockPosition, this.Q);
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 257 */     this.P.remove(paramWorld, paramBlockPosition, this.Q);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stepOn(World paramWorld, BlockPosition paramBlockPosition, Entity paramEntity) {
/* 267 */     this.P.stepOn(paramWorld, paramBlockPosition, paramEntity);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, Random paramRandom) {
/* 272 */     this.P.b(paramWorld, paramBlockPosition, paramIBlockData, paramRandom);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean interact(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 277 */     return this.P.interact(paramWorld, paramBlockPosition, this.Q, paramEntityHuman, paramEnumHand, EnumDirection.DOWN, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void wasExploded(World paramWorld, BlockPosition paramBlockPosition, Explosion paramExplosion) {
/* 282 */     this.P.wasExploded(paramWorld, paramBlockPosition, paramExplosion);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean k(IBlockData paramIBlockData) {
/* 287 */     return (paramIBlockData.get(HALF) == EnumHalf.TOP);
/*     */   }
/*     */ 
/*     */   
/*     */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 292 */     return this.P.c(this.Q, paramIBlockAccess, paramBlockPosition);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, EntityLiving paramEntityLiving) {
/* 297 */     IBlockData iBlockData = super.getPlacedState(paramWorld, paramBlockPosition, paramEnumDirection, paramFloat1, paramFloat2, paramFloat3, paramInt, paramEntityLiving);
/*     */     
/* 299 */     iBlockData = iBlockData.<Comparable, EnumDirection>set(FACING, paramEntityLiving.getDirection()).set(SHAPE, EnumStairShape.STRAIGHT);
/*     */     
/* 301 */     if (paramEnumDirection == EnumDirection.DOWN || (paramEnumDirection != EnumDirection.UP && paramFloat2 > 0.5D)) {
/* 302 */       return iBlockData.set(HALF, EnumHalf.TOP);
/*     */     }
/*     */     
/* 305 */     return iBlockData.set(HALF, EnumHalf.BOTTOM);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public MovingObjectPosition a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition, Vec3D paramVec3D1, Vec3D paramVec3D2) {
/* 311 */     ArrayList<MovingObjectPosition> arrayList = Lists.newArrayList();
/* 312 */     for (AxisAlignedBB axisAlignedBB : y(updateState(paramIBlockData, paramWorld, paramBlockPosition))) {
/* 313 */       arrayList.add(a(paramBlockPosition, paramVec3D1, paramVec3D2, axisAlignedBB));
/*     */     }
/*     */ 
/*     */     
/* 317 */     MovingObjectPosition movingObjectPosition = null;
/* 318 */     double d = 0.0D;
/*     */     
/* 320 */     for (MovingObjectPosition movingObjectPosition1 : arrayList) {
/* 321 */       if (movingObjectPosition1 == null) {
/*     */         continue;
/*     */       }
/* 324 */       double d1 = movingObjectPosition1.pos.distanceSquared(paramVec3D2);
/*     */       
/* 326 */       if (d1 > d) {
/* 327 */         movingObjectPosition = movingObjectPosition1;
/* 328 */         d = d1;
/*     */       } 
/*     */     } 
/*     */     
/* 332 */     return movingObjectPosition;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 338 */     IBlockData iBlockData = getBlockData().set(HALF, ((paramInt & 0x4) > 0) ? EnumHalf.TOP : EnumHalf.BOTTOM);
/*     */     
/* 340 */     iBlockData = iBlockData.set(FACING, EnumDirection.fromType1(5 - (paramInt & 0x3)));
/*     */     
/* 342 */     return iBlockData;
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 347 */     int i = 0;
/*     */     
/* 349 */     if (paramIBlockData.get(HALF) == EnumHalf.TOP) {
/* 350 */       i |= 0x4;
/*     */     }
/*     */     
/* 353 */     i |= 5 - ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).a();
/*     */     
/* 355 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 360 */     return paramIBlockData.set(SHAPE, g(paramIBlockData, paramIBlockAccess, paramBlockPosition));
/*     */   }
/*     */   
/*     */   private static EnumStairShape g(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 364 */     EnumDirection enumDirection = paramIBlockData.<EnumDirection>get(FACING);
/* 365 */     IBlockData iBlockData1 = paramIBlockAccess.getType(paramBlockPosition.shift(enumDirection));
/* 366 */     if (x(iBlockData1) && paramIBlockData.get(HALF) == iBlockData1.get(HALF)) {
/* 367 */       EnumDirection enumDirection1 = iBlockData1.<EnumDirection>get(FACING);
/* 368 */       if (enumDirection1.k() != ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).k() && d(paramIBlockData, paramIBlockAccess, paramBlockPosition, enumDirection1.opposite())) {
/* 369 */         if (enumDirection1 == enumDirection.f()) {
/* 370 */           return EnumStairShape.OUTER_LEFT;
/*     */         }
/* 372 */         return EnumStairShape.OUTER_RIGHT;
/*     */       } 
/*     */     } 
/*     */     
/* 376 */     IBlockData iBlockData2 = paramIBlockAccess.getType(paramBlockPosition.shift(enumDirection.opposite()));
/* 377 */     if (x(iBlockData2) && paramIBlockData.get(HALF) == iBlockData2.get(HALF)) {
/* 378 */       EnumDirection enumDirection1 = iBlockData2.<EnumDirection>get(FACING);
/* 379 */       if (enumDirection1.k() != ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).k() && d(paramIBlockData, paramIBlockAccess, paramBlockPosition, enumDirection1)) {
/* 380 */         if (enumDirection1 == enumDirection.f()) {
/* 381 */           return EnumStairShape.INNER_LEFT;
/*     */         }
/* 383 */         return EnumStairShape.INNER_RIGHT;
/*     */       } 
/*     */     } 
/*     */     
/* 387 */     return EnumStairShape.STRAIGHT;
/*     */   }
/*     */   
/*     */   private static boolean d(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 391 */     IBlockData iBlockData = paramIBlockAccess.getType(paramBlockPosition.shift(paramEnumDirection));
/* 392 */     return (!x(iBlockData) || iBlockData.get(FACING) != paramIBlockData.get(FACING) || iBlockData.get(HALF) != paramIBlockData.get(HALF));
/*     */   }
/*     */   
/*     */   public static boolean x(IBlockData paramIBlockData) {
/* 396 */     return paramIBlockData.getBlock() instanceof BlockStairs;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/* 401 */     return paramIBlockData.set(FACING, paramEnumBlockRotation.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/* 406 */     EnumDirection enumDirection = paramIBlockData.<EnumDirection>get(FACING);
/* 407 */     EnumStairShape enumStairShape = paramIBlockData.<EnumStairShape>get(SHAPE);
/* 408 */     switch (null.c[paramEnumBlockMirror.ordinal()]) {
/*     */       case 1:
/* 410 */         if (enumDirection.k() == EnumDirection.EnumAxis.Z) {
/* 411 */           switch (null.b[enumStairShape.ordinal()]) {
/*     */             case 4:
/* 413 */               return paramIBlockData.a(EnumBlockRotation.CLOCKWISE_180).set(SHAPE, EnumStairShape.INNER_RIGHT);
/*     */             case 3:
/* 415 */               return paramIBlockData.a(EnumBlockRotation.CLOCKWISE_180).set(SHAPE, EnumStairShape.INNER_LEFT);
/*     */             case 1:
/* 417 */               return paramIBlockData.a(EnumBlockRotation.CLOCKWISE_180).set(SHAPE, EnumStairShape.OUTER_RIGHT);
/*     */             case 2:
/* 419 */               return paramIBlockData.a(EnumBlockRotation.CLOCKWISE_180).set(SHAPE, EnumStairShape.OUTER_LEFT);
/*     */           } 
/* 421 */           return paramIBlockData.a(EnumBlockRotation.CLOCKWISE_180);
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 2:
/* 426 */         if (enumDirection.k() == EnumDirection.EnumAxis.X) {
/* 427 */           switch (null.b[enumStairShape.ordinal()]) {
/*     */             case 4:
/* 429 */               return paramIBlockData.a(EnumBlockRotation.CLOCKWISE_180).set(SHAPE, EnumStairShape.INNER_LEFT);
/*     */             case 3:
/* 431 */               return paramIBlockData.a(EnumBlockRotation.CLOCKWISE_180).set(SHAPE, EnumStairShape.INNER_RIGHT);
/*     */             case 1:
/* 433 */               return paramIBlockData.a(EnumBlockRotation.CLOCKWISE_180).set(SHAPE, EnumStairShape.OUTER_RIGHT);
/*     */             case 2:
/* 435 */               return paramIBlockData.a(EnumBlockRotation.CLOCKWISE_180).set(SHAPE, EnumStairShape.OUTER_LEFT);
/*     */             case 5:
/* 437 */               return paramIBlockData.a(EnumBlockRotation.CLOCKWISE_180);
/*     */           } 
/*     */         
/*     */         }
/*     */         break;
/*     */     } 
/*     */     
/* 444 */     return super.a(paramIBlockData, paramEnumBlockMirror);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 449 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING, HALF, SHAPE });
/*     */   }
/*     */   
/*     */   public enum EnumHalf implements INamable {
/* 453 */     TOP("top"),
/* 454 */     BOTTOM("bottom");
/*     */     
/*     */     private final String c;
/*     */ 
/*     */     
/*     */     EnumHalf(String param1String1) {
/* 460 */       this.c = param1String1;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 465 */       return this.c;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 470 */       return this.c;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum EnumStairShape implements INamable {
/* 475 */     STRAIGHT("straight"),
/* 476 */     INNER_LEFT("inner_left"),
/* 477 */     INNER_RIGHT("inner_right"),
/* 478 */     OUTER_LEFT("outer_left"),
/* 479 */     OUTER_RIGHT("outer_right");
/*     */     
/*     */     private final String f;
/*     */ 
/*     */     
/*     */     EnumStairShape(String param1String1) {
/* 485 */       this.f = param1String1;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 490 */       return this.f;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 495 */       return this.f;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockStairs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */