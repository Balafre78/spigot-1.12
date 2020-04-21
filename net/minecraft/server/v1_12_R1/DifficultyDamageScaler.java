/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.concurrent.Immutable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Immutable
/*    */ public class DifficultyDamageScaler
/*    */ {
/*    */   private final EnumDifficulty a;
/*    */   private final float b;
/*    */   
/*    */   public DifficultyDamageScaler(EnumDifficulty paramEnumDifficulty, long paramLong1, long paramLong2, float paramFloat) {
/* 18 */     this.a = paramEnumDifficulty;
/* 19 */     this.b = a(paramEnumDifficulty, paramLong1, paramLong2, paramFloat);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float b() {
/* 27 */     return this.b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(float paramFloat) {
/* 35 */     return (this.b > paramFloat);
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
/*    */   public float d() {
/* 48 */     if (this.b < 2.0F) {
/* 49 */       return 0.0F;
/*    */     }
/* 51 */     if (this.b > 4.0F) {
/* 52 */       return 1.0F;
/*    */     }
/* 54 */     return (this.b - 2.0F) / 2.0F;
/*    */   }
/*    */   
/*    */   private float a(EnumDifficulty paramEnumDifficulty, long paramLong1, long paramLong2, float paramFloat) {
/* 58 */     if (paramEnumDifficulty == EnumDifficulty.PEACEFUL) {
/* 59 */       return 0.0F;
/*    */     }
/*    */     
/* 62 */     boolean bool = (paramEnumDifficulty == EnumDifficulty.HARD) ? true : false;
/* 63 */     float f1 = 0.75F;
/*    */ 
/*    */     
/* 66 */     float f2 = MathHelper.a(((float)paramLong1 + -72000.0F) / 1440000.0F, 0.0F, 1.0F) * 0.25F;
/* 67 */     f1 += f2;
/*    */     
/* 69 */     float f3 = 0.0F;
/*    */ 
/*    */     
/* 72 */     f3 += MathHelper.a((float)paramLong2 / 3600000.0F, 0.0F, 1.0F) * (bool ? 1.0F : 0.75F);
/* 73 */     f3 += MathHelper.a(paramFloat * 0.25F, 0.0F, f2);
/*    */     
/* 75 */     if (paramEnumDifficulty == EnumDifficulty.EASY) {
/* 76 */       f3 *= 0.5F;
/*    */     }
/* 78 */     f1 += f3;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 83 */     return paramEnumDifficulty.a() * f1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DifficultyDamageScaler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */