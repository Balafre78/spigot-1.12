/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class MobEffectAttackDamage
/*    */   extends MobEffectList
/*    */ {
/*    */   protected final double a;
/*    */   
/*    */   protected MobEffectAttackDamage(boolean paramBoolean, int paramInt, double paramDouble) {
/*  9 */     super(paramBoolean, paramInt);
/* 10 */     this.a = paramDouble;
/*    */   }
/*    */ 
/*    */   
/*    */   public double a(int paramInt, AttributeModifier paramAttributeModifier) {
/* 15 */     return this.a * (paramInt + 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MobEffectAttackDamage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */