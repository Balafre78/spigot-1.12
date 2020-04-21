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
/*     */ public class BlockChest
/*     */   extends BlockTileEntity
/*     */ {
/*  34 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*     */   
/*     */   public enum Type {
/*  37 */     BASIC, TRAP;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   protected static final AxisAlignedBB b = new AxisAlignedBB(0.0625D, 0.0D, 0.0D, 0.9375D, 0.875D, 0.9375D);
/*  45 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 1.0D);
/*  46 */   protected static final AxisAlignedBB d = new AxisAlignedBB(0.0D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
/*  47 */   protected static final AxisAlignedBB e = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 1.0D, 0.875D, 0.9375D);
/*  48 */   protected static final AxisAlignedBB f = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
/*     */   
/*     */   public final Type g;
/*     */   
/*     */   protected BlockChest(Type paramType) {
/*  53 */     super(Material.WOOD);
/*  54 */     w(this.blockStateList.getBlockData().set(FACING, EnumDirection.NORTH));
/*  55 */     this.g = paramType;
/*  56 */     a((paramType == Type.TRAP) ? CreativeModeTab.d : CreativeModeTab.c);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/*  61 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/*  66 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRenderType a(IBlockData paramIBlockData) {
/*  76 */     return EnumRenderType.ENTITYBLOCK_ANIMATED;
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  81 */     if (paramIBlockAccess.getType(paramBlockPosition.north()).getBlock() == this)
/*  82 */       return b; 
/*  83 */     if (paramIBlockAccess.getType(paramBlockPosition.south()).getBlock() == this)
/*  84 */       return c; 
/*  85 */     if (paramIBlockAccess.getType(paramBlockPosition.west()).getBlock() == this)
/*  86 */       return d; 
/*  87 */     if (paramIBlockAccess.getType(paramBlockPosition.east()).getBlock() == this) {
/*  88 */       return e;
/*     */     }
/*  90 */     return f;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  96 */     e(paramWorld, paramBlockPosition, paramIBlockData);
/*     */     
/*  98 */     for (EnumDirection enumDirection : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/*  99 */       BlockPosition blockPosition = paramBlockPosition.shift(enumDirection);
/* 100 */       IBlockData iBlockData = paramWorld.getType(blockPosition);
/* 101 */       if (iBlockData.getBlock() == this) {
/* 102 */         e(paramWorld, blockPosition, iBlockData);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, EntityLiving paramEntityLiving) {
/* 109 */     return getBlockData().set(FACING, paramEntityLiving.getDirection());
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityLiving paramEntityLiving, ItemStack paramItemStack) {
/* 114 */     EnumDirection enumDirection = EnumDirection.fromType2(MathHelper.floor((paramEntityLiving.yaw * 4.0F / 360.0F) + 0.5D) & 0x3).opposite();
/* 115 */     paramIBlockData = paramIBlockData.set(FACING, enumDirection);
/*     */     
/* 117 */     BlockPosition blockPosition1 = paramBlockPosition.north();
/* 118 */     BlockPosition blockPosition2 = paramBlockPosition.south();
/* 119 */     BlockPosition blockPosition3 = paramBlockPosition.west();
/* 120 */     BlockPosition blockPosition4 = paramBlockPosition.east();
/*     */     
/* 122 */     boolean bool1 = (this == paramWorld.getType(blockPosition1).getBlock()) ? true : false;
/* 123 */     boolean bool2 = (this == paramWorld.getType(blockPosition2).getBlock()) ? true : false;
/* 124 */     boolean bool3 = (this == paramWorld.getType(blockPosition3).getBlock()) ? true : false;
/* 125 */     boolean bool4 = (this == paramWorld.getType(blockPosition4).getBlock()) ? true : false;
/*     */     
/* 127 */     if (bool1 || bool2 || bool3 || bool4) {
/* 128 */       if (enumDirection.k() == EnumDirection.EnumAxis.X && (bool1 || bool2)) {
/* 129 */         if (bool1) {
/* 130 */           paramWorld.setTypeAndData(blockPosition1, paramIBlockData, 3);
/*     */         } else {
/* 132 */           paramWorld.setTypeAndData(blockPosition2, paramIBlockData, 3);
/*     */         } 
/* 134 */         paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData, 3);
/* 135 */       } else if (enumDirection.k() == EnumDirection.EnumAxis.Z && (bool3 || bool4)) {
/* 136 */         if (bool3) {
/* 137 */           paramWorld.setTypeAndData(blockPosition3, paramIBlockData, 3);
/*     */         } else {
/* 139 */           paramWorld.setTypeAndData(blockPosition4, paramIBlockData, 3);
/*     */         } 
/* 141 */         paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData, 3);
/*     */       } 
/*     */     } else {
/* 144 */       paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData, 3);
/*     */     } 
/*     */     
/* 147 */     if (paramItemStack.hasName()) {
/* 148 */       TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/* 149 */       if (tileEntity instanceof TileEntityChest) {
/* 150 */         ((TileEntityChest)tileEntity).setCustomName(paramItemStack.getName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData e(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 157 */     if (paramWorld.isClientSide) {
/* 158 */       return paramIBlockData;
/*     */     }
/*     */     
/* 161 */     IBlockData iBlockData1 = paramWorld.getType(paramBlockPosition.north());
/* 162 */     IBlockData iBlockData2 = paramWorld.getType(paramBlockPosition.south());
/* 163 */     IBlockData iBlockData3 = paramWorld.getType(paramBlockPosition.west());
/* 164 */     IBlockData iBlockData4 = paramWorld.getType(paramBlockPosition.east());
/*     */ 
/*     */     
/* 167 */     EnumDirection enumDirection = paramIBlockData.<EnumDirection>get(FACING);
/*     */     
/* 169 */     if (iBlockData1.getBlock() == this || iBlockData2.getBlock() == this) {
/* 170 */       EnumDirection enumDirection1; BlockPosition blockPosition = (iBlockData1.getBlock() == this) ? paramBlockPosition.north() : paramBlockPosition.south();
/* 171 */       IBlockData iBlockData5 = paramWorld.getType(blockPosition.west());
/* 172 */       IBlockData iBlockData6 = paramWorld.getType(blockPosition.east());
/*     */       
/* 174 */       enumDirection = EnumDirection.EAST;
/*     */ 
/*     */       
/* 177 */       if (iBlockData1.getBlock() == this) {
/* 178 */         enumDirection1 = iBlockData1.<EnumDirection>get(FACING);
/*     */       } else {
/* 180 */         enumDirection1 = iBlockData2.<EnumDirection>get(FACING);
/*     */       } 
/* 182 */       if (enumDirection1 == EnumDirection.WEST) {
/* 183 */         enumDirection = EnumDirection.WEST;
/*     */       }
/*     */       
/* 186 */       if ((iBlockData3.b() || iBlockData5.b()) && !iBlockData4.b() && !iBlockData6.b()) {
/* 187 */         enumDirection = EnumDirection.EAST;
/*     */       }
/* 189 */       if ((iBlockData4.b() || iBlockData6.b()) && !iBlockData3.b() && !iBlockData5.b()) {
/* 190 */         enumDirection = EnumDirection.WEST;
/*     */       }
/*     */     } else {
/* 193 */       boolean bool1 = iBlockData1.b();
/* 194 */       boolean bool2 = iBlockData2.b();
/* 195 */       if (iBlockData3.getBlock() == this || iBlockData4.getBlock() == this) {
/* 196 */         EnumDirection enumDirection1; BlockPosition blockPosition = (iBlockData3.getBlock() == this) ? paramBlockPosition.west() : paramBlockPosition.east();
/* 197 */         IBlockData iBlockData5 = paramWorld.getType(blockPosition.north());
/* 198 */         IBlockData iBlockData6 = paramWorld.getType(blockPosition.south());
/*     */         
/* 200 */         enumDirection = EnumDirection.SOUTH;
/*     */         
/* 202 */         if (iBlockData3.getBlock() == this) {
/* 203 */           enumDirection1 = iBlockData3.<EnumDirection>get(FACING);
/*     */         } else {
/* 205 */           enumDirection1 = iBlockData4.<EnumDirection>get(FACING);
/*     */         } 
/* 207 */         if (enumDirection1 == EnumDirection.NORTH) {
/* 208 */           enumDirection = EnumDirection.NORTH;
/*     */         }
/*     */         
/* 211 */         if ((bool1 || iBlockData5.b()) && !bool2 && !iBlockData6.b()) {
/* 212 */           enumDirection = EnumDirection.SOUTH;
/*     */         }
/* 214 */         if ((bool2 || iBlockData6.b()) && !bool1 && !iBlockData5.b()) {
/* 215 */           enumDirection = EnumDirection.NORTH;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 220 */     paramIBlockData = paramIBlockData.set(FACING, enumDirection);
/* 221 */     paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData, 3);
/* 222 */     return paramIBlockData;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData f(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 227 */     EnumDirection enumDirection1 = null;
/* 228 */     for (EnumDirection enumDirection : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/* 229 */       IBlockData iBlockData = paramWorld.getType(paramBlockPosition.shift(enumDirection));
/* 230 */       if (iBlockData.getBlock() == this) {
/* 231 */         return paramIBlockData;
/*     */       }
/* 233 */       if (iBlockData.b()) {
/* 234 */         if (enumDirection1 == null) {
/* 235 */           enumDirection1 = enumDirection; continue;
/*     */         } 
/* 237 */         enumDirection1 = null;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 242 */     if (enumDirection1 != null) {
/* 243 */       return paramIBlockData.set(FACING, enumDirection1.opposite());
/*     */     }
/*     */ 
/*     */     
/* 247 */     EnumDirection enumDirection2 = paramIBlockData.<EnumDirection>get(FACING);
/* 248 */     if (paramWorld.getType(paramBlockPosition.shift(enumDirection2)).b()) {
/* 249 */       enumDirection2 = enumDirection2.opposite();
/*     */     }
/* 251 */     if (paramWorld.getType(paramBlockPosition.shift(enumDirection2)).b()) {
/* 252 */       enumDirection2 = enumDirection2.e();
/*     */     }
/* 254 */     if (paramWorld.getType(paramBlockPosition.shift(enumDirection2)).b()) {
/* 255 */       enumDirection2 = enumDirection2.opposite();
/*     */     }
/*     */     
/* 258 */     return paramIBlockData.set(FACING, enumDirection2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlace(World paramWorld, BlockPosition paramBlockPosition) {
/* 264 */     byte b = 0;
/*     */     
/* 266 */     BlockPosition blockPosition1 = paramBlockPosition.west();
/* 267 */     BlockPosition blockPosition2 = paramBlockPosition.east();
/* 268 */     BlockPosition blockPosition3 = paramBlockPosition.north();
/* 269 */     BlockPosition blockPosition4 = paramBlockPosition.south();
/*     */     
/* 271 */     if (paramWorld.getType(blockPosition1).getBlock() == this) {
/* 272 */       if (d(paramWorld, blockPosition1)) {
/* 273 */         return false;
/*     */       }
/* 275 */       b++;
/*     */     } 
/* 277 */     if (paramWorld.getType(blockPosition2).getBlock() == this) {
/* 278 */       if (d(paramWorld, blockPosition2)) {
/* 279 */         return false;
/*     */       }
/* 281 */       b++;
/*     */     } 
/* 283 */     if (paramWorld.getType(blockPosition3).getBlock() == this) {
/* 284 */       if (d(paramWorld, blockPosition3)) {
/* 285 */         return false;
/*     */       }
/* 287 */       b++;
/*     */     } 
/* 289 */     if (paramWorld.getType(blockPosition4).getBlock() == this) {
/* 290 */       if (d(paramWorld, blockPosition4)) {
/* 291 */         return false;
/*     */       }
/* 293 */       b++;
/*     */     } 
/*     */     
/* 296 */     return (b <= 1);
/*     */   }
/*     */   
/*     */   private boolean d(World paramWorld, BlockPosition paramBlockPosition) {
/* 300 */     if (paramWorld.getType(paramBlockPosition).getBlock() != this) {
/* 301 */       return false;
/*     */     }
/*     */     
/* 304 */     for (EnumDirection enumDirection : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/* 305 */       if (paramWorld.getType(paramBlockPosition.shift(enumDirection)).getBlock() == this) {
/* 306 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 310 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/* 315 */     super.a(paramIBlockData, paramWorld, paramBlockPosition1, paramBlock, paramBlockPosition2);
/*     */     
/* 317 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition1);
/* 318 */     if (tileEntity instanceof TileEntityChest) {
/* 319 */       tileEntity.invalidateBlockCache();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 325 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/* 326 */     if (tileEntity instanceof IInventory) {
/* 327 */       InventoryUtils.dropInventory(paramWorld, paramBlockPosition, (IInventory)tileEntity);
/*     */       
/* 329 */       paramWorld.updateAdjacentComparators(paramBlockPosition, this);
/*     */     } 
/* 331 */     super.remove(paramWorld, paramBlockPosition, paramIBlockData);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean interact(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 336 */     if (paramWorld.isClientSide) {
/* 337 */       return true;
/*     */     }
/* 339 */     ITileInventory iTileInventory = getInventory(paramWorld, paramBlockPosition);
/*     */     
/* 341 */     if (iTileInventory != null) {
/* 342 */       paramEntityHuman.openContainer(iTileInventory);
/*     */       
/* 344 */       if (this.g == Type.BASIC) {
/* 345 */         paramEntityHuman.b(StatisticList.aa);
/* 346 */       } else if (this.g == Type.TRAP) {
/* 347 */         paramEntityHuman.b(StatisticList.U);
/*     */       } 
/*     */     } 
/*     */     
/* 351 */     return true;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ITileInventory getInventory(World paramWorld, BlockPosition paramBlockPosition) {
/* 356 */     return a(paramWorld, paramBlockPosition, false);
/*     */   }
/*     */   @Nullable
/*     */   public ITileInventory a(World paramWorld, BlockPosition paramBlockPosition, boolean paramBoolean) {
/*     */     InventoryLargeChest inventoryLargeChest;
/* 361 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/* 362 */     if (!(tileEntity instanceof TileEntityChest)) {
/* 363 */       return null;
/*     */     }
/*     */     
/* 366 */     TileEntityChest tileEntityChest = (TileEntityChest)tileEntity;
/*     */     
/* 368 */     if (!paramBoolean && e(paramWorld, paramBlockPosition)) {
/* 369 */       return null;
/*     */     }
/*     */     
/* 372 */     for (EnumDirection enumDirection : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/* 373 */       BlockPosition blockPosition = paramBlockPosition.shift(enumDirection);
/* 374 */       Block block = paramWorld.getType(blockPosition).getBlock();
/*     */       
/* 376 */       if (block == this) {
/* 377 */         if (e(paramWorld, blockPosition)) {
/* 378 */           return null;
/*     */         }
/*     */         
/* 381 */         TileEntity tileEntity1 = paramWorld.getTileEntity(blockPosition);
/* 382 */         if (tileEntity1 instanceof TileEntityChest) {
/*     */           
/* 384 */           if (enumDirection == EnumDirection.WEST || enumDirection == EnumDirection.NORTH) {
/* 385 */             inventoryLargeChest = new InventoryLargeChest("container.chestDouble", (TileEntityChest)tileEntity1, tileEntityChest); continue;
/*     */           } 
/* 387 */           inventoryLargeChest = new InventoryLargeChest("container.chestDouble", inventoryLargeChest, (TileEntityChest)tileEntity1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 392 */     return inventoryLargeChest;
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity a(World paramWorld, int paramInt) {
/* 397 */     return new TileEntityChest();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPowerSource(IBlockData paramIBlockData) {
/* 402 */     return (this.g == Type.TRAP);
/*     */   }
/*     */ 
/*     */   
/*     */   public int b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 407 */     if (!paramIBlockData.m()) {
/* 408 */       return 0;
/*     */     }
/*     */     
/* 411 */     int i = 0;
/* 412 */     TileEntity tileEntity = paramIBlockAccess.getTileEntity(paramBlockPosition);
/* 413 */     if (tileEntity instanceof TileEntityChest) {
/* 414 */       i = ((TileEntityChest)tileEntity).l;
/*     */     }
/* 416 */     return MathHelper.clamp(i, 0, 15);
/*     */   }
/*     */ 
/*     */   
/*     */   public int c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 421 */     if (paramEnumDirection == EnumDirection.UP) {
/* 422 */       return paramIBlockData.a(paramIBlockAccess, paramBlockPosition, paramEnumDirection);
/*     */     }
/* 424 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean e(World paramWorld, BlockPosition paramBlockPosition) {
/* 429 */     return (i(paramWorld, paramBlockPosition) || j(paramWorld, paramBlockPosition));
/*     */   }
/*     */   
/*     */   private boolean i(World paramWorld, BlockPosition paramBlockPosition) {
/* 433 */     return paramWorld.getType(paramBlockPosition.up()).l();
/*     */   }
/*     */   
/*     */   private boolean j(World paramWorld, BlockPosition paramBlockPosition) {
/* 437 */     for (Entity entity : paramWorld.<EntityOcelot>a(EntityOcelot.class, new AxisAlignedBB(paramBlockPosition.getX(), (paramBlockPosition.getY() + 1), paramBlockPosition.getZ(), (paramBlockPosition.getX() + 1), (paramBlockPosition.getY() + 2), (paramBlockPosition.getZ() + 1)))) {
/* 438 */       EntityOcelot entityOcelot = (EntityOcelot)entity;
/* 439 */       if (entityOcelot.isSitting()) {
/* 440 */         return true;
/*     */       }
/*     */     } 
/* 443 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData paramIBlockData) {
/* 448 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int c(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition) {
/* 453 */     return Container.b(getInventory(paramWorld, paramBlockPosition));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 458 */     EnumDirection enumDirection = EnumDirection.fromType1(paramInt);
/* 459 */     if (enumDirection.k() == EnumDirection.EnumAxis.Y) {
/* 460 */       enumDirection = EnumDirection.NORTH;
/*     */     }
/* 462 */     return getBlockData()
/* 463 */       .set(FACING, enumDirection);
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 468 */     return ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/* 473 */     return paramIBlockData.set(FACING, paramEnumBlockRotation.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/* 478 */     return paramIBlockData.a(paramEnumBlockMirror.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 483 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING });
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 488 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */