/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NoiseGeneratorOctaves
/*    */   extends NoiseGenerator
/*    */ {
/*    */   private final NoiseGeneratorPerlin[] a;
/*    */   private final int b;
/*    */   
/*    */   public NoiseGeneratorOctaves(Random paramRandom, int paramInt) {
/* 16 */     this.b = paramInt;
/* 17 */     this.a = new NoiseGeneratorPerlin[paramInt];
/* 18 */     for (byte b = 0; b < paramInt; b++) {
/* 19 */       this.a[b] = new NoiseGeneratorPerlin(paramRandom);
/*    */     }
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
/*    */   public double[] a(double[] paramArrayOfdouble, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, double paramDouble1, double paramDouble2, double paramDouble3) {
/* 49 */     if (paramArrayOfdouble == null) {
/* 50 */       paramArrayOfdouble = new double[paramInt4 * paramInt5 * paramInt6];
/*    */     } else {
/* 52 */       for (byte b1 = 0; b1 < paramArrayOfdouble.length; b1++) {
/* 53 */         paramArrayOfdouble[b1] = 0.0D;
/*    */       }
/*    */     } 
/*    */     
/* 57 */     double d = 1.0D;
/*    */     
/* 59 */     for (byte b = 0; b < this.b; b++) {
/* 60 */       double d1 = paramInt1 * d * paramDouble1;
/* 61 */       double d2 = paramInt2 * d * paramDouble2;
/* 62 */       double d3 = paramInt3 * d * paramDouble3;
/* 63 */       long l1 = MathHelper.d(d1);
/* 64 */       long l2 = MathHelper.d(d3);
/* 65 */       d1 -= l1;
/* 66 */       d3 -= l2;
/* 67 */       l1 %= 16777216L;
/* 68 */       l2 %= 16777216L;
/* 69 */       d1 += l1;
/* 70 */       d3 += l2;
/* 71 */       this.a[b].a(paramArrayOfdouble, d1, d2, d3, paramInt4, paramInt5, paramInt6, paramDouble1 * d, paramDouble2 * d, paramDouble3 * d, d);
/* 72 */       d /= 2.0D;
/*    */     } 
/*    */     
/* 75 */     return paramArrayOfdouble;
/*    */   }
/*    */   
/*    */   public double[] a(double[] paramArrayOfdouble, int paramInt1, int paramInt2, int paramInt3, int paramInt4, double paramDouble1, double paramDouble2, double paramDouble3) {
/* 79 */     return a(paramArrayOfdouble, paramInt1, 10, paramInt2, paramInt3, 1, paramInt4, paramDouble1, 1.0D, paramDouble2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NoiseGeneratorOctaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */