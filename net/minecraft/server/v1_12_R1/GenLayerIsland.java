/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class GenLayerIsland extends GenLayer {
/*    */   public GenLayerIsland(long paramLong, GenLayer paramGenLayer) {
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
/* 20 */         int n = arrayOfInt1[b1 + 0 + (b + 0) * k];
/* 21 */         int i1 = arrayOfInt1[b1 + 2 + (b + 0) * k];
/* 22 */         int i2 = arrayOfInt1[b1 + 0 + (b + 2) * k];
/* 23 */         int i3 = arrayOfInt1[b1 + 2 + (b + 2) * k];
/* 24 */         int i4 = arrayOfInt1[b1 + 1 + (b + 1) * k];
/* 25 */         a((b1 + paramInt1), (b + paramInt2));
/* 26 */         if (i4 == 0 && (n != 0 || i1 != 0 || i2 != 0 || i3 != 0)) {
/* 27 */           byte b2 = 1;
/* 28 */           int i5 = 1;
/* 29 */           if (n != 0 && a(b2++) == 0) {
/* 30 */             i5 = n;
/*    */           }
/* 32 */           if (i1 != 0 && a(b2++) == 0) {
/* 33 */             i5 = i1;
/*    */           }
/* 35 */           if (i2 != 0 && a(b2++) == 0) {
/* 36 */             i5 = i2;
/*    */           }
/* 38 */           if (i3 != 0 && a(b2++) == 0) {
/* 39 */             i5 = i3;
/*    */           }
/* 41 */           if (a(3) == 0) {
/* 42 */             arrayOfInt2[b1 + b * paramInt3] = i5;
/*    */           }
/* 44 */           else if (i5 == 4) {
/* 45 */             arrayOfInt2[b1 + b * paramInt3] = 4;
/*    */           } else {
/* 47 */             arrayOfInt2[b1 + b * paramInt3] = 0;
/*    */           }
/*    */         
/* 50 */         } else if (i4 > 0 && (n == 0 || i1 == 0 || i2 == 0 || i3 == 0)) {
/* 51 */           if (a(5) == 0) {
/* 52 */             if (i4 == 4) {
/* 53 */               arrayOfInt2[b1 + b * paramInt3] = 4;
/*    */             } else {
/* 55 */               arrayOfInt2[b1 + b * paramInt3] = 0;
/*    */             } 
/*    */           } else {
/* 58 */             arrayOfInt2[b1 + b * paramInt3] = i4;
/*    */           } 
/*    */         } else {
/* 61 */           arrayOfInt2[b1 + b * paramInt3] = i4;
/*    */         } 
/*    */       } 
/*    */     } 
/* 65 */     return arrayOfInt2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\GenLayerIsland.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */