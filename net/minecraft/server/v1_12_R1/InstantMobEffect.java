/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class InstantMobEffect extends MobEffectList {
/*    */   public InstantMobEffect(boolean paramBoolean, int paramInt) {
/*  5 */     super(paramBoolean, paramInt);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isInstant() {
/* 10 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(int paramInt1, int paramInt2) {
/* 15 */     return (paramInt1 >= 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\InstantMobEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */