/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
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
/*     */ public class BiomeDecorator
/*     */ {
/*     */   protected boolean a;
/*     */   protected BlockPosition b;
/*     */   protected CustomWorldSettingsFinal c;
/*  32 */   protected WorldGenerator d = new WorldGenClay(4);
/*  33 */   protected WorldGenerator e = new WorldGenSand(Blocks.SAND, 7);
/*  34 */   protected WorldGenerator f = new WorldGenSand(Blocks.GRAVEL, 6);
/*     */   protected WorldGenerator g;
/*     */   protected WorldGenerator h;
/*     */   protected WorldGenerator i;
/*     */   protected WorldGenerator j;
/*     */   protected WorldGenerator k;
/*     */   protected WorldGenerator l;
/*     */   protected WorldGenerator m;
/*     */   protected WorldGenerator n;
/*     */   protected WorldGenerator o;
/*     */   protected WorldGenerator p;
/*     */   protected WorldGenerator q;
/*  46 */   protected WorldGenFlowers r = new WorldGenFlowers(Blocks.YELLOW_FLOWER, BlockFlowers.EnumFlowerVarient.DANDELION);
/*  47 */   protected WorldGenerator s = new WorldGenMushrooms(Blocks.BROWN_MUSHROOM);
/*  48 */   protected WorldGenerator t = new WorldGenMushrooms(Blocks.RED_MUSHROOM);
/*  49 */   protected WorldGenerator u = new WorldGenHugeMushroom();
/*  50 */   protected WorldGenerator v = new WorldGenReed();
/*  51 */   protected WorldGenerator w = new WorldGenCactus();
/*  52 */   protected WorldGenerator x = new WorldGenWaterLily();
/*     */   
/*     */   protected int y;
/*     */   protected int z;
/*  56 */   protected float A = 0.1F;
/*  57 */   protected int B = 2;
/*  58 */   protected int C = 1;
/*     */   protected int D;
/*     */   protected int E;
/*     */   protected int F;
/*     */   protected int G;
/*  63 */   protected int H = 1;
/*  64 */   protected int I = 3;
/*  65 */   protected int J = 1;
/*     */   protected int K;
/*     */   public boolean L = true;
/*     */   
/*     */   public void a(World paramWorld, Random paramRandom, BiomeBase paramBiomeBase, BlockPosition paramBlockPosition) {
/*  70 */     if (this.a) {
/*  71 */       throw new RuntimeException("Already decorating");
/*     */     }
/*  73 */     this.c = CustomWorldSettingsFinal.CustomWorldSettings.a(paramWorld.getWorldData().getGeneratorOptions()).b();
/*  74 */     this.b = paramBlockPosition;
/*     */     
/*  76 */     this.g = new WorldGenMinable(Blocks.DIRT.getBlockData(), this.c.J);
/*  77 */     this.h = new WorldGenMinable(Blocks.GRAVEL.getBlockData(), this.c.N);
/*  78 */     this.i = new WorldGenMinable(Blocks.STONE.getBlockData().set(BlockStone.VARIANT, BlockStone.EnumStoneVariant.GRANITE), this.c.R);
/*  79 */     this.j = new WorldGenMinable(Blocks.STONE.getBlockData().set(BlockStone.VARIANT, BlockStone.EnumStoneVariant.DIORITE), this.c.V);
/*  80 */     this.k = new WorldGenMinable(Blocks.STONE.getBlockData().set(BlockStone.VARIANT, BlockStone.EnumStoneVariant.ANDESITE), this.c.Z);
/*  81 */     this.l = new WorldGenMinable(Blocks.COAL_ORE.getBlockData(), this.c.ad);
/*  82 */     this.m = new WorldGenMinable(Blocks.IRON_ORE.getBlockData(), this.c.ah);
/*  83 */     this.n = new WorldGenMinable(Blocks.GOLD_ORE.getBlockData(), this.c.al);
/*  84 */     this.o = new WorldGenMinable(Blocks.REDSTONE_ORE.getBlockData(), this.c.ap);
/*  85 */     this.p = new WorldGenMinable(Blocks.DIAMOND_ORE.getBlockData(), this.c.at);
/*  86 */     this.q = new WorldGenMinable(Blocks.LAPIS_ORE.getBlockData(), this.c.ax);
/*     */     
/*  88 */     a(paramBiomeBase, paramWorld, paramRandom);
/*     */     
/*  90 */     this.a = false;
/*     */   }
/*     */   
/*     */   protected void a(BiomeBase paramBiomeBase, World paramWorld, Random paramRandom) {
/*  94 */     a(paramWorld, paramRandom);
/*     */     int i;
/*  96 */     for (i = 0; i < this.I; i++) {
/*  97 */       int k = paramRandom.nextInt(16) + 8;
/*  98 */       int m = paramRandom.nextInt(16) + 8;
/*  99 */       this.e.generate(paramWorld, paramRandom, paramWorld.q(this.b.a(k, 0, m)));
/*     */     } 
/*     */     
/* 102 */     for (i = 0; i < this.J; i++) {
/* 103 */       int k = paramRandom.nextInt(16) + 8;
/* 104 */       int m = paramRandom.nextInt(16) + 8;
/* 105 */       this.d.generate(paramWorld, paramRandom, paramWorld.q(this.b.a(k, 0, m)));
/*     */     } 
/*     */     
/* 108 */     for (i = 0; i < this.H; i++) {
/* 109 */       int k = paramRandom.nextInt(16) + 8;
/* 110 */       int m = paramRandom.nextInt(16) + 8;
/* 111 */       this.f.generate(paramWorld, paramRandom, paramWorld.q(this.b.a(k, 0, m)));
/*     */     } 
/*     */     
/* 114 */     i = this.z;
/* 115 */     if (paramRandom.nextFloat() < this.A) {
/* 116 */       i++;
/*     */     }
/*     */     int j;
/* 119 */     for (j = 0; j < i; j++) {
/* 120 */       int k = paramRandom.nextInt(16) + 8;
/* 121 */       int m = paramRandom.nextInt(16) + 8;
/*     */       
/* 123 */       WorldGenTreeAbstract worldGenTreeAbstract = paramBiomeBase.a(paramRandom);
/* 124 */       worldGenTreeAbstract.e();
/*     */       
/* 126 */       BlockPosition blockPosition = paramWorld.getHighestBlockYAt(this.b.a(k, 0, m));
/* 127 */       if (worldGenTreeAbstract.generate(paramWorld, paramRandom, blockPosition)) {
/* 128 */         worldGenTreeAbstract.a(paramWorld, paramRandom, blockPosition);
/*     */       }
/*     */     } 
/*     */     
/* 132 */     for (j = 0; j < this.K; j++) {
/* 133 */       int k = paramRandom.nextInt(16) + 8;
/* 134 */       int m = paramRandom.nextInt(16) + 8;
/* 135 */       this.u.generate(paramWorld, paramRandom, paramWorld.getHighestBlockYAt(this.b.a(k, 0, m)));
/*     */     } 
/*     */     
/* 138 */     for (j = 0; j < this.B; j++) {
/* 139 */       int k = paramRandom.nextInt(16) + 8;
/* 140 */       int m = paramRandom.nextInt(16) + 8;
/* 141 */       int n = paramWorld.getHighestBlockYAt(this.b.a(k, 0, m)).getY() + 32;
/* 142 */       if (n > 0) {
/* 143 */         int i1 = paramRandom.nextInt(n);
/*     */         
/* 145 */         BlockPosition blockPosition = this.b.a(k, i1, m);
/* 146 */         BlockFlowers.EnumFlowerVarient enumFlowerVarient = paramBiomeBase.a(paramRandom, blockPosition);
/* 147 */         BlockFlowers blockFlowers = enumFlowerVarient.a().a();
/* 148 */         if (blockFlowers.getBlockData().getMaterial() != Material.AIR) {
/* 149 */           this.r.a(blockFlowers, enumFlowerVarient);
/* 150 */           this.r.generate(paramWorld, paramRandom, blockPosition);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 155 */     for (j = 0; j < this.C; j++) {
/* 156 */       int k = paramRandom.nextInt(16) + 8;
/* 157 */       int m = paramRandom.nextInt(16) + 8;
/* 158 */       int n = paramWorld.getHighestBlockYAt(this.b.a(k, 0, m)).getY() * 2;
/* 159 */       if (n > 0) {
/* 160 */         int i1 = paramRandom.nextInt(n);
/*     */         
/* 162 */         paramBiomeBase.b(paramRandom).generate(paramWorld, paramRandom, this.b.a(k, i1, m));
/*     */       } 
/*     */     } 
/*     */     
/* 166 */     for (j = 0; j < this.D; j++) {
/* 167 */       int k = paramRandom.nextInt(16) + 8;
/* 168 */       int m = paramRandom.nextInt(16) + 8;
/* 169 */       int n = paramWorld.getHighestBlockYAt(this.b.a(k, 0, m)).getY() * 2;
/* 170 */       if (n > 0) {
/* 171 */         int i1 = paramRandom.nextInt(n);
/*     */         
/* 173 */         (new WorldGenDeadBush()).generate(paramWorld, paramRandom, this.b.a(k, i1, m));
/*     */       } 
/*     */     } 
/*     */     
/* 177 */     for (j = 0; j < this.y; j++) {
/* 178 */       int k = paramRandom.nextInt(16) + 8;
/* 179 */       int m = paramRandom.nextInt(16) + 8;
/* 180 */       int n = paramWorld.getHighestBlockYAt(this.b.a(k, 0, m)).getY() * 2;
/* 181 */       if (n > 0) {
/* 182 */         int i1 = paramRandom.nextInt(n);
/*     */         
/* 184 */         BlockPosition blockPosition = this.b.a(k, i1, m);
/* 185 */         while (blockPosition.getY() > 0) {
/* 186 */           BlockPosition blockPosition1 = blockPosition.down();
/* 187 */           if (!paramWorld.isEmpty(blockPosition1)) {
/*     */             break;
/*     */           }
/*     */           
/* 191 */           blockPosition = blockPosition1;
/*     */         } 
/* 193 */         this.x.generate(paramWorld, paramRandom, blockPosition);
/*     */       } 
/*     */     } 
/*     */     
/* 197 */     for (j = 0; j < this.E; j++) {
/* 198 */       if (paramRandom.nextInt(4) == 0) {
/* 199 */         int k = paramRandom.nextInt(16) + 8;
/* 200 */         int m = paramRandom.nextInt(16) + 8;
/* 201 */         BlockPosition blockPosition = paramWorld.getHighestBlockYAt(this.b.a(k, 0, m));
/* 202 */         this.s.generate(paramWorld, paramRandom, blockPosition);
/*     */       } 
/*     */       
/* 205 */       if (paramRandom.nextInt(8) == 0) {
/* 206 */         int k = paramRandom.nextInt(16) + 8;
/* 207 */         int m = paramRandom.nextInt(16) + 8;
/* 208 */         int n = paramWorld.getHighestBlockYAt(this.b.a(k, 0, m)).getY() * 2;
/* 209 */         if (n > 0) {
/* 210 */           int i1 = paramRandom.nextInt(n);
/* 211 */           BlockPosition blockPosition = this.b.a(k, i1, m);
/* 212 */           this.t.generate(paramWorld, paramRandom, blockPosition);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 217 */     if (paramRandom.nextInt(4) == 0) {
/* 218 */       j = paramRandom.nextInt(16) + 8;
/* 219 */       int k = paramRandom.nextInt(16) + 8;
/* 220 */       int m = paramWorld.getHighestBlockYAt(this.b.a(j, 0, k)).getY() * 2;
/* 221 */       if (m > 0) {
/* 222 */         int n = paramRandom.nextInt(m);
/* 223 */         this.s.generate(paramWorld, paramRandom, this.b.a(j, n, k));
/*     */       } 
/*     */     } 
/*     */     
/* 227 */     if (paramRandom.nextInt(8) == 0) {
/* 228 */       j = paramRandom.nextInt(16) + 8;
/* 229 */       int k = paramRandom.nextInt(16) + 8;
/* 230 */       int m = paramWorld.getHighestBlockYAt(this.b.a(j, 0, k)).getY() * 2;
/* 231 */       if (m > 0) {
/* 232 */         int n = paramRandom.nextInt(m);
/* 233 */         this.t.generate(paramWorld, paramRandom, this.b.a(j, n, k));
/*     */       } 
/*     */     } 
/*     */     
/* 237 */     for (j = 0; j < this.F; j++) {
/* 238 */       int k = paramRandom.nextInt(16) + 8;
/* 239 */       int m = paramRandom.nextInt(16) + 8;
/* 240 */       int n = paramWorld.getHighestBlockYAt(this.b.a(k, 0, m)).getY() * 2;
/* 241 */       if (n > 0) {
/* 242 */         int i1 = paramRandom.nextInt(n);
/* 243 */         this.v.generate(paramWorld, paramRandom, this.b.a(k, i1, m));
/*     */       } 
/*     */     } 
/*     */     
/* 247 */     for (j = 0; j < 10; j++) {
/* 248 */       int k = paramRandom.nextInt(16) + 8;
/* 249 */       int m = paramRandom.nextInt(16) + 8;
/* 250 */       int n = paramWorld.getHighestBlockYAt(this.b.a(k, 0, m)).getY() * 2;
/* 251 */       if (n > 0) {
/* 252 */         int i1 = paramRandom.nextInt(n);
/* 253 */         this.v.generate(paramWorld, paramRandom, this.b.a(k, i1, m));
/*     */       } 
/*     */     } 
/*     */     
/* 257 */     if (paramRandom.nextInt(32) == 0) {
/* 258 */       j = paramRandom.nextInt(16) + 8;
/* 259 */       int k = paramRandom.nextInt(16) + 8;
/* 260 */       int m = paramWorld.getHighestBlockYAt(this.b.a(j, 0, k)).getY() * 2;
/* 261 */       if (m > 0) {
/* 262 */         int n = paramRandom.nextInt(m);
/* 263 */         (new WorldGenPumpkin()).generate(paramWorld, paramRandom, this.b.a(j, n, k));
/*     */       } 
/*     */     } 
/*     */     
/* 267 */     for (j = 0; j < this.G; j++) {
/* 268 */       int k = paramRandom.nextInt(16) + 8;
/* 269 */       int m = paramRandom.nextInt(16) + 8;
/* 270 */       int n = paramWorld.getHighestBlockYAt(this.b.a(k, 0, m)).getY() * 2;
/* 271 */       if (n > 0) {
/* 272 */         int i1 = paramRandom.nextInt(n);
/* 273 */         this.w.generate(paramWorld, paramRandom, this.b.a(k, i1, m));
/*     */       } 
/*     */     } 
/*     */     
/* 277 */     if (this.L) {
/* 278 */       for (j = 0; j < 50; j++) {
/* 279 */         int k = paramRandom.nextInt(16) + 8;
/* 280 */         int m = paramRandom.nextInt(16) + 8;
/* 281 */         int n = paramRandom.nextInt(248) + 8;
/* 282 */         if (n > 0) {
/* 283 */           int i1 = paramRandom.nextInt(n);
/* 284 */           BlockPosition blockPosition = this.b.a(k, i1, m);
/* 285 */           (new WorldGenLiquids(Blocks.FLOWING_WATER)).generate(paramWorld, paramRandom, blockPosition);
/*     */         } 
/*     */       } 
/*     */       
/* 289 */       for (j = 0; j < 20; j++) {
/* 290 */         int k = paramRandom.nextInt(16) + 8;
/* 291 */         int m = paramRandom.nextInt(16) + 8;
/* 292 */         int n = paramRandom.nextInt(paramRandom.nextInt(paramRandom.nextInt(240) + 8) + 8);
/* 293 */         BlockPosition blockPosition = this.b.a(k, n, m);
/*     */         
/* 295 */         (new WorldGenLiquids(Blocks.FLOWING_LAVA)).generate(paramWorld, paramRandom, blockPosition);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void a(World paramWorld, Random paramRandom) {
/* 301 */     a(paramWorld, paramRandom, this.c.K, this.g, this.c.L, this.c.M);
/* 302 */     a(paramWorld, paramRandom, this.c.O, this.h, this.c.P, this.c.Q);
/* 303 */     a(paramWorld, paramRandom, this.c.W, this.j, this.c.X, this.c.Y);
/* 304 */     a(paramWorld, paramRandom, this.c.S, this.i, this.c.T, this.c.U);
/* 305 */     a(paramWorld, paramRandom, this.c.aa, this.k, this.c.ab, this.c.ac);
/* 306 */     a(paramWorld, paramRandom, this.c.ae, this.l, this.c.af, this.c.ag);
/* 307 */     a(paramWorld, paramRandom, this.c.ai, this.m, this.c.aj, this.c.ak);
/* 308 */     a(paramWorld, paramRandom, this.c.am, this.n, this.c.an, this.c.ao);
/* 309 */     a(paramWorld, paramRandom, this.c.aq, this.o, this.c.ar, this.c.as);
/* 310 */     a(paramWorld, paramRandom, this.c.au, this.p, this.c.av, this.c.aw);
/* 311 */     b(paramWorld, paramRandom, this.c.ay, this.q, this.c.az, this.c.aA);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(World paramWorld, Random paramRandom, int paramInt1, WorldGenerator paramWorldGenerator, int paramInt2, int paramInt3) {
/* 316 */     if (paramInt3 < paramInt2) {
/* 317 */       int i = paramInt2;
/* 318 */       paramInt2 = paramInt3;
/* 319 */       paramInt3 = i;
/* 320 */     } else if (paramInt3 == paramInt2) {
/* 321 */       if (paramInt2 < 255) {
/* 322 */         paramInt3++;
/*     */       } else {
/* 324 */         paramInt2--;
/*     */       } 
/*     */     } 
/*     */     
/* 328 */     for (byte b = 0; b < paramInt1; b++) {
/* 329 */       BlockPosition blockPosition = this.b.a(paramRandom.nextInt(16), paramRandom.nextInt(paramInt3 - paramInt2) + paramInt2, paramRandom.nextInt(16));
/* 330 */       paramWorldGenerator.generate(paramWorld, paramRandom, blockPosition);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void b(World paramWorld, Random paramRandom, int paramInt1, WorldGenerator paramWorldGenerator, int paramInt2, int paramInt3) {
/* 335 */     for (byte b = 0; b < paramInt1; b++) {
/* 336 */       BlockPosition blockPosition = this.b.a(paramRandom.nextInt(16), paramRandom.nextInt(paramInt3) + paramRandom.nextInt(paramInt3) + paramInt2 - paramInt3, paramRandom.nextInt(16));
/* 337 */       paramWorldGenerator.generate(paramWorld, paramRandom, blockPosition);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomeDecorator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */