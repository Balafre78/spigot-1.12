/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public abstract class BiomeBase
/*     */ {
/*  54 */   private static final Logger x = LogManager.getLogger();
/*  55 */   protected static final IBlockData a = Blocks.STONE.getBlockData();
/*  56 */   protected static final IBlockData b = Blocks.AIR.getBlockData();
/*  57 */   protected static final IBlockData c = Blocks.BEDROCK.getBlockData();
/*  58 */   protected static final IBlockData d = Blocks.GRAVEL.getBlockData();
/*  59 */   protected static final IBlockData e = Blocks.RED_SANDSTONE.getBlockData();
/*  60 */   protected static final IBlockData f = Blocks.SANDSTONE.getBlockData();
/*  61 */   protected static final IBlockData g = Blocks.ICE.getBlockData();
/*  62 */   protected static final IBlockData h = Blocks.WATER.getBlockData();
/*     */   
/*  64 */   public static final RegistryBlockID<BiomeBase> i = new RegistryBlockID<>();
/*     */ 
/*     */ 
/*     */   
/*  68 */   protected static final NoiseGenerator3 j = new NoiseGenerator3(new Random(1234L), 1);
/*  69 */   protected static final NoiseGenerator3 k = new NoiseGenerator3(new Random(2345L), 1);
/*  70 */   protected static final WorldGenTallPlant l = new WorldGenTallPlant();
/*     */   
/*  72 */   protected static final WorldGenTrees m = new WorldGenTrees(false);
/*  73 */   protected static final WorldGenBigTree n = new WorldGenBigTree(false);
/*  74 */   protected static final WorldGenSwampTree o = new WorldGenSwampTree();
/*     */   
/*  76 */   public static final RegistryMaterials<MinecraftKey, BiomeBase> REGISTRY_ID = new RegistryMaterials<>(); private final String y; private final float z; private final float A; private final float B;
/*     */   
/*     */   public static int a(BiomeBase paramBiomeBase) {
/*  79 */     return REGISTRY_ID.a(paramBiomeBase);
/*     */   } private final float C; private final int D; private final boolean E; private final boolean F; @Nullable
/*     */   private final String G;
/*     */   @Nullable
/*     */   public static BiomeBase a(int paramInt) {
/*  84 */     return REGISTRY_ID.getId(paramInt);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static BiomeBase b(BiomeBase paramBiomeBase) {
/*  89 */     return i.fromId(a(paramBiomeBase));
/*     */   }
/*     */   
/*     */   public enum EnumTemperature {
/*  93 */     OCEAN,
/*  94 */     COLD,
/*  95 */     MEDIUM,
/*  96 */     WARM;
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
/* 109 */   public IBlockData q = Blocks.GRASS.getBlockData();
/* 110 */   public IBlockData r = Blocks.DIRT.getBlockData();
/*     */   
/*     */   public BiomeDecorator s;
/*     */   
/* 114 */   protected List<BiomeMeta> t = Lists.newArrayList();
/* 115 */   protected List<BiomeMeta> u = Lists.newArrayList();
/* 116 */   protected List<BiomeMeta> v = Lists.newArrayList();
/* 117 */   protected List<BiomeMeta> w = Lists.newArrayList();
/*     */   
/*     */   protected BiomeBase(a parama) {
/* 120 */     this.y = a.a(parama);
/* 121 */     this.z = a.b(parama);
/* 122 */     this.A = a.c(parama);
/* 123 */     this.B = a.d(parama);
/* 124 */     this.C = a.e(parama);
/* 125 */     this.D = a.f(parama);
/* 126 */     this.E = a.g(parama);
/* 127 */     this.F = a.h(parama);
/* 128 */     this.G = a.i(parama);
/*     */     
/* 130 */     this.s = a();
/*     */     
/* 132 */     this.u.add(new BiomeMeta((Class)EntitySheep.class, 12, 4, 4));
/* 133 */     this.u.add(new BiomeMeta((Class)EntityPig.class, 10, 4, 4));
/* 134 */     this.u.add(new BiomeMeta((Class)EntityChicken.class, 10, 4, 4));
/* 135 */     this.u.add(new BiomeMeta((Class)EntityCow.class, 8, 4, 4));
/*     */     
/* 137 */     this.t.add(new BiomeMeta((Class)EntitySpider.class, 100, 4, 4));
/* 138 */     this.t.add(new BiomeMeta((Class)EntityZombie.class, 95, 4, 4));
/* 139 */     this.t.add(new BiomeMeta((Class)EntityZombieVillager.class, 5, 1, 1));
/* 140 */     this.t.add(new BiomeMeta((Class)EntitySkeleton.class, 100, 4, 4));
/* 141 */     this.t.add(new BiomeMeta((Class)EntityCreeper.class, 100, 4, 4));
/* 142 */     this.t.add(new BiomeMeta((Class)EntitySlime.class, 100, 4, 4));
/* 143 */     this.t.add(new BiomeMeta((Class)EntityEnderman.class, 10, 1, 4));
/* 144 */     this.t.add(new BiomeMeta((Class)EntityWitch.class, 5, 1, 1));
/*     */ 
/*     */ 
/*     */     
/* 148 */     this.v.add(new BiomeMeta((Class)EntitySquid.class, 10, 4, 4));
/*     */     
/* 150 */     this.w.add(new BiomeMeta((Class)EntityBat.class, 10, 8, 8));
/*     */   }
/*     */   
/*     */   protected BiomeDecorator a() {
/* 154 */     return new BiomeDecorator();
/*     */   }
/*     */   
/*     */   public boolean b() {
/* 158 */     return (this.G != null);
/*     */   }
/*     */   
/*     */   public WorldGenTreeAbstract a(Random paramRandom) {
/* 162 */     if (paramRandom.nextInt(10) == 0) {
/* 163 */       return n;
/*     */     }
/* 165 */     return m;
/*     */   }
/*     */   
/*     */   public WorldGenerator b(Random paramRandom) {
/* 169 */     return new WorldGenGrass(BlockLongGrass.EnumTallGrassType.GRASS);
/*     */   }
/*     */   
/*     */   public BlockFlowers.EnumFlowerVarient a(Random paramRandom, BlockPosition paramBlockPosition) {
/* 173 */     if (paramRandom.nextInt(3) > 0) {
/* 174 */       return BlockFlowers.EnumFlowerVarient.DANDELION;
/*     */     }
/* 176 */     return BlockFlowers.EnumFlowerVarient.POPPY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<BiomeMeta> getMobs(EnumCreatureType paramEnumCreatureType) {
/* 186 */     switch (null.a[paramEnumCreatureType.ordinal()]) {
/*     */       case 1:
/* 188 */         return this.t;
/*     */       case 2:
/* 190 */         return this.u;
/*     */       case 3:
/* 192 */         return this.v;
/*     */       case 4:
/* 194 */         return this.w;
/*     */     } 
/* 196 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   public static class BiomeMeta extends WeightedRandom.WeightedRandomChoice {
/*     */     public Class<? extends EntityInsentient> b;
/*     */     public int c;
/*     */     public int d;
/*     */     
/*     */     public BiomeMeta(Class<? extends EntityInsentient> param1Class, int param1Int1, int param1Int2, int param1Int3) {
/* 205 */       super(param1Int1);
/* 206 */       this.b = param1Class;
/* 207 */       this.c = param1Int2;
/* 208 */       this.d = param1Int3;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 213 */       return this.b.getSimpleName() + "*(" + this.c + "-" + this.d + "):" + this.a;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean c() {
/* 218 */     return p();
/*     */   }
/*     */   
/*     */   public boolean d() {
/* 222 */     if (p()) {
/* 223 */       return false;
/*     */     }
/* 225 */     return this.F;
/*     */   }
/*     */   
/*     */   public boolean e() {
/* 229 */     return (getHumidity() > 0.85F);
/*     */   }
/*     */   
/*     */   public float f() {
/* 233 */     return 0.1F;
/*     */   }
/*     */   
/*     */   public final float a(BlockPosition paramBlockPosition) {
/* 237 */     if (paramBlockPosition.getY() > 64) {
/* 238 */       float f = (float)(j.a((paramBlockPosition.getX() / 8.0F), (paramBlockPosition.getZ() / 8.0F)) * 4.0D);
/* 239 */       return getTemperature() - (f + paramBlockPosition.getY() - 64.0F) * 0.05F / 30.0F;
/*     */     } 
/* 241 */     return getTemperature();
/*     */   }
/*     */   
/*     */   public void a(World paramWorld, Random paramRandom, BlockPosition paramBlockPosition) {
/* 245 */     this.s.a(paramWorld, paramRandom, this, paramBlockPosition);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(World paramWorld, Random paramRandom, ChunkSnapshot paramChunkSnapshot, int paramInt1, int paramInt2, double paramDouble) {
/* 264 */     b(paramWorld, paramRandom, paramChunkSnapshot, paramInt1, paramInt2, paramDouble);
/*     */   }
/*     */   
/*     */   public final void b(World paramWorld, Random paramRandom, ChunkSnapshot paramChunkSnapshot, int paramInt1, int paramInt2, double paramDouble) {
/* 268 */     int i = paramWorld.getSeaLevel();
/* 269 */     IBlockData iBlockData1 = this.q;
/* 270 */     IBlockData iBlockData2 = this.r;
/* 271 */     int j = -1;
/* 272 */     int k = (int)(paramDouble / 3.0D + 3.0D + paramRandom.nextDouble() * 0.25D);
/*     */     
/* 274 */     int m = paramInt1 & 0xF;
/* 275 */     int n = paramInt2 & 0xF;
/* 276 */     BlockPosition.MutableBlockPosition mutableBlockPosition = new BlockPosition.MutableBlockPosition();
/* 277 */     for (char c = 'ÿ'; c >= '\000'; c--) {
/* 278 */       if (c <= paramRandom.nextInt(5)) {
/* 279 */         paramChunkSnapshot.a(n, c, m, c);
/*     */       } else {
/*     */         
/* 282 */         IBlockData iBlockData = paramChunkSnapshot.a(n, c, m);
/*     */         
/* 284 */         if (iBlockData.getMaterial() == Material.AIR) {
/* 285 */           j = -1;
/*     */ 
/*     */         
/*     */         }
/* 289 */         else if (iBlockData.getBlock() == Blocks.STONE) {
/*     */ 
/*     */ 
/*     */           
/* 293 */           if (j == -1) {
/* 294 */             if (k <= 0) {
/* 295 */               iBlockData1 = b;
/* 296 */               iBlockData2 = a;
/* 297 */             } else if (c >= i - 4 && c <= i + 1) {
/* 298 */               iBlockData1 = this.q;
/* 299 */               iBlockData2 = this.r;
/*     */             } 
/*     */             
/* 302 */             if (c < i && (iBlockData1 == null || iBlockData1.getMaterial() == Material.AIR)) {
/* 303 */               if (a(mutableBlockPosition.c(paramInt1, c, paramInt2)) < 0.15F) {
/* 304 */                 iBlockData1 = g;
/*     */               } else {
/* 306 */                 iBlockData1 = h;
/*     */               } 
/*     */             }
/*     */             
/* 310 */             j = k;
/* 311 */             if (c >= i - 1) {
/* 312 */               paramChunkSnapshot.a(n, c, m, iBlockData1);
/* 313 */             } else if (c < i - 7 - k) {
/* 314 */               iBlockData1 = b;
/* 315 */               iBlockData2 = a;
/* 316 */               paramChunkSnapshot.a(n, c, m, d);
/*     */             } else {
/* 318 */               paramChunkSnapshot.a(n, c, m, iBlockData2);
/*     */             } 
/* 320 */           } else if (j > 0) {
/* 321 */             j--;
/* 322 */             paramChunkSnapshot.a(n, c, m, iBlockData2);
/*     */ 
/*     */             
/* 325 */             if (j == 0 && iBlockData2.getBlock() == Blocks.SAND && k > 1) {
/* 326 */               j = paramRandom.nextInt(4) + Math.max(0, c - 63);
/* 327 */               iBlockData2 = (iBlockData2.get(BlockSand.VARIANT) == BlockSand.EnumSandVariant.RED_SAND) ? e : f;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public Class<? extends BiomeBase> g() {
/* 335 */     return (Class)getClass();
/*     */   }
/*     */   
/*     */   public EnumTemperature h() {
/* 339 */     if (getTemperature() < 0.2D) {
/* 340 */       return EnumTemperature.COLD;
/*     */     }
/* 342 */     if (getTemperature() < 1.0D) {
/* 343 */       return EnumTemperature.MEDIUM;
/*     */     }
/* 345 */     return EnumTemperature.WARM;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static BiomeBase getBiome(int paramInt) {
/* 350 */     return getBiome(paramInt, null);
/*     */   }
/*     */   
/*     */   public static BiomeBase getBiome(int paramInt, BiomeBase paramBiomeBase) {
/* 354 */     BiomeBase biomeBase = a(paramInt);
/* 355 */     if (biomeBase == null) {
/* 356 */       return paramBiomeBase;
/*     */     }
/* 358 */     return biomeBase;
/*     */   }
/*     */   
/*     */   public boolean i() {
/* 362 */     return false;
/*     */   }
/*     */   
/*     */   public final float j() {
/* 366 */     return this.z;
/*     */   }
/*     */   
/*     */   public final float getHumidity() {
/* 370 */     return this.C;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final float m() {
/* 378 */     return this.A;
/*     */   }
/*     */   
/*     */   public final float getTemperature() {
/* 382 */     return this.B;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean p() {
/* 390 */     return this.E;
/*     */   }
/*     */   
/*     */   public static void q() {
/* 394 */     a(0, "ocean", new BiomeOcean((new a("Ocean")).c(-1.0F).d(0.1F)));
/* 395 */     a(1, "plains", new BiomePlains(false, (new a("Plains")).c(0.125F).d(0.05F).a(0.8F).b(0.4F)));
/* 396 */     a(2, "desert", new BiomeDesert((new a("Desert")).c(0.125F).d(0.05F).a(2.0F).b(0.0F).a()));
/* 397 */     a(3, "extreme_hills", new BiomeBigHills(BiomeBigHills.Type.NORMAL, (new a("Extreme Hills")).c(1.0F).d(0.5F).a(0.2F).b(0.3F)));
/* 398 */     a(4, "forest", new BiomeForest(BiomeForest.Type.NORMAL, (new a("Forest")).a(0.7F).b(0.8F)));
/* 399 */     a(5, "taiga", new BiomeTaiga(BiomeTaiga.Type.NORMAL, (new a("Taiga")).c(0.2F).d(0.2F).a(0.25F).b(0.8F)));
/* 400 */     a(6, "swampland", new BiomeSwamp((new a("Swampland")).c(-0.2F).d(0.1F).a(0.8F).b(0.9F).a(14745518)));
/* 401 */     a(7, "river", new BiomeRiver((new a("River")).c(-0.5F).d(0.0F)));
/* 402 */     a(8, "hell", new BiomeHell((new a("Hell")).a(2.0F).b(0.0F).a()));
/* 403 */     a(9, "sky", new BiomeTheEnd((new a("The End")).a()));
/* 404 */     a(10, "frozen_ocean", new BiomeOcean((new a("FrozenOcean")).c(-1.0F).d(0.1F).a(0.0F).b(0.5F).b()));
/* 405 */     a(11, "frozen_river", new BiomeRiver((new a("FrozenRiver")).c(-0.5F).d(0.0F).a(0.0F).b(0.5F).b()));
/* 406 */     a(12, "ice_flats", new BiomeIcePlains(false, (new a("Ice Plains")).c(0.125F).d(0.05F).a(0.0F).b(0.5F).b()));
/* 407 */     a(13, "ice_mountains", new BiomeIcePlains(false, (new a("Ice Mountains")).c(0.45F).d(0.3F).a(0.0F).b(0.5F).b()));
/* 408 */     a(14, "mushroom_island", new BiomeMushrooms((new a("MushroomIsland")).c(0.2F).d(0.3F).a(0.9F).b(1.0F)));
/* 409 */     a(15, "mushroom_island_shore", new BiomeMushrooms((new a("MushroomIslandShore")).c(0.0F).d(0.025F).a(0.9F).b(1.0F)));
/* 410 */     a(16, "beaches", new BiomeBeach((new a("Beach")).c(0.0F).d(0.025F).a(0.8F).b(0.4F)));
/* 411 */     a(17, "desert_hills", new BiomeDesert((new a("DesertHills")).c(0.45F).d(0.3F).a(2.0F).b(0.0F).a()));
/* 412 */     a(18, "forest_hills", new BiomeForest(BiomeForest.Type.NORMAL, (new a("ForestHills")).c(0.45F).d(0.3F).a(0.7F).b(0.8F)));
/* 413 */     a(19, "taiga_hills", new BiomeTaiga(BiomeTaiga.Type.NORMAL, (new a("TaigaHills")).a(0.25F).b(0.8F).c(0.45F).d(0.3F)));
/* 414 */     a(20, "smaller_extreme_hills", new BiomeBigHills(BiomeBigHills.Type.EXTRA_TREES, (new a("Extreme Hills Edge")).c(0.8F).d(0.3F).a(0.2F).b(0.3F)));
/* 415 */     a(21, "jungle", new BiomeJungle(false, (new a("Jungle")).a(0.95F).b(0.9F)));
/* 416 */     a(22, "jungle_hills", new BiomeJungle(false, (new a("JungleHills")).c(0.45F).d(0.3F).a(0.95F).b(0.9F)));
/* 417 */     a(23, "jungle_edge", new BiomeJungle(true, (new a("JungleEdge")).a(0.95F).b(0.8F)));
/* 418 */     a(24, "deep_ocean", new BiomeOcean((new a("Deep Ocean")).c(-1.8F).d(0.1F)));
/* 419 */     a(25, "stone_beach", new BiomeStoneBeach((new a("Stone Beach")).c(0.1F).d(0.8F).a(0.2F).b(0.3F)));
/* 420 */     a(26, "cold_beach", new BiomeBeach((new a("Cold Beach")).c(0.0F).d(0.025F).a(0.05F).b(0.3F).b()));
/* 421 */     a(27, "birch_forest", new BiomeForest(BiomeForest.Type.BIRCH, (new a("Birch Forest")).a(0.6F).b(0.6F)));
/* 422 */     a(28, "birch_forest_hills", new BiomeForest(BiomeForest.Type.BIRCH, (new a("Birch Forest Hills")).c(0.45F).d(0.3F).a(0.6F).b(0.6F)));
/* 423 */     a(29, "roofed_forest", new BiomeForest(BiomeForest.Type.ROOFED, (new a("Roofed Forest")).a(0.7F).b(0.8F)));
/* 424 */     a(30, "taiga_cold", new BiomeTaiga(BiomeTaiga.Type.NORMAL, (new a("Cold Taiga")).c(0.2F).d(0.2F).a(-0.5F).b(0.4F).b()));
/* 425 */     a(31, "taiga_cold_hills", new BiomeTaiga(BiomeTaiga.Type.NORMAL, (new a("Cold Taiga Hills")).c(0.45F).d(0.3F).a(-0.5F).b(0.4F).b()));
/* 426 */     a(32, "redwood_taiga", new BiomeTaiga(BiomeTaiga.Type.MEGA, (new a("Mega Taiga")).a(0.3F).b(0.8F).c(0.2F).d(0.2F)));
/* 427 */     a(33, "redwood_taiga_hills", new BiomeTaiga(BiomeTaiga.Type.MEGA, (new a("Mega Taiga Hills")).c(0.45F).d(0.3F).a(0.3F).b(0.8F)));
/* 428 */     a(34, "extreme_hills_with_trees", new BiomeBigHills(BiomeBigHills.Type.EXTRA_TREES, (new a("Extreme Hills+")).c(1.0F).d(0.5F).a(0.2F).b(0.3F)));
/* 429 */     a(35, "savanna", new BiomeSavanna((new a("Savanna")).c(0.125F).d(0.05F).a(1.2F).b(0.0F).a()));
/* 430 */     a(36, "savanna_rock", new BiomeSavanna((new a("Savanna Plateau")).c(1.5F).d(0.025F).a(1.0F).b(0.0F).a()));
/* 431 */     a(37, "mesa", new BiomeMesa(false, false, (new a("Mesa")).a(2.0F).b(0.0F).a()));
/* 432 */     a(38, "mesa_rock", new BiomeMesa(false, true, (new a("Mesa Plateau F")).c(1.5F).d(0.025F).a(2.0F).b(0.0F).a()));
/* 433 */     a(39, "mesa_clear_rock", new BiomeMesa(false, false, (new a("Mesa Plateau")).c(1.5F).d(0.025F).a(2.0F).b(0.0F).a()));
/*     */     
/* 435 */     a(127, "void", new BiomeVoid((new a("The Void")).a()));
/*     */     
/* 437 */     a(129, "mutated_plains", new BiomePlains(true, (new a("Sunflower Plains")).a("plains").c(0.125F).d(0.05F).a(0.8F).b(0.4F)));
/* 438 */     a(130, "mutated_desert", new BiomeDesert((new a("Desert M")).a("desert").c(0.225F).d(0.25F).a(2.0F).b(0.0F).a()));
/* 439 */     a(131, "mutated_extreme_hills", new BiomeBigHills(BiomeBigHills.Type.MUTATED, (new a("Extreme Hills M")).a("extreme_hills").c(1.0F).d(0.5F).a(0.2F).b(0.3F)));
/* 440 */     a(132, "mutated_forest", new BiomeForest(BiomeForest.Type.FLOWER, (new a("Flower Forest")).a("forest").d(0.4F).a(0.7F).b(0.8F)));
/* 441 */     a(133, "mutated_taiga", new BiomeTaiga(BiomeTaiga.Type.NORMAL, (new a("Taiga M")).a("taiga").c(0.3F).d(0.4F).a(0.25F).b(0.8F)));
/* 442 */     a(134, "mutated_swampland", new BiomeSwamp((new a("Swampland M")).a("swampland").c(-0.1F).d(0.3F).a(0.8F).b(0.9F).a(14745518)));
/* 443 */     a(140, "mutated_ice_flats", new BiomeIcePlains(true, (new a("Ice Plains Spikes")).a("ice_flats").c(0.425F).d(0.45000002F).a(0.0F).b(0.5F).b()));
/* 444 */     a(149, "mutated_jungle", new BiomeJungle(false, (new a("Jungle M")).a("jungle").c(0.2F).d(0.4F).a(0.95F).b(0.9F)));
/* 445 */     a(151, "mutated_jungle_edge", new BiomeJungle(true, (new a("JungleEdge M")).a("jungle_edge").c(0.2F).d(0.4F).a(0.95F).b(0.8F)));
/*     */     
/* 447 */     a(155, "mutated_birch_forest", new BiomeForestMutated((new a("Birch Forest M")).a("birch_forest").c(0.2F).d(0.4F).a(0.6F).b(0.6F)));
/* 448 */     a(156, "mutated_birch_forest_hills", new BiomeForestMutated((new a("Birch Forest Hills M")).a("birch_forest_hills").c(0.55F).d(0.5F).a(0.6F).b(0.6F)));
/* 449 */     a(157, "mutated_roofed_forest", new BiomeForest(BiomeForest.Type.ROOFED, (new a("Roofed Forest M")).a("roofed_forest").c(0.2F).d(0.4F).a(0.7F).b(0.8F)));
/* 450 */     a(158, "mutated_taiga_cold", new BiomeTaiga(BiomeTaiga.Type.NORMAL, (new a("Cold Taiga M")).a("taiga_cold").c(0.3F).d(0.4F).a(-0.5F).b(0.4F).b()));
/* 451 */     a(160, "mutated_redwood_taiga", new BiomeTaiga(BiomeTaiga.Type.MEGA_SPRUCE, (new a("Mega Spruce Taiga")).a("redwood_taiga").c(0.2F).d(0.2F).a(0.25F).b(0.8F)));
/* 452 */     a(161, "mutated_redwood_taiga_hills", new BiomeTaiga(BiomeTaiga.Type.MEGA_SPRUCE, (new a("Redwood Taiga Hills M")).a("redwood_taiga_hills").c(0.2F).d(0.2F).a(0.25F).b(0.8F)));
/* 453 */     a(162, "mutated_extreme_hills_with_trees", new BiomeBigHills(BiomeBigHills.Type.MUTATED, (new a("Extreme Hills+ M")).a("extreme_hills_with_trees").c(1.0F).d(0.5F).a(0.2F).b(0.3F)));
/* 454 */     a(163, "mutated_savanna", new BiomeSavannaMutated((new a("Savanna M")).a("savanna").c(0.3625F).d(1.225F).a(1.1F).b(0.0F).a()));
/* 455 */     a(164, "mutated_savanna_rock", new BiomeSavannaMutated((new a("Savanna Plateau M")).a("savanna_rock").c(1.05F).d(1.2125001F).a(1.0F).b(0.0F).a()));
/* 456 */     a(165, "mutated_mesa", new BiomeMesa(true, false, (new a("Mesa (Bryce)")).a("mesa").a(2.0F).b(0.0F).a()));
/* 457 */     a(166, "mutated_mesa_rock", new BiomeMesa(false, true, (new a("Mesa Plateau F M")).a("mesa_rock").c(0.45F).d(0.3F).a(2.0F).b(0.0F).a()));
/* 458 */     a(167, "mutated_mesa_clear_rock", new BiomeMesa(false, false, (new a("Mesa Plateau M")).a("mesa_clear_rock").c(0.45F).d(0.3F).a(2.0F).b(0.0F).a()));
/*     */   }
/*     */   
/*     */   private static void a(int paramInt, String paramString, BiomeBase paramBiomeBase) {
/* 462 */     REGISTRY_ID.a(paramInt, new MinecraftKey(paramString), paramBiomeBase);
/* 463 */     if (paramBiomeBase.b())
/* 464 */       i.a(paramBiomeBase, a(REGISTRY_ID.get(new MinecraftKey(paramBiomeBase.G)))); 
/*     */   }
/*     */   
/*     */   public static class a
/*     */   {
/*     */     private final String a;
/* 470 */     private float b = 0.1F;
/* 471 */     private float c = 0.2F;
/* 472 */     private float d = 0.5F;
/* 473 */     private float e = 0.5F;
/* 474 */     private int f = 16777215; private boolean g;
/*     */     private boolean h = true;
/*     */     @Nullable
/*     */     private String i;
/*     */     
/*     */     public a(String param1String) {
/* 480 */       this.a = param1String;
/*     */     }
/*     */     
/*     */     protected a a(float param1Float) {
/* 484 */       if (param1Float > 0.1F && param1Float < 0.2F) {
/* 485 */         throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
/*     */       }
/* 487 */       this.d = param1Float;
/* 488 */       return this;
/*     */     }
/*     */     
/*     */     protected a b(float param1Float) {
/* 492 */       this.e = param1Float;
/* 493 */       return this;
/*     */     }
/*     */     
/*     */     protected a c(float param1Float) {
/* 497 */       this.b = param1Float;
/* 498 */       return this;
/*     */     }
/*     */     
/*     */     protected a d(float param1Float) {
/* 502 */       this.c = param1Float;
/* 503 */       return this;
/*     */     }
/*     */     
/*     */     protected a a() {
/* 507 */       this.h = false;
/* 508 */       return this;
/*     */     }
/*     */     
/*     */     protected a b() {
/* 512 */       this.g = true;
/* 513 */       return this;
/*     */     }
/*     */     
/*     */     protected a a(int param1Int) {
/* 517 */       this.f = param1Int;
/* 518 */       return this;
/*     */     }
/*     */     
/*     */     protected a a(String param1String) {
/* 522 */       this.i = param1String;
/* 523 */       return this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomeBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */