/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ public class GenLayerMushroomIsland
/*    */   extends GenLayer
/*    */ {
/*    */   public GenLayerMushroomIsland(long paramLong, GenLayer paramGenLayer) {
/*  8 */     super(paramLong);
/*  9 */     this.a = paramGenLayer;
/*    */   }
/*    */ 
/*    */   
/*    */   public int[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 14 */     int i = paramInt1 - 1;
/* 15 */     int j = paramInt2 - 1;
/* 16 */     int k = paramInt3 + 2;
/* 17 */     int m = paramInt4 + 2;
/* 18 */     int[] arrayOfInt1 = this.a.a(i, j, k, m);
/*    */     
/* 20 */     int[] arrayOfInt2 = IntCache.a(paramInt3 * paramInt4);
/* 21 */     for (byte b = 0; b < paramInt4; b++) {
/* 22 */       for (byte b1 = 0; b1 < paramInt3; b1++) {
/* 23 */         int n = arrayOfInt1[b1 + 0 + (b + 0) * k];
/* 24 */         int i1 = arrayOfInt1[b1 + 2 + (b + 0) * k];
/* 25 */         int i2 = arrayOfInt1[b1 + 0 + (b + 2) * k];
/* 26 */         int i3 = arrayOfInt1[b1 + 2 + (b + 2) * k];
/* 27 */         int i4 = arrayOfInt1[b1 + 1 + (b + 1) * k];
/* 28 */         a((b1 + paramInt1), (b + paramInt2));
/* 29 */         if (i4 == 0 && n == 0 && i1 == 0 && i2 == 0 && i3 == 0 && a(100) == 0) {
/* 30 */           arrayOfInt2[b1 + b * paramInt3] = BiomeBase.a(Biomes.p);
/*    */         } else {
/* 32 */           arrayOfInt2[b1 + b * paramInt3] = i4;
/*    */         } 
/*    */       } 
/*    */     } 
/* 36 */     return arrayOfInt2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\GenLayerMushroomIsland.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */