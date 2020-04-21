/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GenLayerMushroomShore
/*     */   extends GenLayer
/*     */ {
/*     */   public GenLayerMushroomShore(long paramLong, GenLayer paramGenLayer) {
/*  10 */     super(paramLong);
/*  11 */     this.a = paramGenLayer;
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*  16 */     int[] arrayOfInt1 = this.a.a(paramInt1 - 1, paramInt2 - 1, paramInt3 + 2, paramInt4 + 2);
/*     */     
/*  18 */     int[] arrayOfInt2 = IntCache.a(paramInt3 * paramInt4);
/*  19 */     for (byte b = 0; b < paramInt4; b++) {
/*  20 */       for (byte b1 = 0; b1 < paramInt3; b1++) {
/*  21 */         a((b1 + paramInt1), (b + paramInt2));
/*  22 */         int i = arrayOfInt1[b1 + 1 + (b + 1) * (paramInt3 + 2)];
/*  23 */         BiomeBase biomeBase = BiomeBase.getBiome(i);
/*  24 */         if (i == BiomeBase.a(Biomes.p)) {
/*  25 */           int j = arrayOfInt1[b1 + 1 + (b + 1 - 1) * (paramInt3 + 2)];
/*  26 */           int k = arrayOfInt1[b1 + 1 + 1 + (b + 1) * (paramInt3 + 2)];
/*  27 */           int m = arrayOfInt1[b1 + 1 - 1 + (b + 1) * (paramInt3 + 2)];
/*  28 */           int n = arrayOfInt1[b1 + 1 + (b + 1 + 1) * (paramInt3 + 2)];
/*  29 */           if (j == BiomeBase.a(Biomes.a) || k == BiomeBase.a(Biomes.a) || m == BiomeBase.a(Biomes.a) || n == BiomeBase.a(Biomes.a)) {
/*  30 */             arrayOfInt2[b1 + b * paramInt3] = BiomeBase.a(Biomes.q);
/*     */           } else {
/*  32 */             arrayOfInt2[b1 + b * paramInt3] = i;
/*     */           } 
/*  34 */         } else if (biomeBase != null && biomeBase.g() == BiomeJungle.class) {
/*  35 */           int j = arrayOfInt1[b1 + 1 + (b + 1 - 1) * (paramInt3 + 2)];
/*  36 */           int k = arrayOfInt1[b1 + 1 + 1 + (b + 1) * (paramInt3 + 2)];
/*  37 */           int m = arrayOfInt1[b1 + 1 - 1 + (b + 1) * (paramInt3 + 2)];
/*  38 */           int n = arrayOfInt1[b1 + 1 + (b + 1 + 1) * (paramInt3 + 2)];
/*  39 */           if (!c(j) || !c(k) || !c(m) || !c(n)) {
/*  40 */             arrayOfInt2[b1 + b * paramInt3] = BiomeBase.a(Biomes.y);
/*  41 */           } else if (b(j) || b(k) || b(m) || b(n)) {
/*  42 */             arrayOfInt2[b1 + b * paramInt3] = BiomeBase.a(Biomes.r);
/*     */           } else {
/*  44 */             arrayOfInt2[b1 + b * paramInt3] = i;
/*     */           } 
/*  46 */         } else if (i == BiomeBase.a(Biomes.e) || i == BiomeBase.a(Biomes.J) || i == BiomeBase.a(Biomes.v)) {
/*  47 */           a(arrayOfInt1, arrayOfInt2, b1, b, paramInt3, i, BiomeBase.a(Biomes.A));
/*  48 */         } else if (biomeBase != null && biomeBase.p()) {
/*  49 */           a(arrayOfInt1, arrayOfInt2, b1, b, paramInt3, i, BiomeBase.a(Biomes.B));
/*  50 */         } else if (i == BiomeBase.a(Biomes.M) || i == BiomeBase.a(Biomes.N)) {
/*  51 */           int j = arrayOfInt1[b1 + 1 + (b + 1 - 1) * (paramInt3 + 2)];
/*  52 */           int k = arrayOfInt1[b1 + 1 + 1 + (b + 1) * (paramInt3 + 2)];
/*  53 */           int m = arrayOfInt1[b1 + 1 - 1 + (b + 1) * (paramInt3 + 2)];
/*  54 */           int n = arrayOfInt1[b1 + 1 + (b + 1 + 1) * (paramInt3 + 2)];
/*  55 */           if (b(j) || b(k) || b(m) || b(n)) {
/*  56 */             arrayOfInt2[b1 + b * paramInt3] = i;
/*  57 */           } else if (!d(j) || !d(k) || !d(m) || !d(n)) {
/*  58 */             arrayOfInt2[b1 + b * paramInt3] = BiomeBase.a(Biomes.d);
/*     */           } else {
/*  60 */             arrayOfInt2[b1 + b * paramInt3] = i;
/*     */           } 
/*  62 */         } else if (i != BiomeBase.a(Biomes.a) && i != BiomeBase.a(Biomes.z) && i != BiomeBase.a(Biomes.i) && i != BiomeBase.a(Biomes.h)) {
/*  63 */           int j = arrayOfInt1[b1 + 1 + (b + 1 - 1) * (paramInt3 + 2)];
/*  64 */           int k = arrayOfInt1[b1 + 1 + 1 + (b + 1) * (paramInt3 + 2)];
/*  65 */           int m = arrayOfInt1[b1 + 1 - 1 + (b + 1) * (paramInt3 + 2)];
/*  66 */           int n = arrayOfInt1[b1 + 1 + (b + 1 + 1) * (paramInt3 + 2)];
/*  67 */           if (b(j) || b(k) || b(m) || b(n)) {
/*  68 */             arrayOfInt2[b1 + b * paramInt3] = BiomeBase.a(Biomes.r);
/*     */           } else {
/*  70 */             arrayOfInt2[b1 + b * paramInt3] = i;
/*     */           } 
/*     */         } else {
/*  73 */           arrayOfInt2[b1 + b * paramInt3] = i;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  78 */     return arrayOfInt2;
/*     */   }
/*     */   
/*     */   private void a(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
/*  82 */     if (b(paramInt4)) {
/*  83 */       paramArrayOfint2[paramInt1 + paramInt2 * paramInt3] = paramInt4;
/*     */       return;
/*     */     } 
/*  86 */     int i = paramArrayOfint1[paramInt1 + 1 + (paramInt2 + 1 - 1) * (paramInt3 + 2)];
/*  87 */     int j = paramArrayOfint1[paramInt1 + 1 + 1 + (paramInt2 + 1) * (paramInt3 + 2)];
/*  88 */     int k = paramArrayOfint1[paramInt1 + 1 - 1 + (paramInt2 + 1) * (paramInt3 + 2)];
/*  89 */     int m = paramArrayOfint1[paramInt1 + 1 + (paramInt2 + 1 + 1) * (paramInt3 + 2)];
/*  90 */     if (b(i) || b(j) || b(k) || b(m)) {
/*  91 */       paramArrayOfint2[paramInt1 + paramInt2 * paramInt3] = paramInt5;
/*     */     } else {
/*  93 */       paramArrayOfint2[paramInt1 + paramInt2 * paramInt3] = paramInt4;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean c(int paramInt) {
/*  98 */     if (BiomeBase.getBiome(paramInt) != null && BiomeBase.getBiome(paramInt).g() == BiomeJungle.class) {
/*  99 */       return true;
/*     */     }
/*     */     
/* 102 */     return (paramInt == BiomeBase.a(Biomes.y) || paramInt == BiomeBase.a(Biomes.w) || paramInt == BiomeBase.a(Biomes.x) || paramInt == BiomeBase.a(Biomes.f) || paramInt == BiomeBase.a(Biomes.g) || b(paramInt));
/*     */   }
/*     */   
/*     */   private boolean d(int paramInt) {
/* 106 */     return BiomeBase.getBiome(paramInt) instanceof BiomeMesa;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\GenLayerMushroomShore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */