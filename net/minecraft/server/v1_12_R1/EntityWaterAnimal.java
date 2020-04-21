/*    */ package net.minecraft.server.v1_12_R1;
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
/*    */ public abstract class EntityWaterAnimal
/*    */   extends EntityInsentient
/*    */   implements IAnimal
/*    */ {
/*    */   public EntityWaterAnimal(World paramWorld) {
/* 20 */     super(paramWorld);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean bN() {
/* 26 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean P() {
/* 31 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 36 */     return this.world.a(getBoundingBox(), this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int C() {
/* 41 */     return 120;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isTypeNotPersistent() {
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getExpValue(EntityHuman paramEntityHuman) {
/* 51 */     return 1 + this.world.random.nextInt(3);
/*    */   }
/*    */ 
/*    */   
/*    */   public void Y() {
/* 56 */     int i = getAirTicks();
/*    */     
/* 58 */     super.Y();
/*    */     
/* 60 */     if (isAlive() && !isInWater()) {
/* 61 */       setAirTicks(--i);
/* 62 */       if (getAirTicks() == -20) {
/* 63 */         setAirTicks(0);
/* 64 */         damageEntity(DamageSource.DROWN, 2.0F);
/*    */       } 
/*    */     } else {
/* 67 */       setAirTicks(300);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean bo() {
/* 74 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityWaterAnimal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */