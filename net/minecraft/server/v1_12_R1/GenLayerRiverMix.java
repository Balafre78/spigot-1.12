/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ public class GenLayerRiverMix
/*    */   extends GenLayer
/*    */ {
/*    */   private final GenLayer c;
/*    */   private final GenLayer d;
/*    */   
/*    */   public GenLayerRiverMix(long paramLong, GenLayer paramGenLayer1, GenLayer paramGenLayer2) {
/* 11 */     super(paramLong);
/* 12 */     this.c = paramGenLayer1;
/* 13 */     this.d = paramGenLayer2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(long paramLong) {
/* 18 */     this.c.a(paramLong);
/* 19 */     this.d.a(paramLong);
/* 20 */     super.a(paramLong);
/*    */   }
/*    */ 
/*    */   
/*    */   public int[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 25 */     int[] arrayOfInt1 = this.c.a(paramInt1, paramInt2, paramInt3, paramInt4);
/* 26 */     int[] arrayOfInt2 = this.d.a(paramInt1, paramInt2, paramInt3, paramInt4);
/*    */     
/* 28 */     int[] arrayOfInt3 = IntCache.a(paramInt3 * paramInt4);
/* 29 */     for (byte b = 0; b < paramInt3 * paramInt4; b++) {
/* 30 */       if (arrayOfInt1[b] == BiomeBase.a(Biomes.a) || arrayOfInt1[b] == BiomeBase.a(Biomes.z)) {
/* 31 */         arrayOfInt3[b] = arrayOfInt1[b];
/*    */       }
/* 33 */       else if (arrayOfInt2[b] == BiomeBase.a(Biomes.i)) {
/* 34 */         if (arrayOfInt1[b] == BiomeBase.a(Biomes.n)) {
/* 35 */           arrayOfInt3[b] = BiomeBase.a(Biomes.m);
/* 36 */         } else if (arrayOfInt1[b] == BiomeBase.a(Biomes.p) || arrayOfInt1[b] == BiomeBase.a(Biomes.q)) {
/* 37 */           arrayOfInt3[b] = BiomeBase.a(Biomes.q);
/*    */         } else {
/* 39 */           arrayOfInt3[b] = arrayOfInt2[b] & 0xFF;
/*    */         } 
/*    */       } else {
/* 42 */         arrayOfInt3[b] = arrayOfInt1[b];
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 47 */     return arrayOfInt3;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\GenLayerRiverMix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */