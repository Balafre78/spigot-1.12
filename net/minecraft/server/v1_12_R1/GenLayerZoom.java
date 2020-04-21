/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GenLayerZoom
/*    */   extends GenLayer
/*    */ {
/*    */   public GenLayerZoom(long paramLong, GenLayer paramGenLayer) {
/*  9 */     super(paramLong);
/* 10 */     this.a = paramGenLayer;
/*    */   }
/*    */ 
/*    */   
/*    */   public int[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 15 */     int i = paramInt1 >> 1;
/* 16 */     int j = paramInt2 >> 1;
/* 17 */     int k = (paramInt3 >> 1) + 2;
/* 18 */     int m = (paramInt4 >> 1) + 2;
/* 19 */     int[] arrayOfInt1 = this.a.a(i, j, k, m);
/*    */     
/* 21 */     int n = k - 1 << 1;
/* 22 */     int i1 = m - 1 << 1;
/*    */     
/* 24 */     int[] arrayOfInt2 = IntCache.a(n * i1);
/*    */     
/* 26 */     for (byte b1 = 0; b1 < m - 1; b1++) {
/* 27 */       int i2 = (b1 << 1) * n;
/*    */       
/* 29 */       byte b = 0;
/* 30 */       int i3 = arrayOfInt1[b + 0 + (b1 + 0) * k];
/* 31 */       int i4 = arrayOfInt1[b + 0 + (b1 + 1) * k];
/* 32 */       for (; b < k - 1; b++) {
/* 33 */         a((b + i << 1), (b1 + j << 1));
/*    */         
/* 35 */         int i5 = arrayOfInt1[b + 1 + (b1 + 0) * k];
/* 36 */         int i6 = arrayOfInt1[b + 1 + (b1 + 1) * k];
/*    */         
/* 38 */         arrayOfInt2[i2] = i3;
/* 39 */         arrayOfInt2[i2++ + n] = a(new int[] { i3, i4 });
/* 40 */         arrayOfInt2[i2] = a(new int[] { i3, i5 });
/* 41 */         arrayOfInt2[i2++ + n] = b(i3, i5, i4, i6);
/*    */         
/* 43 */         i3 = i5;
/* 44 */         i4 = i6;
/*    */       } 
/*    */     } 
/*    */     
/* 48 */     int[] arrayOfInt3 = IntCache.a(paramInt3 * paramInt4);
/* 49 */     for (byte b2 = 0; b2 < paramInt4; b2++) {
/* 50 */       System.arraycopy(arrayOfInt2, (b2 + (paramInt2 & 0x1)) * n + (paramInt1 & 0x1), arrayOfInt3, b2 * paramInt3, paramInt3);
/*    */     }
/* 52 */     return arrayOfInt3;
/*    */   }
/*    */   
/*    */   public static GenLayer b(long paramLong, GenLayer paramGenLayer, int paramInt) {
/* 56 */     GenLayer genLayer = paramGenLayer;
/* 57 */     for (byte b = 0; b < paramInt; b++) {
/* 58 */       genLayer = new GenLayerZoom(paramLong + b, genLayer);
/*    */     }
/* 60 */     return genLayer;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\GenLayerZoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */