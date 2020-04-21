/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class EntityGolem
/*    */   extends EntityCreature
/*    */   implements IAnimal
/*    */ {
/*    */   public EntityGolem(World paramWorld) {
/* 13 */     super(paramWorld);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void e(float paramFloat1, float paramFloat2) {}
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected SoundEffect F() {
/* 23 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected SoundEffect d(DamageSource paramDamageSource) {
/* 29 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected SoundEffect cf() {
/* 35 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public int C() {
/* 40 */     return 120;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isTypeNotPersistent() {
/* 45 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */