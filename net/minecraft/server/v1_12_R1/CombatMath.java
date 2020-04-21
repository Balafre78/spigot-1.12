/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CombatMath
/*    */ {
/*    */   public static float a(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 13 */     float f1 = 2.0F + paramFloat3 / 4.0F;
/* 14 */     float f2 = MathHelper.a(paramFloat2 - paramFloat1 / f1, paramFloat2 * 0.2F, 20.0F);
/* 15 */     return paramFloat1 * (1.0F - f2 / 25.0F);
/*    */   }
/*    */   
/*    */   public static float a(float paramFloat1, float paramFloat2) {
/* 19 */     float f = MathHelper.a(paramFloat2, 0.0F, 20.0F);
/* 20 */     return paramFloat1 * (1.0F - f / 25.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CombatMath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */