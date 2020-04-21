/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import com.google.common.cache.LoadingCache;
/*     */ import java.util.Random;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityPortalEnterEvent;
/*     */ import org.bukkit.event.world.PortalCreateEvent;
/*     */ 
/*     */ public class BlockPortal extends BlockHalfTransparent {
/*  12 */   public static final BlockStateEnum<EnumDirection.EnumAxis> AXIS = BlockStateEnum.of("axis", EnumDirection.EnumAxis.class, new EnumDirection.EnumAxis[] { EnumDirection.EnumAxis.X, EnumDirection.EnumAxis.Z });
/*  13 */   protected static final AxisAlignedBB b = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D);
/*  14 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D);
/*  15 */   protected static final AxisAlignedBB d = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
/*     */   
/*     */   public BlockPortal() {
/*  18 */     super(Material.PORTAL, false);
/*  19 */     w(this.blockStateList.getBlockData().set(AXIS, EnumDirection.EnumAxis.X));
/*  20 */     a(true);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  24 */     switch ((EnumDirection.EnumAxis)iblockdata.get((IBlockState)AXIS)) {
/*     */       case null:
/*  26 */         return b;
/*     */ 
/*     */       
/*     */       default:
/*  30 */         return d;
/*     */       case Z:
/*     */         break;
/*  33 */     }  return c;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  38 */     super.b(world, blockposition, iblockdata, random);
/*  39 */     if (world.spigotConfig.enableZombiePigmenPortalSpawns && world.worldProvider.d() && world.getGameRules().getBoolean("doMobSpawning") && random.nextInt(2000) < world.getDifficulty().a()) {
/*  40 */       int i = blockposition.getY();
/*     */       
/*     */       BlockPosition blockposition1;
/*     */       
/*  44 */       for (blockposition1 = blockposition; !world.getType(blockposition1).q() && blockposition1.getY() > 0; blockposition1 = blockposition1.down());
/*     */ 
/*     */ 
/*     */       
/*  48 */       if (i > 0 && !world.getType(blockposition1.up()).l()) {
/*     */         
/*  50 */         Entity entity = ItemMonsterEgg.spawnCreature(world, EntityTypes.getName((Class)EntityPigZombie.class), blockposition1.getX() + 0.5D, blockposition1.getY() + 1.1D, blockposition1.getZ() + 0.5D, CreatureSpawnEvent.SpawnReason.NETHER_PORTAL);
/*     */         
/*  52 */         if (entity != null) {
/*  53 */           entity.portalCooldown = entity.aM();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  62 */     return k;
/*     */   }
/*     */   
/*     */   public static int a(EnumDirection.EnumAxis enumdirection_enumaxis) {
/*  66 */     return (enumdirection_enumaxis == EnumDirection.EnumAxis.X) ? 1 : ((enumdirection_enumaxis == EnumDirection.EnumAxis.Z) ? 2 : 0);
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  70 */     return false;
/*     */   }
/*     */   
/*     */   public boolean b(World world, BlockPosition blockposition) {
/*  74 */     Shape blockportal_shape = new Shape(world, blockposition, EnumDirection.EnumAxis.X);
/*     */     
/*  76 */     if (blockportal_shape.d() && blockportal_shape.e == 0)
/*     */     {
/*  78 */       return blockportal_shape.createPortal();
/*     */     }
/*     */     
/*  81 */     Shape blockportal_shape1 = new Shape(world, blockposition, EnumDirection.EnumAxis.Z);
/*     */     
/*  83 */     if (blockportal_shape1.d() && blockportal_shape1.e == 0) {
/*  84 */       return blockportal_shape1.createPortal();
/*     */     }
/*     */ 
/*     */     
/*  88 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/*  94 */     EnumDirection.EnumAxis enumdirection_enumaxis = iblockdata.<EnumDirection.EnumAxis>get(AXIS);
/*     */ 
/*     */     
/*  97 */     if (enumdirection_enumaxis == EnumDirection.EnumAxis.X) {
/*  98 */       Shape blockportal_shape = new Shape(world, blockposition, EnumDirection.EnumAxis.X);
/*  99 */       if (!blockportal_shape.d() || blockportal_shape.e < blockportal_shape.width * blockportal_shape.height) {
/* 100 */         world.setTypeUpdate(blockposition, Blocks.AIR.getBlockData());
/*     */       }
/* 102 */     } else if (enumdirection_enumaxis == EnumDirection.EnumAxis.Z) {
/* 103 */       Shape blockportal_shape = new Shape(world, blockposition, EnumDirection.EnumAxis.Z);
/* 104 */       if (!blockportal_shape.d() || blockportal_shape.e < blockportal_shape.width * blockportal_shape.height) {
/* 105 */         world.setTypeUpdate(blockposition, Blocks.AIR.getBlockData());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(Random random) {
/* 112 */     return 0;
/*     */   }
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Entity entity) {
/* 116 */     if (!entity.isPassenger() && !entity.isVehicle() && entity.bf()) {
/*     */       
/* 118 */       EntityPortalEnterEvent event = new EntityPortalEnterEvent((Entity)entity.getBukkitEntity(), new Location((World)world.getWorld(), blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/* 119 */       world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 121 */       entity.e(blockposition);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 127 */     return ItemStack.a;
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 131 */     return getBlockData().set(AXIS, ((i & 0x3) == 2) ? EnumDirection.EnumAxis.Z : EnumDirection.EnumAxis.X);
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 135 */     return a(iblockdata.<EnumDirection.EnumAxis>get(AXIS));
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 139 */     switch (enumblockrotation) {
/*     */       case CLOCKWISE_90:
/*     */       case COUNTERCLOCKWISE_90:
/* 142 */         switch ((EnumDirection.EnumAxis)iblockdata.get((IBlockState)AXIS)) {
/*     */           case null:
/* 144 */             return iblockdata.set(AXIS, EnumDirection.EnumAxis.Z);
/*     */           
/*     */           case Z:
/* 147 */             return iblockdata.set(AXIS, EnumDirection.EnumAxis.X);
/*     */         } 
/*     */         
/* 150 */         return iblockdata;
/*     */     } 
/*     */ 
/*     */     
/* 154 */     return iblockdata;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 159 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { AXIS });
/*     */   }
/*     */   
/*     */   public ShapeDetector.ShapeDetectorCollection c(World world, BlockPosition blockposition) {
/* 163 */     EnumDirection.EnumAxis enumdirection_enumaxis = EnumDirection.EnumAxis.Z;
/* 164 */     Shape blockportal_shape = new Shape(world, blockposition, EnumDirection.EnumAxis.X);
/* 165 */     LoadingCache<BlockPosition, ShapeDetectorBlock> loadingcache = ShapeDetector.a(world, true);
/*     */     
/* 167 */     if (!blockportal_shape.d()) {
/* 168 */       enumdirection_enumaxis = EnumDirection.EnumAxis.X;
/* 169 */       blockportal_shape = new Shape(world, blockposition, EnumDirection.EnumAxis.Z);
/*     */     } 
/*     */     
/* 172 */     if (!blockportal_shape.d()) {
/* 173 */       return new ShapeDetector.ShapeDetectorCollection(blockposition, EnumDirection.NORTH, EnumDirection.UP, loadingcache, 1, 1, 1);
/*     */     }
/* 175 */     int[] aint = new int[(EnumDirection.EnumAxisDirection.values()).length];
/* 176 */     EnumDirection enumdirection = blockportal_shape.c.f();
/* 177 */     BlockPosition blockposition1 = blockportal_shape.position.up(blockportal_shape.a() - 1);
/* 178 */     EnumDirection.EnumAxisDirection[] aenumdirection_enumaxisdirection = EnumDirection.EnumAxisDirection.values();
/* 179 */     int i = aenumdirection_enumaxisdirection.length;
/*     */     
/*     */     int j;
/*     */     
/* 183 */     for (j = 0; j < i; j++) {
/* 184 */       EnumDirection.EnumAxisDirection enumdirection_enumaxisdirection = aenumdirection_enumaxisdirection[j];
/* 185 */       ShapeDetector.ShapeDetectorCollection shapedetector_shapedetectorcollection = new ShapeDetector.ShapeDetectorCollection((enumdirection.c() == enumdirection_enumaxisdirection) ? blockposition1 : blockposition1.shift(blockportal_shape.c, blockportal_shape.b() - 1), EnumDirection.a(enumdirection_enumaxisdirection, enumdirection_enumaxis), EnumDirection.UP, loadingcache, blockportal_shape.b(), blockportal_shape.a(), 1);
/*     */       
/* 187 */       for (int k = 0; k < blockportal_shape.b(); k++) {
/* 188 */         for (int l = 0; l < blockportal_shape.a(); l++) {
/* 189 */           ShapeDetectorBlock shapedetectorblock = shapedetector_shapedetectorcollection.a(k, l, 1);
/*     */           
/* 191 */           if (shapedetectorblock.a() != null && shapedetectorblock.a().getMaterial() != Material.AIR) {
/* 192 */             aint[enumdirection_enumaxisdirection.ordinal()] = aint[enumdirection_enumaxisdirection.ordinal()] + 1;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 198 */     EnumDirection.EnumAxisDirection enumdirection_enumaxisdirection1 = EnumDirection.EnumAxisDirection.POSITIVE;
/* 199 */     EnumDirection.EnumAxisDirection[] aenumdirection_enumaxisdirection1 = EnumDirection.EnumAxisDirection.values();
/*     */     
/* 201 */     j = aenumdirection_enumaxisdirection1.length;
/*     */     
/* 203 */     for (int i1 = 0; i1 < j; i1++) {
/* 204 */       EnumDirection.EnumAxisDirection enumdirection_enumaxisdirection2 = aenumdirection_enumaxisdirection1[i1];
/*     */       
/* 206 */       if (aint[enumdirection_enumaxisdirection2.ordinal()] < aint[enumdirection_enumaxisdirection1.ordinal()]) {
/* 207 */         enumdirection_enumaxisdirection1 = enumdirection_enumaxisdirection2;
/*     */       }
/*     */     } 
/*     */     
/* 211 */     return new ShapeDetector.ShapeDetectorCollection((enumdirection.c() == enumdirection_enumaxisdirection1) ? blockposition1 : blockposition1.shift(blockportal_shape.c, blockportal_shape.b() - 1), EnumDirection.a(enumdirection_enumaxisdirection1, enumdirection_enumaxis), EnumDirection.UP, loadingcache, blockportal_shape.b(), blockportal_shape.a(), 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 216 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */   
/*     */   public static class Shape
/*     */   {
/*     */     private final World a;
/*     */     private final EnumDirection.EnumAxis b;
/*     */     private final EnumDirection c;
/*     */     private final EnumDirection d;
/*     */     private int e;
/*     */     private BlockPosition position;
/*     */     private int height;
/*     */     private int width;
/* 229 */     Collection<Block> blocks = new HashSet<>();
/*     */     
/*     */     public Shape(World world, BlockPosition blockposition, EnumDirection.EnumAxis enumdirection_enumaxis) {
/* 232 */       this.a = world;
/* 233 */       this.b = enumdirection_enumaxis;
/* 234 */       if (enumdirection_enumaxis == EnumDirection.EnumAxis.X) {
/* 235 */         this.d = EnumDirection.EAST;
/* 236 */         this.c = EnumDirection.WEST;
/*     */       } else {
/* 238 */         this.d = EnumDirection.NORTH;
/* 239 */         this.c = EnumDirection.SOUTH;
/*     */       } 
/*     */       
/* 242 */       for (BlockPosition blockposition1 = blockposition; blockposition.getY() > blockposition1.getY() - 21 && blockposition.getY() > 0 && a(world.getType(blockposition.down()).getBlock()); blockposition = blockposition.down());
/*     */ 
/*     */ 
/*     */       
/* 246 */       int i = a(blockposition, this.d) - 1;
/*     */       
/* 248 */       if (i >= 0) {
/* 249 */         this.position = blockposition.shift(this.d, i);
/* 250 */         this.width = a(this.position, this.c);
/* 251 */         if (this.width < 2 || this.width > 21) {
/* 252 */           this.position = null;
/* 253 */           this.width = 0;
/*     */         } 
/*     */       } 
/*     */       
/* 257 */       if (this.position != null) {
/* 258 */         this.height = c();
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected int a(BlockPosition blockposition, EnumDirection enumdirection) {
/*     */       int i;
/* 266 */       for (i = 0; i < 22; i++) {
/* 267 */         BlockPosition blockposition1 = blockposition.shift(enumdirection, i);
/*     */         
/* 269 */         if (!a(this.a.getType(blockposition1).getBlock()) || this.a.getType(blockposition1.down()).getBlock() != Blocks.OBSIDIAN) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */       
/* 274 */       Block block = this.a.getType(blockposition.shift(enumdirection, i)).getBlock();
/*     */       
/* 276 */       return (block == Blocks.OBSIDIAN) ? i : 0;
/*     */     }
/*     */     
/*     */     public int a() {
/* 280 */       return this.height;
/*     */     }
/*     */     
/*     */     public int b() {
/* 284 */       return this.width;
/*     */     }
/*     */ 
/*     */     
/*     */     protected int c() {
/* 289 */       this.blocks.clear();
/* 290 */       CraftWorld craftWorld = this.a.getWorld();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 295 */       label42: for (this.height = 0; this.height < 21; this.height++) {
/* 296 */         for (int j = 0; j < this.width; j++) {
/* 297 */           BlockPosition blockposition = this.position.shift(this.c, j).up(this.height);
/* 298 */           Block block = this.a.getType(blockposition).getBlock();
/*     */           
/* 300 */           if (!a(block)) {
/*     */             break label42;
/*     */           }
/*     */           
/* 304 */           if (block == Blocks.PORTAL) {
/* 305 */             this.e++;
/*     */           }
/*     */           
/* 308 */           if (j == 0) {
/* 309 */             block = this.a.getType(blockposition.shift(this.d)).getBlock();
/* 310 */             if (block != Blocks.OBSIDIAN) {
/*     */               break label42;
/*     */             }
/*     */             
/* 314 */             BlockPosition pos = blockposition.shift(this.d);
/* 315 */             this.blocks.add(craftWorld.getBlockAt(pos.getX(), pos.getY(), pos.getZ()));
/*     */           
/*     */           }
/* 318 */           else if (j == this.width - 1) {
/* 319 */             block = this.a.getType(blockposition.shift(this.c)).getBlock();
/* 320 */             if (block != Blocks.OBSIDIAN) {
/*     */               break label42;
/*     */             }
/*     */             
/* 324 */             BlockPosition pos = blockposition.shift(this.c);
/* 325 */             this.blocks.add(craftWorld.getBlockAt(pos.getX(), pos.getY(), pos.getZ()));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 332 */       for (int i = 0; i < this.width; i++) {
/* 333 */         if (this.a.getType(this.position.shift(this.c, i).up(this.height)).getBlock() != Blocks.OBSIDIAN) {
/* 334 */           this.height = 0;
/*     */           
/*     */           break;
/*     */         } 
/* 338 */         BlockPosition pos = this.position.shift(this.c, i).up(this.height);
/* 339 */         this.blocks.add(craftWorld.getBlockAt(pos.getX(), pos.getY(), pos.getZ()));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 344 */       if (this.height <= 21 && this.height >= 3) {
/* 345 */         return this.height;
/*     */       }
/* 347 */       this.position = null;
/* 348 */       this.width = 0;
/* 349 */       this.height = 0;
/* 350 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean a(Block block) {
/* 355 */       return !(block.material != Material.AIR && block != Blocks.FIRE && block != Blocks.PORTAL);
/*     */     }
/*     */     
/*     */     public boolean d() {
/* 359 */       return (this.position != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean createPortal() {
/* 364 */       CraftWorld craftWorld = this.a.getWorld();
/*     */ 
/*     */       
/* 367 */       for (int i = 0; i < this.width; i++) {
/* 368 */         BlockPosition blockposition = this.position.shift(this.c, i);
/*     */         
/* 370 */         for (int k = 0; k < this.height; k++) {
/* 371 */           BlockPosition pos = blockposition.up(k);
/* 372 */           this.blocks.add(craftWorld.getBlockAt(pos.getX(), pos.getY(), pos.getZ()));
/*     */         } 
/*     */       } 
/*     */       
/* 376 */       PortalCreateEvent event = new PortalCreateEvent(this.blocks, (World)craftWorld, PortalCreateEvent.CreateReason.FIRE);
/* 377 */       this.a.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 379 */       if (event.isCancelled()) {
/* 380 */         return false;
/*     */       }
/*     */       
/* 383 */       for (int j = 0; j < this.width; j++) {
/* 384 */         BlockPosition blockposition = this.position.shift(this.c, j);
/*     */         
/* 386 */         for (int k = 0; k < this.height; k++) {
/* 387 */           this.a.setTypeAndData(blockposition.up(k), Blocks.PORTAL.getBlockData().set(BlockPortal.AXIS, this.b), 2);
/*     */         }
/*     */       } 
/*     */       
/* 391 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockPortal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */