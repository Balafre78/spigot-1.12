/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntitySnowball
/*    */   extends EntityProjectile
/*    */ {
/*    */   public EntitySnowball(World paramWorld) {
/* 14 */     super(paramWorld);
/*    */   }
/*    */   
/*    */   public EntitySnowball(World paramWorld, EntityLiving paramEntityLiving) {
/* 18 */     super(paramWorld, paramEntityLiving);
/*    */   }
/*    */   
/*    */   public EntitySnowball(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3) {
/* 22 */     super(paramWorld, paramDouble1, paramDouble2, paramDouble3);
/*    */   }
/*    */   
/*    */   public static void a(DataConverterManager paramDataConverterManager) {
/* 26 */     EntityProjectile.a(paramDataConverterManager, "Snowball");
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
/*    */   protected void a(MovingObjectPosition paramMovingObjectPosition) {
/* 40 */     if (paramMovingObjectPosition.entity != null) {
/* 41 */       byte b = 0;
/* 42 */       if (paramMovingObjectPosition.entity instanceof EntityBlaze) {
/* 43 */         b = 3;
/*    */       }
/* 45 */       paramMovingObjectPosition.entity.damageEntity(DamageSource.projectile(this, getShooter()), b);
/*    */     } 
/* 47 */     if (!this.world.isClientSide) {
/* 48 */       this.world.broadcastEntityEffect(this, (byte)3);
/* 49 */       die();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntitySnowball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */