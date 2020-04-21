/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class NoiseGenerator3Handler {
/*   6 */   private static final int[][] e = new int[][] { { 1, 1, 0 }, { -1, 1, 0 }, { 1, -1, 0 }, { -1, -1, 0 }, { 1, 0, 1 }, { -1, 0, 1 }, { 1, 0, -1 }, { -1, 0, -1 }, { 0, 1, 1 }, { 0, -1, 1 }, { 0, 1, -1 }, { 0, -1, -1 } };
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
/*  20 */   public static final double a = Math.sqrt(3.0D);
/*     */   
/*  22 */   private final int[] f = new int[512];
/*     */   public double b;
/*     */   public double c;
/*     */   public double d;
/*     */   
/*     */   public NoiseGenerator3Handler() {
/*  28 */     this(new Random());
/*     */   }
/*     */   
/*     */   public NoiseGenerator3Handler(Random paramRandom) {
/*  32 */     this.b = paramRandom.nextDouble() * 256.0D;
/*  33 */     this.c = paramRandom.nextDouble() * 256.0D;
/*  34 */     this.d = paramRandom.nextDouble() * 256.0D; byte b;
/*  35 */     for (b = 0; b < 'Ā'; b++) {
/*  36 */       this.f[b] = b;
/*     */     }
/*     */     
/*  39 */     for (b = 0; b < 'Ā'; b++) {
/*  40 */       int i = paramRandom.nextInt(256 - b) + b;
/*  41 */       int j = this.f[b];
/*  42 */       this.f[b] = this.f[i];
/*  43 */       this.f[i] = j;
/*     */       
/*  45 */       this.f[b + 256] = this.f[b];
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static int a(double paramDouble) {
/*  51 */     return (paramDouble > 0.0D) ? (int)paramDouble : ((int)paramDouble - 1);
/*     */   }
/*     */   
/*     */   private static double a(int[] paramArrayOfint, double paramDouble1, double paramDouble2) {
/*  55 */     return paramArrayOfint[0] * paramDouble1 + paramArrayOfint[1] * paramDouble2;
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
/*     */   public double a(double paramDouble1, double paramDouble2) {
/*     */     byte b1, b2;
/*  68 */     double d14, d16, d18, d1 = 0.5D * (a - 1.0D);
/*  69 */     double d2 = (paramDouble1 + paramDouble2) * d1;
/*  70 */     int i = a(paramDouble1 + d2);
/*  71 */     int j = a(paramDouble2 + d2);
/*  72 */     double d3 = (3.0D - a) / 6.0D;
/*  73 */     double d4 = (i + j) * d3;
/*  74 */     double d5 = i - d4;
/*  75 */     double d6 = j - d4;
/*  76 */     double d7 = paramDouble1 - d5;
/*  77 */     double d8 = paramDouble2 - d6;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     if (d7 > d8) {
/*  83 */       b1 = 1;
/*  84 */       b2 = 0;
/*     */     } else {
/*     */       
/*  87 */       b1 = 0;
/*  88 */       b2 = 1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  93 */     double d9 = d7 - b1 + d3;
/*  94 */     double d10 = d8 - b2 + d3;
/*  95 */     double d11 = d7 - 1.0D + 2.0D * d3;
/*  96 */     double d12 = d8 - 1.0D + 2.0D * d3;
/*     */     
/*  98 */     int k = i & 0xFF;
/*  99 */     int m = j & 0xFF;
/* 100 */     int n = this.f[k + this.f[m]] % 12;
/* 101 */     int i1 = this.f[k + b1 + this.f[m + b2]] % 12;
/* 102 */     int i2 = this.f[k + 1 + this.f[m + 1]] % 12;
/*     */     
/* 104 */     double d13 = 0.5D - d7 * d7 - d8 * d8;
/* 105 */     if (d13 < 0.0D) {
/* 106 */       d14 = 0.0D;
/*     */     } else {
/* 108 */       d13 *= d13;
/* 109 */       d14 = d13 * d13 * a(e[n], d7, d8);
/*     */     } 
/* 111 */     double d15 = 0.5D - d9 * d9 - d10 * d10;
/* 112 */     if (d15 < 0.0D) {
/* 113 */       d16 = 0.0D;
/*     */     } else {
/* 115 */       d15 *= d15;
/* 116 */       d16 = d15 * d15 * a(e[i1], d9, d10);
/*     */     } 
/* 118 */     double d17 = 0.5D - d11 * d11 - d12 * d12;
/* 119 */     if (d17 < 0.0D) {
/* 120 */       d18 = 0.0D;
/*     */     } else {
/* 122 */       d17 *= d17;
/* 123 */       d18 = d17 * d17 * a(e[i2], d11, d12);
/*     */     } 
/*     */ 
/*     */     
/* 127 */     return 70.0D * (d14 + d16 + d18);
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
/* 262 */   private static final double g = 0.5D * (a - 1.0D);
/* 263 */   private static final double h = (3.0D - a) / 6.0D;
/*     */   
/*     */   public void a(double[] paramArrayOfdouble, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2, double paramDouble3, double paramDouble4, double paramDouble5) {
/* 266 */     byte b1 = 0;
/* 267 */     for (byte b2 = 0; b2 < paramInt2; b2++) {
/* 268 */       double d = (paramDouble2 + b2) * paramDouble4 + this.c;
/* 269 */       for (byte b = 0; b < paramInt1; b++) {
/* 270 */         byte b3, b4; double d13, d15, d17, d1 = (paramDouble1 + b) * paramDouble3 + this.b;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 275 */         double d2 = (d1 + d) * g;
/* 276 */         int i = a(d1 + d2);
/* 277 */         int j = a(d + d2);
/* 278 */         double d3 = (i + j) * h;
/* 279 */         double d4 = i - d3;
/* 280 */         double d5 = j - d3;
/* 281 */         double d6 = d1 - d4;
/* 282 */         double d7 = d - d5;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 287 */         if (d6 > d7) {
/* 288 */           b3 = 1;
/* 289 */           b4 = 0;
/*     */         } else {
/*     */           
/* 292 */           b3 = 0;
/* 293 */           b4 = 1;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 298 */         double d8 = d6 - b3 + h;
/* 299 */         double d9 = d7 - b4 + h;
/* 300 */         double d10 = d6 - 1.0D + 2.0D * h;
/* 301 */         double d11 = d7 - 1.0D + 2.0D * h;
/*     */         
/* 303 */         int k = i & 0xFF;
/* 304 */         int m = j & 0xFF;
/* 305 */         int n = this.f[k + this.f[m]] % 12;
/* 306 */         int i1 = this.f[k + b3 + this.f[m + b4]] % 12;
/* 307 */         int i2 = this.f[k + 1 + this.f[m + 1]] % 12;
/*     */         
/* 309 */         double d12 = 0.5D - d6 * d6 - d7 * d7;
/* 310 */         if (d12 < 0.0D) {
/* 311 */           d13 = 0.0D;
/*     */         } else {
/* 313 */           d12 *= d12;
/* 314 */           d13 = d12 * d12 * a(e[n], d6, d7);
/*     */         } 
/* 316 */         double d14 = 0.5D - d8 * d8 - d9 * d9;
/* 317 */         if (d14 < 0.0D) {
/* 318 */           d15 = 0.0D;
/*     */         } else {
/* 320 */           d14 *= d14;
/* 321 */           d15 = d14 * d14 * a(e[i1], d8, d9);
/*     */         } 
/* 323 */         double d16 = 0.5D - d10 * d10 - d11 * d11;
/* 324 */         if (d16 < 0.0D) {
/* 325 */           d17 = 0.0D;
/*     */         } else {
/* 327 */           d16 *= d16;
/* 328 */           d17 = d16 * d16 * a(e[i2], d10, d11);
/*     */         } 
/*     */ 
/*     */         
/* 332 */         paramArrayOfdouble[b1++] = paramArrayOfdouble[b1++] + 70.0D * (d13 + d15 + d17) * paramDouble5;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NoiseGenerator3Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */