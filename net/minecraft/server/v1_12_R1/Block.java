/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.spigotmc.AsyncCatcher;
/*     */ 
/*     */ public class Block {
/*  13 */   private static final MinecraftKey a = new MinecraftKey("air");
/*  14 */   public static final RegistryBlocks<MinecraftKey, Block> REGISTRY = new RegistryBlocks<>(a);
/*  15 */   public static final RegistryBlockID<IBlockData> REGISTRY_ID = new RegistryBlockID<>();
/*  16 */   public static final AxisAlignedBB j = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */   @Nullable
/*  18 */   public static final AxisAlignedBB k = null;
/*     */   private CreativeModeTab creativeTab;
/*     */   protected boolean l;
/*     */   protected int m;
/*     */   protected boolean n;
/*     */   protected int o;
/*     */   protected boolean p;
/*     */   protected float strength;
/*     */   protected float durability;
/*     */   protected boolean s;
/*     */   protected boolean t;
/*     */   protected boolean isTileEntity;
/*     */   protected SoundEffectType stepSound;
/*     */   public float w;
/*     */   protected final Material material;
/*     */   protected final MaterialMapColor y;
/*     */   public float frictionFactor;
/*     */   protected final BlockStateList blockStateList;
/*     */   private IBlockData blockData;
/*     */   private String name;
/*     */   
/*     */   public static int getId(Block block) {
/*  40 */     return REGISTRY.a(block);
/*     */   }
/*     */   
/*     */   public static int getCombinedId(IBlockData iblockdata) {
/*  44 */     Block block = iblockdata.getBlock();
/*     */     
/*  46 */     return getId(block) + (block.toLegacyData(iblockdata) << 12);
/*     */   }
/*     */   
/*     */   public static Block getById(int i) {
/*  50 */     return REGISTRY.getId(i);
/*     */   }
/*     */   
/*     */   public static IBlockData getByCombinedId(int i) {
/*  54 */     int j = i & 0xFFF;
/*  55 */     int k = i >> 12 & 0xF;
/*     */     
/*  57 */     return getById(j).fromLegacyData(k);
/*     */   }
/*     */   
/*     */   public static Block asBlock(@Nullable Item item) {
/*  61 */     return (item instanceof ItemBlock) ? ((ItemBlock)item).getBlock() : Blocks.AIR;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Block getByName(String s) {
/*  66 */     MinecraftKey minecraftkey = new MinecraftKey(s);
/*     */     
/*  68 */     if (REGISTRY.d(minecraftkey)) {
/*  69 */       return REGISTRY.get(minecraftkey);
/*     */     }
/*     */     try {
/*  72 */       return REGISTRY.getId(Integer.parseInt(s));
/*  73 */     } catch (NumberFormatException numberFormatException) {
/*  74 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean k(IBlockData iblockdata) {
/*  81 */     return (iblockdata.getMaterial().k() && iblockdata.g());
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean l(IBlockData iblockdata) {
/*  86 */     return this.l;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean a(IBlockData iblockdata, Entity entity) {
/*  91 */     return true;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public int m(IBlockData iblockdata) {
/*  96 */     return this.m;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public int o(IBlockData iblockdata) {
/* 101 */     return this.o;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean p(IBlockData iblockdata) {
/* 106 */     return this.p;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public Material q(IBlockData iblockdata) {
/* 111 */     return this.material;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public MaterialMapColor c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 116 */     return this.y;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public IBlockData fromLegacyData(int i) {
/* 121 */     return getBlockData();
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 125 */     if (iblockdata.s().isEmpty()) {
/* 126 */       return 0;
/*     */     }
/* 128 */     throw new IllegalArgumentException("Don't know how to convert " + iblockdata + " back into data...");
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public IBlockData updateState(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 134 */     return iblockdata;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 139 */     return iblockdata;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 144 */     return iblockdata;
/*     */   }
/*     */   
/*     */   public Block(Material material, MaterialMapColor materialmapcolor) {
/* 148 */     this.s = true;
/* 149 */     this.stepSound = SoundEffectType.d;
/* 150 */     this.w = 1.0F;
/* 151 */     this.frictionFactor = 0.6F;
/* 152 */     this.material = material;
/* 153 */     this.y = materialmapcolor;
/* 154 */     this.blockStateList = getStateList();
/* 155 */     w(this.blockStateList.getBlockData());
/* 156 */     this.l = getBlockData().p();
/* 157 */     this.m = this.l ? 255 : 0;
/* 158 */     this.n = !material.blocksLight();
/*     */   }
/*     */   
/*     */   protected Block(Material material) {
/* 162 */     this(material, material.r());
/*     */   }
/*     */   
/*     */   protected Block a(SoundEffectType soundeffecttype) {
/* 166 */     this.stepSound = soundeffecttype;
/* 167 */     return this;
/*     */   }
/*     */   
/*     */   protected Block e(int i) {
/* 171 */     this.m = i;
/* 172 */     return this;
/*     */   }
/*     */   
/*     */   protected Block a(float f) {
/* 176 */     this.o = (int)(15.0F * f);
/* 177 */     return this;
/*     */   }
/*     */   
/*     */   protected Block b(float f) {
/* 181 */     this.durability = f * 3.0F;
/* 182 */     return this;
/*     */   }
/*     */   
/*     */   protected static boolean b(Block block) {
/* 186 */     return !(!(block instanceof BlockShulkerBox) && !(block instanceof BlockLeaves) && !(block instanceof BlockTrapdoor) && block != Blocks.BEACON && block != Blocks.cauldron && block != Blocks.GLASS && block != Blocks.GLOWSTONE && block != Blocks.ICE && block != Blocks.SEA_LANTERN && block != Blocks.STAINED_GLASS);
/*     */   }
/*     */   
/*     */   protected static boolean c(Block block) {
/* 190 */     return !(!b(block) && block != Blocks.PISTON && block != Blocks.STICKY_PISTON && block != Blocks.PISTON_HEAD);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean r(IBlockData iblockdata) {
/* 195 */     return (iblockdata.getMaterial().isSolid() && iblockdata.g());
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean isOccluding(IBlockData iblockdata) {
/* 200 */     return (iblockdata.getMaterial().k() && iblockdata.g() && !iblockdata.m());
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean t(IBlockData iblockdata) {
/* 205 */     return (this.material.isSolid() && getBlockData().g());
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean c(IBlockData iblockdata) {
/* 210 */     return true;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 214 */     return !this.material.isSolid();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public EnumRenderType a(IBlockData iblockdata) {
/* 219 */     return EnumRenderType.MODEL;
/*     */   }
/*     */   
/*     */   public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 223 */     return false;
/*     */   }
/*     */   
/*     */   protected Block c(float f) {
/* 227 */     this.strength = f;
/* 228 */     if (this.durability < f * 5.0F) {
/* 229 */       this.durability = f * 5.0F;
/*     */     }
/*     */     
/* 232 */     return this;
/*     */   }
/*     */   
/*     */   protected Block j() {
/* 236 */     c(-1.0F);
/* 237 */     return this;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public float a(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 242 */     return this.strength;
/*     */   }
/*     */   
/*     */   protected Block a(boolean flag) {
/* 246 */     this.t = flag;
/* 247 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isTicking() {
/* 251 */     return this.t;
/*     */   }
/*     */   
/*     */   public boolean isTileEntity() {
/* 255 */     return this.isTileEntity;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 260 */     return j;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 265 */     return EnumBlockFaceShape.SOLID;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, AxisAlignedBB axisalignedbb, List<AxisAlignedBB> list, @Nullable Entity entity, boolean flag) {
/* 270 */     a(blockposition, axisalignedbb, list, iblockdata.d(world, blockposition));
/*     */   }
/*     */   
/*     */   protected static void a(BlockPosition blockposition, AxisAlignedBB axisalignedbb, List<AxisAlignedBB> list, @Nullable AxisAlignedBB axisalignedbb1) {
/* 274 */     if (axisalignedbb1 != k) {
/* 275 */       AxisAlignedBB axisalignedbb2 = axisalignedbb1.a(blockposition);
/*     */       
/* 277 */       if (axisalignedbb.c(axisalignedbb2)) {
/* 278 */         list.add(axisalignedbb2);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @Nullable
/*     */   public AxisAlignedBB a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 287 */     return iblockdata.e(iblockaccess, blockposition);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean b(IBlockData iblockdata) {
/* 292 */     return true;
/*     */   }
/*     */   
/*     */   public boolean a(IBlockData iblockdata, boolean flag) {
/* 296 */     return m();
/*     */   }
/*     */   
/*     */   public boolean m() {
/* 300 */     return true;
/*     */   }
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/* 304 */     b(world, blockposition, iblockdata, random);
/*     */   }
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {}
/*     */   
/*     */   public void postBreak(World world, BlockPosition blockposition, IBlockData iblockdata) {}
/*     */   
/*     */   @Deprecated
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {}
/*     */   
/*     */   public int a(World world) {
/* 315 */     return 10;
/*     */   }
/*     */   
/*     */   public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 319 */     AsyncCatcher.catchOp("block onPlace");
/*     */   }
/*     */   
/*     */   public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 323 */     AsyncCatcher.catchOp("block remove");
/*     */   }
/*     */   
/*     */   public int a(Random random) {
/* 327 */     return 1;
/*     */   }
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/* 331 */     return Item.getItemOf(this);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public float getDamage(IBlockData iblockdata, EntityHuman entityhuman, World world, BlockPosition blockposition) {
/* 336 */     float f = iblockdata.b(world, blockposition);
/*     */     
/* 338 */     return (f < 0.0F) ? 0.0F : (!entityhuman.hasBlock(iblockdata) ? (entityhuman.b(iblockdata) / f / 100.0F) : (entityhuman.b(iblockdata) / f / 30.0F));
/*     */   }
/*     */   
/*     */   public final void b(World world, BlockPosition blockposition, IBlockData iblockdata, int i) {
/* 342 */     dropNaturally(world, blockposition, iblockdata, 1.0F, i);
/*     */   }
/*     */   
/*     */   public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
/* 346 */     if (!world.isClientSide) {
/* 347 */       int j = getDropCount(i, world.random);
/*     */       
/* 349 */       for (int k = 0; k < j; k++) {
/*     */         
/* 351 */         if (world.random.nextFloat() < f) {
/* 352 */           Item item = getDropType(iblockdata, world.random, i);
/*     */           
/* 354 */           if (item != Items.a) {
/* 355 */             a(world, blockposition, new ItemStack(item, 1, getDropData(iblockdata)));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(World world, BlockPosition blockposition, ItemStack itemstack) {
/* 364 */     if (!world.isClientSide && !itemstack.isEmpty() && world.getGameRules().getBoolean("doTileDrops")) {
/*     */       
/* 366 */       double d0 = (world.random.nextFloat() * 0.5F) + 0.25D;
/* 367 */       double d1 = (world.random.nextFloat() * 0.5F) + 0.25D;
/* 368 */       double d2 = (world.random.nextFloat() * 0.5F) + 0.25D;
/* 369 */       EntityItem entityitem = new EntityItem(world, blockposition.getX() + d0, blockposition.getY() + d1, blockposition.getZ() + d2, itemstack);
/*     */       
/* 371 */       entityitem.q();
/*     */       
/* 373 */       if (world.captureDrops != null) {
/* 374 */         world.captureDrops.add(entityitem);
/*     */       } else {
/* 376 */         world.addEntity(entityitem);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dropExperience(World world, BlockPosition blockposition, int i) {
/* 383 */     if (!world.isClientSide && world.getGameRules().getBoolean("doTileDrops")) {
/* 384 */       while (i > 0) {
/* 385 */         int j = EntityExperienceOrb.getOrbValue(i);
/*     */         
/* 387 */         i -= j;
/* 388 */         world.addEntity(new EntityExperienceOrb(world, blockposition.getX() + 0.5D, blockposition.getY() + 0.5D, blockposition.getZ() + 0.5D, j));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData iblockdata) {
/* 395 */     return 0;
/*     */   }
/*     */   
/*     */   public float a(Entity entity) {
/* 399 */     return this.durability / 5.0F;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   @Nullable
/*     */   public MovingObjectPosition a(IBlockData iblockdata, World world, BlockPosition blockposition, Vec3D vec3d, Vec3D vec3d1) {
/* 405 */     return a(blockposition, vec3d, vec3d1, iblockdata.e(world, blockposition));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected MovingObjectPosition a(BlockPosition blockposition, Vec3D vec3d, Vec3D vec3d1, AxisAlignedBB axisalignedbb) {
/* 410 */     Vec3D vec3d2 = vec3d.a(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 411 */     Vec3D vec3d3 = vec3d1.a(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 412 */     MovingObjectPosition movingobjectposition = axisalignedbb.b(vec3d2, vec3d3);
/*     */     
/* 414 */     return (movingobjectposition == null) ? null : new MovingObjectPosition(movingobjectposition.pos.add(blockposition.getX(), blockposition.getY(), blockposition.getZ()), movingobjectposition.direction, blockposition);
/*     */   }
/*     */   
/*     */   public void wasExploded(World world, BlockPosition blockposition, Explosion explosion) {}
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/* 420 */     return canPlace(world, blockposition);
/*     */   }
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition) {
/* 424 */     return (world.getType(blockposition).getBlock()).material.isReplaceable();
/*     */   }
/*     */   
/*     */   public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/* 428 */     return false;
/*     */   }
/*     */   
/*     */   public void stepOn(World world, BlockPosition blockposition, Entity entity) {}
/*     */   
/*     */   public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
/* 434 */     return fromLegacyData(i);
/*     */   }
/*     */   
/*     */   public void attack(World world, BlockPosition blockposition, EntityHuman entityhuman) {}
/*     */   
/*     */   public Vec3D a(World world, BlockPosition blockposition, Entity entity, Vec3D vec3d) {
/* 440 */     return vec3d;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 445 */     return 0;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/* 450 */     return false;
/*     */   }
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Entity entity) {}
/*     */   
/*     */   @Deprecated
/*     */   public int c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 457 */     return 0;
/*     */   }
/*     */   
/*     */   public void a(World world, EntityHuman entityhuman, BlockPosition blockposition, IBlockData iblockdata, @Nullable TileEntity tileentity, ItemStack itemstack) {
/* 461 */     entityhuman.b(StatisticList.a(this));
/* 462 */     entityhuman.applyExhaustion(0.005F);
/* 463 */     if (n() && EnchantmentManager.getEnchantmentLevel(Enchantments.SILK_TOUCH, itemstack) > 0) {
/* 464 */       ItemStack itemstack1 = u(iblockdata);
/*     */       
/* 466 */       a(world, blockposition, itemstack1);
/*     */     } else {
/* 468 */       int i = EnchantmentManager.getEnchantmentLevel(Enchantments.LOOT_BONUS_BLOCKS, itemstack);
/*     */       
/* 470 */       b(world, blockposition, iblockdata, i);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean n() {
/* 476 */     return (getBlockData().g() && !this.isTileEntity);
/*     */   }
/*     */   
/*     */   protected ItemStack u(IBlockData iblockdata) {
/* 480 */     Item item = Item.getItemOf(this);
/* 481 */     int i = 0;
/*     */     
/* 483 */     if (item.k()) {
/* 484 */       i = toLegacyData(iblockdata);
/*     */     }
/*     */     
/* 487 */     return new ItemStack(item, 1, i);
/*     */   }
/*     */   
/*     */   public int getDropCount(int i, Random random) {
/* 491 */     return a(random);
/*     */   }
/*     */   
/*     */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {}
/*     */   
/*     */   public boolean d() {
/* 497 */     return (!this.material.isBuildable() && !this.material.isLiquid());
/*     */   }
/*     */   
/*     */   public Block c(String s) {
/* 501 */     this.name = s;
/* 502 */     return this;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 506 */     return LocaleI18n.get(String.valueOf(a()) + ".name");
/*     */   }
/*     */   
/*     */   public String a() {
/* 510 */     return "tile." + this.name;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean a(IBlockData iblockdata, World world, BlockPosition blockposition, int i, int j) {
/* 515 */     return false;
/*     */   }
/*     */   
/*     */   public boolean o() {
/* 519 */     return this.s;
/*     */   }
/*     */   
/*     */   protected Block p() {
/* 523 */     this.s = false;
/* 524 */     return this;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public EnumPistonReaction h(IBlockData iblockdata) {
/* 529 */     return this.material.getPushReaction();
/*     */   }
/*     */   
/*     */   public void fallOn(World world, BlockPosition blockposition, Entity entity, float f) {
/* 533 */     entity.e(f, 1.0F);
/*     */   }
/*     */   
/*     */   public void a(World world, Entity entity) {
/* 537 */     entity.motY = 0.0D;
/*     */   }
/*     */   
/*     */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 541 */     return new ItemStack(Item.getItemOf(this), 1, getDropData(iblockdata));
/*     */   }
/*     */   
/*     */   public Block a(CreativeModeTab creativemodetab) {
/* 545 */     this.creativeTab = creativemodetab;
/* 546 */     return this;
/*     */   }
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {}
/*     */   
/*     */   public void h(World world, BlockPosition blockposition) {}
/*     */   
/*     */   public boolean r() {
/* 554 */     return true;
/*     */   }
/*     */   
/*     */   public boolean a(Explosion explosion) {
/* 558 */     return true;
/*     */   }
/*     */   
/*     */   public boolean d(Block block) {
/* 562 */     return (this == block);
/*     */   }
/*     */   
/*     */   public static boolean a(Block block, Block block1) {
/* 566 */     return (block != null && block1 != null) ? ((block == block1) ? true : block.d(block1)) : false;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean isComplexRedstone(IBlockData iblockdata) {
/* 571 */     return false;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public int c(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 576 */     return 0;
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 580 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[0]);
/*     */   }
/*     */   
/*     */   public BlockStateList s() {
/* 584 */     return this.blockStateList;
/*     */   }
/*     */   
/*     */   protected final void w(IBlockData iblockdata) {
/* 588 */     this.blockData = iblockdata;
/*     */   }
/*     */   
/*     */   public final IBlockData getBlockData() {
/* 592 */     return this.blockData;
/*     */   }
/*     */   
/*     */   public EnumRandomOffset u() {
/* 596 */     return EnumRandomOffset.NONE;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public Vec3D f(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 601 */     EnumRandomOffset block_enumrandomoffset = u();
/*     */     
/* 603 */     if (block_enumrandomoffset == EnumRandomOffset.NONE) {
/* 604 */       return Vec3D.a;
/*     */     }
/* 606 */     long i = MathHelper.c(blockposition.getX(), 0, blockposition.getZ());
/*     */     
/* 608 */     return new Vec3D((((float)(i >> 16L & 0xFL) / 15.0F) - 0.5D) * 0.5D, (block_enumrandomoffset == EnumRandomOffset.XYZ) ? ((((float)(i >> 20L & 0xFL) / 15.0F) - 1.0D) * 0.2D) : 0.0D, (((float)(i >> 24L & 0xFL) / 15.0F) - 0.5D) * 0.5D);
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundEffectType getStepSound() {
/* 613 */     return this.stepSound;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 617 */     return "Block{" + REGISTRY.b(this) + "}";
/*     */   }
/*     */   
/*     */   public static void w() {
/* 621 */     a(0, a, (new BlockAir()).c("air"));
/* 622 */     a(1, "stone", (new BlockStone()).c(1.5F).b(10.0F).a(SoundEffectType.d).c("stone"));
/* 623 */     a(2, "grass", (new BlockGrass()).c(0.6F).a(SoundEffectType.c).c("grass"));
/* 624 */     a(3, "dirt", (new BlockDirt()).c(0.5F).a(SoundEffectType.b).c("dirt"));
/* 625 */     Block block = (new Block(Material.STONE)).c(2.0F).b(10.0F).a(SoundEffectType.d).c("stonebrick").a(CreativeModeTab.b);
/*     */     
/* 627 */     a(4, "cobblestone", block);
/* 628 */     Block block1 = (new BlockWood()).c(2.0F).b(5.0F).a(SoundEffectType.a).c("wood");
/*     */     
/* 630 */     a(5, "planks", block1);
/* 631 */     a(6, "sapling", (new BlockSapling()).c(0.0F).a(SoundEffectType.c).c("sapling"));
/* 632 */     a(7, "bedrock", (new BlockNoDrop(Material.STONE)).j().b(6000000.0F).a(SoundEffectType.d).c("bedrock").p().a(CreativeModeTab.b));
/* 633 */     a(8, "flowing_water", (new BlockFlowing(Material.WATER)).c(100.0F).e(3).c("water").p());
/* 634 */     a(9, "water", (new BlockStationary(Material.WATER)).c(100.0F).e(3).c("water").p());
/* 635 */     a(10, "flowing_lava", (new BlockFlowing(Material.LAVA)).c(100.0F).a(1.0F).c("lava").p());
/* 636 */     a(11, "lava", (new BlockStationary(Material.LAVA)).c(100.0F).a(1.0F).c("lava").p());
/* 637 */     a(12, "sand", (new BlockSand()).c(0.5F).a(SoundEffectType.h).c("sand"));
/* 638 */     a(13, "gravel", (new BlockGravel()).c(0.6F).a(SoundEffectType.b).c("gravel"));
/* 639 */     a(14, "gold_ore", (new BlockOre()).c(3.0F).b(5.0F).a(SoundEffectType.d).c("oreGold"));
/* 640 */     a(15, "iron_ore", (new BlockOre()).c(3.0F).b(5.0F).a(SoundEffectType.d).c("oreIron"));
/* 641 */     a(16, "coal_ore", (new BlockOre()).c(3.0F).b(5.0F).a(SoundEffectType.d).c("oreCoal"));
/* 642 */     a(17, "log", (new BlockLog1()).c("log"));
/* 643 */     a(18, "leaves", (new BlockLeaves1()).c("leaves"));
/* 644 */     a(19, "sponge", (new BlockSponge()).c(0.6F).a(SoundEffectType.c).c("sponge"));
/* 645 */     a(20, "glass", (new BlockGlass(Material.SHATTERABLE, false)).c(0.3F).a(SoundEffectType.f).c("glass"));
/* 646 */     a(21, "lapis_ore", (new BlockOre()).c(3.0F).b(5.0F).a(SoundEffectType.d).c("oreLapis"));
/* 647 */     a(22, "lapis_block", (new Block(Material.ORE, MaterialMapColor.I)).c(3.0F).b(5.0F).a(SoundEffectType.d).c("blockLapis").a(CreativeModeTab.b));
/* 648 */     a(23, "dispenser", (new BlockDispenser()).c(3.5F).a(SoundEffectType.d).c("dispenser"));
/* 649 */     Block block2 = (new BlockSandStone()).a(SoundEffectType.d).c(0.8F).c("sandStone");
/*     */     
/* 651 */     a(24, "sandstone", block2);
/* 652 */     a(25, "noteblock", (new BlockNote()).a(SoundEffectType.a).c(0.8F).c("musicBlock"));
/* 653 */     a(26, "bed", (new BlockBed()).a(SoundEffectType.a).c(0.2F).c("bed").p());
/* 654 */     a(27, "golden_rail", (new BlockPoweredRail()).c(0.7F).a(SoundEffectType.e).c("goldenRail"));
/* 655 */     a(28, "detector_rail", (new BlockMinecartDetector()).c(0.7F).a(SoundEffectType.e).c("detectorRail"));
/* 656 */     a(29, "sticky_piston", (new BlockPiston(true)).c("pistonStickyBase"));
/* 657 */     a(30, "web", (new BlockWeb()).e(1).c(4.0F).c("web"));
/* 658 */     a(31, "tallgrass", (new BlockLongGrass()).c(0.0F).a(SoundEffectType.c).c("tallgrass"));
/* 659 */     a(32, "deadbush", (new BlockDeadBush()).c(0.0F).a(SoundEffectType.c).c("deadbush"));
/* 660 */     a(33, "piston", (new BlockPiston(false)).c("pistonBase"));
/* 661 */     a(34, "piston_head", (new BlockPistonExtension()).c("pistonBase"));
/* 662 */     a(35, "wool", (new BlockCloth(Material.CLOTH)).c(0.8F).a(SoundEffectType.g).c("cloth"));
/* 663 */     a(36, "piston_extension", new BlockPistonMoving());
/* 664 */     a(37, "yellow_flower", (new BlockYellowFlowers()).c(0.0F).a(SoundEffectType.c).c("flower1"));
/* 665 */     a(38, "red_flower", (new BlockRedFlowers()).c(0.0F).a(SoundEffectType.c).c("flower2"));
/* 666 */     Block block3 = (new BlockMushroom()).c(0.0F).a(SoundEffectType.c).a(0.125F).c("mushroom");
/*     */     
/* 668 */     a(39, "brown_mushroom", block3);
/* 669 */     Block block4 = (new BlockMushroom()).c(0.0F).a(SoundEffectType.c).c("mushroom");
/*     */     
/* 671 */     a(40, "red_mushroom", block4);
/* 672 */     a(41, "gold_block", (new Block(Material.ORE, MaterialMapColor.G)).c(3.0F).b(10.0F).a(SoundEffectType.e).c("blockGold").a(CreativeModeTab.b));
/* 673 */     a(42, "iron_block", (new Block(Material.ORE, MaterialMapColor.i)).c(5.0F).b(10.0F).a(SoundEffectType.e).c("blockIron").a(CreativeModeTab.b));
/* 674 */     a(43, "double_stone_slab", (new BlockDoubleStep()).c(2.0F).b(10.0F).a(SoundEffectType.d).c("stoneSlab"));
/* 675 */     a(44, "stone_slab", (new BlockStep()).c(2.0F).b(10.0F).a(SoundEffectType.d).c("stoneSlab"));
/* 676 */     Block block5 = (new Block(Material.STONE, MaterialMapColor.E)).c(2.0F).b(10.0F).a(SoundEffectType.d).c("brick").a(CreativeModeTab.b);
/*     */     
/* 678 */     a(45, "brick_block", block5);
/* 679 */     a(46, "tnt", (new BlockTNT()).c(0.0F).a(SoundEffectType.c).c("tnt"));
/* 680 */     a(47, "bookshelf", (new BlockBookshelf()).c(1.5F).a(SoundEffectType.a).c("bookshelf"));
/* 681 */     a(48, "mossy_cobblestone", (new Block(Material.STONE)).c(2.0F).b(10.0F).a(SoundEffectType.d).c("stoneMoss").a(CreativeModeTab.b));
/* 682 */     a(49, "obsidian", (new BlockObsidian()).c(50.0F).b(2000.0F).a(SoundEffectType.d).c("obsidian"));
/* 683 */     a(50, "torch", (new BlockTorch()).c(0.0F).a(0.9375F).a(SoundEffectType.a).c("torch"));
/* 684 */     a(51, "fire", (new BlockFire()).c(0.0F).a(1.0F).a(SoundEffectType.g).c("fire").p());
/* 685 */     a(52, "mob_spawner", (new BlockMobSpawner()).c(5.0F).a(SoundEffectType.e).c("mobSpawner").p());
/* 686 */     a(53, "oak_stairs", (new BlockStairs(block1.getBlockData().set(BlockWood.VARIANT, BlockWood.EnumLogVariant.OAK))).c("stairsWood"));
/* 687 */     a(54, "chest", (new BlockChest(BlockChest.Type.BASIC)).c(2.5F).a(SoundEffectType.a).c("chest"));
/* 688 */     a(55, "redstone_wire", (new BlockRedstoneWire()).c(0.0F).a(SoundEffectType.d).c("redstoneDust").p());
/* 689 */     a(56, "diamond_ore", (new BlockOre()).c(3.0F).b(5.0F).a(SoundEffectType.d).c("oreDiamond"));
/* 690 */     a(57, "diamond_block", (new Block(Material.ORE, MaterialMapColor.H)).c(5.0F).b(10.0F).a(SoundEffectType.e).c("blockDiamond").a(CreativeModeTab.b));
/* 691 */     a(58, "crafting_table", (new BlockWorkbench()).c(2.5F).a(SoundEffectType.a).c("workbench"));
/* 692 */     a(59, "wheat", (new BlockCrops()).c("crops"));
/* 693 */     Block block6 = (new BlockSoil()).c(0.6F).a(SoundEffectType.b).c("farmland");
/*     */     
/* 695 */     a(60, "farmland", block6);
/* 696 */     a(61, "furnace", (new BlockFurnace(false)).c(3.5F).a(SoundEffectType.d).c("furnace").a(CreativeModeTab.c));
/* 697 */     a(62, "lit_furnace", (new BlockFurnace(true)).c(3.5F).a(SoundEffectType.d).a(0.875F).c("furnace"));
/* 698 */     a(63, "standing_sign", (new BlockFloorSign()).c(1.0F).a(SoundEffectType.a).c("sign").p());
/* 699 */     a(64, "wooden_door", (new BlockDoor(Material.WOOD)).c(3.0F).a(SoundEffectType.a).c("doorOak").p());
/* 700 */     a(65, "ladder", (new BlockLadder()).c(0.4F).a(SoundEffectType.j).c("ladder"));
/* 701 */     a(66, "rail", (new BlockMinecartTrack()).c(0.7F).a(SoundEffectType.e).c("rail"));
/* 702 */     a(67, "stone_stairs", (new BlockStairs(block.getBlockData())).c("stairsStone"));
/* 703 */     a(68, "wall_sign", (new BlockWallSign()).c(1.0F).a(SoundEffectType.a).c("sign").p());
/* 704 */     a(69, "lever", (new BlockLever()).c(0.5F).a(SoundEffectType.a).c("lever"));
/* 705 */     a(70, "stone_pressure_plate", (new BlockPressurePlateBinary(Material.STONE, BlockPressurePlateBinary.EnumMobType.MOBS)).c(0.5F).a(SoundEffectType.d).c("pressurePlateStone"));
/* 706 */     a(71, "iron_door", (new BlockDoor(Material.ORE)).c(5.0F).a(SoundEffectType.e).c("doorIron").p());
/* 707 */     a(72, "wooden_pressure_plate", (new BlockPressurePlateBinary(Material.WOOD, BlockPressurePlateBinary.EnumMobType.EVERYTHING)).c(0.5F).a(SoundEffectType.a).c("pressurePlateWood"));
/* 708 */     a(73, "redstone_ore", (new BlockRedstoneOre(false)).c(3.0F).b(5.0F).a(SoundEffectType.d).c("oreRedstone").a(CreativeModeTab.b));
/* 709 */     a(74, "lit_redstone_ore", (new BlockRedstoneOre(true)).a(0.625F).c(3.0F).b(5.0F).a(SoundEffectType.d).c("oreRedstone"));
/* 710 */     a(75, "unlit_redstone_torch", (new BlockRedstoneTorch(false)).c(0.0F).a(SoundEffectType.a).c("notGate"));
/* 711 */     a(76, "redstone_torch", (new BlockRedstoneTorch(true)).c(0.0F).a(0.5F).a(SoundEffectType.a).c("notGate").a(CreativeModeTab.d));
/* 712 */     a(77, "stone_button", (new BlockStoneButton()).c(0.5F).a(SoundEffectType.d).c("button"));
/* 713 */     a(78, "snow_layer", (new BlockSnow()).c(0.1F).a(SoundEffectType.i).c("snow").e(0));
/* 714 */     a(79, "ice", (new BlockIce()).c(0.5F).e(3).a(SoundEffectType.f).c("ice"));
/* 715 */     a(80, "snow", (new BlockSnowBlock()).c(0.2F).a(SoundEffectType.i).c("snow"));
/* 716 */     a(81, "cactus", (new BlockCactus()).c(0.4F).a(SoundEffectType.g).c("cactus"));
/* 717 */     a(82, "clay", (new BlockClay()).c(0.6F).a(SoundEffectType.b).c("clay"));
/* 718 */     a(83, "reeds", (new BlockReed()).c(0.0F).a(SoundEffectType.c).c("reeds").p());
/* 719 */     a(84, "jukebox", (new BlockJukeBox()).c(2.0F).b(10.0F).a(SoundEffectType.d).c("jukebox"));
/* 720 */     a(85, "fence", (new BlockFence(Material.WOOD, BlockWood.EnumLogVariant.OAK.c())).c(2.0F).b(5.0F).a(SoundEffectType.a).c("fence"));
/* 721 */     Block block7 = (new BlockPumpkin()).c(1.0F).a(SoundEffectType.a).c("pumpkin");
/*     */     
/* 723 */     a(86, "pumpkin", block7);
/* 724 */     a(87, "netherrack", (new BlockBloodStone()).c(0.4F).a(SoundEffectType.d).c("hellrock"));
/* 725 */     a(88, "soul_sand", (new BlockSlowSand()).c(0.5F).a(SoundEffectType.h).c("hellsand"));
/* 726 */     a(89, "glowstone", (new BlockLightStone(Material.SHATTERABLE)).c(0.3F).a(SoundEffectType.f).a(1.0F).c("lightgem"));
/* 727 */     a(90, "portal", (new BlockPortal()).c(-1.0F).a(SoundEffectType.f).a(0.75F).c("portal"));
/* 728 */     a(91, "lit_pumpkin", (new BlockPumpkin()).c(1.0F).a(SoundEffectType.a).a(1.0F).c("litpumpkin"));
/* 729 */     a(92, "cake", (new BlockCake()).c(0.5F).a(SoundEffectType.g).c("cake").p());
/* 730 */     a(93, "unpowered_repeater", (new BlockRepeater(false)).c(0.0F).a(SoundEffectType.a).c("diode").p());
/* 731 */     a(94, "powered_repeater", (new BlockRepeater(true)).c(0.0F).a(SoundEffectType.a).c("diode").p());
/* 732 */     a(95, "stained_glass", (new BlockStainedGlass(Material.SHATTERABLE)).c(0.3F).a(SoundEffectType.f).c("stainedGlass"));
/* 733 */     a(96, "trapdoor", (new BlockTrapdoor(Material.WOOD)).c(3.0F).a(SoundEffectType.a).c("trapdoor").p());
/* 734 */     a(97, "monster_egg", (new BlockMonsterEggs()).c(0.75F).c("monsterStoneEgg"));
/* 735 */     Block block8 = (new BlockSmoothBrick()).c(1.5F).b(10.0F).a(SoundEffectType.d).c("stonebricksmooth");
/*     */     
/* 737 */     a(98, "stonebrick", block8);
/* 738 */     a(99, "brown_mushroom_block", (new BlockHugeMushroom(Material.WOOD, MaterialMapColor.m, block3)).c(0.2F).a(SoundEffectType.a).c("mushroom"));
/* 739 */     a(100, "red_mushroom_block", (new BlockHugeMushroom(Material.WOOD, MaterialMapColor.E, block4)).c(0.2F).a(SoundEffectType.a).c("mushroom"));
/* 740 */     a(101, "iron_bars", (new BlockThin(Material.ORE, true)).c(5.0F).b(10.0F).a(SoundEffectType.e).c("fenceIron"));
/* 741 */     a(102, "glass_pane", (new BlockThin(Material.SHATTERABLE, false)).c(0.3F).a(SoundEffectType.f).c("thinGlass"));
/* 742 */     Block block9 = (new BlockMelon()).c(1.0F).a(SoundEffectType.a).c("melon");
/*     */     
/* 744 */     a(103, "melon_block", block9);
/* 745 */     a(104, "pumpkin_stem", (new BlockStem(block7)).c(0.0F).a(SoundEffectType.a).c("pumpkinStem"));
/* 746 */     a(105, "melon_stem", (new BlockStem(block9)).c(0.0F).a(SoundEffectType.a).c("pumpkinStem"));
/* 747 */     a(106, "vine", (new BlockVine()).c(0.2F).a(SoundEffectType.c).c("vine"));
/* 748 */     a(107, "fence_gate", (new BlockFenceGate(BlockWood.EnumLogVariant.OAK)).c(2.0F).b(5.0F).a(SoundEffectType.a).c("fenceGate"));
/* 749 */     a(108, "brick_stairs", (new BlockStairs(block5.getBlockData())).c("stairsBrick"));
/* 750 */     a(109, "stone_brick_stairs", (new BlockStairs(block8.getBlockData().set(BlockSmoothBrick.VARIANT, BlockSmoothBrick.EnumStonebrickType.DEFAULT))).c("stairsStoneBrickSmooth"));
/* 751 */     a(110, "mycelium", (new BlockMycel()).c(0.6F).a(SoundEffectType.c).c("mycel"));
/* 752 */     a(111, "waterlily", (new BlockWaterLily()).c(0.0F).a(SoundEffectType.c).c("waterlily"));
/* 753 */     Block block10 = (new BlockNetherbrick()).c(2.0F).b(10.0F).a(SoundEffectType.d).c("netherBrick").a(CreativeModeTab.b);
/*     */     
/* 755 */     a(112, "nether_brick", block10);
/* 756 */     a(113, "nether_brick_fence", (new BlockFence(Material.STONE, MaterialMapColor.L)).c(2.0F).b(10.0F).a(SoundEffectType.d).c("netherFence"));
/* 757 */     a(114, "nether_brick_stairs", (new BlockStairs(block10.getBlockData())).c("stairsNetherBrick"));
/* 758 */     a(115, "nether_wart", (new BlockNetherWart()).c("netherStalk"));
/* 759 */     a(116, "enchanting_table", (new BlockEnchantmentTable()).c(5.0F).b(2000.0F).c("enchantmentTable"));
/* 760 */     a(117, "brewing_stand", (new BlockBrewingStand()).c(0.5F).a(0.125F).c("brewingStand"));
/* 761 */     a(118, "cauldron", (new BlockCauldron()).c(2.0F).c("cauldron"));
/* 762 */     a(119, "end_portal", (new BlockEnderPortal(Material.PORTAL)).c(-1.0F).b(6000000.0F));
/* 763 */     a(120, "end_portal_frame", (new BlockEnderPortalFrame()).a(SoundEffectType.f).a(0.125F).c(-1.0F).c("endPortalFrame").b(6000000.0F).a(CreativeModeTab.c));
/* 764 */     a(121, "end_stone", (new Block(Material.STONE, MaterialMapColor.e)).c(3.0F).b(15.0F).a(SoundEffectType.d).c("whiteStone").a(CreativeModeTab.b));
/* 765 */     a(122, "dragon_egg", (new BlockDragonEgg()).c(3.0F).b(15.0F).a(SoundEffectType.d).a(0.125F).c("dragonEgg"));
/* 766 */     a(123, "redstone_lamp", (new BlockRedstoneLamp(false)).c(0.3F).a(SoundEffectType.f).c("redstoneLight").a(CreativeModeTab.d));
/* 767 */     a(124, "lit_redstone_lamp", (new BlockRedstoneLamp(true)).c(0.3F).a(SoundEffectType.f).c("redstoneLight"));
/* 768 */     a(125, "double_wooden_slab", (new BlockDoubleWoodStep()).c(2.0F).b(5.0F).a(SoundEffectType.a).c("woodSlab"));
/* 769 */     a(126, "wooden_slab", (new BlockWoodStep()).c(2.0F).b(5.0F).a(SoundEffectType.a).c("woodSlab"));
/* 770 */     a(127, "cocoa", (new BlockCocoa()).c(0.2F).b(5.0F).a(SoundEffectType.a).c("cocoa"));
/* 771 */     a(128, "sandstone_stairs", (new BlockStairs(block2.getBlockData().set(BlockSandStone.TYPE, BlockSandStone.EnumSandstoneVariant.SMOOTH))).c("stairsSandStone"));
/* 772 */     a(129, "emerald_ore", (new BlockOre()).c(3.0F).b(5.0F).a(SoundEffectType.d).c("oreEmerald"));
/* 773 */     a(130, "ender_chest", (new BlockEnderChest()).c(22.5F).b(1000.0F).a(SoundEffectType.d).c("enderChest").a(0.5F));
/* 774 */     a(131, "tripwire_hook", (new BlockTripwireHook()).c("tripWireSource"));
/* 775 */     a(132, "tripwire", (new BlockTripwire()).c("tripWire"));
/* 776 */     a(133, "emerald_block", (new Block(Material.ORE, MaterialMapColor.J)).c(5.0F).b(10.0F).a(SoundEffectType.e).c("blockEmerald").a(CreativeModeTab.b));
/* 777 */     a(134, "spruce_stairs", (new BlockStairs(block1.getBlockData().set(BlockWood.VARIANT, BlockWood.EnumLogVariant.SPRUCE))).c("stairsWoodSpruce"));
/* 778 */     a(135, "birch_stairs", (new BlockStairs(block1.getBlockData().set(BlockWood.VARIANT, BlockWood.EnumLogVariant.BIRCH))).c("stairsWoodBirch"));
/* 779 */     a(136, "jungle_stairs", (new BlockStairs(block1.getBlockData().set(BlockWood.VARIANT, BlockWood.EnumLogVariant.JUNGLE))).c("stairsWoodJungle"));
/* 780 */     a(137, "command_block", (new BlockCommand(MaterialMapColor.C)).j().b(6000000.0F).c("commandBlock"));
/* 781 */     a(138, "beacon", (new BlockBeacon()).c("beacon").a(1.0F));
/* 782 */     a(139, "cobblestone_wall", (new BlockCobbleWall(block)).c("cobbleWall"));
/* 783 */     a(140, "flower_pot", (new BlockFlowerPot()).c(0.0F).a(SoundEffectType.d).c("flowerPot"));
/* 784 */     a(141, "carrots", (new BlockCarrots()).c("carrots"));
/* 785 */     a(142, "potatoes", (new BlockPotatoes()).c("potatoes"));
/* 786 */     a(143, "wooden_button", (new BlockWoodButton()).c(0.5F).a(SoundEffectType.a).c("button"));
/* 787 */     a(144, "skull", (new BlockSkull()).c(1.0F).a(SoundEffectType.d).c("skull"));
/* 788 */     a(145, "anvil", (new BlockAnvil()).c(5.0F).a(SoundEffectType.k).b(2000.0F).c("anvil"));
/* 789 */     a(146, "trapped_chest", (new BlockChest(BlockChest.Type.TRAP)).c(2.5F).a(SoundEffectType.a).c("chestTrap"));
/* 790 */     a(147, "light_weighted_pressure_plate", (new BlockPressurePlateWeighted(Material.ORE, 15, MaterialMapColor.G)).c(0.5F).a(SoundEffectType.a).c("weightedPlate_light"));
/* 791 */     a(148, "heavy_weighted_pressure_plate", (new BlockPressurePlateWeighted(Material.ORE, 150)).c(0.5F).a(SoundEffectType.a).c("weightedPlate_heavy"));
/* 792 */     a(149, "unpowered_comparator", (new BlockRedstoneComparator(false)).c(0.0F).a(SoundEffectType.a).c("comparator").p());
/* 793 */     a(150, "powered_comparator", (new BlockRedstoneComparator(true)).c(0.0F).a(0.625F).a(SoundEffectType.a).c("comparator").p());
/* 794 */     a(151, "daylight_detector", new BlockDaylightDetector(false));
/* 795 */     a(152, "redstone_block", (new BlockPowered(Material.ORE, MaterialMapColor.g)).c(5.0F).b(10.0F).a(SoundEffectType.e).c("blockRedstone").a(CreativeModeTab.d));
/* 796 */     a(153, "quartz_ore", (new BlockOre(MaterialMapColor.L)).c(3.0F).b(5.0F).a(SoundEffectType.d).c("netherquartz"));
/* 797 */     a(154, "hopper", (new BlockHopper()).c(3.0F).b(8.0F).a(SoundEffectType.e).c("hopper"));
/* 798 */     Block block11 = (new BlockQuartz()).a(SoundEffectType.d).c(0.8F).c("quartzBlock");
/*     */     
/* 800 */     a(155, "quartz_block", block11);
/* 801 */     a(156, "quartz_stairs", (new BlockStairs(block11.getBlockData().set(BlockQuartz.VARIANT, BlockQuartz.EnumQuartzVariant.DEFAULT))).c("stairsQuartz"));
/* 802 */     a(157, "activator_rail", (new BlockPoweredRail()).c(0.7F).a(SoundEffectType.e).c("activatorRail"));
/* 803 */     a(158, "dropper", (new BlockDropper()).c(3.5F).a(SoundEffectType.d).c("dropper"));
/* 804 */     a(159, "stained_hardened_clay", (new BlockStainedHardenedClay()).c(1.25F).b(7.0F).a(SoundEffectType.d).c("clayHardenedStained"));
/* 805 */     a(160, "stained_glass_pane", (new BlockStainedGlassPane()).c(0.3F).a(SoundEffectType.f).c("thinStainedGlass"));
/* 806 */     a(161, "leaves2", (new BlockLeaves2()).c("leaves"));
/* 807 */     a(162, "log2", (new BlockLog2()).c("log"));
/* 808 */     a(163, "acacia_stairs", (new BlockStairs(block1.getBlockData().set(BlockWood.VARIANT, BlockWood.EnumLogVariant.ACACIA))).c("stairsWoodAcacia"));
/* 809 */     a(164, "dark_oak_stairs", (new BlockStairs(block1.getBlockData().set(BlockWood.VARIANT, BlockWood.EnumLogVariant.DARK_OAK))).c("stairsWoodDarkOak"));
/* 810 */     a(165, "slime", (new BlockSlime()).c("slime").a(SoundEffectType.l));
/* 811 */     a(166, "barrier", (new BlockBarrier()).c("barrier"));
/* 812 */     a(167, "iron_trapdoor", (new BlockTrapdoor(Material.ORE)).c(5.0F).a(SoundEffectType.e).c("ironTrapdoor").p());
/* 813 */     a(168, "prismarine", (new BlockPrismarine()).c(1.5F).b(10.0F).a(SoundEffectType.d).c("prismarine"));
/* 814 */     a(169, "sea_lantern", (new BlockSeaLantern(Material.SHATTERABLE)).c(0.3F).a(SoundEffectType.f).a(1.0F).c("seaLantern"));
/* 815 */     a(170, "hay_block", (new BlockHay()).c(0.5F).a(SoundEffectType.c).c("hayBlock").a(CreativeModeTab.b));
/* 816 */     a(171, "carpet", (new BlockCarpet()).c(0.1F).a(SoundEffectType.g).c("woolCarpet").e(0));
/* 817 */     a(172, "hardened_clay", (new BlockHardenedClay()).c(1.25F).b(7.0F).a(SoundEffectType.d).c("clayHardened"));
/* 818 */     a(173, "coal_block", (new Block(Material.STONE, MaterialMapColor.F)).c(5.0F).b(10.0F).a(SoundEffectType.d).c("blockCoal").a(CreativeModeTab.b));
/* 819 */     a(174, "packed_ice", (new BlockPackedIce()).c(0.5F).a(SoundEffectType.f).c("icePacked"));
/* 820 */     a(175, "double_plant", new BlockTallPlant());
/* 821 */     a(176, "standing_banner", (new BlockBanner.BlockStandingBanner()).c(1.0F).a(SoundEffectType.a).c("banner").p());
/* 822 */     a(177, "wall_banner", (new BlockBanner.BlockWallBanner()).c(1.0F).a(SoundEffectType.a).c("banner").p());
/* 823 */     a(178, "daylight_detector_inverted", new BlockDaylightDetector(true));
/* 824 */     Block block12 = (new BlockRedSandstone()).a(SoundEffectType.d).c(0.8F).c("redSandStone");
/*     */     
/* 826 */     a(179, "red_sandstone", block12);
/* 827 */     a(180, "red_sandstone_stairs", (new BlockStairs(block12.getBlockData().set(BlockRedSandstone.TYPE, BlockRedSandstone.EnumRedSandstoneVariant.SMOOTH))).c("stairsRedSandStone"));
/* 828 */     a(181, "double_stone_slab2", (new BlockDoubleStoneStep2()).c(2.0F).b(10.0F).a(SoundEffectType.d).c("stoneSlab2"));
/* 829 */     a(182, "stone_slab2", (new BlockStoneStep2()).c(2.0F).b(10.0F).a(SoundEffectType.d).c("stoneSlab2"));
/* 830 */     a(183, "spruce_fence_gate", (new BlockFenceGate(BlockWood.EnumLogVariant.SPRUCE)).c(2.0F).b(5.0F).a(SoundEffectType.a).c("spruceFenceGate"));
/* 831 */     a(184, "birch_fence_gate", (new BlockFenceGate(BlockWood.EnumLogVariant.BIRCH)).c(2.0F).b(5.0F).a(SoundEffectType.a).c("birchFenceGate"));
/* 832 */     a(185, "jungle_fence_gate", (new BlockFenceGate(BlockWood.EnumLogVariant.JUNGLE)).c(2.0F).b(5.0F).a(SoundEffectType.a).c("jungleFenceGate"));
/* 833 */     a(186, "dark_oak_fence_gate", (new BlockFenceGate(BlockWood.EnumLogVariant.DARK_OAK)).c(2.0F).b(5.0F).a(SoundEffectType.a).c("darkOakFenceGate"));
/* 834 */     a(187, "acacia_fence_gate", (new BlockFenceGate(BlockWood.EnumLogVariant.ACACIA)).c(2.0F).b(5.0F).a(SoundEffectType.a).c("acaciaFenceGate"));
/* 835 */     a(188, "spruce_fence", (new BlockFence(Material.WOOD, BlockWood.EnumLogVariant.SPRUCE.c())).c(2.0F).b(5.0F).a(SoundEffectType.a).c("spruceFence"));
/* 836 */     a(189, "birch_fence", (new BlockFence(Material.WOOD, BlockWood.EnumLogVariant.BIRCH.c())).c(2.0F).b(5.0F).a(SoundEffectType.a).c("birchFence"));
/* 837 */     a(190, "jungle_fence", (new BlockFence(Material.WOOD, BlockWood.EnumLogVariant.JUNGLE.c())).c(2.0F).b(5.0F).a(SoundEffectType.a).c("jungleFence"));
/* 838 */     a(191, "dark_oak_fence", (new BlockFence(Material.WOOD, BlockWood.EnumLogVariant.DARK_OAK.c())).c(2.0F).b(5.0F).a(SoundEffectType.a).c("darkOakFence"));
/* 839 */     a(192, "acacia_fence", (new BlockFence(Material.WOOD, BlockWood.EnumLogVariant.ACACIA.c())).c(2.0F).b(5.0F).a(SoundEffectType.a).c("acaciaFence"));
/* 840 */     a(193, "spruce_door", (new BlockDoor(Material.WOOD)).c(3.0F).a(SoundEffectType.a).c("doorSpruce").p());
/* 841 */     a(194, "birch_door", (new BlockDoor(Material.WOOD)).c(3.0F).a(SoundEffectType.a).c("doorBirch").p());
/* 842 */     a(195, "jungle_door", (new BlockDoor(Material.WOOD)).c(3.0F).a(SoundEffectType.a).c("doorJungle").p());
/* 843 */     a(196, "acacia_door", (new BlockDoor(Material.WOOD)).c(3.0F).a(SoundEffectType.a).c("doorAcacia").p());
/* 844 */     a(197, "dark_oak_door", (new BlockDoor(Material.WOOD)).c(3.0F).a(SoundEffectType.a).c("doorDarkOak").p());
/* 845 */     a(198, "end_rod", (new BlockEndRod()).c(0.0F).a(0.9375F).a(SoundEffectType.a).c("endRod"));
/* 846 */     a(199, "chorus_plant", (new BlockChorusFruit()).c(0.4F).a(SoundEffectType.a).c("chorusPlant"));
/* 847 */     a(200, "chorus_flower", (new BlockChorusFlower()).c(0.4F).a(SoundEffectType.a).c("chorusFlower"));
/* 848 */     Block block13 = (new Block(Material.STONE, MaterialMapColor.s)).c(1.5F).b(10.0F).a(SoundEffectType.d).a(CreativeModeTab.b).c("purpurBlock");
/*     */     
/* 850 */     a(201, "purpur_block", block13);
/* 851 */     a(202, "purpur_pillar", (new BlockRotatable(Material.STONE, MaterialMapColor.s)).c(1.5F).b(10.0F).a(SoundEffectType.d).a(CreativeModeTab.b).c("purpurPillar"));
/* 852 */     a(203, "purpur_stairs", (new BlockStairs(block13.getBlockData())).c("stairsPurpur"));
/* 853 */     a(204, "purpur_double_slab", (new BlockPurpurSlab.Default()).c(2.0F).b(10.0F).a(SoundEffectType.d).c("purpurSlab"));
/* 854 */     a(205, "purpur_slab", (new BlockPurpurSlab.Half()).c(2.0F).b(10.0F).a(SoundEffectType.d).c("purpurSlab"));
/* 855 */     a(206, "end_bricks", (new Block(Material.STONE, MaterialMapColor.e)).a(SoundEffectType.d).c(0.8F).a(CreativeModeTab.b).c("endBricks"));
/* 856 */     a(207, "beetroots", (new BlockBeetroot()).c("beetroots"));
/* 857 */     Block block14 = (new BlockGrassPath()).c(0.65F).a(SoundEffectType.c).c("grassPath").p();
/*     */     
/* 859 */     a(208, "grass_path", block14);
/* 860 */     a(209, "end_gateway", (new BlockEndGateway(Material.PORTAL)).c(-1.0F).b(6000000.0F));
/* 861 */     a(210, "repeating_command_block", (new BlockCommand(MaterialMapColor.A)).j().b(6000000.0F).c("repeatingCommandBlock"));
/* 862 */     a(211, "chain_command_block", (new BlockCommand(MaterialMapColor.D)).j().b(6000000.0F).c("chainCommandBlock"));
/* 863 */     a(212, "frosted_ice", (new BlockIceFrost()).c(0.5F).e(3).a(SoundEffectType.f).c("frostedIce"));
/* 864 */     a(213, "magma", (new BlockMagma()).c(0.5F).a(SoundEffectType.d).c("magma"));
/* 865 */     a(214, "nether_wart_block", (new Block(Material.GRASS, MaterialMapColor.E)).a(CreativeModeTab.b).c(1.0F).a(SoundEffectType.a).c("netherWartBlock"));
/* 866 */     a(215, "red_nether_brick", (new BlockNetherbrick()).c(2.0F).b(10.0F).a(SoundEffectType.d).c("redNetherBrick").a(CreativeModeTab.b));
/* 867 */     a(216, "bone_block", (new BlockBone()).c("boneBlock"));
/* 868 */     a(217, "structure_void", (new BlockStructureVoid()).c("structureVoid"));
/* 869 */     a(218, "observer", (new BlockObserver()).c(3.0F).c("observer"));
/* 870 */     a(219, "white_shulker_box", (new BlockShulkerBox(EnumColor.WHITE)).c(2.0F).a(SoundEffectType.d).c("shulkerBoxWhite"));
/* 871 */     a(220, "orange_shulker_box", (new BlockShulkerBox(EnumColor.ORANGE)).c(2.0F).a(SoundEffectType.d).c("shulkerBoxOrange"));
/* 872 */     a(221, "magenta_shulker_box", (new BlockShulkerBox(EnumColor.MAGENTA)).c(2.0F).a(SoundEffectType.d).c("shulkerBoxMagenta"));
/* 873 */     a(222, "light_blue_shulker_box", (new BlockShulkerBox(EnumColor.LIGHT_BLUE)).c(2.0F).a(SoundEffectType.d).c("shulkerBoxLightBlue"));
/* 874 */     a(223, "yellow_shulker_box", (new BlockShulkerBox(EnumColor.YELLOW)).c(2.0F).a(SoundEffectType.d).c("shulkerBoxYellow"));
/* 875 */     a(224, "lime_shulker_box", (new BlockShulkerBox(EnumColor.LIME)).c(2.0F).a(SoundEffectType.d).c("shulkerBoxLime"));
/* 876 */     a(225, "pink_shulker_box", (new BlockShulkerBox(EnumColor.PINK)).c(2.0F).a(SoundEffectType.d).c("shulkerBoxPink"));
/* 877 */     a(226, "gray_shulker_box", (new BlockShulkerBox(EnumColor.GRAY)).c(2.0F).a(SoundEffectType.d).c("shulkerBoxGray"));
/* 878 */     a(227, "silver_shulker_box", (new BlockShulkerBox(EnumColor.SILVER)).c(2.0F).a(SoundEffectType.d).c("shulkerBoxSilver"));
/* 879 */     a(228, "cyan_shulker_box", (new BlockShulkerBox(EnumColor.CYAN)).c(2.0F).a(SoundEffectType.d).c("shulkerBoxCyan"));
/* 880 */     a(229, "purple_shulker_box", (new BlockShulkerBox(EnumColor.PURPLE)).c(2.0F).a(SoundEffectType.d).c("shulkerBoxPurple"));
/* 881 */     a(230, "blue_shulker_box", (new BlockShulkerBox(EnumColor.BLUE)).c(2.0F).a(SoundEffectType.d).c("shulkerBoxBlue"));
/* 882 */     a(231, "brown_shulker_box", (new BlockShulkerBox(EnumColor.BROWN)).c(2.0F).a(SoundEffectType.d).c("shulkerBoxBrown"));
/* 883 */     a(232, "green_shulker_box", (new BlockShulkerBox(EnumColor.GREEN)).c(2.0F).a(SoundEffectType.d).c("shulkerBoxGreen"));
/* 884 */     a(233, "red_shulker_box", (new BlockShulkerBox(EnumColor.RED)).c(2.0F).a(SoundEffectType.d).c("shulkerBoxRed"));
/* 885 */     a(234, "black_shulker_box", (new BlockShulkerBox(EnumColor.BLACK)).c(2.0F).a(SoundEffectType.d).c("shulkerBoxBlack"));
/* 886 */     a(235, "white_glazed_terracotta", new BlockGlazedTerracotta(EnumColor.WHITE));
/* 887 */     a(236, "orange_glazed_terracotta", new BlockGlazedTerracotta(EnumColor.ORANGE));
/* 888 */     a(237, "magenta_glazed_terracotta", new BlockGlazedTerracotta(EnumColor.MAGENTA));
/* 889 */     a(238, "light_blue_glazed_terracotta", new BlockGlazedTerracotta(EnumColor.LIGHT_BLUE));
/* 890 */     a(239, "yellow_glazed_terracotta", new BlockGlazedTerracotta(EnumColor.YELLOW));
/* 891 */     a(240, "lime_glazed_terracotta", new BlockGlazedTerracotta(EnumColor.LIME));
/* 892 */     a(241, "pink_glazed_terracotta", new BlockGlazedTerracotta(EnumColor.PINK));
/* 893 */     a(242, "gray_glazed_terracotta", new BlockGlazedTerracotta(EnumColor.GRAY));
/* 894 */     a(243, "silver_glazed_terracotta", new BlockGlazedTerracotta(EnumColor.SILVER));
/* 895 */     a(244, "cyan_glazed_terracotta", new BlockGlazedTerracotta(EnumColor.CYAN));
/* 896 */     a(245, "purple_glazed_terracotta", new BlockGlazedTerracotta(EnumColor.PURPLE));
/* 897 */     a(246, "blue_glazed_terracotta", new BlockGlazedTerracotta(EnumColor.BLUE));
/* 898 */     a(247, "brown_glazed_terracotta", new BlockGlazedTerracotta(EnumColor.BROWN));
/* 899 */     a(248, "green_glazed_terracotta", new BlockGlazedTerracotta(EnumColor.GREEN));
/* 900 */     a(249, "red_glazed_terracotta", new BlockGlazedTerracotta(EnumColor.RED));
/* 901 */     a(250, "black_glazed_terracotta", new BlockGlazedTerracotta(EnumColor.BLACK));
/* 902 */     a(251, "concrete", (new BlockCloth(Material.STONE)).c(1.8F).a(SoundEffectType.d).c("concrete"));
/* 903 */     a(252, "concrete_powder", (new BlockConcretePowder()).c(0.5F).a(SoundEffectType.h).c("concretePowder"));
/* 904 */     a(255, "structure_block", (new BlockStructure()).j().b(6000000.0F).c("structureBlock"));
/* 905 */     REGISTRY.a();
/* 906 */     Iterator<Block> iterator = REGISTRY.iterator();
/*     */     
/* 908 */     while (iterator.hasNext()) {
/* 909 */       Block block15 = iterator.next();
/*     */       
/* 911 */       if (block15.material == Material.AIR) {
/* 912 */         block15.p = false; continue;
/*     */       } 
/* 914 */       boolean flag = false;
/* 915 */       boolean flag1 = block15 instanceof BlockStairs;
/* 916 */       boolean flag2 = block15 instanceof BlockStepAbstract;
/* 917 */       boolean flag3 = !(block15 != block6 && block15 != block14);
/* 918 */       boolean flag4 = block15.n;
/* 919 */       boolean flag5 = (block15.m == 0);
/*     */       
/* 921 */       if (flag1 || flag2 || flag3 || flag4 || flag5) {
/* 922 */         flag = true;
/*     */       }
/*     */       
/* 925 */       block15.p = flag;
/*     */     } 
/*     */ 
/*     */     
/* 929 */     HashSet hashset = Sets.newHashSet((Object[])new Block[] { REGISTRY.get(new MinecraftKey("tripwire")) });
/* 930 */     Iterator<Block> iterator1 = REGISTRY.iterator();
/*     */     
/* 932 */     while (iterator1.hasNext()) {
/* 933 */       Block block16 = iterator1.next();
/*     */       
/* 935 */       if (hashset.contains(block16)) {
/* 936 */         for (int i = 0; i < 15; i++) {
/* 937 */           int j = REGISTRY.a(block16) << 4 | i;
/*     */           
/* 939 */           REGISTRY_ID.a(block16.fromLegacyData(i), j);
/*     */         }  continue;
/*     */       } 
/* 942 */       UnmodifiableIterator unmodifiableiterator = block16.s().a().iterator();
/*     */       
/* 944 */       while (unmodifiableiterator.hasNext()) {
/* 945 */         IBlockData iblockdata = (IBlockData)unmodifiableiterator.next();
/* 946 */         int k = REGISTRY.a(block16) << 4 | block16.toLegacyData(iblockdata);
/*     */         
/* 948 */         REGISTRY_ID.a(iblockdata, k);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExpDrop(World world, IBlockData data, int enchantmentLevel) {
/* 957 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void a(int i, MinecraftKey minecraftkey, Block block) {
/* 962 */     REGISTRY.a(i, minecraftkey, block);
/*     */   }
/*     */   
/*     */   private static void a(int i, String s, Block block) {
/* 966 */     a(i, new MinecraftKey(s), block);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float range(float min, float value, float max) {
/* 971 */     if (value < min) {
/* 972 */       return min;
/*     */     }
/* 974 */     if (value > max) {
/* 975 */       return max;
/*     */     }
/* 977 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   public enum EnumRandomOffset
/*     */   {
/* 983 */     NONE, XZ, XYZ;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\Block.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */