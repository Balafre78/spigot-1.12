/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ public class GenLayerPlains
/*    */   extends GenLayer
/*    */ {
/*    */   public GenLayerPlains(long paramLong, GenLayer paramGenLayer) {
/*  8 */     super(paramLong);
/*  9 */     this.a = paramGenLayer;
/*    */   }
/*    */ 
/*    */   
/*    */   public int[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 14 */     int[] arrayOfInt1 = this.a.a(paramInt1 - 1, paramInt2 - 1, paramInt3 + 2, paramInt4 + 2);
/*    */     
/* 16 */     int[] arrayOfInt2 = IntCache.a(paramInt3 * paramInt4);
/* 17 */     for (byte b = 0; b < paramInt4; b++) {
/* 18 */       for (byte b1 = 0; b1 < paramInt3; b1++) {
/* 19 */         a((b1 + paramInt1), (b + paramInt2));
/* 20 */         int i = arrayOfInt1[b1 + 1 + (b + 1) * (paramInt3 + 2)];
/* 21 */         if (a(57) == 0) {
/* 22 */           if (i == BiomeBase.a(Biomes.c)) {
/* 23 */             arrayOfInt2[b1 + b * paramInt3] = BiomeBase.a(Biomes.Q);
/*    */           } else {
/* 25 */             arrayOfInt2[b1 + b * paramInt3] = i;
/*    */           } 
/*    */         } else {
/* 28 */           arrayOfInt2[b1 + b * paramInt3] = i;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 33 */     return arrayOfInt2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\GenLayerPlains.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */