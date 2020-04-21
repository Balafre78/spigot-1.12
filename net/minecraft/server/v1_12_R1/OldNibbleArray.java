/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OldNibbleArray
/*    */ {
/*    */   public final byte[] a;
/*    */   private final int b;
/*    */   private final int c;
/*    */   
/*    */   public OldNibbleArray(byte[] paramArrayOfbyte, int paramInt) {
/* 15 */     this.a = paramArrayOfbyte;
/* 16 */     this.b = paramInt;
/* 17 */     this.c = paramInt + 4;
/*    */   }
/*    */   
/*    */   public int a(int paramInt1, int paramInt2, int paramInt3) {
/* 21 */     int i = paramInt1 << this.c | paramInt3 << this.b | paramInt2;
/* 22 */     int j = i >> 1;
/* 23 */     int k = i & 0x1;
/*    */     
/* 25 */     if (k == 0) {
/* 26 */       return this.a[j] & 0xF;
/*    */     }
/* 28 */     return this.a[j] >> 4 & 0xF;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\OldNibbleArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */