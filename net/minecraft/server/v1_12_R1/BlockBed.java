/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockBed
/*     */   extends BlockFacingHorizontal
/*     */   implements ITileEntity
/*     */ {
/*  36 */   public static final BlockStateEnum<EnumBedPart> PART = BlockStateEnum.of("part", EnumBedPart.class);
/*  37 */   public static final BlockStateBoolean OCCUPIED = BlockStateBoolean.of("occupied");
/*  38 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D);
/*     */   
/*     */   public BlockBed() {
/*  41 */     super(Material.CLOTH);
/*  42 */     w(this.blockStateList.getBlockData().<EnumBedPart, EnumBedPart>set(PART, EnumBedPart.FOOT).set(OCCUPIED, Boolean.valueOf(false)));
/*  43 */     this.isTileEntity = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  48 */     if (paramIBlockData.get(PART) == EnumBedPart.FOOT) {
/*  49 */       TileEntity tileEntity = paramIBlockAccess.getTileEntity(paramBlockPosition);
/*  50 */       if (tileEntity instanceof TileEntityBed) {
/*  51 */         EnumColor enumColor = ((TileEntityBed)tileEntity).a();
/*  52 */         return MaterialMapColor.a(enumColor);
/*     */       } 
/*     */     } 
/*  55 */     return MaterialMapColor.f;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean interact(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/*  60 */     if (paramWorld.isClientSide) {
/*  61 */       return true;
/*     */     }
/*     */     
/*  64 */     if (paramIBlockData.get(PART) != EnumBedPart.HEAD) {
/*     */       
/*  66 */       paramBlockPosition = paramBlockPosition.shift(paramIBlockData.<EnumDirection>get(FACING));
/*  67 */       paramIBlockData = paramWorld.getType(paramBlockPosition);
/*  68 */       if (paramIBlockData.getBlock() != this) {
/*  69 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  73 */     if (!paramWorld.worldProvider.e() || paramWorld.getBiome(paramBlockPosition) == Biomes.j) {
/*     */       
/*  75 */       paramWorld.setAir(paramBlockPosition);
/*     */ 
/*     */       
/*  78 */       BlockPosition blockPosition = paramBlockPosition.shift(((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).opposite());
/*  79 */       if (paramWorld.getType(blockPosition).getBlock() == this) {
/*  80 */         paramWorld.setAir(blockPosition);
/*     */       }
/*     */       
/*  83 */       paramWorld.createExplosion(null, paramBlockPosition.getX() + 0.5D, paramBlockPosition.getY() + 0.5D, paramBlockPosition.getZ() + 0.5D, 5.0F, true, true);
/*  84 */       return true;
/*     */     } 
/*     */     
/*  87 */     if (((Boolean)paramIBlockData.<Boolean>get(OCCUPIED)).booleanValue()) {
/*  88 */       EntityHuman entityHuman = c(paramWorld, paramBlockPosition);
/*  89 */       if (entityHuman == null) {
/*  90 */         paramIBlockData = paramIBlockData.set(OCCUPIED, Boolean.valueOf(false));
/*  91 */         paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData, 4);
/*     */       } else {
/*  93 */         paramEntityHuman.a(new ChatMessage("tile.bed.occupied", new Object[0]), true);
/*  94 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/*  98 */     EntityHuman.EnumBedResult enumBedResult = paramEntityHuman.a(paramBlockPosition);
/*  99 */     if (enumBedResult == EntityHuman.EnumBedResult.OK) {
/* 100 */       paramIBlockData = paramIBlockData.set(OCCUPIED, Boolean.valueOf(true));
/* 101 */       paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData, 4);
/* 102 */       return true;
/*     */     } 
/*     */     
/* 105 */     if (enumBedResult == EntityHuman.EnumBedResult.NOT_POSSIBLE_NOW) {
/* 106 */       paramEntityHuman.a(new ChatMessage("tile.bed.noSleep", new Object[0]), true);
/* 107 */     } else if (enumBedResult == EntityHuman.EnumBedResult.NOT_SAFE) {
/* 108 */       paramEntityHuman.a(new ChatMessage("tile.bed.notSafe", new Object[0]), true);
/* 109 */     } else if (enumBedResult == EntityHuman.EnumBedResult.TOO_FAR_AWAY) {
/* 110 */       paramEntityHuman.a(new ChatMessage("tile.bed.tooFarAway", new Object[0]), true);
/*     */     } 
/* 112 */     return true;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private EntityHuman c(World paramWorld, BlockPosition paramBlockPosition) {
/* 117 */     for (EntityHuman entityHuman : paramWorld.players) {
/* 118 */       if (entityHuman.isSleeping() && entityHuman.bedPosition.equals(paramBlockPosition)) {
/* 119 */         return entityHuman;
/*     */       }
/*     */     } 
/*     */     
/* 123 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/* 128 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/* 133 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void fallOn(World paramWorld, BlockPosition paramBlockPosition, Entity paramEntity, float paramFloat) {
/* 138 */     super.fallOn(paramWorld, paramBlockPosition, paramEntity, paramFloat * 0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World paramWorld, Entity paramEntity) {
/* 143 */     if (paramEntity.isSneaking()) {
/* 144 */       super.a(paramWorld, paramEntity);
/*     */     }
/* 146 */     else if (paramEntity.motY < 0.0D) {
/* 147 */       paramEntity.motY = -paramEntity.motY * 0.6600000262260437D;
/*     */ 
/*     */       
/* 150 */       if (!(paramEntity instanceof EntityLiving)) {
/* 151 */         paramEntity.motY *= 0.8D;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/* 159 */     EnumDirection enumDirection = paramIBlockData.<EnumDirection>get(FACING);
/*     */     
/* 161 */     if (paramIBlockData.get(PART) == EnumBedPart.FOOT) {
/* 162 */       if (paramWorld.getType(paramBlockPosition1.shift(enumDirection)).getBlock() != this) {
/* 163 */         paramWorld.setAir(paramBlockPosition1);
/*     */       }
/*     */     }
/* 166 */     else if (paramWorld.getType(paramBlockPosition1.shift(enumDirection.opposite())).getBlock() != this) {
/* 167 */       if (!paramWorld.isClientSide) {
/* 168 */         b(paramWorld, paramBlockPosition1, paramIBlockData, 0);
/*     */       }
/* 170 */       paramWorld.setAir(paramBlockPosition1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/* 177 */     if (paramIBlockData.get(PART) == EnumBedPart.FOOT) {
/* 178 */       return Items.a;
/*     */     }
/* 180 */     return Items.BED;
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 185 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static BlockPosition a(World paramWorld, BlockPosition paramBlockPosition, int paramInt) {
/* 195 */     EnumDirection enumDirection = paramWorld.getType(paramBlockPosition).<EnumDirection>get(FACING);
/*     */ 
/*     */     
/* 198 */     int i = paramBlockPosition.getX();
/* 199 */     int j = paramBlockPosition.getY();
/* 200 */     int k = paramBlockPosition.getZ();
/*     */ 
/*     */     
/* 203 */     for (byte b = 0; b <= 1; b++) {
/* 204 */       int m = i - enumDirection.getAdjacentX() * b - 1;
/* 205 */       int n = k - enumDirection.getAdjacentZ() * b - 1;
/* 206 */       int i1 = m + 2;
/* 207 */       int i2 = n + 2;
/*     */       
/* 209 */       for (int i3 = m; i3 <= i1; i3++) {
/* 210 */         for (int i4 = n; i4 <= i2; i4++) {
/* 211 */           BlockPosition blockPosition = new BlockPosition(i3, j, i4);
/* 212 */           if (b(paramWorld, blockPosition)) {
/* 213 */             if (paramInt > 0) {
/* 214 */               paramInt--;
/*     */             } else {
/*     */               
/* 217 */               return blockPosition;
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 223 */     return null;
/*     */   }
/*     */   
/*     */   protected static boolean b(World paramWorld, BlockPosition paramBlockPosition) {
/* 227 */     return (paramWorld.getType(paramBlockPosition.down()).q() && 
/* 228 */       !paramWorld.getType(paramBlockPosition).getMaterial().isBuildable() && 
/* 229 */       !paramWorld.getType(paramBlockPosition.up()).getMaterial().isBuildable());
/*     */   }
/*     */ 
/*     */   
/*     */   public void dropNaturally(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, float paramFloat, int paramInt) {
/* 234 */     if (paramIBlockData.get(PART) == EnumBedPart.HEAD) {
/* 235 */       TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/*     */       
/* 237 */       EnumColor enumColor = (tileEntity instanceof TileEntityBed) ? ((TileEntityBed)tileEntity).a() : EnumColor.RED;
/*     */       
/* 239 */       a(paramWorld, paramBlockPosition, new ItemStack(Items.BED, 1, enumColor.getColorIndex()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumPistonReaction h(IBlockData paramIBlockData) {
/* 245 */     return EnumPistonReaction.DESTROY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRenderType a(IBlockData paramIBlockData) {
/* 255 */     return EnumRenderType.ENTITYBLOCK_ANIMATED;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 260 */     BlockPosition blockPosition = paramBlockPosition;
/* 261 */     if (paramIBlockData.get(PART) == EnumBedPart.FOOT) {
/* 262 */       blockPosition = paramBlockPosition.shift(paramIBlockData.<EnumDirection>get(FACING));
/*     */     }
/*     */     
/* 265 */     TileEntity tileEntity = paramWorld.getTileEntity(blockPosition);
/* 266 */     EnumColor enumColor = (tileEntity instanceof TileEntityBed) ? ((TileEntityBed)tileEntity).a() : EnumColor.RED;
/*     */     
/* 268 */     return new ItemStack(Items.BED, 1, enumColor.getColorIndex());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman) {
/* 276 */     if (paramEntityHuman.abilities.canInstantlyBuild && 
/* 277 */       paramIBlockData.get(PART) == EnumBedPart.FOOT) {
/* 278 */       BlockPosition blockPosition = paramBlockPosition.shift(paramIBlockData.<EnumDirection>get(FACING));
/* 279 */       if (paramWorld.getType(blockPosition).getBlock() == this) {
/* 280 */         paramWorld.setAir(blockPosition);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(World paramWorld, EntityHuman paramEntityHuman, BlockPosition paramBlockPosition, IBlockData paramIBlockData, TileEntity paramTileEntity, ItemStack paramItemStack) {
/* 289 */     if (paramIBlockData.get(PART) == EnumBedPart.HEAD && paramTileEntity instanceof TileEntityBed) {
/* 290 */       TileEntityBed tileEntityBed = (TileEntityBed)paramTileEntity;
/*     */       
/* 292 */       ItemStack itemStack = tileEntityBed.f();
/*     */       
/* 294 */       a(paramWorld, paramBlockPosition, itemStack);
/*     */     } else {
/* 296 */       super.a(paramWorld, paramEntityHuman, paramBlockPosition, paramIBlockData, null, paramItemStack);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 302 */     super.remove(paramWorld, paramBlockPosition, paramIBlockData);
/* 303 */     paramWorld.s(paramBlockPosition);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 308 */     EnumDirection enumDirection = EnumDirection.fromType2(paramInt);
/* 309 */     if ((paramInt & 0x8) > 0) {
/* 310 */       return getBlockData()
/* 311 */         .<EnumBedPart, EnumBedPart>set(PART, EnumBedPart.HEAD)
/* 312 */         .<Comparable, EnumDirection>set(FACING, enumDirection)
/* 313 */         .set(OCCUPIED, Boolean.valueOf(((paramInt & 0x4) > 0)));
/*     */     }
/* 315 */     return getBlockData()
/* 316 */       .<EnumBedPart, EnumBedPart>set(PART, EnumBedPart.FOOT)
/* 317 */       .set(FACING, enumDirection);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 323 */     if (paramIBlockData.get(PART) == EnumBedPart.FOOT) {
/* 324 */       IBlockData iBlockData = paramIBlockAccess.getType(paramBlockPosition.shift(paramIBlockData.<EnumDirection>get(FACING)));
/* 325 */       if (iBlockData.getBlock() == this) {
/* 326 */         paramIBlockData = paramIBlockData.set(OCCUPIED, iBlockData.get(OCCUPIED));
/*     */       }
/*     */     } 
/*     */     
/* 330 */     return paramIBlockData;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/* 335 */     return paramIBlockData.set(FACING, paramEnumBlockRotation.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/* 340 */     return paramIBlockData.a(paramEnumBlockMirror.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 345 */     int i = 0;
/*     */     
/* 347 */     i |= ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).get2DRotationValue();
/*     */     
/* 349 */     if (paramIBlockData.get(PART) == EnumBedPart.HEAD) {
/* 350 */       i |= 0x8;
/*     */       
/* 352 */       if (((Boolean)paramIBlockData.<Boolean>get(OCCUPIED)).booleanValue()) {
/* 353 */         i |= 0x4;
/*     */       }
/*     */     } 
/*     */     
/* 357 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 362 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 367 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING, PART, OCCUPIED });
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity a(World paramWorld, int paramInt) {
/* 372 */     return new TileEntityBed();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum EnumBedPart
/*     */     implements INamable
/*     */   {
/* 380 */     HEAD("head"),
/* 381 */     FOOT("foot");
/*     */     
/*     */     private final String c;
/*     */     
/*     */     EnumBedPart(String param1String1) {
/* 386 */       this.c = param1String1;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 391 */       return this.c;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 396 */       return this.c;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */