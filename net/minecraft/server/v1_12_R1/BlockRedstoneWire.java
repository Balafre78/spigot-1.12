/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ 
/*     */ public class BlockRedstoneWire
/*     */   extends Block {
/*  16 */   public static final BlockStateEnum<EnumRedstoneWireConnection> NORTH = BlockStateEnum.of("north", EnumRedstoneWireConnection.class);
/*  17 */   public static final BlockStateEnum<EnumRedstoneWireConnection> EAST = BlockStateEnum.of("east", EnumRedstoneWireConnection.class);
/*  18 */   public static final BlockStateEnum<EnumRedstoneWireConnection> SOUTH = BlockStateEnum.of("south", EnumRedstoneWireConnection.class);
/*  19 */   public static final BlockStateEnum<EnumRedstoneWireConnection> WEST = BlockStateEnum.of("west", EnumRedstoneWireConnection.class);
/*  20 */   public static final BlockStateInteger POWER = BlockStateInteger.of("power", 0, 15);
/*  21 */   protected static final AxisAlignedBB[] f = new AxisAlignedBB[] { new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 0.8125D), new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 0.8125D), new AxisAlignedBB(0.0D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 1.0D), new AxisAlignedBB(0.1875D, 0.0D, 0.0D, 0.8125D, 0.0625D, 0.8125D), new AxisAlignedBB(0.1875D, 0.0D, 0.0D, 0.8125D, 0.0625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.8125D, 0.0625D, 0.8125D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.8125D, 0.0625D, 1.0D), new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 1.0D, 0.0625D, 0.8125D), new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 1.0D, 0.0625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.1875D, 1.0D, 0.0625D, 0.8125D), new AxisAlignedBB(0.0D, 0.0D, 0.1875D, 1.0D, 0.0625D, 1.0D), new AxisAlignedBB(0.1875D, 0.0D, 0.0D, 1.0D, 0.0625D, 0.8125D), new AxisAlignedBB(0.1875D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 0.8125D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D) };
/*     */   private boolean g = true;
/*  23 */   private final Set<BlockPosition> B = Sets.newHashSet();
/*     */   
/*     */   public BlockRedstoneWire() {
/*  26 */     super(Material.ORIENTABLE);
/*  27 */     w(this.blockStateList.getBlockData().<EnumRedstoneWireConnection, EnumRedstoneWireConnection>set(NORTH, EnumRedstoneWireConnection.NONE).<EnumRedstoneWireConnection, EnumRedstoneWireConnection>set(EAST, EnumRedstoneWireConnection.NONE).<EnumRedstoneWireConnection, EnumRedstoneWireConnection>set(SOUTH, EnumRedstoneWireConnection.NONE).<EnumRedstoneWireConnection, EnumRedstoneWireConnection>set(WEST, EnumRedstoneWireConnection.NONE).set(POWER, Integer.valueOf(0)));
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  31 */     return f[y(iblockdata.c(iblockaccess, blockposition))];
/*     */   }
/*     */   
/*     */   private static int y(IBlockData iblockdata) {
/*  35 */     int i = 0;
/*  36 */     boolean flag = (iblockdata.get(NORTH) != EnumRedstoneWireConnection.NONE);
/*  37 */     boolean flag1 = (iblockdata.get(EAST) != EnumRedstoneWireConnection.NONE);
/*  38 */     boolean flag2 = (iblockdata.get(SOUTH) != EnumRedstoneWireConnection.NONE);
/*  39 */     boolean flag3 = (iblockdata.get(WEST) != EnumRedstoneWireConnection.NONE);
/*     */     
/*  41 */     if (flag || (flag2 && !flag && !flag1 && !flag3)) {
/*  42 */       i |= 1 << EnumDirection.NORTH.get2DRotationValue();
/*     */     }
/*     */     
/*  45 */     if (flag1 || (flag3 && !flag && !flag1 && !flag2)) {
/*  46 */       i |= 1 << EnumDirection.EAST.get2DRotationValue();
/*     */     }
/*     */     
/*  49 */     if (flag2 || (flag && !flag1 && !flag2 && !flag3)) {
/*  50 */       i |= 1 << EnumDirection.SOUTH.get2DRotationValue();
/*     */     }
/*     */     
/*  53 */     if (flag3 || (flag1 && !flag && !flag2 && !flag3)) {
/*  54 */       i |= 1 << EnumDirection.WEST.get2DRotationValue();
/*     */     }
/*     */     
/*  57 */     return i;
/*     */   }
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  61 */     iblockdata = iblockdata.set(WEST, a(iblockaccess, blockposition, EnumDirection.WEST));
/*  62 */     iblockdata = iblockdata.set(EAST, a(iblockaccess, blockposition, EnumDirection.EAST));
/*  63 */     iblockdata = iblockdata.set(NORTH, a(iblockaccess, blockposition, EnumDirection.NORTH));
/*  64 */     iblockdata = iblockdata.set(SOUTH, a(iblockaccess, blockposition, EnumDirection.SOUTH));
/*  65 */     return iblockdata;
/*     */   }
/*     */   
/*     */   private EnumRedstoneWireConnection a(IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/*  69 */     BlockPosition blockposition1 = blockposition.shift(enumdirection);
/*  70 */     IBlockData iblockdata = iblockaccess.getType(blockposition.shift(enumdirection));
/*     */     
/*  72 */     if (!a(iblockaccess.getType(blockposition1), enumdirection) && (iblockdata.l() || !x(iblockaccess.getType(blockposition1.down())))) {
/*  73 */       IBlockData iblockdata1 = iblockaccess.getType(blockposition.up());
/*     */       
/*  75 */       if (!iblockdata1.l()) {
/*  76 */         boolean flag = !(!iblockaccess.getType(blockposition1).q() && iblockaccess.getType(blockposition1).getBlock() != Blocks.GLOWSTONE);
/*     */         
/*  78 */         if (flag && x(iblockaccess.getType(blockposition1.up()))) {
/*  79 */           if (iblockdata.k()) {
/*  80 */             return EnumRedstoneWireConnection.UP;
/*     */           }
/*     */           
/*  83 */           return EnumRedstoneWireConnection.SIDE;
/*     */         } 
/*     */       } 
/*     */       
/*  87 */       return EnumRedstoneWireConnection.NONE;
/*     */     } 
/*  89 */     return EnumRedstoneWireConnection.SIDE;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  95 */     return k;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  99 */     return false;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/* 103 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition) {
/* 107 */     return !(!world.getType(blockposition.down()).q() && world.getType(blockposition.down()).getBlock() != Blocks.GLOWSTONE);
/*     */   }
/*     */   
/*     */   private IBlockData e(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 111 */     iblockdata = a(world, blockposition, blockposition, iblockdata);
/* 112 */     ArrayList arraylist = Lists.newArrayList(this.B);
/*     */     
/* 114 */     this.B.clear();
/* 115 */     Iterator<BlockPosition> iterator = arraylist.iterator();
/*     */     
/* 117 */     while (iterator.hasNext()) {
/* 118 */       BlockPosition blockposition1 = iterator.next();
/*     */       
/* 120 */       world.applyPhysics(blockposition1, this, false);
/*     */     } 
/*     */     
/* 123 */     return iblockdata;
/*     */   }
/*     */   
/*     */   private IBlockData a(World world, BlockPosition blockposition, BlockPosition blockposition1, IBlockData iblockdata) {
/* 127 */     IBlockData iblockdata1 = iblockdata;
/* 128 */     int i = ((Integer)iblockdata.<Integer>get(POWER)).intValue();
/* 129 */     byte b0 = 0;
/* 130 */     int j = getPower(world, blockposition1, b0);
/*     */     
/* 132 */     this.g = false;
/* 133 */     int k = world.z(blockposition);
/*     */     
/* 135 */     this.g = true;
/* 136 */     if (k > 0 && k > j - 1) {
/* 137 */       j = k;
/*     */     }
/*     */     
/* 140 */     int l = 0;
/* 141 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */     
/* 143 */     while (iterator.hasNext()) {
/* 144 */       EnumDirection enumdirection = iterator.next();
/* 145 */       BlockPosition blockposition2 = blockposition.shift(enumdirection);
/* 146 */       boolean flag = !(blockposition2.getX() == blockposition1.getX() && blockposition2.getZ() == blockposition1.getZ());
/*     */       
/* 148 */       if (flag) {
/* 149 */         l = getPower(world, blockposition2, l);
/*     */       }
/*     */       
/* 152 */       if (world.getType(blockposition2).l() && !world.getType(blockposition.up()).l()) {
/* 153 */         if (flag && blockposition.getY() >= blockposition1.getY())
/* 154 */           l = getPower(world, blockposition2.up(), l);  continue;
/*     */       } 
/* 156 */       if (!world.getType(blockposition2).l() && flag && blockposition.getY() <= blockposition1.getY()) {
/* 157 */         l = getPower(world, blockposition2.down(), l);
/*     */       }
/*     */     } 
/*     */     
/* 161 */     if (l > j) {
/* 162 */       j = l - 1;
/* 163 */     } else if (j > 0) {
/* 164 */       j--;
/*     */     } else {
/* 166 */       j = 0;
/*     */     } 
/*     */     
/* 169 */     if (k > j - 1) {
/* 170 */       j = k;
/*     */     }
/*     */ 
/*     */     
/* 174 */     if (i != j) {
/* 175 */       BlockRedstoneEvent event = new BlockRedstoneEvent(world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), i, j);
/* 176 */       world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 178 */       j = event.getNewCurrent();
/*     */     } 
/*     */ 
/*     */     
/* 182 */     if (i != j) {
/* 183 */       iblockdata = iblockdata.set(POWER, Integer.valueOf(j));
/* 184 */       if (world.getType(blockposition) == iblockdata1) {
/* 185 */         world.setTypeAndData(blockposition, iblockdata, 2);
/*     */       }
/*     */       
/* 188 */       this.B.add(blockposition);
/* 189 */       EnumDirection[] aenumdirection = EnumDirection.values();
/* 190 */       int i1 = aenumdirection.length;
/*     */       
/* 192 */       for (int j1 = 0; j1 < i1; j1++) {
/* 193 */         EnumDirection enumdirection1 = aenumdirection[j1];
/*     */         
/* 195 */         this.B.add(blockposition.shift(enumdirection1));
/*     */       } 
/*     */     } 
/*     */     
/* 199 */     return iblockdata;
/*     */   }
/*     */   
/*     */   private void b(World world, BlockPosition blockposition) {
/* 203 */     if (world.getType(blockposition).getBlock() == this) {
/* 204 */       world.applyPhysics(blockposition, this, false);
/* 205 */       EnumDirection[] aenumdirection = EnumDirection.values();
/* 206 */       int i = aenumdirection.length;
/*     */       
/* 208 */       for (int j = 0; j < i; j++) {
/* 209 */         EnumDirection enumdirection = aenumdirection[j];
/*     */         
/* 211 */         world.applyPhysics(blockposition.shift(enumdirection), this, false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 218 */     if (!world.isClientSide) {
/* 219 */       e(world, blockposition, iblockdata);
/* 220 */       Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.VERTICAL.iterator();
/*     */ 
/*     */ 
/*     */       
/* 224 */       while (iterator.hasNext()) {
/* 225 */         EnumDirection enumdirection = iterator.next();
/* 226 */         world.applyPhysics(blockposition.shift(enumdirection), this, false);
/*     */       } 
/*     */       
/* 229 */       iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */       
/* 231 */       while (iterator.hasNext()) {
/* 232 */         EnumDirection enumdirection = iterator.next();
/* 233 */         b(world, blockposition.shift(enumdirection));
/*     */       } 
/*     */       
/* 236 */       iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */       
/* 238 */       while (iterator.hasNext()) {
/* 239 */         EnumDirection enumdirection = iterator.next();
/* 240 */         BlockPosition blockposition1 = blockposition.shift(enumdirection);
/*     */         
/* 242 */         if (world.getType(blockposition1).l()) {
/* 243 */           b(world, blockposition1.up()); continue;
/*     */         } 
/* 245 */         b(world, blockposition1.down());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 253 */     super.remove(world, blockposition, iblockdata);
/* 254 */     if (!world.isClientSide) {
/* 255 */       EnumDirection[] aenumdirection = EnumDirection.values();
/* 256 */       int i = aenumdirection.length;
/*     */       
/* 258 */       for (int j = 0; j < i; j++) {
/* 259 */         EnumDirection enumdirection = aenumdirection[j];
/*     */         
/* 261 */         world.applyPhysics(blockposition.shift(enumdirection), this, false);
/*     */       } 
/*     */       
/* 264 */       e(world, blockposition, iblockdata);
/* 265 */       Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */ 
/*     */ 
/*     */       
/* 269 */       while (iterator.hasNext()) {
/* 270 */         EnumDirection enumdirection1 = iterator.next();
/* 271 */         b(world, blockposition.shift(enumdirection1));
/*     */       } 
/*     */       
/* 274 */       iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */       
/* 276 */       while (iterator.hasNext()) {
/* 277 */         EnumDirection enumdirection1 = iterator.next();
/* 278 */         BlockPosition blockposition1 = blockposition.shift(enumdirection1);
/*     */         
/* 280 */         if (world.getType(blockposition1).l()) {
/* 281 */           b(world, blockposition1.up()); continue;
/*     */         } 
/* 283 */         b(world, blockposition1.down());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPower(World world, BlockPosition blockposition, int i) {
/* 291 */     if (world.getType(blockposition).getBlock() != this) {
/* 292 */       return i;
/*     */     }
/* 294 */     int j = ((Integer)world.getType(blockposition).<Integer>get(POWER)).intValue();
/*     */     
/* 296 */     return (j > i) ? j : i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/* 301 */     if (!world.isClientSide) {
/* 302 */       if (canPlace(world, blockposition)) {
/* 303 */         e(world, blockposition, iblockdata);
/*     */       } else {
/* 305 */         b(world, blockposition, iblockdata, 0);
/* 306 */         world.setAir(blockposition);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/* 313 */     return Items.REDSTONE;
/*     */   }
/*     */   
/*     */   public int c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 317 */     return !this.g ? 0 : iblockdata.a(iblockaccess, blockposition, enumdirection);
/*     */   }
/*     */   
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 321 */     if (!this.g) {
/* 322 */       return 0;
/*     */     }
/* 324 */     int i = ((Integer)iblockdata.<Integer>get(POWER)).intValue();
/*     */     
/* 326 */     if (i == 0)
/* 327 */       return 0; 
/* 328 */     if (enumdirection == EnumDirection.UP) {
/* 329 */       return i;
/*     */     }
/* 331 */     EnumSet<EnumDirection> enumset = EnumSet.noneOf(EnumDirection.class);
/* 332 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */     
/* 334 */     while (iterator.hasNext()) {
/* 335 */       EnumDirection enumdirection1 = iterator.next();
/*     */       
/* 337 */       if (b(iblockaccess, blockposition, enumdirection1)) {
/* 338 */         enumset.add(enumdirection1);
/*     */       }
/*     */     } 
/*     */     
/* 342 */     if (enumdirection.k().c() && enumset.isEmpty())
/* 343 */       return i; 
/* 344 */     if (enumset.contains(enumdirection) && !enumset.contains(enumdirection.f()) && !enumset.contains(enumdirection.e())) {
/* 345 */       return i;
/*     */     }
/* 347 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean b(IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 354 */     BlockPosition blockposition1 = blockposition.shift(enumdirection);
/* 355 */     IBlockData iblockdata = iblockaccess.getType(blockposition1);
/* 356 */     boolean flag = iblockdata.l();
/* 357 */     boolean flag1 = iblockaccess.getType(blockposition.up()).l();
/*     */     
/* 359 */     return (!flag1 && flag && c(iblockaccess, blockposition1.up())) ? true : (a(iblockdata, enumdirection) ? true : ((iblockdata.getBlock() == Blocks.POWERED_REPEATER && iblockdata.get(BlockDiodeAbstract.FACING) == enumdirection) ? true : ((!flag && c(iblockaccess, blockposition1.down())))));
/*     */   }
/*     */   
/*     */   protected static boolean c(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 363 */     return x(iblockaccess.getType(blockposition));
/*     */   }
/*     */   
/*     */   protected static boolean x(IBlockData iblockdata) {
/* 367 */     return a(iblockdata, (EnumDirection)null);
/*     */   }
/*     */   
/*     */   protected static boolean a(IBlockData iblockdata, @Nullable EnumDirection enumdirection) {
/* 371 */     Block block = iblockdata.getBlock();
/*     */     
/* 373 */     if (block == Blocks.REDSTONE_WIRE)
/* 374 */       return true; 
/* 375 */     if (Blocks.UNPOWERED_REPEATER.D(iblockdata)) {
/* 376 */       EnumDirection enumdirection1 = iblockdata.<EnumDirection>get(BlockRepeater.FACING);
/*     */       
/* 378 */       return !(enumdirection1 != enumdirection && enumdirection1.opposite() != enumdirection);
/*     */     } 
/* 380 */     return (Blocks.dk == iblockdata.getBlock()) ? ((enumdirection == iblockdata.get(BlockObserver.FACING))) : ((iblockdata.m() && enumdirection != null));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/* 385 */     return this.g;
/*     */   }
/*     */   
/*     */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 389 */     return new ItemStack(Items.REDSTONE);
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 393 */     return getBlockData().set(POWER, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 397 */     return ((Integer)iblockdata.<Integer>get(POWER)).intValue();
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 401 */     switch (enumblockrotation) {
/*     */       case null:
/* 403 */         return iblockdata.<EnumRedstoneWireConnection, EnumRedstoneWireConnection>set(NORTH, iblockdata.<EnumRedstoneWireConnection>get(SOUTH)).<EnumRedstoneWireConnection, EnumRedstoneWireConnection>set(EAST, iblockdata.<EnumRedstoneWireConnection>get(WEST)).<EnumRedstoneWireConnection, EnumRedstoneWireConnection>set(SOUTH, iblockdata.<EnumRedstoneWireConnection>get(NORTH)).set(WEST, iblockdata.<EnumRedstoneWireConnection>get(EAST));
/*     */       
/*     */       case COUNTERCLOCKWISE_90:
/* 406 */         return iblockdata.<EnumRedstoneWireConnection, EnumRedstoneWireConnection>set(NORTH, iblockdata.<EnumRedstoneWireConnection>get(EAST)).<EnumRedstoneWireConnection, EnumRedstoneWireConnection>set(EAST, iblockdata.<EnumRedstoneWireConnection>get(SOUTH)).<EnumRedstoneWireConnection, EnumRedstoneWireConnection>set(SOUTH, iblockdata.<EnumRedstoneWireConnection>get(WEST)).set(WEST, iblockdata.<EnumRedstoneWireConnection>get(NORTH));
/*     */       
/*     */       case CLOCKWISE_90:
/* 409 */         return iblockdata.<EnumRedstoneWireConnection, EnumRedstoneWireConnection>set(NORTH, iblockdata.<EnumRedstoneWireConnection>get(WEST)).<EnumRedstoneWireConnection, EnumRedstoneWireConnection>set(EAST, iblockdata.<EnumRedstoneWireConnection>get(NORTH)).<EnumRedstoneWireConnection, EnumRedstoneWireConnection>set(SOUTH, iblockdata.<EnumRedstoneWireConnection>get(EAST)).set(WEST, iblockdata.<EnumRedstoneWireConnection>get(SOUTH));
/*     */     } 
/*     */     
/* 412 */     return iblockdata;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 417 */     switch (enumblockmirror) {
/*     */       case LEFT_RIGHT:
/* 419 */         return iblockdata.<EnumRedstoneWireConnection, EnumRedstoneWireConnection>set(NORTH, iblockdata.<EnumRedstoneWireConnection>get(SOUTH)).set(SOUTH, iblockdata.<EnumRedstoneWireConnection>get(NORTH));
/*     */       
/*     */       case null:
/* 422 */         return iblockdata.<EnumRedstoneWireConnection, EnumRedstoneWireConnection>set(EAST, iblockdata.<EnumRedstoneWireConnection>get(WEST)).set(WEST, iblockdata.<EnumRedstoneWireConnection>get(EAST));
/*     */     } 
/*     */     
/* 425 */     return super.a(iblockdata, enumblockmirror);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 430 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { NORTH, EAST, SOUTH, WEST, POWER });
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 434 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */   
/*     */   enum EnumRedstoneWireConnection
/*     */     implements INamable {
/* 439 */     UP("up"), SIDE("side"), NONE("none");
/*     */     
/*     */     private final String d;
/*     */     
/*     */     EnumRedstoneWireConnection(String s) {
/* 444 */       this.d = s;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 448 */       return getName();
/*     */     }
/*     */     
/*     */     public String getName() {
/* 452 */       return this.d;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockRedstoneWire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */