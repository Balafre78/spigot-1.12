/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GenLayerBiome
/*    */   extends GenLayer
/*    */ {
/*  9 */   private BiomeBase[] c = new BiomeBase[] { Biomes.d, Biomes.d, Biomes.d, Biomes.K, Biomes.K, Biomes.c };
/*    */ 
/*    */ 
/*    */   
/* 13 */   private final BiomeBase[] d = new BiomeBase[] { Biomes.f, Biomes.E, Biomes.e, Biomes.c, Biomes.C, Biomes.h };
/*    */ 
/*    */ 
/*    */   
/* 17 */   private final BiomeBase[] e = new BiomeBase[] { Biomes.f, Biomes.e, Biomes.g, Biomes.c };
/*    */ 
/*    */ 
/*    */   
/* 21 */   private final BiomeBase[] f = new BiomeBase[] { Biomes.n, Biomes.n, Biomes.n, Biomes.F };
/*    */ 
/*    */   
/*    */   private final CustomWorldSettingsFinal g;
/*    */ 
/*    */   
/*    */   public GenLayerBiome(long paramLong, GenLayer paramGenLayer, WorldType paramWorldType, CustomWorldSettingsFinal paramCustomWorldSettingsFinal) {
/* 28 */     super(paramLong);
/* 29 */     this.a = paramGenLayer;
/*    */     
/* 31 */     if (paramWorldType == WorldType.NORMAL_1_1) {
/* 32 */       this.c = new BiomeBase[] { Biomes.d, Biomes.f, Biomes.e, Biomes.h, Biomes.c, Biomes.g };
/*    */ 
/*    */       
/* 35 */       this.g = null;
/*    */     } else {
/* 37 */       this.g = paramCustomWorldSettingsFinal;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 43 */     int[] arrayOfInt1 = this.a.a(paramInt1, paramInt2, paramInt3, paramInt4);
/*    */     
/* 45 */     int[] arrayOfInt2 = IntCache.a(paramInt3 * paramInt4);
/* 46 */     for (byte b = 0; b < paramInt4; b++) {
/* 47 */       for (byte b1 = 0; b1 < paramInt3; b1++) {
/* 48 */         a((b1 + paramInt1), (b + paramInt2));
/* 49 */         int i = arrayOfInt1[b1 + b * paramInt3];
/* 50 */         int j = (i & 0xF00) >> 8;
/* 51 */         i &= 0xFFFFF0FF;
/* 52 */         if (this.g != null && this.g.G >= 0) {
/* 53 */           arrayOfInt2[b1 + b * paramInt3] = this.g.G;
/* 54 */         } else if (b(i)) {
/* 55 */           arrayOfInt2[b1 + b * paramInt3] = i;
/* 56 */         } else if (i == BiomeBase.a(Biomes.p)) {
/* 57 */           arrayOfInt2[b1 + b * paramInt3] = i;
/* 58 */         } else if (i == 1) {
/* 59 */           if (j > 0) {
/* 60 */             if (a(3) == 0) {
/* 61 */               arrayOfInt2[b1 + b * paramInt3] = BiomeBase.a(Biomes.O);
/*    */             } else {
/* 63 */               arrayOfInt2[b1 + b * paramInt3] = BiomeBase.a(Biomes.N);
/*    */             } 
/*    */           } else {
/* 66 */             arrayOfInt2[b1 + b * paramInt3] = BiomeBase.a(this.c[a(this.c.length)]);
/*    */           } 
/* 68 */         } else if (i == 2) {
/* 69 */           if (j > 0) {
/* 70 */             arrayOfInt2[b1 + b * paramInt3] = BiomeBase.a(Biomes.w);
/*    */           } else {
/* 72 */             arrayOfInt2[b1 + b * paramInt3] = BiomeBase.a(this.d[a(this.d.length)]);
/*    */           } 
/* 74 */         } else if (i == 3) {
/* 75 */           if (j > 0) {
/* 76 */             arrayOfInt2[b1 + b * paramInt3] = BiomeBase.a(Biomes.H);
/*    */           } else {
/* 78 */             arrayOfInt2[b1 + b * paramInt3] = BiomeBase.a(this.e[a(this.e.length)]);
/*    */           } 
/* 80 */         } else if (i == 4) {
/* 81 */           arrayOfInt2[b1 + b * paramInt3] = BiomeBase.a(this.f[a(this.f.length)]);
/*    */         } else {
/* 83 */           arrayOfInt2[b1 + b * paramInt3] = BiomeBase.a(Biomes.p);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 88 */     return arrayOfInt2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\GenLayerBiome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */