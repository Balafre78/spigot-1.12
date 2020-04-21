/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GenLayerZoomVoronoi
/*    */   extends GenLayer
/*    */ {
/*    */   public GenLayerZoomVoronoi(long paramLong, GenLayer paramGenLayer) {
/* 10 */     super(paramLong);
/* 11 */     this.a = paramGenLayer;
/*    */   }
/*    */ 
/*    */   
/*    */   public int[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 16 */     paramInt1 -= 2;
/* 17 */     paramInt2 -= 2;
/* 18 */     int i = paramInt1 >> 2;
/* 19 */     int j = paramInt2 >> 2;
/* 20 */     int k = (paramInt3 >> 2) + 2;
/* 21 */     int m = (paramInt4 >> 2) + 2;
/* 22 */     int[] arrayOfInt1 = this.a.a(i, j, k, m);
/*    */     
/* 24 */     int n = k - 1 << 2;
/* 25 */     int i1 = m - 1 << 2;
/*    */     
/* 27 */     int[] arrayOfInt2 = IntCache.a(n * i1);
/* 28 */     for (byte b1 = 0; b1 < m - 1; b1++) {
/* 29 */       byte b = 0;
/* 30 */       int i2 = arrayOfInt1[b + 0 + (b1 + 0) * k];
/* 31 */       int i3 = arrayOfInt1[b + 0 + (b1 + 1) * k];
/* 32 */       for (; b < k - 1; b++) {
/* 33 */         double d1 = 3.6D;
/* 34 */         a((b + i << 2), (b1 + j << 2));
/* 35 */         double d2 = (a(1024) / 1024.0D - 0.5D) * 3.6D;
/* 36 */         double d3 = (a(1024) / 1024.0D - 0.5D) * 3.6D;
/*    */         
/* 38 */         a((b + i + 1 << 2), (b1 + j << 2));
/* 39 */         double d4 = (a(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
/* 40 */         double d5 = (a(1024) / 1024.0D - 0.5D) * 3.6D;
/*    */         
/* 42 */         a((b + i << 2), (b1 + j + 1 << 2));
/* 43 */         double d6 = (a(1024) / 1024.0D - 0.5D) * 3.6D;
/* 44 */         double d7 = (a(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
/*    */         
/* 46 */         a((b + i + 1 << 2), (b1 + j + 1 << 2));
/* 47 */         double d8 = (a(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
/* 48 */         double d9 = (a(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
/*    */         
/* 50 */         int i4 = arrayOfInt1[b + 1 + (b1 + 0) * k] & 0xFF;
/* 51 */         int i5 = arrayOfInt1[b + 1 + (b1 + 1) * k] & 0xFF;
/*    */         
/* 53 */         for (byte b3 = 0; b3 < 4; b3++) {
/* 54 */           int i6 = ((b1 << 2) + b3) * n + (b << 2);
/* 55 */           for (byte b4 = 0; b4 < 4; b4++) {
/* 56 */             double d10 = (b3 - d3) * (b3 - d3) + (b4 - d2) * (b4 - d2);
/* 57 */             double d11 = (b3 - d5) * (b3 - d5) + (b4 - d4) * (b4 - d4);
/* 58 */             double d12 = (b3 - d7) * (b3 - d7) + (b4 - d6) * (b4 - d6);
/* 59 */             double d13 = (b3 - d9) * (b3 - d9) + (b4 - d8) * (b4 - d8);
/*    */             
/* 61 */             if (d10 < d11 && d10 < d12 && d10 < d13) {
/* 62 */               arrayOfInt2[i6++] = i2;
/* 63 */             } else if (d11 < d10 && d11 < d12 && d11 < d13) {
/* 64 */               arrayOfInt2[i6++] = i4;
/* 65 */             } else if (d12 < d10 && d12 < d11 && d12 < d13) {
/* 66 */               arrayOfInt2[i6++] = i3;
/*    */             } else {
/* 68 */               arrayOfInt2[i6++] = i5;
/*    */             } 
/*    */           } 
/*    */         } 
/*    */         
/* 73 */         i2 = i4;
/* 74 */         i3 = i5;
/*    */       } 
/*    */     } 
/*    */     
/* 78 */     int[] arrayOfInt3 = IntCache.a(paramInt3 * paramInt4);
/* 79 */     for (byte b2 = 0; b2 < paramInt4; b2++) {
/* 80 */       System.arraycopy(arrayOfInt2, (b2 + (paramInt2 & 0x3)) * n + (paramInt1 & 0x3), arrayOfInt3, b2 * paramInt3, paramInt3);
/*    */     }
/* 82 */     return arrayOfInt3;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\GenLayerZoomVoronoi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */