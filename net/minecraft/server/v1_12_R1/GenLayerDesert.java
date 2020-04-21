/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GenLayerDesert
/*     */   extends GenLayer
/*     */ {
/*     */   public GenLayerDesert(long paramLong, GenLayer paramGenLayer) {
/*   9 */     super(paramLong);
/*  10 */     this.a = paramGenLayer;
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*  15 */     int[] arrayOfInt1 = this.a.a(paramInt1 - 1, paramInt2 - 1, paramInt3 + 2, paramInt4 + 2);
/*     */     
/*  17 */     int[] arrayOfInt2 = IntCache.a(paramInt3 * paramInt4);
/*  18 */     for (byte b = 0; b < paramInt4; b++) {
/*  19 */       for (byte b1 = 0; b1 < paramInt3; b1++) {
/*  20 */         a((b1 + paramInt1), (b + paramInt2));
/*  21 */         int i = arrayOfInt1[b1 + 1 + (b + 1) * (paramInt3 + 2)];
/*  22 */         if (!a(arrayOfInt1, arrayOfInt2, b1, b, paramInt3, i, BiomeBase.a(Biomes.e), BiomeBase.a(Biomes.v)) && 
/*  23 */           !b(arrayOfInt1, arrayOfInt2, b1, b, paramInt3, i, BiomeBase.a(Biomes.N), BiomeBase.a(Biomes.M)) && 
/*  24 */           !b(arrayOfInt1, arrayOfInt2, b1, b, paramInt3, i, BiomeBase.a(Biomes.O), BiomeBase.a(Biomes.M)) && 
/*  25 */           !b(arrayOfInt1, arrayOfInt2, b1, b, paramInt3, i, BiomeBase.a(Biomes.H), BiomeBase.a(Biomes.g))) {
/*  26 */           if (i == BiomeBase.a(Biomes.d)) {
/*  27 */             int j = arrayOfInt1[b1 + 1 + (b + 1 - 1) * (paramInt3 + 2)];
/*  28 */             int k = arrayOfInt1[b1 + 1 + 1 + (b + 1) * (paramInt3 + 2)];
/*  29 */             int m = arrayOfInt1[b1 + 1 - 1 + (b + 1) * (paramInt3 + 2)];
/*  30 */             int n = arrayOfInt1[b1 + 1 + (b + 1 + 1) * (paramInt3 + 2)];
/*  31 */             if (j == BiomeBase.a(Biomes.n) || k == BiomeBase.a(Biomes.n) || m == BiomeBase.a(Biomes.n) || n == BiomeBase.a(Biomes.n)) {
/*  32 */               arrayOfInt2[b1 + b * paramInt3] = BiomeBase.a(Biomes.J);
/*     */             } else {
/*  34 */               arrayOfInt2[b1 + b * paramInt3] = i;
/*     */             } 
/*  36 */           } else if (i == BiomeBase.a(Biomes.h)) {
/*     */             
/*  38 */             int j = arrayOfInt1[b1 + 1 + (b + 1 - 1) * (paramInt3 + 2)];
/*  39 */             int k = arrayOfInt1[b1 + 1 + 1 + (b + 1) * (paramInt3 + 2)];
/*  40 */             int m = arrayOfInt1[b1 + 1 - 1 + (b + 1) * (paramInt3 + 2)];
/*  41 */             int n = arrayOfInt1[b1 + 1 + (b + 1 + 1) * (paramInt3 + 2)];
/*  42 */             if (j == BiomeBase.a(Biomes.d) || k == BiomeBase.a(Biomes.d) || m == BiomeBase.a(Biomes.d) || n == BiomeBase.a(Biomes.d) || j == BiomeBase.a(Biomes.F) || k == BiomeBase.a(Biomes.F) || m == BiomeBase.a(Biomes.F) || n == BiomeBase.a(Biomes.F) || j == BiomeBase.a(Biomes.n) || k == BiomeBase.a(Biomes.n) || m == BiomeBase.a(Biomes.n) || n == BiomeBase.a(Biomes.n)) {
/*  43 */               arrayOfInt2[b1 + b * paramInt3] = BiomeBase.a(Biomes.c);
/*  44 */             } else if (j == BiomeBase.a(Biomes.w) || n == BiomeBase.a(Biomes.w) || k == BiomeBase.a(Biomes.w) || m == BiomeBase.a(Biomes.w)) {
/*  45 */               arrayOfInt2[b1 + b * paramInt3] = BiomeBase.a(Biomes.y);
/*     */             } else {
/*  47 */               arrayOfInt2[b1 + b * paramInt3] = i;
/*     */             } 
/*     */           } else {
/*  50 */             arrayOfInt2[b1 + b * paramInt3] = i;
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*  55 */     return arrayOfInt2;
/*     */   }
/*     */   
/*     */   private boolean a(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
/*  59 */     if (a(paramInt4, paramInt5)) {
/*  60 */       int i = paramArrayOfint1[paramInt1 + 1 + (paramInt2 + 1 - 1) * (paramInt3 + 2)];
/*  61 */       int j = paramArrayOfint1[paramInt1 + 1 + 1 + (paramInt2 + 1) * (paramInt3 + 2)];
/*  62 */       int k = paramArrayOfint1[paramInt1 + 1 - 1 + (paramInt2 + 1) * (paramInt3 + 2)];
/*  63 */       int m = paramArrayOfint1[paramInt1 + 1 + (paramInt2 + 1 + 1) * (paramInt3 + 2)];
/*  64 */       if (!b(i, paramInt5) || !b(j, paramInt5) || !b(k, paramInt5) || !b(m, paramInt5)) {
/*  65 */         paramArrayOfint2[paramInt1 + paramInt2 * paramInt3] = paramInt6;
/*     */       } else {
/*  67 */         paramArrayOfint2[paramInt1 + paramInt2 * paramInt3] = paramInt4;
/*     */       } 
/*  69 */       return true;
/*     */     } 
/*  71 */     return false;
/*     */   }
/*     */   
/*     */   private boolean b(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
/*  75 */     if (paramInt4 == paramInt5) {
/*  76 */       int i = paramArrayOfint1[paramInt1 + 1 + (paramInt2 + 1 - 1) * (paramInt3 + 2)];
/*  77 */       int j = paramArrayOfint1[paramInt1 + 1 + 1 + (paramInt2 + 1) * (paramInt3 + 2)];
/*  78 */       int k = paramArrayOfint1[paramInt1 + 1 - 1 + (paramInt2 + 1) * (paramInt3 + 2)];
/*  79 */       int m = paramArrayOfint1[paramInt1 + 1 + (paramInt2 + 1 + 1) * (paramInt3 + 2)];
/*  80 */       if (!a(i, paramInt5) || !a(j, paramInt5) || !a(k, paramInt5) || !a(m, paramInt5)) {
/*  81 */         paramArrayOfint2[paramInt1 + paramInt2 * paramInt3] = paramInt6;
/*     */       } else {
/*  83 */         paramArrayOfint2[paramInt1 + paramInt2 * paramInt3] = paramInt4;
/*     */       } 
/*  85 */       return true;
/*     */     } 
/*  87 */     return false;
/*     */   }
/*     */   
/*     */   private boolean b(int paramInt1, int paramInt2) {
/*  91 */     if (a(paramInt1, paramInt2)) {
/*  92 */       return true;
/*     */     }
/*  94 */     BiomeBase biomeBase1 = BiomeBase.getBiome(paramInt1);
/*  95 */     BiomeBase biomeBase2 = BiomeBase.getBiome(paramInt2);
/*  96 */     if (biomeBase1 != null && biomeBase2 != null) {
/*  97 */       BiomeBase.EnumTemperature enumTemperature1 = biomeBase1.h();
/*  98 */       BiomeBase.EnumTemperature enumTemperature2 = biomeBase2.h();
/*  99 */       return (enumTemperature1 == enumTemperature2 || enumTemperature1 == BiomeBase.EnumTemperature.MEDIUM || enumTemperature2 == BiomeBase.EnumTemperature.MEDIUM);
/*     */     } 
/* 101 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\GenLayerDesert.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */