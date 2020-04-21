/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.List;
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
/*     */ public class ChunkProviderHell
/*     */   implements ChunkGenerator
/*     */ {
/*  35 */   protected static final IBlockData a = Blocks.AIR.getBlockData();
/*  36 */   protected static final IBlockData b = Blocks.NETHERRACK.getBlockData();
/*  37 */   protected static final IBlockData c = Blocks.BEDROCK.getBlockData();
/*  38 */   protected static final IBlockData d = Blocks.LAVA.getBlockData();
/*  39 */   protected static final IBlockData e = Blocks.GRAVEL.getBlockData();
/*  40 */   protected static final IBlockData f = Blocks.SOUL_SAND.getBlockData();
/*     */   
/*     */   private final World n;
/*     */   
/*     */   private final boolean o;
/*     */   private final Random p;
/*  46 */   private double[] q = new double[256];
/*  47 */   private double[] r = new double[256];
/*  48 */   private double[] s = new double[256];
/*     */   
/*     */   private double[] t;
/*     */   
/*     */   private final NoiseGeneratorOctaves u;
/*     */   private final NoiseGeneratorOctaves v;
/*     */   private final NoiseGeneratorOctaves w;
/*     */   private final NoiseGeneratorOctaves x;
/*     */   private final NoiseGeneratorOctaves y;
/*     */   public final NoiseGeneratorOctaves g;
/*     */   public final NoiseGeneratorOctaves h;
/*  59 */   private final WorldGenFire z = new WorldGenFire();
/*  60 */   private final WorldGenLightStone1 A = new WorldGenLightStone1();
/*  61 */   private final WorldGenLightStone2 B = new WorldGenLightStone2();
/*  62 */   private final WorldGenerator C = new WorldGenMinable(Blocks.QUARTZ_ORE.getBlockData(), 14, BlockPredicate.a(Blocks.NETHERRACK));
/*  63 */   private final WorldGenerator D = new WorldGenMinable(Blocks.df.getBlockData(), 33, BlockPredicate.a(Blocks.NETHERRACK));
/*  64 */   private final WorldGenHellLava E = new WorldGenHellLava(Blocks.FLOWING_LAVA, true);
/*  65 */   private final WorldGenHellLava F = new WorldGenHellLava(Blocks.FLOWING_LAVA, false);
/*  66 */   private final WorldGenMushrooms G = new WorldGenMushrooms(Blocks.BROWN_MUSHROOM);
/*  67 */   private final WorldGenMushrooms H = new WorldGenMushrooms(Blocks.RED_MUSHROOM);
/*  68 */   private final WorldGenNether I = new WorldGenNether();
/*  69 */   private final WorldGenBase J = new WorldGenCavesHell(); double[] i; double[] j;
/*     */   
/*     */   public ChunkProviderHell(World paramWorld, boolean paramBoolean, long paramLong) {
/*  72 */     this.n = paramWorld;
/*  73 */     this.o = paramBoolean;
/*     */     
/*  75 */     this.p = new Random(paramLong);
/*  76 */     this.u = new NoiseGeneratorOctaves(this.p, 16);
/*  77 */     this.v = new NoiseGeneratorOctaves(this.p, 16);
/*  78 */     this.w = new NoiseGeneratorOctaves(this.p, 8);
/*  79 */     this.x = new NoiseGeneratorOctaves(this.p, 4);
/*  80 */     this.y = new NoiseGeneratorOctaves(this.p, 4);
/*     */     
/*  82 */     this.g = new NoiseGeneratorOctaves(this.p, 10);
/*  83 */     this.h = new NoiseGeneratorOctaves(this.p, 16);
/*     */     
/*  85 */     paramWorld.b(63);
/*     */   }
/*     */   double[] k; double[] l; double[] m;
/*     */   public void a(int paramInt1, int paramInt2, ChunkSnapshot paramChunkSnapshot) {
/*  89 */     byte b1 = 4;
/*  90 */     int i = this.n.getSeaLevel() / 2 + 1;
/*     */     
/*  92 */     byte b2 = 5;
/*  93 */     byte b3 = 17;
/*  94 */     byte b4 = 5;
/*  95 */     this.t = a(this.t, paramInt1 * 4, 0, paramInt2 * 4, 5, 17, 5);
/*     */     
/*  97 */     for (byte b5 = 0; b5 < 4; b5++) {
/*  98 */       for (byte b = 0; b < 4; b++) {
/*  99 */         for (byte b6 = 0; b6 < 16; b6++) {
/* 100 */           double d1 = 0.125D;
/* 101 */           double d2 = this.t[((b5 + 0) * 5 + b + 0) * 17 + b6 + 0];
/* 102 */           double d3 = this.t[((b5 + 0) * 5 + b + 1) * 17 + b6 + 0];
/* 103 */           double d4 = this.t[((b5 + 1) * 5 + b + 0) * 17 + b6 + 0];
/* 104 */           double d5 = this.t[((b5 + 1) * 5 + b + 1) * 17 + b6 + 0];
/*     */           
/* 106 */           double d6 = (this.t[((b5 + 0) * 5 + b + 0) * 17 + b6 + 1] - d2) * 0.125D;
/* 107 */           double d7 = (this.t[((b5 + 0) * 5 + b + 1) * 17 + b6 + 1] - d3) * 0.125D;
/* 108 */           double d8 = (this.t[((b5 + 1) * 5 + b + 0) * 17 + b6 + 1] - d4) * 0.125D;
/* 109 */           double d9 = (this.t[((b5 + 1) * 5 + b + 1) * 17 + b6 + 1] - d5) * 0.125D;
/*     */           
/* 111 */           for (byte b7 = 0; b7 < 8; b7++) {
/* 112 */             double d10 = 0.25D;
/*     */             
/* 114 */             double d11 = d2;
/* 115 */             double d12 = d3;
/* 116 */             double d13 = (d4 - d2) * 0.25D;
/* 117 */             double d14 = (d5 - d3) * 0.25D;
/*     */             
/* 119 */             for (byte b8 = 0; b8 < 4; b8++) {
/* 120 */               double d15 = 0.25D;
/*     */               
/* 122 */               double d16 = d11;
/* 123 */               double d17 = (d12 - d11) * 0.25D;
/* 124 */               for (byte b9 = 0; b9 < 4; b9++) {
/* 125 */                 IBlockData iBlockData = null;
/* 126 */                 if (b6 * 8 + b7 < i) {
/* 127 */                   iBlockData = d;
/*     */                 }
/* 129 */                 if (d16 > 0.0D) {
/* 130 */                   iBlockData = b;
/*     */                 }
/*     */                 
/* 133 */                 int j = b8 + b5 * 4;
/* 134 */                 int k = b7 + b6 * 8;
/* 135 */                 int m = b9 + b * 4;
/* 136 */                 paramChunkSnapshot.a(j, k, m, iBlockData);
/* 137 */                 d16 += d17;
/*     */               } 
/* 139 */               d11 += d13;
/* 140 */               d12 += d14;
/*     */             } 
/*     */             
/* 143 */             d2 += d6;
/* 144 */             d3 += d7;
/* 145 */             d4 += d8;
/* 146 */             d5 += d9;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void b(int paramInt1, int paramInt2, ChunkSnapshot paramChunkSnapshot) {
/* 154 */     int i = this.n.getSeaLevel() + 1;
/*     */     
/* 156 */     double d = 0.03125D;
/* 157 */     this.q = this.x.a(this.q, paramInt1 * 16, paramInt2 * 16, 0, 16, 16, 1, 0.03125D, 0.03125D, 1.0D);
/* 158 */     this.r = this.x.a(this.r, paramInt1 * 16, 109, paramInt2 * 16, 16, 1, 16, 0.03125D, 1.0D, 0.03125D);
/* 159 */     this.s = this.y.a(this.s, paramInt1 * 16, paramInt2 * 16, 0, 16, 16, 1, 0.0625D, 0.0625D, 0.0625D);
/*     */     
/* 161 */     for (byte b = 0; b < 16; b++) {
/* 162 */       for (byte b1 = 0; b1 < 16; b1++) {
/* 163 */         boolean bool1 = (this.q[b + b1 * 16] + this.p.nextDouble() * 0.2D > 0.0D) ? true : false;
/* 164 */         boolean bool2 = (this.r[b + b1 * 16] + this.p.nextDouble() * 0.2D > 0.0D) ? true : false;
/* 165 */         int j = (int)(this.s[b + b1 * 16] / 3.0D + 3.0D + this.p.nextDouble() * 0.25D);
/*     */         
/* 167 */         int k = -1;
/*     */         
/* 169 */         IBlockData iBlockData1 = b;
/* 170 */         IBlockData iBlockData2 = b;
/*     */         
/* 172 */         for (byte b2 = 127; b2 >= 0; b2--) {
/* 173 */           if (b2 >= 127 - this.p.nextInt(5) || b2 <= this.p.nextInt(5)) {
/* 174 */             paramChunkSnapshot.a(b1, b2, b, c);
/*     */           } else {
/* 176 */             IBlockData iBlockData = paramChunkSnapshot.a(b1, b2, b);
/* 177 */             if (iBlockData.getBlock() == null || iBlockData.getMaterial() == Material.AIR) {
/* 178 */               k = -1;
/* 179 */             } else if (iBlockData.getBlock() == Blocks.NETHERRACK) {
/* 180 */               if (k == -1) {
/* 181 */                 if (j <= 0) {
/* 182 */                   iBlockData1 = a;
/* 183 */                   iBlockData2 = b;
/* 184 */                 } else if (b2 >= i - 4 && b2 <= i + 1) {
/* 185 */                   iBlockData1 = b;
/* 186 */                   iBlockData2 = b;
/* 187 */                   if (bool2) {
/* 188 */                     iBlockData1 = e;
/* 189 */                     iBlockData2 = b;
/*     */                   } 
/* 191 */                   if (bool1) {
/* 192 */                     iBlockData1 = f;
/* 193 */                     iBlockData2 = f;
/*     */                   } 
/*     */                 } 
/*     */                 
/* 197 */                 if (b2 < i && (iBlockData1 == null || iBlockData1.getMaterial() == Material.AIR)) {
/* 198 */                   iBlockData1 = d;
/*     */                 }
/*     */                 
/* 201 */                 k = j;
/* 202 */                 if (b2 >= i - 1) {
/* 203 */                   paramChunkSnapshot.a(b1, b2, b, iBlockData1);
/*     */                 } else {
/* 205 */                   paramChunkSnapshot.a(b1, b2, b, iBlockData2);
/*     */                 } 
/* 207 */               } else if (k > 0) {
/* 208 */                 k--;
/* 209 */                 paramChunkSnapshot.a(b1, b2, b, iBlockData2);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Chunk getOrCreateChunk(int paramInt1, int paramInt2) {
/* 220 */     this.p.setSeed(paramInt1 * 341873128712L + paramInt2 * 132897987541L);
/*     */     
/* 222 */     ChunkSnapshot chunkSnapshot = new ChunkSnapshot();
/*     */     
/* 224 */     a(paramInt1, paramInt2, chunkSnapshot);
/* 225 */     b(paramInt1, paramInt2, chunkSnapshot);
/*     */     
/* 227 */     this.J.a(this.n, paramInt1, paramInt2, chunkSnapshot);
/* 228 */     if (this.o) {
/* 229 */       this.I.a(this.n, paramInt1, paramInt2, chunkSnapshot);
/*     */     }
/*     */     
/* 232 */     Chunk chunk = new Chunk(this.n, chunkSnapshot, paramInt1, paramInt2);
/* 233 */     BiomeBase[] arrayOfBiomeBase = this.n.getWorldChunkManager().getBiomeBlock(null, paramInt1 * 16, paramInt2 * 16, 16, 16);
/* 234 */     byte[] arrayOfByte = chunk.getBiomeIndex();
/*     */     
/* 236 */     for (byte b = 0; b < arrayOfByte.length; b++) {
/* 237 */       arrayOfByte[b] = (byte)BiomeBase.a(arrayOfBiomeBase[b]);
/*     */     }
/*     */     
/* 240 */     chunk.m();
/*     */     
/* 242 */     return chunk;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private double[] a(double[] paramArrayOfdouble, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
/* 248 */     if (paramArrayOfdouble == null) {
/* 249 */       paramArrayOfdouble = new double[paramInt4 * paramInt5 * paramInt6];
/*     */     }
/*     */     
/* 252 */     double d1 = 684.412D;
/* 253 */     double d2 = 2053.236D;
/*     */     
/* 255 */     this.l = this.g.a(this.l, paramInt1, paramInt2, paramInt3, paramInt4, 1, paramInt6, 1.0D, 0.0D, 1.0D);
/* 256 */     this.m = this.h.a(this.m, paramInt1, paramInt2, paramInt3, paramInt4, 1, paramInt6, 100.0D, 0.0D, 100.0D);
/*     */     
/* 258 */     this.i = this.w.a(this.i, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, 8.555150000000001D, 34.2206D, 8.555150000000001D);
/* 259 */     this.j = this.u.a(this.j, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, 684.412D, 2053.236D, 684.412D);
/* 260 */     this.k = this.v.a(this.k, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, 684.412D, 2053.236D, 684.412D);
/*     */     
/* 262 */     byte b1 = 0;
/* 263 */     double[] arrayOfDouble = new double[paramInt5]; byte b2;
/* 264 */     for (b2 = 0; b2 < paramInt5; b2++) {
/* 265 */       arrayOfDouble[b2] = Math.cos(b2 * Math.PI * 6.0D / paramInt5) * 2.0D;
/*     */       
/* 267 */       double d = b2;
/* 268 */       if (b2 > paramInt5 / 2) {
/* 269 */         d = (paramInt5 - 1 - b2);
/*     */       }
/* 271 */       if (d < 4.0D) {
/* 272 */         d = 4.0D - d;
/* 273 */         arrayOfDouble[b2] = arrayOfDouble[b2] - d * d * d * 10.0D;
/*     */       } 
/*     */     } 
/*     */     
/* 277 */     for (b2 = 0; b2 < paramInt4; b2++) {
/* 278 */       for (byte b = 0; b < paramInt6; b++) {
/* 279 */         double d = 0.0D;
/*     */         
/* 281 */         for (byte b3 = 0; b3 < paramInt5; b3++) {
/* 282 */           double d7, d3 = arrayOfDouble[b3];
/* 283 */           double d4 = this.j[b1] / 512.0D;
/* 284 */           double d5 = this.k[b1] / 512.0D;
/* 285 */           double d6 = (this.i[b1] / 10.0D + 1.0D) / 2.0D;
/*     */ 
/*     */           
/* 288 */           if (d6 < 0.0D) {
/* 289 */             d7 = d4;
/* 290 */           } else if (d6 > 1.0D) {
/* 291 */             d7 = d5;
/*     */           } else {
/* 293 */             d7 = d4 + (d5 - d4) * d6;
/*     */           } 
/*     */           
/* 296 */           d7 -= d3;
/*     */           
/* 298 */           if (b3 > paramInt5 - 4) {
/* 299 */             double d8 = ((b3 - paramInt5 - 4) / 3.0F);
/* 300 */             d7 = d7 * (1.0D - d8) + -10.0D * d8;
/*     */           } 
/*     */           
/* 303 */           if (b3 < 0.0D) {
/* 304 */             double d8 = (0.0D - b3) / 4.0D;
/* 305 */             d8 = MathHelper.a(d8, 0.0D, 1.0D);
/* 306 */             d7 = d7 * (1.0D - d8) + -10.0D * d8;
/*     */           } 
/*     */           
/* 309 */           paramArrayOfdouble[b1] = d7;
/* 310 */           b1++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 315 */     return paramArrayOfdouble;
/*     */   }
/*     */ 
/*     */   
/*     */   public void recreateStructures(int paramInt1, int paramInt2) {
/* 320 */     BlockFalling.instaFall = true;
/* 321 */     int i = paramInt1 * 16;
/* 322 */     int j = paramInt2 * 16;
/* 323 */     BlockPosition blockPosition = new BlockPosition(i, 0, j);
/* 324 */     BiomeBase biomeBase = this.n.getBiome(blockPosition.a(16, 0, 16));
/*     */     
/* 326 */     ChunkCoordIntPair chunkCoordIntPair = new ChunkCoordIntPair(paramInt1, paramInt2);
/*     */     
/* 328 */     this.I.a(this.n, this.p, chunkCoordIntPair);
/*     */     int k;
/* 330 */     for (k = 0; k < 8; k++) {
/* 331 */       this.F.generate(this.n, this.p, blockPosition.a(this.p.nextInt(16) + 8, this.p.nextInt(120) + 4, this.p.nextInt(16) + 8));
/*     */     }
/*     */     
/* 334 */     for (k = 0; k < this.p.nextInt(this.p.nextInt(10) + 1) + 1; k++) {
/* 335 */       this.z.generate(this.n, this.p, blockPosition.a(this.p.nextInt(16) + 8, this.p.nextInt(120) + 4, this.p.nextInt(16) + 8));
/*     */     }
/*     */     
/* 338 */     for (k = 0; k < this.p.nextInt(this.p.nextInt(10) + 1); k++) {
/* 339 */       this.A.generate(this.n, this.p, blockPosition.a(this.p.nextInt(16) + 8, this.p.nextInt(120) + 4, this.p.nextInt(16) + 8));
/*     */     }
/*     */     
/* 342 */     for (k = 0; k < 10; k++) {
/* 343 */       this.B.generate(this.n, this.p, blockPosition.a(this.p.nextInt(16) + 8, this.p.nextInt(128), this.p.nextInt(16) + 8));
/*     */     }
/*     */     
/* 346 */     if (this.p.nextBoolean()) {
/* 347 */       this.G.generate(this.n, this.p, blockPosition.a(this.p.nextInt(16) + 8, this.p.nextInt(128), this.p.nextInt(16) + 8));
/*     */     }
/*     */     
/* 350 */     if (this.p.nextBoolean()) {
/* 351 */       this.H.generate(this.n, this.p, blockPosition.a(this.p.nextInt(16) + 8, this.p.nextInt(128), this.p.nextInt(16) + 8));
/*     */     }
/*     */     
/* 354 */     for (k = 0; k < 16; k++) {
/* 355 */       this.C.generate(this.n, this.p, blockPosition.a(this.p.nextInt(16), this.p.nextInt(108) + 10, this.p.nextInt(16)));
/*     */     }
/*     */     
/* 358 */     k = this.n.getSeaLevel() / 2 + 1; byte b;
/* 359 */     for (b = 0; b < 4; b++) {
/* 360 */       this.D.generate(this.n, this.p, blockPosition.a(this.p.nextInt(16), k - 5 + this.p.nextInt(10), this.p.nextInt(16)));
/*     */     }
/*     */     
/* 363 */     for (b = 0; b < 16; b++) {
/* 364 */       this.E.generate(this.n, this.p, blockPosition.a(this.p.nextInt(16), this.p.nextInt(108) + 10, this.p.nextInt(16)));
/*     */     }
/*     */     
/* 367 */     biomeBase.a(this.n, this.p, new BlockPosition(i, 0, j));
/*     */     
/* 369 */     BlockFalling.instaFall = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(Chunk paramChunk, int paramInt1, int paramInt2) {
/* 374 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<BiomeBase.BiomeMeta> getMobsFor(EnumCreatureType paramEnumCreatureType, BlockPosition paramBlockPosition) {
/* 380 */     if (paramEnumCreatureType == EnumCreatureType.MONSTER) {
/* 381 */       if (this.I.b(paramBlockPosition)) {
/* 382 */         return this.I.b();
/*     */       }
/* 384 */       if (this.I.a(this.n, paramBlockPosition) && this.n.getType(paramBlockPosition.down()).getBlock() == Blocks.NETHER_BRICK) {
/* 385 */         return this.I.b();
/*     */       }
/*     */     } 
/*     */     
/* 389 */     BiomeBase biomeBase = this.n.getBiome(paramBlockPosition);
/* 390 */     return biomeBase.getMobs(paramEnumCreatureType);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockPosition findNearestMapFeature(World paramWorld, String paramString, BlockPosition paramBlockPosition, boolean paramBoolean) {
/* 396 */     if ("Fortress".equals(paramString) && this.I != null) {
/* 397 */       return this.I.getNearestGeneratedFeature(paramWorld, paramBlockPosition, paramBoolean);
/*     */     }
/* 399 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(World paramWorld, String paramString, BlockPosition paramBlockPosition) {
/* 404 */     if ("Fortress".equals(paramString) && this.I != null) {
/* 405 */       return this.I.b(paramBlockPosition);
/*     */     }
/* 407 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void recreateStructures(Chunk paramChunk, int paramInt1, int paramInt2) {
/* 412 */     this.I.a(this.n, paramInt1, paramInt2, (ChunkSnapshot)null);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChunkProviderHell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */