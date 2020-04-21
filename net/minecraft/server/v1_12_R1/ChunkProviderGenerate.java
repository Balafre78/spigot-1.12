/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class ChunkProviderGenerate
/*     */   implements ChunkGenerator {
/*   9 */   protected static final IBlockData a = Blocks.STONE.getBlockData();
/*     */   private final Random i;
/*     */   private final NoiseGeneratorOctaves j;
/*     */   private final NoiseGeneratorOctaves k;
/*     */   private final NoiseGeneratorOctaves l;
/*     */   private final NoiseGenerator3 m;
/*     */   public NoiseGeneratorOctaves b;
/*     */   public NoiseGeneratorOctaves c;
/*     */   public NoiseGeneratorOctaves d;
/*     */   private final World n;
/*     */   private final boolean o;
/*     */   private final WorldType p;
/*     */   private final double[] q;
/*     */   private final float[] r;
/*     */   private CustomWorldSettingsFinal s;
/*     */   private IBlockData t;
/*     */   private double[] u;
/*     */   private final WorldGenBase v;
/*     */   private final WorldGenStronghold w;
/*     */   private final WorldGenVillage x;
/*     */   private final WorldGenMineshaft y;
/*     */   private final WorldGenLargeFeature z;
/*     */   private final WorldGenBase A;
/*     */   private final WorldGenMonument B;
/*     */   private final WorldGenWoodlandMansion C;
/*     */   private BiomeBase[] D;
/*     */   double[] e;
/*     */   double[] f;
/*     */   double[] g;
/*     */   double[] h;
/*     */   
/*     */   public ChunkProviderGenerate(World world, long i, boolean flag, String s) {
/*  41 */     this.t = Blocks.WATER.getBlockData();
/*  42 */     this.u = new double[256];
/*  43 */     this.v = new WorldGenCaves();
/*  44 */     this.w = new WorldGenStronghold();
/*  45 */     this.x = new WorldGenVillage();
/*  46 */     this.y = new WorldGenMineshaft();
/*  47 */     this.z = new WorldGenLargeFeature();
/*  48 */     this.A = new WorldGenCanyon();
/*  49 */     this.B = new WorldGenMonument();
/*  50 */     this.C = new WorldGenWoodlandMansion(this);
/*  51 */     this.n = world;
/*  52 */     this.o = flag;
/*  53 */     this.p = world.getWorldData().getType();
/*  54 */     this.i = new Random(i);
/*  55 */     this.j = new NoiseGeneratorOctaves(this.i, 16);
/*  56 */     this.k = new NoiseGeneratorOctaves(this.i, 16);
/*  57 */     this.l = new NoiseGeneratorOctaves(this.i, 8);
/*  58 */     this.m = new NoiseGenerator3(this.i, 4);
/*  59 */     this.b = new NoiseGeneratorOctaves(this.i, 10);
/*  60 */     this.c = new NoiseGeneratorOctaves(this.i, 16);
/*  61 */     this.d = new NoiseGeneratorOctaves(this.i, 8);
/*  62 */     this.q = new double[825];
/*  63 */     this.r = new float[25];
/*     */     
/*  65 */     for (int j = -2; j <= 2; j++) {
/*  66 */       for (int k = -2; k <= 2; k++) {
/*  67 */         float f = 10.0F / MathHelper.c((j * j + k * k) + 0.2F);
/*     */         
/*  69 */         this.r[j + 2 + (k + 2) * 5] = f;
/*     */       } 
/*     */     } 
/*     */     
/*  73 */     if (s != null) {
/*  74 */       this.s = CustomWorldSettingsFinal.CustomWorldSettings.a(s).b();
/*  75 */       this.t = this.s.F ? Blocks.LAVA.getBlockData() : Blocks.WATER.getBlockData();
/*  76 */       world.b(this.s.q);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(int i, int j, ChunkSnapshot chunksnapshot) {
/*  82 */     this.D = this.n.getWorldChunkManager().getBiomes(this.D, i * 4 - 2, j * 4 - 2, 10, 10);
/*  83 */     a(i * 4, 0, j * 4);
/*     */     
/*  85 */     for (int k = 0; k < 4; k++) {
/*  86 */       int l = k * 5;
/*  87 */       int i1 = (k + 1) * 5;
/*     */       
/*  89 */       for (int j1 = 0; j1 < 4; j1++) {
/*  90 */         int k1 = (l + j1) * 33;
/*  91 */         int l1 = (l + j1 + 1) * 33;
/*  92 */         int i2 = (i1 + j1) * 33;
/*  93 */         int j2 = (i1 + j1 + 1) * 33;
/*     */         
/*  95 */         for (int k2 = 0; k2 < 32; k2++) {
/*     */           
/*  97 */           double d1 = this.q[k1 + k2];
/*  98 */           double d2 = this.q[l1 + k2];
/*  99 */           double d3 = this.q[i2 + k2];
/* 100 */           double d4 = this.q[j2 + k2];
/* 101 */           double d5 = (this.q[k1 + k2 + 1] - d1) * 0.125D;
/* 102 */           double d6 = (this.q[l1 + k2 + 1] - d2) * 0.125D;
/* 103 */           double d7 = (this.q[i2 + k2 + 1] - d3) * 0.125D;
/* 104 */           double d8 = (this.q[j2 + k2 + 1] - d4) * 0.125D;
/*     */           
/* 106 */           for (int l2 = 0; l2 < 8; l2++) {
/*     */             
/* 108 */             double d10 = d1;
/* 109 */             double d11 = d2;
/* 110 */             double d12 = (d3 - d1) * 0.25D;
/* 111 */             double d13 = (d4 - d2) * 0.25D;
/*     */             
/* 113 */             for (int i3 = 0; i3 < 4; i3++) {
/*     */               
/* 115 */               double d15 = (d11 - d10) * 0.25D;
/* 116 */               double d16 = d10 - d15;
/*     */               
/* 118 */               for (int j3 = 0; j3 < 4; j3++) {
/* 119 */                 if ((d16 += d15) > 0.0D) {
/* 120 */                   chunksnapshot.a(k * 4 + i3, k2 * 8 + l2, j1 * 4 + j3, a);
/* 121 */                 } else if (k2 * 8 + l2 < this.s.q) {
/* 122 */                   chunksnapshot.a(k * 4 + i3, k2 * 8 + l2, j1 * 4 + j3, this.t);
/*     */                 } 
/*     */               } 
/*     */               
/* 126 */               d10 += d12;
/* 127 */               d11 += d13;
/*     */             } 
/*     */             
/* 130 */             d1 += d5;
/* 131 */             d2 += d6;
/* 132 */             d3 += d7;
/* 133 */             d4 += d8;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(int i, int j, ChunkSnapshot chunksnapshot, BiomeBase[] abiomebase) {
/* 144 */     this.u = this.m.a(this.u, (i * 16), (j * 16), 16, 16, 0.0625D, 0.0625D, 1.0D);
/*     */     
/* 146 */     for (int k = 0; k < 16; k++) {
/* 147 */       for (int l = 0; l < 16; l++) {
/* 148 */         BiomeBase biomebase = abiomebase[l + k * 16];
/*     */         
/* 150 */         biomebase.a(this.n, this.i, chunksnapshot, i * 16 + k, j * 16 + l, this.u[l + k * 16]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Chunk getOrCreateChunk(int i, int j) {
/* 157 */     this.i.setSeed(i * 341873128712L + j * 132897987541L);
/* 158 */     ChunkSnapshot chunksnapshot = new ChunkSnapshot();
/*     */     
/* 160 */     a(i, j, chunksnapshot);
/* 161 */     this.D = this.n.getWorldChunkManager().getBiomeBlock(this.D, i * 16, j * 16, 16, 16);
/* 162 */     a(i, j, chunksnapshot, this.D);
/* 163 */     if (this.s.r) {
/* 164 */       this.v.a(this.n, i, j, chunksnapshot);
/*     */     }
/*     */     
/* 167 */     if (this.s.A) {
/* 168 */       this.A.a(this.n, i, j, chunksnapshot);
/*     */     }
/*     */     
/* 171 */     if (this.o) {
/* 172 */       if (this.s.w) {
/* 173 */         this.y.a(this.n, i, j, chunksnapshot);
/*     */       }
/*     */       
/* 176 */       if (this.s.v) {
/* 177 */         this.x.a(this.n, i, j, chunksnapshot);
/*     */       }
/*     */       
/* 180 */       if (this.s.u) {
/* 181 */         this.w.a(this.n, i, j, chunksnapshot);
/*     */       }
/*     */       
/* 184 */       if (this.s.x) {
/* 185 */         this.z.a(this.n, i, j, chunksnapshot);
/*     */       }
/*     */       
/* 188 */       if (this.s.y) {
/* 189 */         this.B.a(this.n, i, j, chunksnapshot);
/*     */       }
/*     */       
/* 192 */       if (this.s.z) {
/* 193 */         this.C.a(this.n, i, j, chunksnapshot);
/*     */       }
/*     */     } 
/*     */     
/* 197 */     Chunk chunk = new Chunk(this.n, chunksnapshot, i, j);
/* 198 */     byte[] abyte = chunk.getBiomeIndex();
/*     */     
/* 200 */     for (int k = 0; k < abyte.length; k++) {
/* 201 */       abyte[k] = (byte)BiomeBase.a(this.D[k]);
/*     */     }
/*     */     
/* 204 */     chunk.initLighting();
/* 205 */     return chunk;
/*     */   }
/*     */   
/*     */   private void a(int i, int j, int k) {
/* 209 */     this.h = this.c.a(this.h, i, k, 5, 5, this.s.e, this.s.f, this.s.g);
/* 210 */     float f = this.s.a;
/* 211 */     float f1 = this.s.b;
/*     */     
/* 213 */     this.e = this.l.a(this.e, i, j, k, 5, 33, 5, (f / this.s.h), (f1 / this.s.i), (f / this.s.j));
/* 214 */     this.f = this.j.a(this.f, i, j, k, 5, 33, 5, f, f1, f);
/* 215 */     this.g = this.k.a(this.g, i, j, k, 5, 33, 5, f, f1, f);
/* 216 */     int l = 0;
/* 217 */     int i1 = 0;
/*     */     
/* 219 */     for (int j1 = 0; j1 < 5; j1++) {
/* 220 */       for (int k1 = 0; k1 < 5; k1++) {
/* 221 */         float f2 = 0.0F;
/* 222 */         float f3 = 0.0F;
/* 223 */         float f4 = 0.0F;
/*     */         
/* 225 */         BiomeBase biomebase = this.D[j1 + 2 + (k1 + 2) * 10];
/*     */         
/* 227 */         for (int l1 = -2; l1 <= 2; l1++) {
/* 228 */           for (int i2 = -2; i2 <= 2; i2++) {
/* 229 */             BiomeBase biomebase1 = this.D[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
/* 230 */             float f5 = this.s.n + biomebase1.j() * this.s.m;
/* 231 */             float f6 = this.s.p + biomebase1.m() * this.s.o;
/*     */             
/* 233 */             if (this.p == WorldType.AMPLIFIED && f5 > 0.0F) {
/* 234 */               f5 = 1.0F + f5 * 2.0F;
/* 235 */               f6 = 1.0F + f6 * 4.0F;
/*     */             } 
/*     */             
/* 238 */             if (f5 < -1.8F) {
/* 239 */               f5 = -1.8F;
/*     */             }
/*     */ 
/*     */             
/* 243 */             float f7 = this.r[l1 + 2 + (i2 + 2) * 5] / (f5 + 2.0F);
/*     */             
/* 245 */             if (biomebase1.j() > biomebase.j()) {
/* 246 */               f7 /= 2.0F;
/*     */             }
/*     */             
/* 249 */             f2 += f6 * f7;
/* 250 */             f3 += f5 * f7;
/* 251 */             f4 += f7;
/*     */           } 
/*     */         } 
/*     */         
/* 255 */         f2 /= f4;
/* 256 */         f3 /= f4;
/* 257 */         f2 = f2 * 0.9F + 0.1F;
/* 258 */         f3 = (f3 * 4.0F - 1.0F) / 8.0F;
/* 259 */         double d0 = this.h[i1] / 8000.0D;
/*     */         
/* 261 */         if (d0 < 0.0D) {
/* 262 */           d0 = -d0 * 0.3D;
/*     */         }
/*     */         
/* 265 */         d0 = d0 * 3.0D - 2.0D;
/* 266 */         if (d0 < 0.0D) {
/* 267 */           d0 /= 2.0D;
/* 268 */           if (d0 < -1.0D) {
/* 269 */             d0 = -1.0D;
/*     */           }
/*     */           
/* 272 */           d0 /= 1.4D;
/* 273 */           d0 /= 2.0D;
/*     */         } else {
/* 275 */           if (d0 > 1.0D) {
/* 276 */             d0 = 1.0D;
/*     */           }
/*     */           
/* 279 */           d0 /= 8.0D;
/*     */         } 
/*     */         
/* 282 */         i1++;
/* 283 */         double d1 = f3;
/* 284 */         double d2 = f2;
/*     */         
/* 286 */         d1 += d0 * 0.2D;
/* 287 */         d1 = d1 * this.s.k / 8.0D;
/* 288 */         double d3 = this.s.k + d1 * 4.0D;
/*     */         
/* 290 */         for (int j2 = 0; j2 < 33; j2++) {
/* 291 */           double d4 = (j2 - d3) * this.s.l * 128.0D / 256.0D / d2;
/*     */           
/* 293 */           if (d4 < 0.0D) {
/* 294 */             d4 *= 4.0D;
/*     */           }
/*     */           
/* 297 */           double d5 = this.f[l] / this.s.d;
/* 298 */           double d6 = this.g[l] / this.s.c;
/* 299 */           double d7 = (this.e[l] / 10.0D + 1.0D) / 2.0D;
/* 300 */           double d8 = MathHelper.b(d5, d6, d7) - d4;
/*     */           
/* 302 */           if (j2 > 29) {
/* 303 */             double d9 = ((j2 - 29) / 3.0F);
/*     */             
/* 305 */             d8 = d8 * (1.0D - d9) + -10.0D * d9;
/*     */           } 
/*     */           
/* 308 */           this.q[l] = d8;
/* 309 */           l++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void recreateStructures(int i, int j) {
/* 317 */     BlockFalling.instaFall = true;
/* 318 */     int k = i * 16;
/* 319 */     int l = j * 16;
/* 320 */     BlockPosition blockposition = new BlockPosition(k, 0, l);
/* 321 */     BiomeBase biomebase = this.n.getBiome(blockposition.a(16, 0, 16));
/*     */     
/* 323 */     this.i.setSeed(this.n.getSeed());
/* 324 */     long i1 = this.i.nextLong() / 2L * 2L + 1L;
/* 325 */     long j1 = this.i.nextLong() / 2L * 2L + 1L;
/*     */     
/* 327 */     this.i.setSeed(i * i1 + j * j1 ^ this.n.getSeed());
/* 328 */     boolean flag = false;
/* 329 */     ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i, j);
/*     */     
/* 331 */     if (this.o) {
/* 332 */       if (this.s.w) {
/* 333 */         this.y.a(this.n, this.i, chunkcoordintpair);
/*     */       }
/*     */       
/* 336 */       if (this.s.v) {
/* 337 */         flag = this.x.a(this.n, this.i, chunkcoordintpair);
/*     */       }
/*     */       
/* 340 */       if (this.s.u) {
/* 341 */         this.w.a(this.n, this.i, chunkcoordintpair);
/*     */       }
/*     */       
/* 344 */       if (this.s.x) {
/* 345 */         this.z.a(this.n, this.i, chunkcoordintpair);
/*     */       }
/*     */       
/* 348 */       if (this.s.y) {
/* 349 */         this.B.a(this.n, this.i, chunkcoordintpair);
/*     */       }
/*     */       
/* 352 */       if (this.s.z) {
/* 353 */         this.C.a(this.n, this.i, chunkcoordintpair);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 361 */     if (biomebase != Biomes.d && biomebase != Biomes.s && this.s.B && !flag && this.i.nextInt(this.s.C) == 0) {
/* 362 */       int m = this.i.nextInt(16) + 8;
/* 363 */       int l1 = this.i.nextInt(256);
/* 364 */       int i2 = this.i.nextInt(16) + 8;
/* 365 */       (new WorldGenLakes(Blocks.WATER)).generate(this.n, this.i, blockposition.a(m, l1, i2));
/*     */     } 
/*     */     
/* 368 */     if (!flag && this.i.nextInt(this.s.E / 10) == 0 && this.s.D) {
/* 369 */       int m = this.i.nextInt(16) + 8;
/* 370 */       int l1 = this.i.nextInt(this.i.nextInt(248) + 8);
/* 371 */       int i2 = this.i.nextInt(16) + 8;
/* 372 */       if (l1 < this.n.getSeaLevel() || this.i.nextInt(this.s.E / 8) == 0) {
/* 373 */         (new WorldGenLakes(Blocks.LAVA)).generate(this.n, this.i, blockposition.a(m, l1, i2));
/*     */       }
/*     */     } 
/*     */     
/* 377 */     if (this.s.s) {
/* 378 */       for (int m = 0; m < this.s.t; m++) {
/* 379 */         int l1 = this.i.nextInt(16) + 8;
/* 380 */         int i2 = this.i.nextInt(256);
/* 381 */         int j2 = this.i.nextInt(16) + 8;
/*     */         
/* 383 */         (new WorldGenDungeons()).generate(this.n, this.i, blockposition.a(l1, i2, j2));
/*     */       } 
/*     */     }
/*     */     
/* 387 */     biomebase.a(this.n, this.i, new BlockPosition(k, 0, l));
/* 388 */     SpawnerCreature.a(this.n, biomebase, k + 8, l + 8, 16, 16, this.i);
/* 389 */     blockposition = blockposition.a(8, 0, 8);
/*     */     
/* 391 */     for (int k1 = 0; k1 < 16; k1++) {
/* 392 */       for (int l1 = 0; l1 < 16; l1++) {
/* 393 */         BlockPosition blockposition1 = this.n.p(blockposition.a(k1, 0, l1));
/* 394 */         BlockPosition blockposition2 = blockposition1.down();
/*     */         
/* 396 */         if (this.n.u(blockposition2)) {
/* 397 */           this.n.setTypeAndData(blockposition2, Blocks.ICE.getBlockData(), 2);
/*     */         }
/*     */         
/* 400 */         if (this.n.f(blockposition1, true)) {
/* 401 */           this.n.setTypeAndData(blockposition1, Blocks.SNOW_LAYER.getBlockData(), 2);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 406 */     BlockFalling.instaFall = false;
/*     */   }
/*     */   
/*     */   public boolean a(Chunk chunk, int i, int j) {
/* 410 */     boolean flag = false;
/*     */     
/* 412 */     if (this.s.y && this.o && chunk.x() < 3600L) {
/* 413 */       flag |= this.B.a(this.n, this.i, new ChunkCoordIntPair(i, j));
/*     */     }
/*     */     
/* 416 */     return flag;
/*     */   }
/*     */   
/*     */   public List<BiomeBase.BiomeMeta> getMobsFor(EnumCreatureType enumcreaturetype, BlockPosition blockposition) {
/* 420 */     BiomeBase biomebase = this.n.getBiome(blockposition);
/*     */     
/* 422 */     if (this.o) {
/* 423 */       if (enumcreaturetype == EnumCreatureType.MONSTER && this.z.a(blockposition)) {
/* 424 */         return this.z.b();
/*     */       }
/*     */       
/* 427 */       if (enumcreaturetype == EnumCreatureType.MONSTER && this.s.y && this.B.a(this.n, blockposition)) {
/* 428 */         return this.B.b();
/*     */       }
/*     */     } 
/*     */     
/* 432 */     return biomebase.getMobs(enumcreaturetype);
/*     */   }
/*     */   
/*     */   public boolean a(World world, String s, BlockPosition blockposition) {
/* 436 */     return !this.o ? false : (("Stronghold".equals(s) && this.w != null) ? this.w.b(blockposition) : (("Mansion".equals(s) && this.C != null) ? this.C.b(blockposition) : (("Monument".equals(s) && this.B != null) ? this.B.b(blockposition) : (("Village".equals(s) && this.x != null) ? this.x.b(blockposition) : (("Mineshaft".equals(s) && this.y != null) ? this.y.b(blockposition) : (("Temple".equals(s) && this.z != null) ? this.z.b(blockposition) : false))))));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BlockPosition findNearestMapFeature(World world, String s, BlockPosition blockposition, boolean flag) {
/* 441 */     return !this.o ? null : (("Stronghold".equals(s) && this.w != null) ? this.w.getNearestGeneratedFeature(world, blockposition, flag) : (("Mansion".equals(s) && this.C != null) ? this.C.getNearestGeneratedFeature(world, blockposition, flag) : (("Monument".equals(s) && this.B != null) ? this.B.getNearestGeneratedFeature(world, blockposition, flag) : (("Village".equals(s) && this.x != null) ? this.x.getNearestGeneratedFeature(world, blockposition, flag) : (("Mineshaft".equals(s) && this.y != null) ? this.y.getNearestGeneratedFeature(world, blockposition, flag) : (("Temple".equals(s) && this.z != null) ? this.z.getNearestGeneratedFeature(world, blockposition, flag) : null))))));
/*     */   }
/*     */   
/*     */   public void recreateStructures(Chunk chunk, int i, int j) {
/* 445 */     if (this.o) {
/* 446 */       if (this.s.w) {
/* 447 */         this.y.a(this.n, i, j, (ChunkSnapshot)null);
/*     */       }
/*     */       
/* 450 */       if (this.s.v) {
/* 451 */         this.x.a(this.n, i, j, (ChunkSnapshot)null);
/*     */       }
/*     */       
/* 454 */       if (this.s.u) {
/* 455 */         this.w.a(this.n, i, j, (ChunkSnapshot)null);
/*     */       }
/*     */       
/* 458 */       if (this.s.x) {
/* 459 */         this.z.a(this.n, i, j, (ChunkSnapshot)null);
/*     */       }
/*     */       
/* 462 */       if (this.s.y) {
/* 463 */         this.B.a(this.n, i, j, (ChunkSnapshot)null);
/*     */       }
/*     */       
/* 466 */       if (this.s.z)
/* 467 */         this.C.a(this.n, i, j, (ChunkSnapshot)null); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChunkProviderGenerate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */