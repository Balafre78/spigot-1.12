/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class GenLayerTopSoil extends GenLayer {
/*    */   public GenLayerTopSoil(long paramLong, GenLayer paramGenLayer) {
/*  5 */     super(paramLong);
/*  6 */     this.a = paramGenLayer;
/*    */   }
/*    */ 
/*    */   
/*    */   public int[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 11 */     int i = paramInt1 - 1;
/* 12 */     int j = paramInt2 - 1;
/* 13 */     int k = paramInt3 + 2;
/* 14 */     int m = paramInt4 + 2;
/* 15 */     int[] arrayOfInt1 = this.a.a(i, j, k, m);
/*    */     
/* 17 */     int[] arrayOfInt2 = IntCache.a(paramInt3 * paramInt4);
/* 18 */     for (byte b = 0; b < paramInt4; b++) {
/* 19 */       for (byte b1 = 0; b1 < paramInt3; b1++) {
/* 20 */         int n = arrayOfInt1[b1 + 1 + (b + 1) * k];
/* 21 */         a((b1 + paramInt1), (b + paramInt2));
/* 22 */         if (n == 0) {
/* 23 */           arrayOfInt2[b1 + b * paramInt3] = 0;
/*    */         } else {
/* 25 */           int i1 = a(6);
/* 26 */           if (i1 == 0) {
/* 27 */             i1 = 4;
/* 28 */           } else if (i1 <= 1) {
/* 29 */             i1 = 3;
/*    */           } else {
/* 31 */             i1 = 1;
/*    */           } 
/* 33 */           arrayOfInt2[b1 + b * paramInt3] = i1;
/*    */         } 
/*    */       } 
/*    */     } 
/* 37 */     return arrayOfInt2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\GenLayerTopSoil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */