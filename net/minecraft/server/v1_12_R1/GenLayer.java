/*     */ package net.minecraft.server.v1_12_R1;
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
/*     */ public abstract class GenLayer
/*     */ {
/*     */   private long c;
/*     */   protected GenLayer a;
/*     */   private long d;
/*     */   protected long b;
/*     */   
/*     */   public static GenLayer[] a(long paramLong, WorldType paramWorldType, CustomWorldSettingsFinal paramCustomWorldSettingsFinal) {
/*  27 */     LayerIsland layerIsland = new LayerIsland(1L);
/*  28 */     GenLayerZoomFuzzy genLayerZoomFuzzy = new GenLayerZoomFuzzy(2000L, layerIsland);
/*  29 */     GenLayerIsland genLayerIsland4 = new GenLayerIsland(1L, genLayerZoomFuzzy);
/*  30 */     GenLayerZoom genLayerZoom2 = new GenLayerZoom(2001L, genLayerIsland4);
/*  31 */     GenLayerIsland genLayerIsland3 = new GenLayerIsland(2L, genLayerZoom2);
/*  32 */     genLayerIsland3 = new GenLayerIsland(50L, genLayerIsland3);
/*  33 */     genLayerIsland3 = new GenLayerIsland(70L, genLayerIsland3);
/*  34 */     GenLayerIcePlains genLayerIcePlains = new GenLayerIcePlains(2L, genLayerIsland3);
/*  35 */     GenLayerTopSoil genLayerTopSoil = new GenLayerTopSoil(2L, genLayerIcePlains);
/*  36 */     GenLayerIsland genLayerIsland2 = new GenLayerIsland(3L, genLayerTopSoil);
/*  37 */     GenLayerSpecial genLayerSpecial = new GenLayerSpecial(2L, genLayerIsland2, GenLayerSpecial.EnumGenLayerSpecial.COOL_WARM);
/*  38 */     genLayerSpecial = new GenLayerSpecial(2L, genLayerSpecial, GenLayerSpecial.EnumGenLayerSpecial.HEAT_ICE);
/*  39 */     genLayerSpecial = new GenLayerSpecial(3L, genLayerSpecial, GenLayerSpecial.EnumGenLayerSpecial.SPECIAL);
/*  40 */     GenLayerZoom genLayerZoom1 = new GenLayerZoom(2002L, genLayerSpecial);
/*  41 */     genLayerZoom1 = new GenLayerZoom(2003L, genLayerZoom1);
/*  42 */     GenLayerIsland genLayerIsland1 = new GenLayerIsland(4L, genLayerZoom1);
/*  43 */     GenLayerMushroomIsland genLayerMushroomIsland = new GenLayerMushroomIsland(5L, genLayerIsland1);
/*  44 */     GenLayerDeepOcean genLayerDeepOcean = new GenLayerDeepOcean(4L, genLayerMushroomIsland);
/*  45 */     GenLayer genLayer1 = GenLayerZoom.b(1000L, genLayerDeepOcean, 0);
/*     */     
/*  47 */     int i = 4;
/*  48 */     int j = i;
/*  49 */     if (paramCustomWorldSettingsFinal != null) {
/*  50 */       i = paramCustomWorldSettingsFinal.H;
/*  51 */       j = paramCustomWorldSettingsFinal.I;
/*     */     } 
/*  53 */     if (paramWorldType == WorldType.LARGE_BIOMES) {
/*  54 */       i = 6;
/*     */     }
/*     */     
/*  57 */     GenLayer genLayer2 = genLayer1;
/*  58 */     genLayer2 = GenLayerZoom.b(1000L, genLayer2, 0);
/*  59 */     genLayer2 = new GenLayerCleaner(100L, genLayer2);
/*     */     
/*  61 */     GenLayer genLayer3 = genLayer1;
/*  62 */     genLayer3 = new GenLayerBiome(200L, genLayer3, paramWorldType, paramCustomWorldSettingsFinal);
/*  63 */     genLayer3 = GenLayerZoom.b(1000L, genLayer3, 2);
/*  64 */     genLayer3 = new GenLayerDesert(1000L, genLayer3);
/*  65 */     GenLayer genLayer4 = genLayer2;
/*  66 */     genLayer4 = GenLayerZoom.b(1000L, genLayer4, 2);
/*  67 */     genLayer3 = new GenLayerRegionHills(1000L, genLayer3, genLayer4);
/*     */     
/*  69 */     genLayer2 = GenLayerZoom.b(1000L, genLayer2, 2);
/*  70 */     genLayer2 = GenLayerZoom.b(1000L, genLayer2, j);
/*  71 */     genLayer2 = new GenLayerRiver(1L, genLayer2);
/*  72 */     genLayer2 = new GenLayerSmooth(1000L, genLayer2);
/*     */     
/*  74 */     genLayer3 = new GenLayerPlains(1001L, genLayer3);
/*  75 */     for (byte b = 0; b < i; b++) {
/*  76 */       genLayer3 = new GenLayerZoom((1000 + b), genLayer3);
/*  77 */       if (b == 0) {
/*  78 */         genLayer3 = new GenLayerIsland(3L, genLayer3);
/*     */       }
/*     */       
/*  81 */       if (b == 1 || i == 1) {
/*  82 */         genLayer3 = new GenLayerMushroomShore(1000L, genLayer3);
/*     */       }
/*     */     } 
/*     */     
/*  86 */     genLayer3 = new GenLayerSmooth(1000L, genLayer3);
/*     */     
/*  88 */     genLayer3 = new GenLayerRiverMix(100L, genLayer3, genLayer2);
/*     */     
/*  90 */     GenLayer genLayer5 = genLayer3;
/*  91 */     GenLayerZoomVoronoi genLayerZoomVoronoi = new GenLayerZoomVoronoi(10L, genLayer3);
/*     */     
/*  93 */     genLayer3.a(paramLong);
/*  94 */     genLayerZoomVoronoi.a(paramLong);
/*     */     
/*  96 */     return new GenLayer[] { genLayer3, genLayerZoomVoronoi, genLayer5 };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GenLayer(long paramLong) {
/* 102 */     this.b = paramLong;
/* 103 */     this.b *= this.b * 6364136223846793005L + 1442695040888963407L;
/* 104 */     this.b += paramLong;
/* 105 */     this.b *= this.b * 6364136223846793005L + 1442695040888963407L;
/* 106 */     this.b += paramLong;
/* 107 */     this.b *= this.b * 6364136223846793005L + 1442695040888963407L;
/* 108 */     this.b += paramLong;
/*     */   }
/*     */   
/*     */   public void a(long paramLong) {
/* 112 */     this.c = paramLong;
/* 113 */     if (this.a != null) {
/* 114 */       this.a.a(paramLong);
/*     */     }
/* 116 */     this.c *= this.c * 6364136223846793005L + 1442695040888963407L;
/* 117 */     this.c += this.b;
/* 118 */     this.c *= this.c * 6364136223846793005L + 1442695040888963407L;
/* 119 */     this.c += this.b;
/* 120 */     this.c *= this.c * 6364136223846793005L + 1442695040888963407L;
/* 121 */     this.c += this.b;
/*     */   }
/*     */   
/*     */   public void a(long paramLong1, long paramLong2) {
/* 125 */     this.d = this.c;
/* 126 */     this.d *= this.d * 6364136223846793005L + 1442695040888963407L;
/* 127 */     this.d += paramLong1;
/* 128 */     this.d *= this.d * 6364136223846793005L + 1442695040888963407L;
/* 129 */     this.d += paramLong2;
/* 130 */     this.d *= this.d * 6364136223846793005L + 1442695040888963407L;
/* 131 */     this.d += paramLong1;
/* 132 */     this.d *= this.d * 6364136223846793005L + 1442695040888963407L;
/* 133 */     this.d += paramLong2;
/*     */   }
/*     */   
/*     */   protected int a(int paramInt) {
/* 137 */     int i = (int)((this.d >> 24L) % paramInt);
/* 138 */     if (i < 0) {
/* 139 */       i += paramInt;
/*     */     }
/* 141 */     this.d *= this.d * 6364136223846793005L + 1442695040888963407L;
/* 142 */     this.d += this.c;
/* 143 */     return i;
/*     */   }
/*     */   
/*     */   public abstract int[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*     */   
/*     */   protected static boolean a(int paramInt1, int paramInt2) {
/* 149 */     if (paramInt1 == paramInt2) {
/* 150 */       return true;
/*     */     }
/*     */     
/* 153 */     BiomeBase biomeBase1 = BiomeBase.getBiome(paramInt1);
/* 154 */     BiomeBase biomeBase2 = BiomeBase.getBiome(paramInt2);
/*     */     
/* 156 */     if (biomeBase1 == null || biomeBase2 == null) {
/* 157 */       return false;
/*     */     }
/*     */     
/* 160 */     if (biomeBase1 == Biomes.N || biomeBase1 == Biomes.O) {
/* 161 */       return (biomeBase2 == Biomes.N || biomeBase2 == Biomes.O);
/*     */     }
/*     */     
/* 164 */     return (biomeBase1 == biomeBase2 || biomeBase1.g() == biomeBase2.g());
/*     */   }
/*     */   
/*     */   protected static boolean b(int paramInt) {
/* 168 */     BiomeBase biomeBase = BiomeBase.getBiome(paramInt);
/* 169 */     return (biomeBase == Biomes.a || biomeBase == Biomes.z || biomeBase == Biomes.l);
/*     */   }
/*     */   
/*     */   protected int a(int... paramVarArgs) {
/* 173 */     return paramVarArgs[a(paramVarArgs.length)];
/*     */   }
/*     */   
/*     */   protected int b(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 177 */     if (paramInt2 == paramInt3 && paramInt3 == paramInt4) {
/* 178 */       return paramInt2;
/*     */     }
/* 180 */     if (paramInt1 == paramInt2 && paramInt1 == paramInt3) {
/* 181 */       return paramInt1;
/*     */     }
/* 183 */     if (paramInt1 == paramInt2 && paramInt1 == paramInt4) {
/* 184 */       return paramInt1;
/*     */     }
/* 186 */     if (paramInt1 == paramInt3 && paramInt1 == paramInt4) {
/* 187 */       return paramInt1;
/*     */     }
/*     */     
/* 190 */     if (paramInt1 == paramInt2 && paramInt3 != paramInt4) {
/* 191 */       return paramInt1;
/*     */     }
/* 193 */     if (paramInt1 == paramInt3 && paramInt2 != paramInt4) {
/* 194 */       return paramInt1;
/*     */     }
/* 196 */     if (paramInt1 == paramInt4 && paramInt2 != paramInt3) {
/* 197 */       return paramInt1;
/*     */     }
/*     */ 
/*     */     
/* 201 */     if (paramInt2 == paramInt3 && paramInt1 != paramInt4) {
/* 202 */       return paramInt2;
/*     */     }
/* 204 */     if (paramInt2 == paramInt4 && paramInt1 != paramInt3) {
/* 205 */       return paramInt2;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 210 */     if (paramInt3 == paramInt4 && paramInt1 != paramInt2) {
/* 211 */       return paramInt3;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 218 */     return a(new int[] { paramInt1, paramInt2, paramInt3, paramInt4 });
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\GenLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */