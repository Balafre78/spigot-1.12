/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DragonControllerLandedAttack
/*    */   extends AbstractDragonControllerLanded
/*    */ {
/*    */   private int b;
/*    */   
/*    */   public DragonControllerLandedAttack(EntityEnderDragon paramEntityEnderDragon) {
/* 13 */     super(paramEntityEnderDragon);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b() {
/* 18 */     this.a.world.a(this.a.locX, this.a.locY, this.a.locZ, SoundEffects.aY, this.a.bK(), 2.5F, 0.8F + this.a.getRandom().nextFloat() * 0.3F, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 23 */     if (this.b++ >= 40) {
/* 24 */       this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.f);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 30 */     this.b = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonControllerPhase<DragonControllerLandedAttack> getControllerPhase() {
/* 35 */     return DragonControllerPhase.h;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DragonControllerLandedAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */