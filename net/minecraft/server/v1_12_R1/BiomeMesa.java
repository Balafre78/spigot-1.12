/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Random;
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
/*     */ public class BiomeMesa
/*     */   extends BiomeBase
/*     */ {
/*  20 */   protected static final IBlockData x = Blocks.DIRT.getBlockData().set(BlockDirt.VARIANT, BlockDirt.EnumDirtVariant.COARSE_DIRT);
/*  21 */   protected static final IBlockData y = Blocks.GRASS.getBlockData();
/*  22 */   protected static final IBlockData z = Blocks.HARDENED_CLAY.getBlockData();
/*  23 */   protected static final IBlockData A = Blocks.STAINED_HARDENED_CLAY.getBlockData();
/*  24 */   protected static final IBlockData B = A.set(BlockCloth.COLOR, EnumColor.ORANGE);
/*  25 */   protected static final IBlockData C = Blocks.SAND.getBlockData().set(BlockSand.VARIANT, BlockSand.EnumSandVariant.RED_SAND);
/*     */   
/*     */   private IBlockData[] D;
/*     */   
/*     */   private long E;
/*     */   
/*     */   private NoiseGenerator3 F;
/*     */   private NoiseGenerator3 G;
/*     */   private NoiseGenerator3 H;
/*     */   private final boolean I;
/*     */   private final boolean J;
/*     */   
/*     */   public BiomeMesa(boolean paramBoolean1, boolean paramBoolean2, BiomeBase.a parama) {
/*  38 */     super(parama);
/*  39 */     this.I = paramBoolean1;
/*  40 */     this.J = paramBoolean2;
/*     */ 
/*     */     
/*  43 */     this.u.clear();
/*  44 */     this.q = C;
/*  45 */     this.r = A;
/*     */     
/*  47 */     this.s.z = -999;
/*  48 */     this.s.D = 20;
/*  49 */     this.s.F = 3;
/*  50 */     this.s.G = 5;
/*  51 */     this.s.B = 0;
/*     */     
/*  53 */     this.u.clear();
/*     */     
/*  55 */     if (paramBoolean2) {
/*  56 */       this.s.z = 5;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected BiomeDecorator a() {
/*  62 */     return new a();
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldGenTreeAbstract a(Random paramRandom) {
/*  67 */     return m;
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
/*     */   public void a(World paramWorld, Random paramRandom, ChunkSnapshot paramChunkSnapshot, int paramInt1, int paramInt2, double paramDouble) {
/*  82 */     if (this.D == null || this.E != paramWorld.getSeed()) {
/*  83 */       a(paramWorld.getSeed());
/*     */     }
/*  85 */     if (this.F == null || this.G == null || this.E != paramWorld.getSeed()) {
/*  86 */       Random random = new Random(this.E);
/*  87 */       this.F = new NoiseGenerator3(random, 4);
/*  88 */       this.G = new NoiseGenerator3(random, 1);
/*     */     } 
/*  90 */     this.E = paramWorld.getSeed();
/*     */     
/*  92 */     double d = 0.0D;
/*  93 */     if (this.I) {
/*  94 */       int i1 = (paramInt1 & 0xFFFFFFF0) + (paramInt2 & 0xF);
/*  95 */       int i2 = (paramInt2 & 0xFFFFFFF0) + (paramInt1 & 0xF);
/*     */       
/*  97 */       double d1 = Math.min(Math.abs(paramDouble), this.F.a(i1 * 0.25D, i2 * 0.25D));
/*  98 */       if (d1 > 0.0D) {
/*  99 */         double d2 = 0.001953125D;
/* 100 */         double d3 = Math.abs(this.G.a(i1 * 0.001953125D, i2 * 0.001953125D));
/* 101 */         d = d1 * d1 * 2.5D;
/* 102 */         double d4 = Math.ceil(d3 * 50.0D) + 14.0D;
/* 103 */         if (d > d4) {
/* 104 */           d = d4;
/*     */         }
/* 106 */         d += 64.0D;
/*     */       } 
/*     */     } 
/*     */     
/* 110 */     int i = paramInt1 & 0xF;
/* 111 */     int j = paramInt2 & 0xF;
/*     */     
/* 113 */     int k = paramWorld.getSeaLevel();
/*     */     
/* 115 */     IBlockData iBlockData1 = A;
/* 116 */     IBlockData iBlockData2 = this.r;
/*     */     
/* 118 */     int m = (int)(paramDouble / 3.0D + 3.0D + paramRandom.nextDouble() * 0.25D);
/* 119 */     boolean bool1 = (Math.cos(paramDouble / 3.0D * Math.PI) > 0.0D) ? true : false;
/* 120 */     int n = -1;
/* 121 */     boolean bool2 = false;
/* 122 */     byte b = 0;
/*     */     
/* 124 */     for (char c = 'ÿ'; c >= '\000'; c--) {
/* 125 */       if (paramChunkSnapshot.a(j, c, i).getMaterial() == Material.AIR && c < (int)d) {
/* 126 */         paramChunkSnapshot.a(j, c, i, a);
/*     */       }
/*     */       
/* 129 */       if (c <= paramRandom.nextInt(5)) {
/* 130 */         paramChunkSnapshot.a(j, c, i, c);
/* 131 */       } else if (b < 15 || this.I) {
/* 132 */         IBlockData iBlockData = paramChunkSnapshot.a(j, c, i);
/*     */         
/* 134 */         if (iBlockData.getMaterial() == Material.AIR) {
/* 135 */           n = -1;
/* 136 */         } else if (iBlockData.getBlock() == Blocks.STONE) {
/* 137 */           if (n == -1) {
/* 138 */             bool2 = false;
/* 139 */             if (m <= 0) {
/* 140 */               iBlockData1 = b;
/* 141 */               iBlockData2 = a;
/* 142 */             } else if (c >= k - 4 && c <= k + 1) {
/* 143 */               iBlockData1 = A;
/* 144 */               iBlockData2 = this.r;
/*     */             } 
/*     */             
/* 147 */             if (c < k && (iBlockData1 == null || iBlockData1.getMaterial() == Material.AIR)) {
/* 148 */               iBlockData1 = h;
/*     */             }
/*     */             
/* 151 */             n = m + Math.max(0, c - k);
/* 152 */             if (c >= k - 1) {
/* 153 */               if (this.J && c > 86 + m * 2) {
/* 154 */                 if (bool1) {
/* 155 */                   paramChunkSnapshot.a(j, c, i, x);
/*     */                 } else {
/* 157 */                   paramChunkSnapshot.a(j, c, i, y);
/*     */                 } 
/* 159 */               } else if (c > k + 3 + m) {
/*     */                 IBlockData iBlockData3;
/* 161 */                 if (c < '@' || c > '') {
/* 162 */                   iBlockData3 = B;
/* 163 */                 } else if (bool1) {
/* 164 */                   iBlockData3 = z;
/*     */                 } else {
/* 166 */                   iBlockData3 = a(paramInt1, c, paramInt2);
/*     */                 } 
/* 168 */                 paramChunkSnapshot.a(j, c, i, iBlockData3);
/*     */               } else {
/* 170 */                 paramChunkSnapshot.a(j, c, i, this.q);
/* 171 */                 bool2 = true;
/*     */               } 
/*     */             } else {
/* 174 */               paramChunkSnapshot.a(j, c, i, iBlockData2);
/* 175 */               if (iBlockData2.getBlock() == Blocks.STAINED_HARDENED_CLAY) {
/* 176 */                 paramChunkSnapshot.a(j, c, i, B);
/*     */               }
/*     */             } 
/* 179 */           } else if (n > 0) {
/* 180 */             n--;
/*     */             
/* 182 */             if (bool2) {
/* 183 */               paramChunkSnapshot.a(j, c, i, B);
/*     */             } else {
/* 185 */               paramChunkSnapshot.a(j, c, i, a(paramInt1, c, paramInt2));
/*     */             } 
/*     */           } 
/* 188 */           b++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(long paramLong) {
/* 195 */     this.D = new IBlockData[64];
/* 196 */     Arrays.fill((Object[])this.D, z);
/*     */     
/* 198 */     Random random = new Random(paramLong);
/* 199 */     this.H = new NoiseGenerator3(random, 1);
/*     */     int i;
/* 201 */     for (i = 0; i < 64; i++) {
/* 202 */       i += random.nextInt(5) + 1;
/* 203 */       if (i < 64) {
/* 204 */         this.D[i] = B;
/*     */       }
/*     */     } 
/*     */     
/* 208 */     i = random.nextInt(4) + 2; int j;
/* 209 */     for (j = 0; j < i; j++) {
/* 210 */       int i1 = random.nextInt(3) + 1;
/* 211 */       int i2 = random.nextInt(64);
/*     */       
/* 213 */       for (byte b1 = 0; i2 + b1 < 64 && b1 < i1; b1++) {
/* 214 */         this.D[i2 + b1] = A.set(BlockCloth.COLOR, EnumColor.YELLOW);
/*     */       }
/*     */     } 
/* 217 */     j = random.nextInt(4) + 2; int k;
/* 218 */     for (k = 0; k < j; k++) {
/* 219 */       int i1 = random.nextInt(3) + 2;
/* 220 */       int i2 = random.nextInt(64);
/*     */       
/* 222 */       for (byte b1 = 0; i2 + b1 < 64 && b1 < i1; b1++) {
/* 223 */         this.D[i2 + b1] = A.set(BlockCloth.COLOR, EnumColor.BROWN);
/*     */       }
/*     */     } 
/* 226 */     k = random.nextInt(4) + 2; int m;
/* 227 */     for (m = 0; m < k; m++) {
/* 228 */       int i1 = random.nextInt(3) + 1;
/* 229 */       int i2 = random.nextInt(64);
/*     */       
/* 231 */       for (byte b1 = 0; i2 + b1 < 64 && b1 < i1; b1++) {
/* 232 */         this.D[i2 + b1] = A.set(BlockCloth.COLOR, EnumColor.RED);
/*     */       }
/*     */     } 
/* 235 */     m = random.nextInt(3) + 3;
/* 236 */     int n = 0;
/* 237 */     for (byte b = 0; b < m; b++) {
/* 238 */       boolean bool = true;
/* 239 */       n += random.nextInt(16) + 4;
/*     */       
/* 241 */       for (byte b1 = 0; n + b1 < 64 && b1 < 1; b1++) {
/* 242 */         this.D[n + b1] = A.set(BlockCloth.COLOR, EnumColor.WHITE);
/* 243 */         if (n + b1 > 1 && random.nextBoolean()) {
/* 244 */           this.D[n + b1 - 1] = A.set(BlockCloth.COLOR, EnumColor.SILVER);
/*     */         }
/* 246 */         if (n + b1 < 63 && random.nextBoolean()) {
/* 247 */           this.D[n + b1 + 1] = A.set(BlockCloth.COLOR, EnumColor.SILVER);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private IBlockData a(int paramInt1, int paramInt2, int paramInt3) {
/* 254 */     int i = (int)Math.round(this.H.a(paramInt1 / 512.0D, paramInt1 / 512.0D) * 2.0D);
/* 255 */     return this.D[(paramInt2 + i + 64) % 64];
/*     */   }
/*     */   
/*     */   class a extends BiomeDecorator { private a(BiomeMesa this$0) {}
/*     */     
/*     */     protected void a(World param1World, Random param1Random) {
/* 261 */       super.a(param1World, param1Random);
/*     */ 
/*     */       
/* 264 */       a(param1World, param1Random, 20, this.n, 32, 80);
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomeMesa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */