/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockBurnEvent;
/*     */ import org.bukkit.event.block.BlockSpreadEvent;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ public class BlockFire extends Block {
/*  16 */   public static final BlockStateInteger AGE = BlockStateInteger.of("age", 0, 15);
/*  17 */   public static final BlockStateBoolean NORTH = BlockStateBoolean.of("north");
/*  18 */   public static final BlockStateBoolean EAST = BlockStateBoolean.of("east");
/*  19 */   public static final BlockStateBoolean SOUTH = BlockStateBoolean.of("south");
/*  20 */   public static final BlockStateBoolean WEST = BlockStateBoolean.of("west");
/*  21 */   public static final BlockStateBoolean UPPER = BlockStateBoolean.of("up");
/*  22 */   private final Map<Block, Integer> flameChances = Maps.newIdentityHashMap();
/*  23 */   private final Map<Block, Integer> B = Maps.newIdentityHashMap();
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  26 */     return (!iblockaccess.getType(blockposition.down()).q() && !Blocks.FIRE.c(iblockaccess, blockposition.down())) ? iblockdata.<Comparable, Boolean>set(NORTH, Boolean.valueOf(c(iblockaccess, blockposition.north()))).<Comparable, Boolean>set(EAST, Boolean.valueOf(c(iblockaccess, blockposition.east()))).<Comparable, Boolean>set(SOUTH, Boolean.valueOf(c(iblockaccess, blockposition.south()))).<Comparable, Boolean>set(WEST, Boolean.valueOf(c(iblockaccess, blockposition.west()))).<Comparable, Boolean>set(UPPER, Boolean.valueOf(c(iblockaccess, blockposition.up()))) : getBlockData();
/*     */   }
/*     */   
/*     */   protected BlockFire() {
/*  30 */     super(Material.FIRE);
/*  31 */     w(this.blockStateList.getBlockData().<Comparable, Integer>set(AGE, Integer.valueOf(0)).<Comparable, Boolean>set(NORTH, Boolean.valueOf(false)).<Comparable, Boolean>set(EAST, Boolean.valueOf(false)).<Comparable, Boolean>set(SOUTH, Boolean.valueOf(false)).<Comparable, Boolean>set(WEST, Boolean.valueOf(false)).set(UPPER, Boolean.valueOf(false)));
/*  32 */     a(true);
/*     */   }
/*     */   
/*     */   public static void e() {
/*  36 */     Blocks.FIRE.a(Blocks.PLANKS, 5, 20);
/*  37 */     Blocks.FIRE.a(Blocks.DOUBLE_WOODEN_SLAB, 5, 20);
/*  38 */     Blocks.FIRE.a(Blocks.WOODEN_SLAB, 5, 20);
/*  39 */     Blocks.FIRE.a(Blocks.FENCE_GATE, 5, 20);
/*  40 */     Blocks.FIRE.a(Blocks.SPRUCE_FENCE_GATE, 5, 20);
/*  41 */     Blocks.FIRE.a(Blocks.BIRCH_FENCE_GATE, 5, 20);
/*  42 */     Blocks.FIRE.a(Blocks.JUNGLE_FENCE_GATE, 5, 20);
/*  43 */     Blocks.FIRE.a(Blocks.DARK_OAK_FENCE_GATE, 5, 20);
/*  44 */     Blocks.FIRE.a(Blocks.ACACIA_FENCE_GATE, 5, 20);
/*  45 */     Blocks.FIRE.a(Blocks.FENCE, 5, 20);
/*  46 */     Blocks.FIRE.a(Blocks.SPRUCE_FENCE, 5, 20);
/*  47 */     Blocks.FIRE.a(Blocks.BIRCH_FENCE, 5, 20);
/*  48 */     Blocks.FIRE.a(Blocks.JUNGLE_FENCE, 5, 20);
/*  49 */     Blocks.FIRE.a(Blocks.DARK_OAK_FENCE, 5, 20);
/*  50 */     Blocks.FIRE.a(Blocks.ACACIA_FENCE, 5, 20);
/*  51 */     Blocks.FIRE.a(Blocks.OAK_STAIRS, 5, 20);
/*  52 */     Blocks.FIRE.a(Blocks.BIRCH_STAIRS, 5, 20);
/*  53 */     Blocks.FIRE.a(Blocks.SPRUCE_STAIRS, 5, 20);
/*  54 */     Blocks.FIRE.a(Blocks.JUNGLE_STAIRS, 5, 20);
/*  55 */     Blocks.FIRE.a(Blocks.ACACIA_STAIRS, 5, 20);
/*  56 */     Blocks.FIRE.a(Blocks.DARK_OAK_STAIRS, 5, 20);
/*  57 */     Blocks.FIRE.a(Blocks.LOG, 5, 5);
/*  58 */     Blocks.FIRE.a(Blocks.LOG2, 5, 5);
/*  59 */     Blocks.FIRE.a(Blocks.LEAVES, 30, 60);
/*  60 */     Blocks.FIRE.a(Blocks.LEAVES2, 30, 60);
/*  61 */     Blocks.FIRE.a(Blocks.BOOKSHELF, 30, 20);
/*  62 */     Blocks.FIRE.a(Blocks.TNT, 15, 100);
/*  63 */     Blocks.FIRE.a(Blocks.TALLGRASS, 60, 100);
/*  64 */     Blocks.FIRE.a(Blocks.DOUBLE_PLANT, 60, 100);
/*  65 */     Blocks.FIRE.a(Blocks.YELLOW_FLOWER, 60, 100);
/*  66 */     Blocks.FIRE.a(Blocks.RED_FLOWER, 60, 100);
/*  67 */     Blocks.FIRE.a(Blocks.DEADBUSH, 60, 100);
/*  68 */     Blocks.FIRE.a(Blocks.WOOL, 30, 60);
/*  69 */     Blocks.FIRE.a(Blocks.VINE, 15, 100);
/*  70 */     Blocks.FIRE.a(Blocks.COAL_BLOCK, 5, 5);
/*  71 */     Blocks.FIRE.a(Blocks.HAY_BLOCK, 60, 20);
/*  72 */     Blocks.FIRE.a(Blocks.CARPET, 60, 20);
/*     */   }
/*     */   
/*     */   public void a(Block block, int i, int j) {
/*  76 */     this.flameChances.put(block, Integer.valueOf(i));
/*  77 */     this.B.put(block, Integer.valueOf(j));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  82 */     return k;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  86 */     return false;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  90 */     return false;
/*     */   }
/*     */   
/*     */   public int a(Random random) {
/*  94 */     return 0;
/*     */   }
/*     */   
/*     */   public int a(World world) {
/*  98 */     return 30;
/*     */   }
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/* 102 */     if (world.getGameRules().getBoolean("doFireTick")) {
/* 103 */       if (!canPlace(world, blockposition)) {
/* 104 */         fireExtinguished(world, blockposition);
/*     */       }
/*     */       
/* 107 */       Block block = world.getType(blockposition.down()).getBlock();
/* 108 */       boolean flag = !(block != Blocks.NETHERRACK && block != Blocks.df);
/*     */       
/* 110 */       if (world.worldProvider instanceof WorldProviderTheEnd && block == Blocks.BEDROCK) {
/* 111 */         flag = true;
/*     */       }
/*     */       
/* 114 */       int i = ((Integer)iblockdata.<Integer>get(AGE)).intValue();
/*     */       
/* 116 */       if (!flag && world.isRaining() && b(world, blockposition) && random.nextFloat() < 0.2F + i * 0.03F) {
/* 117 */         fireExtinguished(world, blockposition);
/*     */       } else {
/* 119 */         if (i < 15) {
/* 120 */           iblockdata = iblockdata.set(AGE, Integer.valueOf(i + random.nextInt(3) / 2));
/* 121 */           world.setTypeAndData(blockposition, iblockdata, 4);
/*     */         } 
/*     */         
/* 124 */         world.a(blockposition, this, a(world) + random.nextInt(10));
/* 125 */         if (!flag) {
/* 126 */           if (!c(world, blockposition)) {
/* 127 */             if (!world.getType(blockposition.down()).q() || i > 3) {
/* 128 */               fireExtinguished(world, blockposition);
/*     */             }
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/* 134 */           if (!c(world, blockposition.down()) && i == 15 && random.nextInt(4) == 0) {
/* 135 */             fireExtinguished(world, blockposition);
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/* 140 */         boolean flag1 = world.C(blockposition);
/* 141 */         byte b0 = 0;
/*     */         
/* 143 */         if (flag1) {
/* 144 */           b0 = -50;
/*     */         }
/*     */ 
/*     */         
/* 148 */         a(world, blockposition.east(), 300 + b0, random, i, blockposition);
/* 149 */         a(world, blockposition.west(), 300 + b0, random, i, blockposition);
/* 150 */         a(world, blockposition.down(), 250 + b0, random, i, blockposition);
/* 151 */         a(world, blockposition.up(), 250 + b0, random, i, blockposition);
/* 152 */         a(world, blockposition.north(), 300 + b0, random, i, blockposition);
/* 153 */         a(world, blockposition.south(), 300 + b0, random, i, blockposition);
/*     */ 
/*     */         
/* 156 */         for (int j = -1; j <= 1; j++) {
/* 157 */           for (int k = -1; k <= 1; k++) {
/* 158 */             for (int l = -1; l <= 4; l++) {
/* 159 */               if (j != 0 || l != 0 || k != 0) {
/* 160 */                 int i1 = 100;
/*     */                 
/* 162 */                 if (l > 1) {
/* 163 */                   i1 += (l - 1) * 100;
/*     */                 }
/*     */                 
/* 166 */                 BlockPosition blockposition1 = blockposition.a(j, l, k);
/* 167 */                 int j1 = d(world, blockposition1);
/*     */                 
/* 169 */                 if (j1 > 0) {
/* 170 */                   int k1 = (j1 + 40 + world.getDifficulty().a() * 7) / (i + 30);
/*     */                   
/* 172 */                   if (flag1) {
/* 173 */                     k1 /= 2;
/*     */                   }
/*     */                   
/* 176 */                   if (k1 > 0 && random.nextInt(i1) <= k1 && (!world.isRaining() || !b(world, blockposition1))) {
/* 177 */                     int l1 = i + random.nextInt(5) / 4;
/*     */                     
/* 179 */                     if (l1 > 15) {
/* 180 */                       l1 = 15;
/*     */                     }
/*     */ 
/*     */                     
/* 184 */                     if (world.getType(blockposition1) != Blocks.FIRE && 
/* 185 */                       !CraftEventFactory.callBlockIgniteEvent(world, blockposition1.getX(), blockposition1.getY(), blockposition1.getZ(), blockposition.getX(), blockposition.getY(), blockposition.getZ()).isCancelled()) {
/*     */ 
/*     */ 
/*     */                       
/* 189 */                       CraftServer craftServer = world.getServer();
/* 190 */                       CraftWorld craftWorld = world.getWorld();
/* 191 */                       BlockState blockState = craftWorld.getBlockAt(blockposition1.getX(), blockposition1.getY(), blockposition1.getZ()).getState();
/* 192 */                       blockState.setTypeId(Block.getId(this));
/* 193 */                       blockState.setData(new MaterialData(Block.getId(this), (byte)l1));
/*     */                       
/* 195 */                       BlockSpreadEvent spreadEvent = new BlockSpreadEvent(blockState.getBlock(), craftWorld.getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), blockState);
/* 196 */                       craftServer.getPluginManager().callEvent((Event)spreadEvent);
/*     */                       
/* 198 */                       if (!spreadEvent.isCancelled()) {
/* 199 */                         blockState.update(true);
/*     */                       }
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean b(World world, BlockPosition blockposition) {
/* 215 */     return !(!world.isRainingAt(blockposition) && !world.isRainingAt(blockposition.west()) && !world.isRainingAt(blockposition.east()) && !world.isRainingAt(blockposition.north()) && !world.isRainingAt(blockposition.south()));
/*     */   }
/*     */   
/*     */   public boolean r() {
/* 219 */     return false;
/*     */   }
/*     */   
/*     */   private int e(Block block) {
/* 223 */     Integer integer = this.B.get(block);
/*     */     
/* 225 */     return (integer == null) ? 0 : integer.intValue();
/*     */   }
/*     */   
/*     */   private int f(Block block) {
/* 229 */     Integer integer = this.flameChances.get(block);
/*     */     
/* 231 */     return (integer == null) ? 0 : integer.intValue();
/*     */   }
/*     */   
/*     */   private void a(World world, BlockPosition blockposition, int i, Random random, int j, BlockPosition sourceposition) {
/* 235 */     int k = e(world.getType(blockposition).getBlock());
/*     */     
/* 237 */     if (random.nextInt(i) < k) {
/* 238 */       IBlockData iblockdata = world.getType(blockposition);
/*     */ 
/*     */       
/* 241 */       Block theBlock = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 242 */       Block sourceBlock = world.getWorld().getBlockAt(sourceposition.getX(), sourceposition.getY(), sourceposition.getZ());
/*     */       
/* 244 */       BlockBurnEvent event = new BlockBurnEvent(theBlock, sourceBlock);
/* 245 */       world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 247 */       if (event.isCancelled()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 252 */       if (random.nextInt(j + 10) < 5 && !world.isRainingAt(blockposition)) {
/* 253 */         int l = j + random.nextInt(5) / 4;
/*     */         
/* 255 */         if (l > 15) {
/* 256 */           l = 15;
/*     */         }
/*     */         
/* 259 */         world.setTypeAndData(blockposition, getBlockData().set(AGE, Integer.valueOf(l)), 3);
/*     */       } else {
/* 261 */         world.setAir(blockposition);
/*     */       } 
/*     */       
/* 264 */       if (iblockdata.getBlock() == Blocks.TNT) {
/* 265 */         Blocks.TNT.postBreak(world, blockposition, iblockdata.set(BlockTNT.EXPLODE, Boolean.valueOf(true)));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean c(World world, BlockPosition blockposition) {
/* 272 */     EnumDirection[] aenumdirection = EnumDirection.values();
/* 273 */     int i = aenumdirection.length;
/*     */     
/* 275 */     for (int j = 0; j < i; j++) {
/* 276 */       EnumDirection enumdirection = aenumdirection[j];
/*     */       
/* 278 */       if (c(world, blockposition.shift(enumdirection))) {
/* 279 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 283 */     return false;
/*     */   }
/*     */   
/*     */   private int d(World world, BlockPosition blockposition) {
/* 287 */     if (!world.isEmpty(blockposition)) {
/* 288 */       return 0;
/*     */     }
/* 290 */     int i = 0;
/* 291 */     EnumDirection[] aenumdirection = EnumDirection.values();
/* 292 */     int j = aenumdirection.length;
/*     */     
/* 294 */     for (int k = 0; k < j; k++) {
/* 295 */       EnumDirection enumdirection = aenumdirection[k];
/*     */       
/* 297 */       i = Math.max(f(world.getType(blockposition.shift(enumdirection)).getBlock()), i);
/*     */     } 
/*     */     
/* 300 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean m() {
/* 305 */     return false;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 309 */     return (f(iblockaccess.getType(blockposition).getBlock()) > 0);
/*     */   }
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition) {
/* 313 */     return !(!world.getType(blockposition.down()).q() && !c(world, blockposition));
/*     */   }
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/* 317 */     if (!world.getType(blockposition.down()).q() && !c(world, blockposition)) {
/* 318 */       fireExtinguished(world, blockposition);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 324 */     if (world.worldProvider.getDimensionManager().getDimensionID() > 0 || !Blocks.PORTAL.b(world, blockposition)) {
/* 325 */       if (!world.getType(blockposition.down()).q() && !c(world, blockposition)) {
/* 326 */         fireExtinguished(world, blockposition);
/*     */       } else {
/* 328 */         world.a(blockposition, this, a(world) + world.random.nextInt(10));
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public MaterialMapColor c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 334 */     return MaterialMapColor.g;
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 338 */     return getBlockData().set(AGE, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 342 */     return ((Integer)iblockdata.<Integer>get(AGE)).intValue();
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 346 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { AGE, NORTH, EAST, SOUTH, WEST, UPPER });
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 350 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ 
/*     */   
/*     */   private void fireExtinguished(World world, BlockPosition position) {
/* 355 */     if (!CraftEventFactory.callBlockFadeEvent(world.getWorld().getBlockAt(position.getX(), position.getY(), position.getZ()), Blocks.AIR).isCancelled())
/* 356 */       world.setAir(position); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockFire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */