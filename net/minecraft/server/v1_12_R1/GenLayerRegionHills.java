/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class GenLayerRegionHills
/*     */   extends GenLayer
/*     */ {
/*   9 */   private static final Logger c = LogManager.getLogger();
/*     */   private final GenLayer d;
/*     */   
/*     */   public GenLayerRegionHills(long paramLong, GenLayer paramGenLayer1, GenLayer paramGenLayer2) {
/*  13 */     super(paramLong);
/*  14 */     this.a = paramGenLayer1;
/*  15 */     this.d = paramGenLayer2;
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*  20 */     int[] arrayOfInt1 = this.a.a(paramInt1 - 1, paramInt2 - 1, paramInt3 + 2, paramInt4 + 2);
/*  21 */     int[] arrayOfInt2 = this.d.a(paramInt1 - 1, paramInt2 - 1, paramInt3 + 2, paramInt4 + 2);
/*     */     
/*  23 */     int[] arrayOfInt3 = IntCache.a(paramInt3 * paramInt4);
/*  24 */     for (byte b = 0; b < paramInt4; b++) {
/*  25 */       for (byte b1 = 0; b1 < paramInt3; b1++) {
/*  26 */         a((b1 + paramInt1), (b + paramInt2));
/*  27 */         int i = arrayOfInt1[b1 + 1 + (b + 1) * (paramInt3 + 2)];
/*  28 */         int j = arrayOfInt2[b1 + 1 + (b + 1) * (paramInt3 + 2)];
/*  29 */         boolean bool1 = ((j - 2) % 29 == 0) ? true : false;
/*  30 */         if (i > 255) {
/*  31 */           c.debug("old! {}", Integer.valueOf(i));
/*     */         }
/*     */         
/*  34 */         BiomeBase biomeBase = BiomeBase.a(i);
/*  35 */         boolean bool2 = (biomeBase != null && biomeBase.b()) ? true : false;
/*  36 */         if (i != 0 && j >= 2 && (j - 2) % 29 == 1 && !bool2) {
/*  37 */           BiomeBase biomeBase1 = BiomeBase.b(biomeBase);
/*  38 */           arrayOfInt3[b1 + b * paramInt3] = (biomeBase1 == null) ? i : BiomeBase.a(biomeBase1);
/*  39 */         } else if (a(3) == 0 || bool1) {
/*  40 */           BiomeBase biomeBase1 = biomeBase;
/*  41 */           if (biomeBase == Biomes.d) {
/*  42 */             biomeBase1 = Biomes.s;
/*  43 */           } else if (biomeBase == Biomes.f) {
/*  44 */             biomeBase1 = Biomes.t;
/*  45 */           } else if (biomeBase == Biomes.C) {
/*  46 */             biomeBase1 = Biomes.D;
/*  47 */           } else if (biomeBase == Biomes.E) {
/*  48 */             biomeBase1 = Biomes.c;
/*  49 */           } else if (biomeBase == Biomes.g) {
/*  50 */             biomeBase1 = Biomes.u;
/*  51 */           } else if (biomeBase == Biomes.H) {
/*  52 */             biomeBase1 = Biomes.I;
/*  53 */           } else if (biomeBase == Biomes.F) {
/*  54 */             biomeBase1 = Biomes.G;
/*  55 */           } else if (biomeBase == Biomes.c) {
/*  56 */             if (a(3) == 0) {
/*  57 */               biomeBase1 = Biomes.t;
/*     */             } else {
/*  59 */               biomeBase1 = Biomes.f;
/*     */             } 
/*  61 */           } else if (biomeBase == Biomes.n) {
/*  62 */             biomeBase1 = Biomes.o;
/*  63 */           } else if (biomeBase == Biomes.w) {
/*  64 */             biomeBase1 = Biomes.x;
/*  65 */           } else if (biomeBase == Biomes.a) {
/*  66 */             biomeBase1 = Biomes.z;
/*  67 */           } else if (biomeBase == Biomes.e) {
/*  68 */             biomeBase1 = Biomes.J;
/*  69 */           } else if (biomeBase == Biomes.K) {
/*  70 */             biomeBase1 = Biomes.L;
/*  71 */           } else if (a(i, BiomeBase.a(Biomes.N))) {
/*  72 */             biomeBase1 = Biomes.M;
/*  73 */           } else if (biomeBase == Biomes.z && 
/*  74 */             a(3) == 0) {
/*  75 */             int m = a(2);
/*  76 */             if (m == 0) {
/*  77 */               biomeBase1 = Biomes.c;
/*     */             } else {
/*  79 */               biomeBase1 = Biomes.f;
/*     */             } 
/*     */           } 
/*     */           
/*  83 */           int k = BiomeBase.a(biomeBase1);
/*  84 */           if (bool1 && k != i) {
/*  85 */             BiomeBase biomeBase2 = BiomeBase.b(biomeBase1);
/*  86 */             k = (biomeBase2 == null) ? i : BiomeBase.a(biomeBase2);
/*     */           } 
/*  88 */           if (k == i) {
/*  89 */             arrayOfInt3[b1 + b * paramInt3] = i;
/*     */           } else {
/*  91 */             int m = arrayOfInt1[b1 + 1 + (b + 0) * (paramInt3 + 2)];
/*  92 */             int n = arrayOfInt1[b1 + 2 + (b + 1) * (paramInt3 + 2)];
/*  93 */             int i1 = arrayOfInt1[b1 + 0 + (b + 1) * (paramInt3 + 2)];
/*  94 */             int i2 = arrayOfInt1[b1 + 1 + (b + 2) * (paramInt3 + 2)];
/*  95 */             byte b2 = 0;
/*  96 */             if (a(m, i)) {
/*  97 */               b2++;
/*     */             }
/*  99 */             if (a(n, i)) {
/* 100 */               b2++;
/*     */             }
/* 102 */             if (a(i1, i)) {
/* 103 */               b2++;
/*     */             }
/* 105 */             if (a(i2, i)) {
/* 106 */               b2++;
/*     */             }
/* 108 */             if (b2 >= 3) {
/* 109 */               arrayOfInt3[b1 + b * paramInt3] = k;
/*     */             } else {
/* 111 */               arrayOfInt3[b1 + b * paramInt3] = i;
/*     */             } 
/*     */           } 
/*     */         } else {
/* 115 */           arrayOfInt3[b1 + b * paramInt3] = i;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 120 */     return arrayOfInt3;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\GenLayerRegionHills.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */