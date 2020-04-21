/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class NoiseGeneratorPerlin extends NoiseGenerator {
/*   6 */   private final int[] d = new int[512];
/*     */   public double a;
/*     */   public double b;
/*     */   public double c;
/*     */   
/*     */   public NoiseGeneratorPerlin() {
/*  12 */     this(new Random());
/*     */   }
/*     */   
/*     */   public NoiseGeneratorPerlin(Random paramRandom) {
/*  16 */     this.a = paramRandom.nextDouble() * 256.0D;
/*  17 */     this.b = paramRandom.nextDouble() * 256.0D;
/*  18 */     this.c = paramRandom.nextDouble() * 256.0D; byte b;
/*  19 */     for (b = 0; b < 'Ā'; b++) {
/*  20 */       this.d[b] = b;
/*     */     }
/*     */     
/*  23 */     for (b = 0; b < 'Ā'; b++) {
/*  24 */       int i = paramRandom.nextInt(256 - b) + b;
/*  25 */       int j = this.d[b];
/*  26 */       this.d[b] = this.d[i];
/*  27 */       this.d[i] = j;
/*     */       
/*  29 */       this.d[b + 256] = this.d[b];
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final double b(double paramDouble1, double paramDouble2, double paramDouble3) {
/*  88 */     return paramDouble2 + paramDouble1 * (paramDouble3 - paramDouble2);
/*     */   }
/*     */   
/*  91 */   private static final double[] e = new double[] { 1.0D, -1.0D, 1.0D, -1.0D, 1.0D, -1.0D, 1.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, -1.0D, 0.0D };
/*  92 */   private static final double[] f = new double[] { 1.0D, 1.0D, -1.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, -1.0D, 1.0D, -1.0D, 1.0D, -1.0D, 1.0D, -1.0D };
/*  93 */   private static final double[] g = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, -1.0D, -1.0D, 1.0D, 1.0D, -1.0D, -1.0D, 0.0D, 1.0D, 0.0D, -1.0D };
/*  94 */   private static final double[] h = new double[] { 1.0D, -1.0D, 1.0D, -1.0D, 1.0D, -1.0D, 1.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, -1.0D, 0.0D };
/*  95 */   private static final double[] i = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, -1.0D, -1.0D, 1.0D, 1.0D, -1.0D, -1.0D, 0.0D, 1.0D, 0.0D, -1.0D };
/*     */   
/*     */   public final double a(int paramInt, double paramDouble1, double paramDouble2) {
/*  98 */     int i = paramInt & 0xF;
/*  99 */     return h[i] * paramDouble1 + i[i] * paramDouble2;
/*     */   }
/*     */   
/*     */   public final double a(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3) {
/* 103 */     int i = paramInt & 0xF;
/* 104 */     return e[i] * paramDouble1 + f[i] * paramDouble2 + g[i] * paramDouble3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(double[] paramArrayOfdouble, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt1, int paramInt2, int paramInt3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7) {
/* 117 */     if (paramInt2 == 1) {
/* 118 */       int i3 = 0, i4 = 0, i5 = 0, i6 = 0;
/* 119 */       double d6 = 0.0D, d7 = 0.0D;
/* 120 */       byte b3 = 0;
/* 121 */       double d8 = 1.0D / paramDouble7;
/* 122 */       for (byte b4 = 0; b4 < paramInt1; b4++) {
/* 123 */         double d9 = paramDouble1 + b4 * paramDouble4 + this.a;
/* 124 */         int i7 = (int)d9;
/* 125 */         if (d9 < i7) {
/* 126 */           i7--;
/*     */         }
/* 128 */         int i8 = i7 & 0xFF;
/* 129 */         d9 -= i7;
/* 130 */         double d10 = d9 * d9 * d9 * (d9 * (d9 * 6.0D - 15.0D) + 10.0D);
/*     */         
/* 132 */         for (byte b = 0; b < paramInt3; b++) {
/* 133 */           double d11 = paramDouble3 + b * paramDouble6 + this.c;
/* 134 */           int i9 = (int)d11;
/* 135 */           if (d11 < i9) {
/* 136 */             i9--;
/*     */           }
/* 138 */           int i10 = i9 & 0xFF;
/* 139 */           d11 -= i9;
/* 140 */           double d12 = d11 * d11 * d11 * (d11 * (d11 * 6.0D - 15.0D) + 10.0D);
/*     */           
/* 142 */           i3 = this.d[i8] + 0;
/* 143 */           i4 = this.d[i3] + i10;
/* 144 */           i5 = this.d[i8 + 1] + 0;
/* 145 */           i6 = this.d[i5] + i10;
/* 146 */           d6 = b(d10, a(this.d[i4], d9, d11), a(this.d[i6], d9 - 1.0D, 0.0D, d11));
/* 147 */           d7 = b(d10, a(this.d[i4 + 1], d9, 0.0D, d11 - 1.0D), a(this.d[i6 + 1], d9 - 1.0D, 0.0D, d11 - 1.0D));
/*     */           
/* 149 */           double d13 = b(d12, d6, d7);
/*     */           
/* 151 */           paramArrayOfdouble[b3++] = paramArrayOfdouble[b3++] + d13 * d8;
/*     */         } 
/*     */       } 
/*     */       return;
/*     */     } 
/* 156 */     byte b1 = 0;
/* 157 */     double d3 = 1.0D / paramDouble7;
/* 158 */     int i = -1;
/* 159 */     int k = 0, m = 0, n = 0, i1 = 0, j = 0, i2 = 0;
/* 160 */     double d4 = 0.0D, d1 = 0.0D, d5 = 0.0D, d2 = 0.0D;
/*     */     
/* 162 */     for (byte b2 = 0; b2 < paramInt1; b2++) {
/* 163 */       double d6 = paramDouble1 + b2 * paramDouble4 + this.a;
/* 164 */       int i3 = (int)d6;
/* 165 */       if (d6 < i3) {
/* 166 */         i3--;
/*     */       }
/* 168 */       int i4 = i3 & 0xFF;
/* 169 */       d6 -= i3;
/* 170 */       double d7 = d6 * d6 * d6 * (d6 * (d6 * 6.0D - 15.0D) + 10.0D);
/*     */       
/* 172 */       for (byte b = 0; b < paramInt3; b++) {
/* 173 */         double d8 = paramDouble3 + b * paramDouble6 + this.c;
/* 174 */         int i5 = (int)d8;
/* 175 */         if (d8 < i5) {
/* 176 */           i5--;
/*     */         }
/* 178 */         int i6 = i5 & 0xFF;
/* 179 */         d8 -= i5;
/* 180 */         double d9 = d8 * d8 * d8 * (d8 * (d8 * 6.0D - 15.0D) + 10.0D);
/*     */         
/* 182 */         for (byte b3 = 0; b3 < paramInt2; b3++) {
/* 183 */           double d10 = paramDouble2 + b3 * paramDouble5 + this.b;
/* 184 */           int i7 = (int)d10;
/* 185 */           if (d10 < i7) {
/* 186 */             i7--;
/*     */           }
/* 188 */           int i8 = i7 & 0xFF;
/* 189 */           d10 -= i7;
/* 190 */           double d11 = d10 * d10 * d10 * (d10 * (d10 * 6.0D - 15.0D) + 10.0D);
/*     */           
/* 192 */           if (b3 == 0 || i8 != i) {
/* 193 */             i = i8;
/* 194 */             k = this.d[i4] + i8;
/* 195 */             m = this.d[k] + i6;
/* 196 */             n = this.d[k + 1] + i6;
/* 197 */             i1 = this.d[i4 + 1] + i8;
/* 198 */             j = this.d[i1] + i6;
/* 199 */             i2 = this.d[i1 + 1] + i6;
/* 200 */             d4 = b(d7, a(this.d[m], d6, d10, d8), a(this.d[j], d6 - 1.0D, d10, d8));
/* 201 */             d1 = b(d7, a(this.d[n], d6, d10 - 1.0D, d8), a(this.d[i2], d6 - 1.0D, d10 - 1.0D, d8));
/* 202 */             d5 = b(d7, a(this.d[m + 1], d6, d10, d8 - 1.0D), a(this.d[j + 1], d6 - 1.0D, d10, d8 - 1.0D));
/* 203 */             d2 = b(d7, a(this.d[n + 1], d6, d10 - 1.0D, d8 - 1.0D), a(this.d[i2 + 1], d6 - 1.0D, d10 - 1.0D, d8 - 1.0D));
/*     */           } 
/*     */           
/* 206 */           double d12 = b(d11, d4, d1);
/* 207 */           double d13 = b(d11, d5, d2);
/* 208 */           double d14 = b(d9, d12, d13);
/*     */           
/* 210 */           paramArrayOfdouble[b1++] = paramArrayOfdouble[b1++] + d14 * d3;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NoiseGeneratorPerlin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */