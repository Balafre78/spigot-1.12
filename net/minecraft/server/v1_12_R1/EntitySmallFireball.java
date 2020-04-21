/*    */ package net.minecraft.server.v1_12_R1;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.entity.EntityCombustByEntityEvent;
/*    */ 
/*    */ public class EntitySmallFireball extends EntityFireball {
/*    */   public EntitySmallFireball(World world) {
/*  8 */     super(world);
/*  9 */     setSize(0.3125F, 0.3125F);
/*    */   }
/*    */   
/*    */   public EntitySmallFireball(World world, EntityLiving entityliving, double d0, double d1, double d2) {
/* 13 */     super(world, entityliving, d0, d1, d2);
/* 14 */     setSize(0.3125F, 0.3125F);
/*    */     
/* 16 */     if (this.shooter != null && this.shooter instanceof EntityInsentient) {
/* 17 */       this.isIncendiary = this.world.getGameRules().getBoolean("mobGriefing");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public EntitySmallFireball(World world, double d0, double d1, double d2, double d3, double d4, double d5) {
/* 23 */     super(world, d0, d1, d2, d3, d4, d5);
/* 24 */     setSize(0.3125F, 0.3125F);
/*    */   }
/*    */   
/*    */   public static void a(DataConverterManager dataconvertermanager) {
/* 28 */     EntityFireball.a(dataconvertermanager, "SmallFireball");
/*    */   }
/*    */   
/*    */   protected void a(MovingObjectPosition movingobjectposition) {
/* 32 */     if (!this.world.isClientSide) {
/*    */ 
/*    */       
/* 35 */       if (movingobjectposition.entity != null) {
/* 36 */         if (!movingobjectposition.entity.isFireProof()) {
/*    */           
/* 38 */           this.isIncendiary = movingobjectposition.entity.damageEntity(DamageSource.fireball(this, this.shooter), 5.0F);
/* 39 */           if (this.isIncendiary) {
/* 40 */             a(this.shooter, movingobjectposition.entity);
/* 41 */             EntityCombustByEntityEvent event = new EntityCombustByEntityEvent((Entity)getBukkitEntity(), (Entity)movingobjectposition.entity.getBukkitEntity(), 5);
/* 42 */             movingobjectposition.entity.world.getServer().getPluginManager().callEvent((Event)event);
/*    */             
/* 44 */             if (!event.isCancelled()) {
/* 45 */               movingobjectposition.entity.setOnFire(event.getDuration());
/*    */             }
/*    */           }
/*    */         
/*    */         } 
/*    */       } else {
/*    */         
/* 52 */         if (this.shooter != null && this.shooter instanceof EntityInsentient) {
/* 53 */           this.world.getGameRules().getBoolean("mobGriefing");
/*    */         }
/*    */ 
/*    */         
/* 57 */         if (this.isIncendiary) {
/* 58 */           BlockPosition blockposition = movingobjectposition.a().shift(movingobjectposition.direction);
/*    */           
/* 60 */           if (this.world.isEmpty(blockposition) && 
/* 61 */             !CraftEventFactory.callBlockIgniteEvent(this.world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), this).isCancelled()) {
/* 62 */             this.world.setTypeUpdate(blockposition, Blocks.FIRE.getBlockData());
/*    */           }
/*    */         } 
/*    */       } 
/*    */ 
/*    */ 
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
/*    */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 79 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntitySmallFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */