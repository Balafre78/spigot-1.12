/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import org.apache.commons.lang3.Validate;
/*    */ 
/*    */ 
/*    */ public class DataBits
/*    */ {
/*    */   private final long[] a;
/*    */   private final int b;
/*    */   private final long c;
/*    */   private final int d;
/*    */   
/*    */   public DataBits(int paramInt1, int paramInt2) {
/* 14 */     Validate.inclusiveBetween(1L, 32L, paramInt1);
/*    */     
/* 16 */     this.d = paramInt2;
/* 17 */     this.b = paramInt1;
/* 18 */     this.c = (1L << paramInt1) - 1L;
/*    */     
/* 20 */     this.a = new long[MathHelper.c(paramInt2 * paramInt1, 64) / 64];
/*    */   }
/*    */   
/*    */   public void a(int paramInt1, int paramInt2) {
/* 24 */     Validate.inclusiveBetween(0L, (this.d - 1), paramInt1);
/* 25 */     Validate.inclusiveBetween(0L, this.c, paramInt2);
/*    */     
/* 27 */     int i = paramInt1 * this.b;
/* 28 */     int j = i / 64;
/* 29 */     int k = ((paramInt1 + 1) * this.b - 1) / 64;
/* 30 */     int m = i % 64;
/*    */     
/* 32 */     this.a[j] = this.a[j] & (this.c << m ^ 0xFFFFFFFFFFFFFFFFL) | (paramInt2 & this.c) << m;
/* 33 */     if (j != k) {
/* 34 */       int n = 64 - m;
/* 35 */       int i1 = this.b - n;
/* 36 */       this.a[k] = this.a[k] >>> i1 << i1 | (paramInt2 & this.c) >> n;
/*    */     } 
/*    */   }
/*    */   
/*    */   public int a(int paramInt) {
/* 41 */     Validate.inclusiveBetween(0L, (this.d - 1), paramInt);
/*    */     
/* 43 */     int i = paramInt * this.b;
/* 44 */     int j = i / 64;
/* 45 */     int k = ((paramInt + 1) * this.b - 1) / 64;
/* 46 */     int m = i % 64;
/*    */     
/* 48 */     if (j == k) {
/* 49 */       return (int)(this.a[j] >>> m & this.c);
/*    */     }
/* 51 */     int n = 64 - m;
/* 52 */     return (int)((this.a[j] >>> m | this.a[k] << n) & this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public long[] a() {
/* 57 */     return this.a;
/*    */   }
/*    */   
/*    */   public int b() {
/* 61 */     return this.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataBits.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */