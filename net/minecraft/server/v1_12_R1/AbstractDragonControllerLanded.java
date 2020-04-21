/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractDragonControllerLanded
/*    */   extends AbstractDragonController
/*    */ {
/*    */   public AbstractDragonControllerLanded(EntityEnderDragon paramEntityEnderDragon) {
/* 10 */     super(paramEntityEnderDragon);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 15 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float a(EntityComplexPart paramEntityComplexPart, DamageSource paramDamageSource, float paramFloat) {
/* 20 */     if (paramDamageSource.i() instanceof EntityArrow) {
/* 21 */       paramDamageSource.i().setOnFire(1);
/* 22 */       return 0.0F;
/*    */     } 
/* 24 */     return super.a(paramEntityComplexPart, paramDamageSource, paramFloat);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\AbstractDragonControllerLanded.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */