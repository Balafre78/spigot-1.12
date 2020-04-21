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
/*     */ public class ChunkProviderTheEnd
/*     */   implements ChunkGenerator
/*     */ {
/*     */   private final Random h;
/*  34 */   protected static final IBlockData a = Blocks.END_STONE.getBlockData();
/*  35 */   protected static final IBlockData b = Blocks.AIR.getBlockData();
/*     */   
/*     */   private final NoiseGeneratorOctaves i;
/*     */   
/*     */   private final NoiseGeneratorOctaves j;
/*     */   
/*     */   private final NoiseGeneratorOctaves k;
/*     */   public NoiseGeneratorOctaves c;
/*     */   public NoiseGeneratorOctaves d;
/*     */   private final World l;
/*     */   private final boolean m;
/*     */   private final BlockPosition n;
/*  47 */   private final WorldGenEndCity o = new WorldGenEndCity(this);
/*     */ 
/*     */   
/*     */   private final NoiseGenerator3Handler p;
/*     */ 
/*     */   
/*     */   private double[] q;
/*     */ 
/*     */   
/*     */   private BiomeBase[] r;
/*     */ 
/*     */   
/*     */   double[] e;
/*     */ 
/*     */   
/*     */   double[] f;
/*     */ 
/*     */   
/*     */   double[] g;
/*     */ 
/*     */   
/*     */   private final WorldGenEndIsland s;
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(int paramInt1, int paramInt2, ChunkSnapshot paramChunkSnapshot) {
/*  73 */     byte b1 = 2;
/*     */     
/*  75 */     byte b2 = 3;
/*  76 */     byte b3 = 33;
/*  77 */     byte b4 = 3;
/*  78 */     this.q = a(this.q, paramInt1 * 2, 0, paramInt2 * 2, 3, 33, 3);
/*     */     
/*  80 */     for (byte b5 = 0; b5 < 2; b5++) {
/*  81 */       for (byte b = 0; b < 2; b++) {
/*  82 */         for (byte b6 = 0; b6 < 32; b6++) {
/*  83 */           double d1 = 0.25D;
/*  84 */           double d2 = this.q[((b5 + 0) * 3 + b + 0) * 33 + b6 + 0];
/*  85 */           double d3 = this.q[((b5 + 0) * 3 + b + 1) * 33 + b6 + 0];
/*  86 */           double d4 = this.q[((b5 + 1) * 3 + b + 0) * 33 + b6 + 0];
/*  87 */           double d5 = this.q[((b5 + 1) * 3 + b + 1) * 33 + b6 + 0];
/*     */           
/*  89 */           double d6 = (this.q[((b5 + 0) * 3 + b + 0) * 33 + b6 + 1] - d2) * 0.25D;
/*  90 */           double d7 = (this.q[((b5 + 0) * 3 + b + 1) * 33 + b6 + 1] - d3) * 0.25D;
/*  91 */           double d8 = (this.q[((b5 + 1) * 3 + b + 0) * 33 + b6 + 1] - d4) * 0.25D;
/*  92 */           double d9 = (this.q[((b5 + 1) * 3 + b + 1) * 33 + b6 + 1] - d5) * 0.25D;
/*     */           
/*  94 */           for (byte b7 = 0; b7 < 4; b7++) {
/*  95 */             double d10 = 0.125D;
/*     */             
/*  97 */             double d11 = d2;
/*  98 */             double d12 = d3;
/*  99 */             double d13 = (d4 - d2) * 0.125D;
/* 100 */             double d14 = (d5 - d3) * 0.125D;
/*     */             
/* 102 */             for (byte b8 = 0; b8 < 8; b8++) {
/* 103 */               double d15 = 0.125D;
/*     */               
/* 105 */               double d16 = d11;
/* 106 */               double d17 = (d12 - d11) * 0.125D;
/* 107 */               for (byte b9 = 0; b9 < 8; b9++) {
/* 108 */                 IBlockData iBlockData = b;
/* 109 */                 if (d16 > 0.0D) {
/* 110 */                   iBlockData = a;
/*     */                 }
/*     */                 
/* 113 */                 int i = b8 + b5 * 8;
/* 114 */                 int j = b7 + b6 * 4;
/* 115 */                 int k = b9 + b * 8;
/* 116 */                 paramChunkSnapshot.a(i, j, k, iBlockData);
/* 117 */                 d16 += d17;
/*     */               } 
/* 119 */               d11 += d13;
/* 120 */               d12 += d14;
/*     */             } 
/*     */             
/* 123 */             d2 += d6;
/* 124 */             d3 += d7;
/* 125 */             d4 += d8;
/* 126 */             d5 += d9;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(ChunkSnapshot paramChunkSnapshot) {
/* 134 */     for (byte b = 0; b < 16; b++) {
/* 135 */       for (byte b1 = 0; b1 < 16; b1++) {
/* 136 */         boolean bool = true;
/* 137 */         byte b2 = -1;
/*     */         
/* 139 */         IBlockData iBlockData1 = a;
/* 140 */         IBlockData iBlockData2 = a;
/*     */         
/* 142 */         for (byte b3 = 127; b3 >= 0; b3--) {
/* 143 */           IBlockData iBlockData = paramChunkSnapshot.a(b, b3, b1);
/* 144 */           if (iBlockData.getMaterial() == Material.AIR) {
/* 145 */             b2 = -1;
/* 146 */           } else if (iBlockData.getBlock() == Blocks.STONE) {
/* 147 */             if (b2 == -1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 153 */               b2 = 1;
/* 154 */               if (b3 >= 0) {
/* 155 */                 paramChunkSnapshot.a(b, b3, b1, iBlockData1);
/*     */               } else {
/* 157 */                 paramChunkSnapshot.a(b, b3, b1, iBlockData2);
/*     */               } 
/* 159 */             } else if (b2 > 0) {
/* 160 */               b2--;
/* 161 */               paramChunkSnapshot.a(b, b3, b1, iBlockData2);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Chunk getOrCreateChunk(int paramInt1, int paramInt2) {
/* 173 */     this.h.setSeed(paramInt1 * 341873128712L + paramInt2 * 132897987541L);
/*     */     
/* 175 */     ChunkSnapshot chunkSnapshot = new ChunkSnapshot();
/* 176 */     this.r = this.l.getWorldChunkManager().getBiomeBlock(this.r, paramInt1 * 16, paramInt2 * 16, 16, 16);
/*     */     
/* 178 */     a(paramInt1, paramInt2, chunkSnapshot);
/* 179 */     a(chunkSnapshot);
/*     */     
/* 181 */     if (this.m) {
/* 182 */       this.o.a(this.l, paramInt1, paramInt2, chunkSnapshot);
/*     */     }
/*     */     
/* 185 */     Chunk chunk = new Chunk(this.l, chunkSnapshot, paramInt1, paramInt2);
/* 186 */     byte[] arrayOfByte = chunk.getBiomeIndex();
/*     */     
/* 188 */     for (byte b = 0; b < arrayOfByte.length; b++) {
/* 189 */       arrayOfByte[b] = (byte)BiomeBase.a(this.r[b]);
/*     */     }
/*     */     
/* 192 */     chunk.initLighting();
/*     */     
/* 194 */     return chunk;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 201 */     float f1 = (paramInt1 * 2 + paramInt3);
/* 202 */     float f2 = (paramInt2 * 2 + paramInt4);
/* 203 */     float f3 = 100.0F - MathHelper.c(f1 * f1 + f2 * f2) * 8.0F;
/* 204 */     if (f3 > 80.0F) {
/* 205 */       f3 = 80.0F;
/*     */     }
/* 207 */     if (f3 < -100.0F) {
/* 208 */       f3 = -100.0F;
/*     */     }
/*     */ 
/*     */     
/* 212 */     for (byte b = -12; b <= 12; b++) {
/* 213 */       for (byte b1 = -12; b1 <= 12; b1++) {
/* 214 */         long l1 = (paramInt1 + b);
/* 215 */         long l2 = (paramInt2 + b1);
/* 216 */         if (l1 * l1 + l2 * l2 > 4096L && this.p.a(l1, l2) < -0.8999999761581421D) {
/* 217 */           float f4 = (MathHelper.e((float)l1) * 3439.0F + MathHelper.e((float)l2) * 147.0F) % 13.0F + 9.0F;
/* 218 */           f1 = (paramInt3 - b * 2);
/* 219 */           f2 = (paramInt4 - b1 * 2);
/* 220 */           float f5 = 100.0F - MathHelper.c(f1 * f1 + f2 * f2) * f4;
/* 221 */           if (f5 > 80.0F) {
/* 222 */             f5 = 80.0F;
/*     */           }
/* 224 */           if (f5 < -100.0F) {
/* 225 */             f5 = -100.0F;
/*     */           }
/* 227 */           if (f5 > f3) {
/* 228 */             f3 = f5;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 234 */     return f3;
/*     */   }
/*     */   
/*     */   public boolean c(int paramInt1, int paramInt2) {
/* 238 */     return (paramInt1 * paramInt1 + paramInt2 * paramInt2 > 4096L && a(paramInt1, paramInt2, 1, 1) >= 0.0F);
/*     */   }
/*     */   
/*     */   private double[] a(double[] paramArrayOfdouble, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
/* 242 */     if (paramArrayOfdouble == null) {
/* 243 */       paramArrayOfdouble = new double[paramInt4 * paramInt5 * paramInt6];
/*     */     }
/*     */     
/* 246 */     double d1 = 684.412D;
/* 247 */     double d2 = 684.412D;
/* 248 */     d1 *= 2.0D;
/*     */     
/* 250 */     this.e = this.k.a(this.e, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, d1 / 80.0D, 4.277575000000001D, d1 / 80.0D);
/* 251 */     this.f = this.i.a(this.f, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, d1, 684.412D, d1);
/* 252 */     this.g = this.j.a(this.g, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, d1, 684.412D, d1);
/*     */     
/* 254 */     int i = paramInt1 / 2;
/* 255 */     int j = paramInt3 / 2;
/*     */     
/* 257 */     byte b1 = 0;
/*     */     
/* 259 */     for (byte b2 = 0; b2 < paramInt4; b2++) {
/* 260 */       for (byte b = 0; b < paramInt6; b++) {
/* 261 */         float f = a(i, j, b2, b);
/*     */         
/* 263 */         for (byte b3 = 0; b3 < paramInt5; b3++) {
/* 264 */           double d6, d3 = this.f[b1] / 512.0D;
/* 265 */           double d4 = this.g[b1] / 512.0D;
/*     */ 
/*     */           
/* 268 */           double d5 = (this.e[b1] / 10.0D + 1.0D) / 2.0D;
/* 269 */           if (d5 < 0.0D) {
/* 270 */             d6 = d3;
/* 271 */           } else if (d5 > 1.0D) {
/* 272 */             d6 = d4;
/*     */           } else {
/* 274 */             d6 = d3 + (d4 - d3) * d5;
/*     */           } 
/* 276 */           d6 -= 8.0D;
/*     */           
/* 278 */           d6 += f;
/*     */           
/* 280 */           byte b4 = 2;
/* 281 */           if (b3 > paramInt5 / 2 - b4) {
/* 282 */             double d = ((b3 - paramInt5 / 2 - b4) / 64.0F);
/* 283 */             d = MathHelper.a(d, 0.0D, 1.0D);
/* 284 */             d6 = d6 * (1.0D - d) + -3000.0D * d;
/*     */           } 
/* 286 */           b4 = 8;
/* 287 */           if (b3 < b4) {
/* 288 */             double d = ((b4 - b3) / (b4 - 1.0F));
/* 289 */             d6 = d6 * (1.0D - d) + -30.0D * d;
/*     */           } 
/*     */           
/* 292 */           paramArrayOfdouble[b1] = d6;
/* 293 */           b1++;
/*     */         } 
/*     */       } 
/*     */     } 
/* 297 */     return paramArrayOfdouble;
/*     */   }
/*     */   
/* 300 */   public ChunkProviderTheEnd(World paramWorld, boolean paramBoolean, long paramLong, BlockPosition paramBlockPosition) { this.s = new WorldGenEndIsland(); this.l = paramWorld; this.m = paramBoolean; this.n = paramBlockPosition; this.h = new Random(paramLong); this.i = new NoiseGeneratorOctaves(this.h, 16); this.j = new NoiseGeneratorOctaves(this.h, 16);
/*     */     this.k = new NoiseGeneratorOctaves(this.h, 8);
/*     */     this.c = new NoiseGeneratorOctaves(this.h, 10);
/*     */     this.d = new NoiseGeneratorOctaves(this.h, 16);
/* 304 */     this.p = new NoiseGenerator3Handler(this.h); } public void recreateStructures(int paramInt1, int paramInt2) { BlockFalling.instaFall = true;
/*     */     
/* 306 */     BlockPosition blockPosition = new BlockPosition(paramInt1 * 16, 0, paramInt2 * 16);
/* 307 */     if (this.m) {
/* 308 */       this.o.a(this.l, this.h, new ChunkCoordIntPair(paramInt1, paramInt2));
/*     */     }
/* 310 */     this.l.getBiome(blockPosition.a(16, 0, 16)).a(this.l, this.l.random, blockPosition);
/*     */     
/* 312 */     long l = paramInt1 * paramInt1 + paramInt2 * paramInt2;
/* 313 */     if (l > 4096L) {
/* 314 */       float f = a(paramInt1, paramInt2, 1, 1);
/*     */       
/* 316 */       if (f < -20.0F && this.h.nextInt(14) == 0) {
/* 317 */         this.s.generate(this.l, this.h, blockPosition.a(this.h.nextInt(16) + 8, 55 + this.h.nextInt(16), this.h.nextInt(16) + 8));
/*     */         
/* 319 */         if (this.h.nextInt(4) == 0) {
/* 320 */           this.s.generate(this.l, this.h, blockPosition.a(this.h.nextInt(16) + 8, 55 + this.h.nextInt(16), this.h.nextInt(16) + 8));
/*     */         }
/*     */       } 
/*     */       
/* 324 */       if (a(paramInt1, paramInt2, 1, 1) > 40.0F) {
/* 325 */         int i = this.h.nextInt(5); int j;
/* 326 */         for (j = 0; j < i; j++) {
/* 327 */           int k = this.h.nextInt(16) + 8;
/* 328 */           int m = this.h.nextInt(16) + 8;
/* 329 */           int n = this.l.getHighestBlockYAt(blockPosition.a(k, 0, m)).getY();
/* 330 */           if (n > 0) {
/* 331 */             int i1 = n - 1;
/*     */             
/* 333 */             if (this.l.isEmpty(blockPosition.a(k, i1 + 1, m)) && this.l.getType(blockPosition.a(k, i1, m)).getBlock() == Blocks.END_STONE) {
/* 334 */               BlockChorusFlower.a(this.l, blockPosition.a(k, i1 + 1, m), this.h, 8);
/*     */             }
/*     */           } 
/*     */         } 
/* 338 */         if (this.h.nextInt(700) == 0) {
/* 339 */           j = this.h.nextInt(16) + 8;
/* 340 */           int k = this.h.nextInt(16) + 8;
/* 341 */           int m = this.l.getHighestBlockYAt(blockPosition.a(j, 0, k)).getY();
/* 342 */           if (m > 0) {
/* 343 */             int n = m + 3 + this.h.nextInt(7);
/* 344 */             BlockPosition blockPosition1 = blockPosition.a(j, n, k);
/* 345 */             (new WorldGenEndGateway()).generate(this.l, this.h, blockPosition1);
/* 346 */             TileEntity tileEntity = this.l.getTileEntity(blockPosition1);
/* 347 */             if (tileEntity instanceof TileEntityEndGateway) {
/* 348 */               TileEntityEndGateway tileEntityEndGateway = (TileEntityEndGateway)tileEntity;
/* 349 */               tileEntityEndGateway.b(this.n);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 356 */     BlockFalling.instaFall = false; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(Chunk paramChunk, int paramInt1, int paramInt2) {
/* 361 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<BiomeBase.BiomeMeta> getMobsFor(EnumCreatureType paramEnumCreatureType, BlockPosition paramBlockPosition) {
/* 366 */     return this.l.getBiome(paramBlockPosition).getMobs(paramEnumCreatureType);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockPosition findNearestMapFeature(World paramWorld, String paramString, BlockPosition paramBlockPosition, boolean paramBoolean) {
/* 372 */     if ("EndCity".equals(paramString) && this.o != null) {
/* 373 */       return this.o.getNearestGeneratedFeature(paramWorld, paramBlockPosition, paramBoolean);
/*     */     }
/* 375 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(World paramWorld, String paramString, BlockPosition paramBlockPosition) {
/* 380 */     if ("EndCity".equals(paramString) && this.o != null) {
/* 381 */       return this.o.b(paramBlockPosition);
/*     */     }
/* 383 */     return false;
/*     */   }
/*     */   
/*     */   public void recreateStructures(Chunk paramChunk, int paramInt1, int paramInt2) {}
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChunkProviderTheEnd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */