/*    */ package net.minecraft.server.v1_12_R1;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Egg;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*    */ import org.bukkit.event.player.PlayerEggThrowEvent;
/*    */ 
/*    */ public class EntityEgg extends EntityProjectile {
/*    */   public EntityEgg(World world) {
/* 13 */     super(world);
/*    */   }
/*    */   
/*    */   public EntityEgg(World world, EntityLiving entityliving) {
/* 17 */     super(world, entityliving);
/*    */   }
/*    */   
/*    */   public EntityEgg(World world, double d0, double d1, double d2) {
/* 21 */     super(world, d0, d1, d2);
/*    */   }
/*    */   
/*    */   public static void a(DataConverterManager dataconvertermanager) {
/* 25 */     EntityProjectile.a(dataconvertermanager, "ThrownEgg");
/*    */   }
/*    */   
/*    */   protected void a(MovingObjectPosition movingobjectposition) {
/* 29 */     if (movingobjectposition.entity != null) {
/* 30 */       movingobjectposition.entity.damageEntity(DamageSource.projectile(this, getShooter()), 0.0F);
/*    */     }
/*    */     
/* 33 */     if (!this.world.isClientSide) {
/* 34 */       boolean hatching = (this.random.nextInt(8) == 0);
/*    */       
/* 36 */       byte b0 = 1;
/*    */       
/* 38 */       if (this.random.nextInt(32) == 0) {
/* 39 */         b0 = 4;
/*    */       }
/*    */ 
/*    */       
/* 43 */       if (!hatching) {
/* 44 */         b0 = 0;
/*    */       }
/* 46 */       EntityType hatchingType = EntityType.CHICKEN;
/*    */       
/* 48 */       Entity shooter = getShooter();
/* 49 */       if (shooter instanceof EntityPlayer) {
/* 50 */         PlayerEggThrowEvent event = new PlayerEggThrowEvent((Player)shooter.getBukkitEntity(), (Egg)getBukkitEntity(), hatching, b0, hatchingType);
/* 51 */         this.world.getServer().getPluginManager().callEvent((Event)event);
/*    */         
/* 53 */         b0 = event.getNumHatches();
/* 54 */         hatching = event.isHatching();
/* 55 */         hatchingType = event.getHatchingType();
/*    */       } 
/*    */       
/* 58 */       if (hatching) {
/* 59 */         for (int i = 0; i < b0; i++) {
/* 60 */           Entity entity = this.world.getWorld().createEntity(new Location((World)this.world.getWorld(), this.locX, this.locY, this.locZ, this.yaw, 0.0F), hatchingType.getEntityClass());
/* 61 */           if (entity.getBukkitEntity() instanceof Ageable) {
/* 62 */             ((Ageable)entity.getBukkitEntity()).setBaby();
/*    */           }
/* 64 */           this.world.getWorld().addEntity(entity, CreatureSpawnEvent.SpawnReason.EGG);
/*    */         } 
/*    */       }
/*    */ 
/*    */ 
/*    */       
/* 70 */       this.world.broadcastEntityEffect(this, (byte)3);
/* 71 */       die();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */