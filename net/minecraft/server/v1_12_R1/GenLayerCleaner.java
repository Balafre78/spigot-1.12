/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class GenLayerCleaner extends GenLayer {
/*    */   public GenLayerCleaner(long paramLong, GenLayer paramGenLayer) {
/*  5 */     super(paramLong);
/*  6 */     this.a = paramGenLayer;
/*    */   }
/*    */ 
/*    */   
/*    */   public int[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 11 */     int[] arrayOfInt1 = this.a.a(paramInt1, paramInt2, paramInt3, paramInt4);
/*    */     
/* 13 */     int[] arrayOfInt2 = IntCache.a(paramInt3 * paramInt4);
/* 14 */     for (byte b = 0; b < paramInt4; b++) {
/* 15 */       for (byte b1 = 0; b1 < paramInt3; b1++) {
/* 16 */         a((b1 + paramInt1), (b + paramInt2));
/* 17 */         arrayOfInt2[b1 + b * paramInt3] = (arrayOfInt1[b1 + b * paramInt3] > 0) ? (a(299999) + 2) : 0;
/*    */       } 
/*    */     } 
/*    */     
/* 21 */     return arrayOfInt2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\GenLayerCleaner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */