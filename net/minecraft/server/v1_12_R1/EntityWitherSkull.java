/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.ExplosionPrimeEvent;
/*     */ 
/*     */ public class EntityWitherSkull extends EntityFireball {
/*   7 */   private static final DataWatcherObject<Boolean> e = DataWatcher.a((Class)EntityWitherSkull.class, DataWatcherRegistry.h);
/*     */   
/*     */   public EntityWitherSkull(World world) {
/*  10 */     super(world);
/*  11 */     setSize(0.3125F, 0.3125F);
/*     */   }
/*     */   
/*     */   public EntityWitherSkull(World world, EntityLiving entityliving, double d0, double d1, double d2) {
/*  15 */     super(world, entityliving, d0, d1, d2);
/*  16 */     setSize(0.3125F, 0.3125F);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/*  20 */     EntityFireball.a(dataconvertermanager, "WitherSkull");
/*     */   }
/*     */   
/*     */   protected float l() {
/*  24 */     return isCharged() ? 0.73F : super.l();
/*     */   }
/*     */   
/*     */   public boolean isBurning() {
/*  28 */     return false;
/*     */   }
/*     */   
/*     */   public float a(Explosion explosion, World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  32 */     float f = super.a(explosion, world, blockposition, iblockdata);
/*  33 */     Block block = iblockdata.getBlock();
/*     */     
/*  35 */     if (isCharged() && EntityWither.a(block)) {
/*  36 */       f = Math.min(0.8F, f);
/*     */     }
/*     */     
/*  39 */     return f;
/*     */   }
/*     */   
/*     */   protected void a(MovingObjectPosition movingobjectposition) {
/*  43 */     if (!this.world.isClientSide) {
/*  44 */       if (movingobjectposition.entity != null) {
/*     */         
/*  46 */         boolean didDamage = false;
/*  47 */         if (this.shooter != null) {
/*  48 */           didDamage = movingobjectposition.entity.damageEntity(DamageSource.projectile(this, this.shooter), 8.0F);
/*  49 */           if (didDamage) {
/*  50 */             if (movingobjectposition.entity.isAlive()) {
/*  51 */               a(this.shooter, movingobjectposition.entity);
/*     */             } else {
/*  53 */               this.shooter.heal(5.0F, EntityRegainHealthEvent.RegainReason.WITHER);
/*     */             } 
/*     */           }
/*     */         } else {
/*  57 */           didDamage = movingobjectposition.entity.damageEntity(DamageSource.MAGIC, 5.0F);
/*     */         } 
/*     */         
/*  60 */         if (didDamage && movingobjectposition.entity instanceof EntityLiving) {
/*     */           
/*  62 */           byte b0 = 0;
/*     */           
/*  64 */           if (this.world.getDifficulty() == EnumDifficulty.NORMAL) {
/*  65 */             b0 = 10;
/*  66 */           } else if (this.world.getDifficulty() == EnumDifficulty.HARD) {
/*  67 */             b0 = 40;
/*     */           } 
/*     */           
/*  70 */           if (b0 > 0) {
/*  71 */             ((EntityLiving)movingobjectposition.entity).addEffect(new MobEffect(MobEffects.WITHER, 20 * b0, 1));
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  78 */       ExplosionPrimeEvent event = new ExplosionPrimeEvent((Entity)getBukkitEntity(), 1.0F, false);
/*  79 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/*  81 */       if (!event.isCancelled()) {
/*  82 */         this.world.createExplosion(this, this.locX, this.locY, this.locZ, event.getRadius(), event.getFire(), this.world.getGameRules().getBoolean("mobGriefing"));
/*     */       }
/*     */       
/*  85 */       die();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInteractable() {
/*  91 */     return false;
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/*  95 */     return false;
/*     */   }
/*     */   
/*     */   protected void i() {
/*  99 */     this.datawatcher.register(e, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public boolean isCharged() {
/* 103 */     return ((Boolean)this.datawatcher.<Boolean>get(e)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setCharged(boolean flag) {
/* 107 */     this.datawatcher.set(e, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   protected boolean k() {
/* 111 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityWitherSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */