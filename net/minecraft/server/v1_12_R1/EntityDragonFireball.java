/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.List;
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
/*    */ 
/*    */ public class EntityDragonFireball
/*    */   extends EntityFireball
/*    */ {
/*    */   public EntityDragonFireball(World paramWorld) {
/* 22 */     super(paramWorld);
/* 23 */     setSize(1.0F, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityDragonFireball(World paramWorld, EntityLiving paramEntityLiving, double paramDouble1, double paramDouble2, double paramDouble3) {
/* 32 */     super(paramWorld, paramEntityLiving, paramDouble1, paramDouble2, paramDouble3);
/* 33 */     setSize(1.0F, 1.0F);
/*    */   }
/*    */   
/*    */   public static void a(DataConverterManager paramDataConverterManager) {
/* 37 */     EntityFireball.a(paramDataConverterManager, "DragonFireball");
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(MovingObjectPosition paramMovingObjectPosition) {
/* 42 */     if (paramMovingObjectPosition.entity != null && paramMovingObjectPosition.entity.s(this.shooter)) {
/*    */       return;
/*    */     }
/* 45 */     if (!this.world.isClientSide) {
/* 46 */       List<EntityLiving> list = this.world.a(EntityLiving.class, getBoundingBox().grow(4.0D, 2.0D, 4.0D));
/*    */       
/* 48 */       EntityAreaEffectCloud entityAreaEffectCloud = new EntityAreaEffectCloud(this.world, this.locX, this.locY, this.locZ);
/* 49 */       entityAreaEffectCloud.setSource(this.shooter);
/* 50 */       entityAreaEffectCloud.setParticle(EnumParticle.DRAGON_BREATH);
/* 51 */       entityAreaEffectCloud.setRadius(3.0F);
/* 52 */       entityAreaEffectCloud.setDuration(600);
/* 53 */       entityAreaEffectCloud.setRadiusPerTick((7.0F - entityAreaEffectCloud.getRadius()) / entityAreaEffectCloud.getDuration());
/* 54 */       entityAreaEffectCloud.a(new MobEffect(MobEffects.HARM, 1, 1));
/*    */       
/* 56 */       if (!list.isEmpty()) {
/* 57 */         for (EntityLiving entityLiving : list) {
/* 58 */           double d = h(entityLiving);
/* 59 */           if (d < 16.0D) {
/* 60 */             entityAreaEffectCloud.setPosition(entityLiving.locX, entityLiving.locY, entityLiving.locZ);
/*    */             
/*    */             break;
/*    */           } 
/*    */         } 
/*    */       }
/* 66 */       this.world.triggerEffect(2006, new BlockPosition(this.locX, this.locY, this.locZ), 0);
/* 67 */       this.world.addEntity(entityAreaEffectCloud);
/*    */       
/* 69 */       die();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isInteractable() {
/* 75 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean damageEntity(DamageSource paramDamageSource, float paramFloat) {
/* 80 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected EnumParticle j() {
/* 85 */     return EnumParticle.DRAGON_BREATH;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean k() {
/* 90 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityDragonFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */