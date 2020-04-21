/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
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
/*     */ public abstract class BlockMinecartTrackAbstract
/*     */   extends Block
/*     */ {
/*  23 */   protected static final AxisAlignedBB a = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
/*  24 */   protected static final AxisAlignedBB b = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
/*     */   protected final boolean c;
/*     */   
/*     */   public class MinecartTrackLogic
/*     */   {
/*     */     private final World b;
/*     */     private final BlockPosition c;
/*     */     private final BlockMinecartTrackAbstract d;
/*     */     private IBlockData e;
/*     */     private final boolean f;
/*  34 */     private final List<BlockPosition> g = Lists.newArrayList();
/*     */     
/*     */     public MinecartTrackLogic(BlockMinecartTrackAbstract this$0, World param1World, BlockPosition param1BlockPosition, IBlockData param1IBlockData) {
/*  37 */       this.b = param1World;
/*  38 */       this.c = param1BlockPosition;
/*  39 */       this.e = param1IBlockData;
/*  40 */       this.d = (BlockMinecartTrackAbstract)param1IBlockData.getBlock();
/*  41 */       BlockMinecartTrackAbstract.EnumTrackPosition enumTrackPosition = param1IBlockData.<BlockMinecartTrackAbstract.EnumTrackPosition>get(this.d.g());
/*  42 */       this.f = this.d.c;
/*  43 */       a(enumTrackPosition);
/*     */     }
/*     */     
/*     */     public List<BlockPosition> a() {
/*  47 */       return this.g;
/*     */     }
/*     */     
/*     */     private void a(BlockMinecartTrackAbstract.EnumTrackPosition param1EnumTrackPosition) {
/*  51 */       this.g.clear();
/*  52 */       switch (BlockMinecartTrackAbstract.null.a[param1EnumTrackPosition.ordinal()]) {
/*     */         case 1:
/*  54 */           this.g.add(this.c.north());
/*  55 */           this.g.add(this.c.south());
/*     */           break;
/*     */         case 2:
/*  58 */           this.g.add(this.c.west());
/*  59 */           this.g.add(this.c.east());
/*     */           break;
/*     */         case 3:
/*  62 */           this.g.add(this.c.west());
/*  63 */           this.g.add(this.c.east().up());
/*     */           break;
/*     */         case 4:
/*  66 */           this.g.add(this.c.west().up());
/*  67 */           this.g.add(this.c.east());
/*     */           break;
/*     */         case 5:
/*  70 */           this.g.add(this.c.north().up());
/*  71 */           this.g.add(this.c.south());
/*     */           break;
/*     */         case 6:
/*  74 */           this.g.add(this.c.north());
/*  75 */           this.g.add(this.c.south().up());
/*     */           break;
/*     */         case 7:
/*  78 */           this.g.add(this.c.east());
/*  79 */           this.g.add(this.c.south());
/*     */           break;
/*     */         case 8:
/*  82 */           this.g.add(this.c.west());
/*  83 */           this.g.add(this.c.south());
/*     */           break;
/*     */         case 9:
/*  86 */           this.g.add(this.c.west());
/*  87 */           this.g.add(this.c.north());
/*     */           break;
/*     */         case 10:
/*  90 */           this.g.add(this.c.east());
/*  91 */           this.g.add(this.c.north());
/*     */           break;
/*     */       } 
/*     */     }
/*     */     
/*     */     private void d() {
/*  97 */       for (byte b = 0; b < this.g.size(); b++) {
/*  98 */         MinecartTrackLogic minecartTrackLogic = b(this.g.get(b));
/*  99 */         if (minecartTrackLogic == null || !minecartTrackLogic.a(this)) {
/* 100 */           this.g.remove(b--);
/*     */         } else {
/* 102 */           this.g.set(b, minecartTrackLogic.c);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private boolean a(BlockPosition param1BlockPosition) {
/* 108 */       return (BlockMinecartTrackAbstract.b(this.b, param1BlockPosition) || BlockMinecartTrackAbstract.b(this.b, param1BlockPosition.up()) || BlockMinecartTrackAbstract.b(this.b, param1BlockPosition.down()));
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     private MinecartTrackLogic b(BlockPosition param1BlockPosition) {
/* 113 */       BlockPosition blockPosition = param1BlockPosition;
/* 114 */       IBlockData iBlockData = this.b.getType(blockPosition);
/* 115 */       if (BlockMinecartTrackAbstract.i(iBlockData)) {
/* 116 */         return new MinecartTrackLogic(this.a, this.b, blockPosition, iBlockData);
/*     */       }
/*     */       
/* 119 */       blockPosition = param1BlockPosition.up();
/* 120 */       iBlockData = this.b.getType(blockPosition);
/* 121 */       if (BlockMinecartTrackAbstract.i(iBlockData)) {
/* 122 */         return new MinecartTrackLogic(this.a, this.b, blockPosition, iBlockData);
/*     */       }
/*     */       
/* 125 */       blockPosition = param1BlockPosition.down();
/* 126 */       iBlockData = this.b.getType(blockPosition);
/* 127 */       if (BlockMinecartTrackAbstract.i(iBlockData)) {
/* 128 */         return new MinecartTrackLogic(this.a, this.b, blockPosition, iBlockData);
/*     */       }
/*     */       
/* 131 */       return null;
/*     */     }
/*     */     
/*     */     private boolean a(MinecartTrackLogic param1MinecartTrackLogic) {
/* 135 */       return c(param1MinecartTrackLogic.c);
/*     */     }
/*     */     
/*     */     private boolean c(BlockPosition param1BlockPosition) {
/* 139 */       for (byte b = 0; b < this.g.size(); b++) {
/* 140 */         BlockPosition blockPosition = this.g.get(b);
/* 141 */         if (blockPosition.getX() == param1BlockPosition.getX() && blockPosition.getZ() == param1BlockPosition.getZ()) {
/* 142 */           return true;
/*     */         }
/*     */       } 
/* 145 */       return false;
/*     */     }
/*     */     
/*     */     protected int b() {
/* 149 */       byte b = 0;
/*     */       
/* 151 */       for (EnumDirection enumDirection : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/* 152 */         if (a(this.c.shift(enumDirection))) {
/* 153 */           b++;
/*     */         }
/*     */       } 
/*     */       
/* 157 */       return b;
/*     */     }
/*     */     
/*     */     private boolean b(MinecartTrackLogic param1MinecartTrackLogic) {
/* 161 */       return (a(param1MinecartTrackLogic) || this.g.size() != 2);
/*     */     }
/*     */     
/*     */     private void c(MinecartTrackLogic param1MinecartTrackLogic) {
/* 165 */       this.g.add(param1MinecartTrackLogic.c);
/*     */       
/* 167 */       BlockPosition blockPosition1 = this.c.north();
/* 168 */       BlockPosition blockPosition2 = this.c.south();
/* 169 */       BlockPosition blockPosition3 = this.c.west();
/* 170 */       BlockPosition blockPosition4 = this.c.east();
/*     */       
/* 172 */       boolean bool1 = c(blockPosition1);
/* 173 */       boolean bool2 = c(blockPosition2);
/* 174 */       boolean bool3 = c(blockPosition3);
/* 175 */       boolean bool4 = c(blockPosition4);
/*     */       
/* 177 */       BlockMinecartTrackAbstract.EnumTrackPosition enumTrackPosition = null;
/*     */       
/* 179 */       if (bool1 || bool2) {
/* 180 */         enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH;
/*     */       }
/* 182 */       if (bool3 || bool4) {
/* 183 */         enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.EAST_WEST;
/*     */       }
/* 185 */       if (!this.f) {
/* 186 */         if (bool2 && bool4 && !bool1 && !bool3) {
/* 187 */           enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST;
/*     */         }
/* 189 */         if (bool2 && bool3 && !bool1 && !bool4) {
/* 190 */           enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST;
/*     */         }
/* 192 */         if (bool1 && bool3 && !bool2 && !bool4) {
/* 193 */           enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST;
/*     */         }
/* 195 */         if (bool1 && bool4 && !bool2 && !bool3) {
/* 196 */           enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST;
/*     */         }
/*     */       } 
/* 199 */       if (enumTrackPosition == BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH) {
/* 200 */         if (BlockMinecartTrackAbstract.b(this.b, blockPosition1.up())) {
/* 201 */           enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_NORTH;
/*     */         }
/* 203 */         if (BlockMinecartTrackAbstract.b(this.b, blockPosition2.up())) {
/* 204 */           enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_SOUTH;
/*     */         }
/*     */       } 
/* 207 */       if (enumTrackPosition == BlockMinecartTrackAbstract.EnumTrackPosition.EAST_WEST) {
/* 208 */         if (BlockMinecartTrackAbstract.b(this.b, blockPosition4.up())) {
/* 209 */           enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_EAST;
/*     */         }
/* 211 */         if (BlockMinecartTrackAbstract.b(this.b, blockPosition3.up())) {
/* 212 */           enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_WEST;
/*     */         }
/*     */       } 
/*     */       
/* 216 */       if (enumTrackPosition == null) {
/* 217 */         enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH;
/*     */       }
/*     */       
/* 220 */       this.e = this.e.set(this.d.g(), enumTrackPosition);
/* 221 */       this.b.setTypeAndData(this.c, this.e, 3);
/*     */     }
/*     */     
/*     */     private boolean d(BlockPosition param1BlockPosition) {
/* 225 */       MinecartTrackLogic minecartTrackLogic = b(param1BlockPosition);
/* 226 */       if (minecartTrackLogic == null) {
/* 227 */         return false;
/*     */       }
/*     */       
/* 230 */       minecartTrackLogic.d();
/* 231 */       return minecartTrackLogic.b(this);
/*     */     }
/*     */     
/*     */     public MinecartTrackLogic a(boolean param1Boolean1, boolean param1Boolean2) {
/* 235 */       BlockPosition blockPosition1 = this.c.north();
/* 236 */       BlockPosition blockPosition2 = this.c.south();
/* 237 */       BlockPosition blockPosition3 = this.c.west();
/* 238 */       BlockPosition blockPosition4 = this.c.east();
/*     */       
/* 240 */       boolean bool1 = d(blockPosition1);
/* 241 */       boolean bool2 = d(blockPosition2);
/* 242 */       boolean bool3 = d(blockPosition3);
/* 243 */       boolean bool4 = d(blockPosition4);
/*     */       
/* 245 */       BlockMinecartTrackAbstract.EnumTrackPosition enumTrackPosition = null;
/*     */       
/* 247 */       if ((bool1 || bool2) && !bool3 && !bool4) {
/* 248 */         enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH;
/*     */       }
/* 250 */       if ((bool3 || bool4) && !bool1 && !bool2) {
/* 251 */         enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.EAST_WEST;
/*     */       }
/*     */       
/* 254 */       if (!this.f) {
/* 255 */         if (bool2 && bool4 && !bool1 && !bool3) {
/* 256 */           enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST;
/*     */         }
/* 258 */         if (bool2 && bool3 && !bool1 && !bool4) {
/* 259 */           enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST;
/*     */         }
/* 261 */         if (bool1 && bool3 && !bool2 && !bool4) {
/* 262 */           enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST;
/*     */         }
/* 264 */         if (bool1 && bool4 && !bool2 && !bool3) {
/* 265 */           enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST;
/*     */         }
/*     */       } 
/* 268 */       if (enumTrackPosition == null) {
/* 269 */         if (bool1 || bool2) {
/* 270 */           enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH;
/*     */         }
/* 272 */         if (bool3 || bool4) {
/* 273 */           enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.EAST_WEST;
/*     */         }
/*     */         
/* 276 */         if (!this.f) {
/* 277 */           if (param1Boolean1) {
/* 278 */             if (bool2 && bool4) {
/* 279 */               enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST;
/*     */             }
/* 281 */             if (bool3 && bool2) {
/* 282 */               enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST;
/*     */             }
/* 284 */             if (bool4 && bool1) {
/* 285 */               enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST;
/*     */             }
/* 287 */             if (bool1 && bool3) {
/* 288 */               enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST;
/*     */             }
/*     */           } else {
/* 291 */             if (bool1 && bool3) {
/* 292 */               enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST;
/*     */             }
/* 294 */             if (bool4 && bool1) {
/* 295 */               enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST;
/*     */             }
/* 297 */             if (bool3 && bool2) {
/* 298 */               enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST;
/*     */             }
/* 300 */             if (bool2 && bool4) {
/* 301 */               enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST;
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 307 */       if (enumTrackPosition == BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH) {
/* 308 */         if (BlockMinecartTrackAbstract.b(this.b, blockPosition1.up())) {
/* 309 */           enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_NORTH;
/*     */         }
/* 311 */         if (BlockMinecartTrackAbstract.b(this.b, blockPosition2.up())) {
/* 312 */           enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_SOUTH;
/*     */         }
/*     */       } 
/* 315 */       if (enumTrackPosition == BlockMinecartTrackAbstract.EnumTrackPosition.EAST_WEST) {
/* 316 */         if (BlockMinecartTrackAbstract.b(this.b, blockPosition4.up())) {
/* 317 */           enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_EAST;
/*     */         }
/* 319 */         if (BlockMinecartTrackAbstract.b(this.b, blockPosition3.up())) {
/* 320 */           enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_WEST;
/*     */         }
/*     */       } 
/*     */       
/* 324 */       if (enumTrackPosition == null) {
/* 325 */         enumTrackPosition = BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH;
/*     */       }
/*     */       
/* 328 */       a(enumTrackPosition);
/* 329 */       this.e = this.e.set(this.d.g(), enumTrackPosition);
/*     */       
/* 331 */       if (param1Boolean2 || this.b.getType(this.c) != this.e) {
/* 332 */         this.b.setTypeAndData(this.c, this.e, 3);
/*     */         
/* 334 */         for (byte b = 0; b < this.g.size(); b++) {
/* 335 */           MinecartTrackLogic minecartTrackLogic = b(this.g.get(b));
/* 336 */           if (minecartTrackLogic != null) {
/*     */ 
/*     */             
/* 339 */             minecartTrackLogic.d();
/*     */             
/* 341 */             if (minecartTrackLogic.b(this)) {
/* 342 */               minecartTrackLogic.c(this);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/* 347 */       return this;
/*     */     }
/*     */     
/*     */     public IBlockData c() {
/* 351 */       return this.e;
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean b(World paramWorld, BlockPosition paramBlockPosition) {
/* 356 */     return i(paramWorld.getType(paramBlockPosition));
/*     */   }
/*     */   
/*     */   public static boolean i(IBlockData paramIBlockData) {
/* 360 */     Block block = paramIBlockData.getBlock();
/* 361 */     return (block == Blocks.RAIL || block == Blocks.GOLDEN_RAIL || block == Blocks.DETECTOR_RAIL || block == Blocks.ACTIVATOR_RAIL);
/*     */   }
/*     */   
/*     */   protected BlockMinecartTrackAbstract(boolean paramBoolean) {
/* 365 */     super(Material.ORIENTABLE);
/* 366 */     this.c = paramBoolean;
/* 367 */     a(CreativeModeTab.e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB a(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 377 */     return k;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/* 382 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 387 */     EnumTrackPosition enumTrackPosition = (paramIBlockData.getBlock() == this) ? paramIBlockData.<EnumTrackPosition>get(g()) : null;
/* 388 */     if (enumTrackPosition != null && enumTrackPosition.c()) {
/* 389 */       return b;
/*     */     }
/* 391 */     return a;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 397 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/* 402 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(World paramWorld, BlockPosition paramBlockPosition) {
/* 407 */     return paramWorld.getType(paramBlockPosition.down()).q();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 412 */     if (!paramWorld.isClientSide) {
/* 413 */       paramIBlockData = a(paramWorld, paramBlockPosition, paramIBlockData, true);
/*     */       
/* 415 */       if (this.c) {
/* 416 */         paramIBlockData.doPhysics(paramWorld, paramBlockPosition, this, paramBlockPosition);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/* 423 */     if (paramWorld.isClientSide) {
/*     */       return;
/*     */     }
/*     */     
/* 427 */     EnumTrackPosition enumTrackPosition = paramIBlockData.<EnumTrackPosition>get(g());
/* 428 */     boolean bool = false;
/*     */     
/* 430 */     if (!paramWorld.getType(paramBlockPosition1.down()).q()) {
/* 431 */       bool = true;
/*     */     }
/* 433 */     if (enumTrackPosition == EnumTrackPosition.ASCENDING_EAST && !paramWorld.getType(paramBlockPosition1.east()).q()) {
/* 434 */       bool = true;
/* 435 */     } else if (enumTrackPosition == EnumTrackPosition.ASCENDING_WEST && !paramWorld.getType(paramBlockPosition1.west()).q()) {
/* 436 */       bool = true;
/* 437 */     } else if (enumTrackPosition == EnumTrackPosition.ASCENDING_NORTH && !paramWorld.getType(paramBlockPosition1.north()).q()) {
/* 438 */       bool = true;
/* 439 */     } else if (enumTrackPosition == EnumTrackPosition.ASCENDING_SOUTH && !paramWorld.getType(paramBlockPosition1.south()).q()) {
/* 440 */       bool = true;
/*     */     } 
/*     */     
/* 443 */     if (bool && !paramWorld.isEmpty(paramBlockPosition1)) {
/* 444 */       b(paramWorld, paramBlockPosition1, paramIBlockData, 0);
/* 445 */       paramWorld.setAir(paramBlockPosition1);
/*     */     } else {
/* 447 */       a(paramIBlockData, paramWorld, paramBlockPosition1, paramBlock);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition, Block paramBlock) {}
/*     */   
/*     */   protected IBlockData a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, boolean paramBoolean) {
/* 455 */     if (paramWorld.isClientSide) {
/* 456 */       return paramIBlockData;
/*     */     }
/* 458 */     return (new MinecartTrackLogic(this, paramWorld, paramBlockPosition, paramIBlockData)).a(paramWorld.isBlockIndirectlyPowered(paramBlockPosition), paramBoolean).c();
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumPistonReaction h(IBlockData paramIBlockData) {
/* 463 */     return EnumPistonReaction.NORMAL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 473 */     super.remove(paramWorld, paramBlockPosition, paramIBlockData);
/*     */     
/* 475 */     if (((EnumTrackPosition)paramIBlockData.<EnumTrackPosition>get(g())).c()) {
/* 476 */       paramWorld.applyPhysics(paramBlockPosition.up(), this, false);
/*     */     }
/*     */     
/* 479 */     if (this.c) {
/* 480 */       paramWorld.applyPhysics(paramBlockPosition, this, false);
/* 481 */       paramWorld.applyPhysics(paramBlockPosition.down(), this, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract IBlockState<EnumTrackPosition> g();
/*     */   
/*     */   public enum EnumTrackPosition implements INamable {
/* 488 */     NORTH_SOUTH(0, "north_south"),
/* 489 */     EAST_WEST(1, "east_west"),
/* 490 */     ASCENDING_EAST(2, "ascending_east"),
/* 491 */     ASCENDING_WEST(3, "ascending_west"),
/* 492 */     ASCENDING_NORTH(4, "ascending_north"),
/* 493 */     ASCENDING_SOUTH(5, "ascending_south"),
/* 494 */     SOUTH_EAST(6, "south_east"),
/* 495 */     SOUTH_WEST(7, "south_west"),
/* 496 */     NORTH_WEST(8, "north_west"),
/* 497 */     NORTH_EAST(9, "north_east");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 505 */     private static final EnumTrackPosition[] k = new EnumTrackPosition[(values()).length]; private final int l;
/*     */     static {
/* 507 */       for (EnumTrackPosition enumTrackPosition : values())
/* 508 */         k[enumTrackPosition.a()] = enumTrackPosition; 
/*     */     }
/*     */     private final String m;
/*     */     
/*     */     EnumTrackPosition(int param1Int1, String param1String1) {
/* 513 */       this.l = param1Int1;
/* 514 */       this.m = param1String1;
/*     */     }
/*     */     
/*     */     public int a() {
/* 518 */       return this.l;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 527 */       return this.m;
/*     */     }
/*     */     
/*     */     public boolean c() {
/* 531 */       return (this == ASCENDING_NORTH || this == ASCENDING_EAST || this == ASCENDING_SOUTH || this == ASCENDING_WEST);
/*     */     }
/*     */     
/*     */     public static EnumTrackPosition a(int param1Int) {
/* 535 */       if (param1Int < 0 || param1Int >= k.length) {
/* 536 */         param1Int = 0;
/*     */       }
/* 538 */       return k[param1Int];
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 543 */       return this.m;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockMinecartTrackAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */