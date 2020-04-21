/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class LayerIsland extends GenLayer {
/*    */   public LayerIsland(long paramLong) {
/*  5 */     super(paramLong);
/*    */   }
/*    */ 
/*    */   
/*    */   public int[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 10 */     int[] arrayOfInt = IntCache.a(paramInt3 * paramInt4);
/* 11 */     for (byte b = 0; b < paramInt4; b++) {
/* 12 */       for (byte b1 = 0; b1 < paramInt3; b1++) {
/* 13 */         a((paramInt1 + b1), (paramInt2 + b));
/* 14 */         arrayOfInt[b1 + b * paramInt3] = (a(10) == 0) ? 1 : 0;
/*    */       } 
/*    */     } 
/*    */     
/* 18 */     if (paramInt1 > -paramInt3 && paramInt1 <= 0 && paramInt2 > -paramInt4 && paramInt2 <= 0) {
/* 19 */       arrayOfInt[-paramInt1 + -paramInt2 * paramInt3] = 1;
/*    */     }
/* 21 */     return arrayOfInt;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LayerIsland.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */