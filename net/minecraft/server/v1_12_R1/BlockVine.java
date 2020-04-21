/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockVine
/*     */   extends Block {
/*  11 */   public static final BlockStateBoolean UP = BlockStateBoolean.of("up");
/*  12 */   public static final BlockStateBoolean NORTH = BlockStateBoolean.of("north");
/*  13 */   public static final BlockStateBoolean EAST = BlockStateBoolean.of("east");
/*  14 */   public static final BlockStateBoolean SOUTH = BlockStateBoolean.of("south");
/*  15 */   public static final BlockStateBoolean WEST = BlockStateBoolean.of("west");
/*  16 */   public static final BlockStateBoolean[] f = new BlockStateBoolean[] { UP, NORTH, SOUTH, WEST, EAST };
/*  17 */   protected static final AxisAlignedBB g = new AxisAlignedBB(0.0D, 0.9375D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  18 */   protected static final AxisAlignedBB B = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0625D, 1.0D, 1.0D);
/*  19 */   protected static final AxisAlignedBB C = new AxisAlignedBB(0.9375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  20 */   protected static final AxisAlignedBB D = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0625D);
/*  21 */   protected static final AxisAlignedBB E = new AxisAlignedBB(0.0D, 0.0D, 0.9375D, 1.0D, 1.0D, 1.0D);
/*     */   
/*     */   public BlockVine() {
/*  24 */     super(Material.REPLACEABLE_PLANT);
/*  25 */     w(this.blockStateList.getBlockData().<Comparable, Boolean>set(UP, Boolean.valueOf(false)).<Comparable, Boolean>set(NORTH, Boolean.valueOf(false)).<Comparable, Boolean>set(EAST, Boolean.valueOf(false)).<Comparable, Boolean>set(SOUTH, Boolean.valueOf(false)).set(WEST, Boolean.valueOf(false)));
/*  26 */     a(true);
/*  27 */     a(CreativeModeTab.c);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  32 */     return k;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  36 */     iblockdata = iblockdata.c(iblockaccess, blockposition);
/*  37 */     int i = 0;
/*  38 */     AxisAlignedBB axisalignedbb = j;
/*     */     
/*  40 */     if (((Boolean)iblockdata.<Boolean>get(UP)).booleanValue()) {
/*  41 */       axisalignedbb = g;
/*  42 */       i++;
/*     */     } 
/*     */     
/*  45 */     if (((Boolean)iblockdata.<Boolean>get(NORTH)).booleanValue()) {
/*  46 */       axisalignedbb = D;
/*  47 */       i++;
/*     */     } 
/*     */     
/*  50 */     if (((Boolean)iblockdata.<Boolean>get(EAST)).booleanValue()) {
/*  51 */       axisalignedbb = C;
/*  52 */       i++;
/*     */     } 
/*     */     
/*  55 */     if (((Boolean)iblockdata.<Boolean>get(SOUTH)).booleanValue()) {
/*  56 */       axisalignedbb = E;
/*  57 */       i++;
/*     */     } 
/*     */     
/*  60 */     if (((Boolean)iblockdata.<Boolean>get(WEST)).booleanValue()) {
/*  61 */       axisalignedbb = B;
/*  62 */       i++;
/*     */     } 
/*     */     
/*  65 */     return (i == 1) ? axisalignedbb : j;
/*     */   }
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  69 */     BlockPosition blockposition1 = blockposition.up();
/*     */     
/*  71 */     return iblockdata.set(UP, Boolean.valueOf((iblockaccess.getType(blockposition1).d(iblockaccess, blockposition1, EnumDirection.DOWN) == EnumBlockFaceShape.SOLID)));
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  75 */     return false;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  79 */     return false;
/*     */   }
/*     */   
/*     */   public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  83 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/*  87 */     return (enumdirection != EnumDirection.DOWN && enumdirection != EnumDirection.UP && a(world, blockposition, enumdirection));
/*     */   }
/*     */   
/*     */   public boolean a(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/*  91 */     Block block = world.getType(blockposition.up()).getBlock();
/*     */     
/*  93 */     return (c(world, blockposition.shift(enumdirection.opposite()), enumdirection) && (block == Blocks.AIR || block == Blocks.VINE || c(world, blockposition.up(), EnumDirection.UP)));
/*     */   }
/*     */   
/*     */   private boolean c(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/*  97 */     IBlockData iblockdata = world.getType(blockposition);
/*     */     
/*  99 */     return (iblockdata.d(world, blockposition, enumdirection) == EnumBlockFaceShape.SOLID && !e(iblockdata.getBlock()));
/*     */   }
/*     */   
/*     */   protected static boolean e(Block block) {
/* 103 */     return !(!(block instanceof BlockShulkerBox) && block != Blocks.BEACON && block != Blocks.cauldron && block != Blocks.GLASS && block != Blocks.STAINED_GLASS && block != Blocks.PISTON && block != Blocks.STICKY_PISTON && block != Blocks.PISTON_HEAD && block != Blocks.TRAPDOOR);
/*     */   }
/*     */   
/*     */   private boolean e(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 107 */     IBlockData iblockdata1 = iblockdata;
/* 108 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */     
/* 110 */     while (iterator.hasNext()) {
/* 111 */       EnumDirection enumdirection = iterator.next();
/* 112 */       BlockStateBoolean blockstateboolean = getDirection(enumdirection);
/*     */       
/* 114 */       if (((Boolean)iblockdata.<Boolean>get(blockstateboolean)).booleanValue() && !a(world, blockposition, enumdirection.opposite())) {
/* 115 */         IBlockData iblockdata2 = world.getType(blockposition.up());
/*     */         
/* 117 */         if (iblockdata2.getBlock() != this || !((Boolean)iblockdata2.<Boolean>get(blockstateboolean)).booleanValue()) {
/* 118 */           iblockdata = iblockdata.set(blockstateboolean, Boolean.valueOf(false));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 123 */     if (x(iblockdata) == 0) {
/* 124 */       return false;
/*     */     }
/* 126 */     if (iblockdata1 != iblockdata) {
/* 127 */       world.setTypeAndData(blockposition, iblockdata, 2);
/*     */     }
/*     */     
/* 130 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/* 135 */     if (!world.isClientSide && !e(world, blockposition, iblockdata)) {
/* 136 */       b(world, blockposition, iblockdata, 0);
/* 137 */       world.setAir(blockposition);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/* 143 */     if (!world.isClientSide && 
/* 144 */       world.random.nextInt(Math.max(1, (int)(100.0F / world.spigotConfig.vineModifier) * 4)) == 0) {
/*     */       
/* 146 */       int i = 5;
/* 147 */       boolean flag1 = false;
/*     */       
/*     */       int j;
/* 150 */       label100: for (j = -4; j <= 4; j++) {
/* 151 */         for (int k = -4; k <= 4; k++) {
/* 152 */           for (int l = -1; l <= 1; l++) {
/*     */             
/* 154 */             i--;
/* 155 */             if (world.getType(blockposition.a(j, l, k)).getBlock() == this && i <= 0) {
/* 156 */               flag1 = true;
/*     */               
/*     */               break label100;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 164 */       EnumDirection enumdirection = EnumDirection.a(random);
/* 165 */       BlockPosition blockposition1 = blockposition.up();
/*     */       
/* 167 */       if (enumdirection == EnumDirection.UP && blockposition.getY() < 255 && world.isEmpty(blockposition1)) {
/* 168 */         IBlockData iblockdata1 = iblockdata;
/* 169 */         Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */         
/* 171 */         while (iterator.hasNext()) {
/* 172 */           EnumDirection enumdirection1 = iterator.next();
/*     */           
/* 174 */           if (random.nextBoolean() && a(world, blockposition1, enumdirection1.opposite())) {
/* 175 */             iblockdata1 = iblockdata1.set(getDirection(enumdirection1), Boolean.valueOf(true)); continue;
/*     */           } 
/* 177 */           iblockdata1 = iblockdata1.set(getDirection(enumdirection1), Boolean.valueOf(false));
/*     */         } 
/*     */ 
/*     */         
/* 181 */         if (((Boolean)iblockdata1.<Boolean>get(NORTH)).booleanValue() || ((Boolean)iblockdata1.<Boolean>get(EAST)).booleanValue() || ((Boolean)iblockdata1.<Boolean>get(SOUTH)).booleanValue() || ((Boolean)iblockdata1.<Boolean>get(WEST)).booleanValue())
/*     */         {
/*     */           
/* 184 */           BlockPosition target = blockposition1;
/* 185 */           Block source = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 186 */           Block block = world.getWorld().getBlockAt(target.getX(), target.getY(), target.getZ());
/* 187 */           CraftEventFactory.handleBlockSpreadEvent(block, source, this, toLegacyData(iblockdata1));
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 196 */       else if (enumdirection.k().c() && !((Boolean)iblockdata.<Boolean>get(getDirection(enumdirection))).booleanValue()) {
/* 197 */         if (!flag1) {
/* 198 */           BlockPosition blockposition2 = blockposition.shift(enumdirection);
/* 199 */           IBlockData iblockdata2 = world.getType(blockposition2);
/* 200 */           Block block = iblockdata2.getBlock();
/* 201 */           if (block.material == Material.AIR) {
/* 202 */             EnumDirection enumdirection2 = enumdirection.e();
/* 203 */             EnumDirection enumdirection3 = enumdirection.f();
/* 204 */             boolean flag2 = ((Boolean)iblockdata.<Boolean>get(getDirection(enumdirection2))).booleanValue();
/* 205 */             boolean flag3 = ((Boolean)iblockdata.<Boolean>get(getDirection(enumdirection3))).booleanValue();
/* 206 */             BlockPosition blockposition3 = blockposition2.shift(enumdirection2);
/* 207 */             BlockPosition blockposition4 = blockposition2.shift(enumdirection3);
/*     */ 
/*     */             
/* 210 */             Block source = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 211 */             Block bukkitBlock = world.getWorld().getBlockAt(blockposition2.getX(), blockposition2.getY(), blockposition2.getZ());
/*     */             
/* 213 */             if (flag2 && a(world, blockposition3.shift(enumdirection2), enumdirection2)) {
/*     */               
/* 215 */               CraftEventFactory.handleBlockSpreadEvent(bukkitBlock, source, this, toLegacyData(getBlockData().set(getDirection(enumdirection2), Boolean.valueOf(true))));
/* 216 */             } else if (flag3 && a(world, blockposition4.shift(enumdirection3), enumdirection3)) {
/*     */               
/* 218 */               CraftEventFactory.handleBlockSpreadEvent(bukkitBlock, source, this, toLegacyData(getBlockData().set(getDirection(enumdirection3), Boolean.valueOf(true))));
/* 219 */             } else if (flag2 && world.isEmpty(blockposition3) && a(world, blockposition3, enumdirection)) {
/*     */               
/* 221 */               bukkitBlock = world.getWorld().getBlockAt(blockposition3.getX(), blockposition3.getY(), blockposition3.getZ());
/* 222 */               CraftEventFactory.handleBlockSpreadEvent(bukkitBlock, source, this, toLegacyData(getBlockData().set(getDirection(enumdirection.opposite()), Boolean.valueOf(true))));
/* 223 */             } else if (flag3 && world.isEmpty(blockposition4) && a(world, blockposition4, enumdirection)) {
/*     */               
/* 225 */               bukkitBlock = world.getWorld().getBlockAt(blockposition4.getX(), blockposition4.getY(), blockposition4.getZ());
/* 226 */               CraftEventFactory.handleBlockSpreadEvent(bukkitBlock, source, this, toLegacyData(getBlockData().set(getDirection(enumdirection.opposite()), Boolean.valueOf(true))));
/*     */             }
/*     */           
/* 229 */           } else if (iblockdata2.d(world, blockposition2, enumdirection) == EnumBlockFaceShape.SOLID) {
/* 230 */             world.setTypeAndData(blockposition, iblockdata.set(getDirection(enumdirection), Boolean.valueOf(true)), 2);
/*     */           }
/*     */         
/*     */         }
/*     */       
/* 235 */       } else if (blockposition.getY() > 1) {
/* 236 */         BlockPosition blockposition2 = blockposition.down();
/* 237 */         IBlockData iblockdata2 = world.getType(blockposition2);
/* 238 */         Block block = iblockdata2.getBlock();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 243 */         if (block.material == Material.AIR) {
/* 244 */           IBlockData iblockdata3 = iblockdata;
/* 245 */           Iterator<EnumDirection> iterator1 = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */           
/* 247 */           while (iterator1.hasNext()) {
/* 248 */             EnumDirection enumdirection4 = iterator1.next();
/* 249 */             if (random.nextBoolean()) {
/* 250 */               iblockdata3 = iblockdata3.set(getDirection(enumdirection4), Boolean.valueOf(false));
/*     */             }
/*     */           } 
/*     */           
/* 254 */           if (((Boolean)iblockdata3.<Boolean>get(NORTH)).booleanValue() || ((Boolean)iblockdata3.<Boolean>get(EAST)).booleanValue() || ((Boolean)iblockdata3.<Boolean>get(SOUTH)).booleanValue() || ((Boolean)iblockdata3.<Boolean>get(WEST)).booleanValue())
/*     */           {
/*     */             
/* 257 */             Block source = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 258 */             Block bukkitBlock = world.getWorld().getBlockAt(blockposition2.getX(), blockposition2.getY(), blockposition2.getZ());
/* 259 */             CraftEventFactory.handleBlockSpreadEvent(bukkitBlock, source, this, toLegacyData(iblockdata3));
/*     */           }
/*     */         
/* 262 */         } else if (block == this) {
/* 263 */           IBlockData iblockdata3 = iblockdata2;
/* 264 */           Iterator<EnumDirection> iterator1 = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */           
/* 266 */           while (iterator1.hasNext()) {
/* 267 */             EnumDirection enumdirection4 = iterator1.next();
/* 268 */             BlockStateBoolean blockstateboolean = getDirection(enumdirection4);
/*     */             
/* 270 */             if (random.nextBoolean() && ((Boolean)iblockdata.<Boolean>get(blockstateboolean)).booleanValue()) {
/* 271 */               iblockdata3 = iblockdata3.set(blockstateboolean, Boolean.valueOf(true));
/*     */             }
/*     */           } 
/*     */           
/* 275 */           if (((Boolean)iblockdata3.<Boolean>get(NORTH)).booleanValue() || ((Boolean)iblockdata3.<Boolean>get(EAST)).booleanValue() || ((Boolean)iblockdata3.<Boolean>get(SOUTH)).booleanValue() || ((Boolean)iblockdata3.<Boolean>get(WEST)).booleanValue()) {
/* 276 */             world.setTypeAndData(blockposition2, iblockdata3, 2);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
/* 288 */     IBlockData iblockdata = getBlockData().<Comparable, Boolean>set(UP, Boolean.valueOf(false)).<Comparable, Boolean>set(NORTH, Boolean.valueOf(false)).<Comparable, Boolean>set(EAST, Boolean.valueOf(false)).<Comparable, Boolean>set(SOUTH, Boolean.valueOf(false)).set(WEST, Boolean.valueOf(false));
/*     */     
/* 290 */     return enumdirection.k().c() ? iblockdata.<Comparable, Boolean>set(getDirection(enumdirection.opposite()), Boolean.valueOf(true)) : iblockdata;
/*     */   }
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/* 294 */     return Items.a;
/*     */   }
/*     */   
/*     */   public int a(Random random) {
/* 298 */     return 0;
/*     */   }
/*     */   
/*     */   public void a(World world, EntityHuman entityhuman, BlockPosition blockposition, IBlockData iblockdata, @Nullable TileEntity tileentity, ItemStack itemstack) {
/* 302 */     if (!world.isClientSide && itemstack.getItem() == Items.SHEARS) {
/* 303 */       entityhuman.b(StatisticList.a(this));
/* 304 */       a(world, blockposition, new ItemStack(Blocks.VINE, 1, 0));
/*     */     } else {
/* 306 */       super.a(world, entityhuman, blockposition, iblockdata, tileentity, itemstack);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 312 */     return getBlockData().<Comparable, Boolean>set(SOUTH, Boolean.valueOf(((i & 0x1) > 0))).<Comparable, Boolean>set(WEST, Boolean.valueOf(((i & 0x2) > 0))).<Comparable, Boolean>set(NORTH, Boolean.valueOf(((i & 0x4) > 0))).set(EAST, Boolean.valueOf(((i & 0x8) > 0)));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 316 */     int i = 0;
/*     */     
/* 318 */     if (((Boolean)iblockdata.<Boolean>get(SOUTH)).booleanValue()) {
/* 319 */       i |= 0x1;
/*     */     }
/*     */     
/* 322 */     if (((Boolean)iblockdata.<Boolean>get(WEST)).booleanValue()) {
/* 323 */       i |= 0x2;
/*     */     }
/*     */     
/* 326 */     if (((Boolean)iblockdata.<Boolean>get(NORTH)).booleanValue()) {
/* 327 */       i |= 0x4;
/*     */     }
/*     */     
/* 330 */     if (((Boolean)iblockdata.<Boolean>get(EAST)).booleanValue()) {
/* 331 */       i |= 0x8;
/*     */     }
/*     */     
/* 334 */     return i;
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 338 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { UP, NORTH, EAST, SOUTH, WEST });
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 342 */     switch (enumblockrotation) {
/*     */       case null:
/* 344 */         return iblockdata.<Comparable, Boolean>set(NORTH, iblockdata.<Boolean>get(SOUTH)).<Comparable, Boolean>set(EAST, iblockdata.<Boolean>get(WEST)).<Comparable, Boolean>set(SOUTH, iblockdata.<Boolean>get(NORTH)).set(WEST, iblockdata.<Boolean>get(EAST));
/*     */       
/*     */       case COUNTERCLOCKWISE_90:
/* 347 */         return iblockdata.<Comparable, Boolean>set(NORTH, iblockdata.<Boolean>get(EAST)).<Comparable, Boolean>set(EAST, iblockdata.<Boolean>get(SOUTH)).<Comparable, Boolean>set(SOUTH, iblockdata.<Boolean>get(WEST)).set(WEST, iblockdata.<Boolean>get(NORTH));
/*     */       
/*     */       case CLOCKWISE_90:
/* 350 */         return iblockdata.<Comparable, Boolean>set(NORTH, iblockdata.<Boolean>get(WEST)).<Comparable, Boolean>set(EAST, iblockdata.<Boolean>get(NORTH)).<Comparable, Boolean>set(SOUTH, iblockdata.<Boolean>get(EAST)).set(WEST, iblockdata.<Boolean>get(SOUTH));
/*     */     } 
/*     */     
/* 353 */     return iblockdata;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 358 */     switch (enumblockmirror) {
/*     */       case LEFT_RIGHT:
/* 360 */         return iblockdata.<Comparable, Boolean>set(NORTH, iblockdata.<Boolean>get(SOUTH)).set(SOUTH, iblockdata.<Boolean>get(NORTH));
/*     */       
/*     */       case null:
/* 363 */         return iblockdata.<Comparable, Boolean>set(EAST, iblockdata.<Boolean>get(WEST)).set(WEST, iblockdata.<Boolean>get(EAST));
/*     */     } 
/*     */     
/* 366 */     return super.a(iblockdata, enumblockmirror);
/*     */   }
/*     */ 
/*     */   
/*     */   public static BlockStateBoolean getDirection(EnumDirection enumdirection) {
/* 371 */     switch (enumdirection) {
/*     */       case UP:
/* 373 */         return UP;
/*     */       
/*     */       case NORTH:
/* 376 */         return NORTH;
/*     */       
/*     */       case SOUTH:
/* 379 */         return SOUTH;
/*     */       
/*     */       case WEST:
/* 382 */         return WEST;
/*     */       
/*     */       case EAST:
/* 385 */         return EAST;
/*     */     } 
/*     */     
/* 388 */     throw new IllegalArgumentException(enumdirection + " is an invalid choice");
/*     */   }
/*     */ 
/*     */   
/*     */   public static int x(IBlockData iblockdata) {
/* 393 */     int i = 0;
/* 394 */     BlockStateBoolean[] ablockstateboolean = f;
/* 395 */     int j = ablockstateboolean.length;
/*     */     
/* 397 */     for (int k = 0; k < j; k++) {
/* 398 */       BlockStateBoolean blockstateboolean = ablockstateboolean[k];
/*     */       
/* 400 */       if (((Boolean)iblockdata.<Boolean>get(blockstateboolean)).booleanValue()) {
/* 401 */         i++;
/*     */       }
/*     */     } 
/*     */     
/* 405 */     return i;
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 409 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockVine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */