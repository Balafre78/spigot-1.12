/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NoiseGenerator3
/*    */   extends NoiseGenerator
/*    */ {
/*    */   private final NoiseGenerator3Handler[] a;
/*    */   private final int b;
/*    */   
/*    */   public NoiseGenerator3(Random paramRandom, int paramInt) {
/* 14 */     this.b = paramInt;
/* 15 */     this.a = new NoiseGenerator3Handler[paramInt];
/* 16 */     for (byte b = 0; b < paramInt; b++) {
/* 17 */       this.a[b] = new NoiseGenerator3Handler(paramRandom);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public double a(double paramDouble1, double paramDouble2) {
/* 23 */     double d1 = 0.0D;
/* 24 */     double d2 = 1.0D;
/*    */     
/* 26 */     for (byte b = 0; b < this.b; b++) {
/* 27 */       d1 += this.a[b].a(paramDouble1 * d2, paramDouble2 * d2) / d2;
/* 28 */       d2 /= 2.0D;
/*    */     } 
/*    */     
/* 31 */     return d1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double[] a(double[] paramArrayOfdouble, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2, double paramDouble3, double paramDouble4, double paramDouble5) {
/* 47 */     return a(paramArrayOfdouble, paramDouble1, paramDouble2, paramInt1, paramInt2, paramDouble3, paramDouble4, paramDouble5, 0.5D);
/*    */   }
/*    */   
/*    */   public double[] a(double[] paramArrayOfdouble, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6) {
/* 51 */     if (paramArrayOfdouble == null || paramArrayOfdouble.length < paramInt1 * paramInt2) {
/* 52 */       paramArrayOfdouble = new double[paramInt1 * paramInt2];
/*    */     } else {
/* 54 */       for (byte b1 = 0; b1 < paramArrayOfdouble.length; b1++) {
/* 55 */         paramArrayOfdouble[b1] = 0.0D;
/*    */       }
/*    */     } 
/*    */     
/* 59 */     double d1 = 1.0D;
/* 60 */     double d2 = 1.0D;
/* 61 */     for (byte b = 0; b < this.b; b++) {
/* 62 */       this.a[b].a(paramArrayOfdouble, paramDouble1, paramDouble2, paramInt1, paramInt2, paramDouble3 * d2 * d1, paramDouble4 * d2 * d1, 0.55D / d1);
/* 63 */       d2 *= paramDouble5;
/* 64 */       d1 *= paramDouble6;
/*    */     } 
/*    */     
/* 67 */     return paramArrayOfdouble;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NoiseGenerator3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */