/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class NibbleArray
/*    */ {
/*    */   private final byte[] a;
/*    */   
/*    */   public NibbleArray() {
/*  8 */     this.a = new byte[2048];
/*    */   }
/*    */   
/*    */   public NibbleArray(byte[] abyte) {
/* 12 */     this.a = abyte;
/* 13 */     if (abyte.length != 2048) {
/* 14 */       throw new IllegalArgumentException("ChunkNibbleArrays should be 2048 bytes not: " + abyte.length);
/*    */     }
/*    */   }
/*    */   
/*    */   public int a(int i, int j, int k) {
/* 19 */     return a(b(i, j, k));
/*    */   }
/*    */   
/*    */   public void a(int i, int j, int k, int l) {
/* 23 */     a(b(i, j, k), l);
/*    */   }
/*    */   
/*    */   private int b(int i, int j, int k) {
/* 27 */     return j << 8 | k << 4 | i;
/*    */   }
/*    */   
/*    */   public int a(int i) {
/* 31 */     int j = c(i);
/*    */     
/* 33 */     return this.a[j] >> (i & 0x1) << 2 & 0xF;
/*    */   }
/*    */   
/*    */   public void a(int i, int j) {
/* 37 */     int k = c(i);
/*    */ 
/*    */     
/* 40 */     int shift = (i & 0x1) << 2;
/* 41 */     this.a[k] = (byte)(this.a[k] & (15 << shift ^ 0xFFFFFFFF) | (j & 0xF) << shift);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean b(int i) {
/* 46 */     return ((i & 0x1) == 0);
/*    */   }
/*    */   
/*    */   private int c(int i) {
/* 50 */     return i >> 1;
/*    */   }
/*    */   
/*    */   public byte[] asBytes() {
/* 54 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NibbleArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */