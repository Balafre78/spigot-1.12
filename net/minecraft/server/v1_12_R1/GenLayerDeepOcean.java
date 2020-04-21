/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ public class GenLayerDeepOcean
/*    */   extends GenLayer
/*    */ {
/*    */   public GenLayerDeepOcean(long paramLong, GenLayer paramGenLayer) {
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
/* 23 */         int n = arrayOfInt1[b1 + 1 + (b + 1 - 1) * (paramInt3 + 2)];
/* 24 */         int i1 = arrayOfInt1[b1 + 1 + 1 + (b + 1) * (paramInt3 + 2)];
/* 25 */         int i2 = arrayOfInt1[b1 + 1 - 1 + (b + 1) * (paramInt3 + 2)];
/* 26 */         int i3 = arrayOfInt1[b1 + 1 + (b + 1 + 1) * (paramInt3 + 2)];
/*    */         
/* 28 */         int i4 = arrayOfInt1[b1 + 1 + (b + 1) * k];
/* 29 */         byte b2 = 0;
/* 30 */         if (n == 0) {
/* 31 */           b2++;
/*    */         }
/* 33 */         if (i1 == 0) {
/* 34 */           b2++;
/*    */         }
/* 36 */         if (i2 == 0) {
/* 37 */           b2++;
/*    */         }
/* 39 */         if (i3 == 0) {
/* 40 */           b2++;
/*    */         }
/*    */         
/* 43 */         if (i4 == 0 && b2 > 3) {
/* 44 */           arrayOfInt2[b1 + b * paramInt3] = BiomeBase.a(Biomes.z);
/*    */         } else {
/* 46 */           arrayOfInt2[b1 + b * paramInt3] = i4;
/*    */         } 
/*    */       } 
/*    */     } 
/* 50 */     return arrayOfInt2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\GenLayerDeepOcean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */